package com.example.buildsrc

import com.android.build.api.transform.*
import com.android.build.gradle.internal.pipeline.TransformManager
import com.android.utils.FileUtils
import org.apache.commons.codec.digest.DigestUtils
import org.apache.commons.io.IOUtils
import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassReader.EXPAND_FRAMES
import org.objectweb.asm.ClassWriter
import java.io.Closeable
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.jar.JarFile
import java.util.jar.JarOutputStream
import java.util.zip.ZipEntry


internal class TraceTransform : Transform() {
    override fun getName() = "traceTransform"

    override fun getInputTypes(): MutableSet<QualifiedContent.ContentType> = TransformManager.CONTENT_CLASS

    override fun getScopes(): MutableSet<QualifiedContent.Scope> = TransformManager.SCOPE_FULL_PROJECT

    override fun isIncremental() = false

    override fun transform(transformInvocation: TransformInvocation) {
        transformInvocation.inputs.forEach { transformInput ->
            transformInput.directoryInputs.forEach { directoryInput ->
                traceDirectoryFiles(directoryInput, transformInvocation.outputProvider)
            }
            transformInput.jarInputs.forEach { jarInput ->
                traceJarFiles(jarInput, transformInvocation.outputProvider)
            }
        }
    }

    private fun traceDirectoryFiles(directoryInput: DirectoryInput, outputProvider: TransformOutputProvider) {
        directoryInput.file.walkTopDown()
                .filter { it.isFile }
                .forEach { file ->
                    FileInputStream(file).use { fis ->
                        val classReader = ClassReader(fis)
                        val classWriter = ClassWriter(classReader, ClassWriter.COMPUTE_MAXS)
                        val classTraceVisitor = ClassTraceVisitor(classWriter)
                        classReader.accept(classTraceVisitor, EXPAND_FRAMES)

                        file.writeBytes(classWriter.toByteArray())
                    }
                }
        val dest = outputProvider.getContentLocation(
                directoryInput.name,
                directoryInput.contentTypes,
                directoryInput.scopes,
                Format.DIRECTORY)

        FileUtils.copyDirectory(directoryInput.file, dest)
    }

    private fun traceJarFiles(jarInput: JarInput, outputProvider: TransformOutputProvider) {
        if (!jarInput.file.absolutePath.endsWith(".jar")) {
            println(jarInput.file.absolutePath)
            return
        }

        val tmpFile = File(jarInput.file.parentFile, "${jarInput.file.name}.temp").also { it.createNewFile() }

        JarFile(jarInput.file).use { jarFile ->
            JarOutputStream(FileOutputStream(tmpFile)).use { jarOutputStream ->
                jarFile.entries().iterator().forEach { jarEntry ->
                    val zipEntry = ZipEntry(jarEntry.name)
                    jarFile.getInputStream(jarEntry).use { inputStream ->
                        if (jarEntry.name.endsWith(".class")) {
                            jarOutputStream.putNextEntry(zipEntry)
                            val classReader = ClassReader(IOUtils.toByteArray(inputStream))
                            val classWriter = ClassWriter(classReader, ClassWriter.COMPUTE_MAXS)
                            classReader.accept(ClassTraceVisitor(classWriter), EXPAND_FRAMES)
                            jarOutputStream.write(classWriter.toByteArray())
                        } else {
                            jarOutputStream.putNextEntry(zipEntry)
                            jarOutputStream.write(IOUtils.toByteArray(inputStream))
                        }
                    }

                    jarOutputStream.closeEntry()
                }
            }
        }

        val dest = outputProvider.getContentLocation(
                jarInput.file.nameWithoutExtension + DigestUtils.md5Hex(jarInput.file.absolutePath),
                jarInput.contentTypes,
                jarInput.scopes,
                Format.JAR)
        FileUtils.copyFile(tmpFile, dest)
    }
}