package com.example.buildsrc

import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

class ClassTraceVisitor(cv: ClassVisitor?) : ClassVisitor(Opcodes.ASM7, cv), Opcodes {
    lateinit var className: String

    override fun visit(version: Int, access: Int, name: String, signature: String?,
                       superName: String?, interfaces: Array<out String>?) {
        super.visit(version, access, name, signature, superName, interfaces)
        this.className = name
    }

    override fun visitMethod(access: Int, name: String?, descriptor: String?,
                             signature: String?, exceptions: Array<out String>?): MethodVisitor? {
        return MethodTraceVisitor(
                Opcodes.ASM7,
                super.visitMethod(access, name, descriptor, signature, exceptions),
                access,
                name,
                descriptor
        ).also {
            it.className = this.className
        }
    }
}