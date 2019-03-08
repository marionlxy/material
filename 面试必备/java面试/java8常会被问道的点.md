### java8带来的新特性

目录
1. 接口的默认方法和静态方法
2. 函数式接口FunctionInterface与lambda表达式
3. 方法引用
4. Stream
    4.1 什么是流Stream
    4.2 生成Stream的方式
    4.3 Stream的操作
5. Optional
6. Date/time API的改进
7. 其他改进
https://juejin.im/post/5ae6bfb66fb9a07a9b35bac1#heading-1

### 1.文件拷贝
今天在资料上练习文件拷贝程序时，遇到了java 7中的一个新特性TWR，可以减少实际中的部分代码书写，对其做以记录。

try-with-resources语句是声明了一个或多个资源的try语句块。在java中资源作为一个对象，在程序完成后必须关闭。try-with-resources语句确保每个资源在语句结束时关闭。只要是实现了java.lang.AutoCloseable的任何对象（包括实现java.lang.Closeable的所有对象）都可以使用该方式对资源进行关闭。

在java 7之前，一般在进行文件IO操作时都需要显式的进行文件流（也可以理解为资源）的close操作，无论是操作到文件流末尾还是发生异常。往往很简单的一个逻辑都要好几行的代码进行修饰，使得代码结构变的复杂。如下例子，不管try语句块正常结束还是发生异常，都可以使用finally语句块来确保资源被关闭：

public final class CopyFile {////使用final定义工具类，不用实例化


    private CopyFile() {   //若实例化，则报错
        throw new AssertionError();
    }

    public static void fileCopy(String source, String target) throws IOException {
        try(InputStream in = new FileInputStream(source)) {
            try(OutputStream out = new FileOutputStream(target)){
                byte[] buffer = new byte[4096];
                int bytesToRead;
                while((bytesToRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesToRead);
                }
            }
        }
    }

    public static void fileCopyNIO(String source, String target) throws IOException {
        try(FileInputStream in = new FileInputStream(source)) {
            try(FileOutputStream out = new FileOutputStream(target)) {
                FileChannel inChannel = in.getChannel();
                FileChannel outChannel = out.getChannel();
                ByteBuffer buffer = ByteBuffer.allocate(4096);  //申请4096字节缓冲
                while(inChannel.read(buffer) != -1) {
                    buffer.flip();   //反转此缓冲区，设置当前位置指针为0，read读文件后文件指针在缓冲区末尾，需要使用flip重置
                    outChannel.write(buffer);
                    buffer.clear();   //清空缓冲区
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        //CopyFile copyfile = new CopyFile();
        long start = System.currentTimeMillis();
        CopyFile.fileCopyNIO("E:\\大数据.rar", "E:\\testtest");
        long end = System.currentTimeMillis();
        System.out.println("耗时："+(end-start));
    }


