详解IO流

原创 2016-05-20 威哥 马剑威

关注（mjw-java）——不一样的技术分享，不一样的学习体验，跟威哥一起学Java,你一定可以！每天早上六点半，我们不见不散。

前两天看有同学说想看IO的文章，我这边就整理了一下，大家会的可以一起回顾回顾。

一、流的概念
流从字面理解就是一个流向，通常我们说的水流，河流，都称为流，程序 ，文件和程序之间连接一个管道，水流就在之间形成了,自然也就出现了方向：可以流进，也可以流出.便于理解，这么定义流： 流就是一个管道里面有流水，这个管道连接了文件和程序。来实现程序或进程间的通信，或读写外围设备、外部文件等。

二、流的分类
根据它们操作对象的类型是字符还是字节可分为两大类： 字符流和字节流。整体结构如下：

三、字节流
1、字节输出流 OutputStream类定义 public abstract class OutputStream extends Object implements Closeable, Flushable  此抽象类是表示输出字节流的所有类的超类。输出流接受输出字节并将这些字节发送到InputStream 类某个接收器要向文件中输出，使用FileOutputStream类 
2、字节输入流 定义： public abstract class InputStream extends Object implements Closeable  此抽象类是表示字节输入流的所有类的超类。 FileInputStream 从文件系统中的某个文件中获得输入字节。 

四、字符流
1、Writer  写入字符流的抽象类。子类必须实现的方法仅有 write(char[], int, int)、flush() 和 close()。但是，多数子类将重写此处定义的一些方法，以提供更高的效率和/或其他功能。  与OutputStream一样，对文件的操作使用：FileWriter类完成。 
2、Reader 用于读取字符流的抽象类。子类必须实现的方法只有 read(char[], int, int) 和 close()。但是，多数子类将重写此处定义的一些方法，以提供更高的效率和/或其他功能。  使用FileReader类进行实例化操作。  

五、缓冲流
对文件或其它目标频繁的读写操作，效率低，性能差。 使用缓冲流的好处是，能够更高效的读写信息，原理是将数据先缓冲起来，然后一起写入或者读取出来。 
BufferedInputStream： 为另一个输入流添加一些功能，在创建 BufferedInputStream 时，会创建一个内部缓冲区数组，用于缓冲数据。 
BufferedOutputStream：通过设置这种输出流，应用程序就可以将各个字节写入底层输出流中，而不必针对每次字节写入调用底层系统。 
BufferedReader：从字符输入流中读取文本，缓冲各个字符，从而实现字符、数组和行的高效读取。 
BufferedWriter：将文本写入字符输出流，缓冲各个字符，从而提供单个字符、数组和字符串的高效写入。  

六、打印流
打印流的主要功能是用于输出，在整个IO包中打印流分为两种类型：  
字节打印流：PrintStream  
字符打印流：PrintWriter 
打印流可以很方便的进行输出 

七、对象流
对象流的两个类：
ObjectOutputStream 将 Java 对象的基本数据类型和图形写入 OutputStream 
ObjectInputStream 对以前使用 ObjectOutputStream 写入的基本数据和对象进行反序列化。这里要注意，序列化对象的时候要用到serializable接口标记为可序列化的

八、字节数组流
ByteArrayInputStream  包含一个内部缓冲区，该缓冲区包含从流中读取的字节。内部计数器跟踪 read 方法要提供的下一个字节。 关闭 ByteArrayInputStream 无效。此类中的方法在关闭此流后仍可被调用，而不会产生任何 IOException。  
ByteArrayOutputStream 此类实现了一个输出流，其中的数据被写入一个 byte 数组。缓冲区会随着数据的不断写入而自动增长。可使用 toByteArray() 和 toString() 获取数据。 关闭 ByteArrayOutputStream 无效。此类中的方法在关闭此流后仍可被调用，而不会产生任何 IOException。  

九、数据流
DataInputStream： 数据输入流允许应用程序以与机器无关方式从底层输入流中读取基本 Java 数据类型。应用程序可以使用数据输出流写入稍后由数据输入流读取的数据。 DataInputStream 对于多线程访问不一定是安全的。 线程安全是可选的，它由此类方法的使用者负责。 
DataOutputStream： 数据输出流允许应用程序以适当方式将基本 Java 数据类型写入输出流中。然后，应用程序可以使用数据输入流将数据读入。   

十、字符串流
StringReader 其源为一个字符串的字符流。  
StringWriter 一个字符流，可以用其回收在字符串缓冲区中的输出来构造字符串。 关闭 StringWriter 无效。此类中的方法在关闭该流后仍可被调用，而不会产生任何 IOException。

简单举个复制文件的例子，如下，我们一边从原路径中读取，一边向指定路径写入：
public static void copyFile(String src,String target){
        File srcFile = new File(src);
        File targetFile = new File(target);
        try {
            InputStream in = new FileInputStream(srcFile); 
            OutputStream out = new FileOutputStream(targetFile);
            byte[] bytes = new byte[1024];
            int len = -1;
            while((len=in.read(bytes))!=-1){
                out.write(bytes, 0, len);
            }
            in.close();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("文件复制成功");
    }

总结：根据传输的类型和格式来选择效率最高的流去处理，比如如果确定传输的都是文本，那么我们就用字符流，传输的是数据我们要用数据流等等，另外这里面涉及到一个经典设计模式装饰者设计模式，大家有时间不妨掌握一下。

