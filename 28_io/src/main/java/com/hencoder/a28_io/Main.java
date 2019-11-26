package com.hencoder.a28_io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.Writer;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

import okio.Buffer;
import okio.BufferedSource;
import okio.Okio;
import okio.Source;

public class Main {
    public static void main(String[] args) {
        okio1();
    }

    private static void io1() {
        try (OutputStream outputStream = new FileOutputStream("./28_io/text.txt")) {
            outputStream.write('a');
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void io2() {
        try (InputStream inputStream = new FileInputStream("./28_io/text.txt")) {
            System.out.print((char) inputStream.read());
            System.out.print((char) inputStream.read());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void io3() {
        try (InputStream inputStream = new FileInputStream("./28_io/text.txt");
             Reader reader = new InputStreamReader(inputStream);
             BufferedReader bufferedReader = new BufferedReader(reader)) {
            System.out.print(bufferedReader.readLine());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void io4() {
        try (OutputStream outputStream = new FileOutputStream("./28_io/text.txt");
             Writer writer = new OutputStreamWriter(outputStream);
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
            bufferedWriter.write("a");
            bufferedWriter.write("b");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void io5() {
        try (InputStream inputStream = new FileInputStream("./28_io/text.txt");
             OutputStream outputStream = new FileOutputStream("./28_io/text_copy.txt")) {
            byte[] data = new byte[1024];
            int read;
            while ((read = inputStream.read(data)) != -1) {
                outputStream.write(data, 0, read);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void io6() {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            Socket socket = serverSocket.accept();
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            String data;
            while (true) {
                data = reader.readLine();
                writer.write("你输入了：");
                writer.write(data);
                writer.write("\n");
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void nio1() {
        try {
            RandomAccessFile file = new RandomAccessFile("./28_io/text.txt", "r");
            FileChannel channel = file.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            channel.read(byteBuffer);
            byteBuffer.flip();
            System.out.println(Charset.defaultCharset().decode(byteBuffer));
            byteBuffer.clear();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void nio2() {
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(8080));
            serverSocketChannel.configureBlocking(false);
            Selector selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            SocketChannel socketChannel = serverSocketChannel.accept();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            while (socketChannel.read(byteBuffer) != -1) {
                byteBuffer.flip();
                socketChannel.write(byteBuffer);
                byteBuffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void okio1() {
        try (BufferedSource source = Okio.buffer(Okio.source(new File("./28_io/text.txt")))) {
            Buffer buffer = new Buffer();
            source.read(buffer, 1024);
            System.out.println(buffer.readUtf8Line());

            OutputStream outputStream = buffer.outputStream();
            InputStream inputStream = buffer.inputStream();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
