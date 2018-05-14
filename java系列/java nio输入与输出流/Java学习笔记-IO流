Java学习笔记-IO流

2016-06-19 有余同学

File类
1.所属的包：java.io.File
2.用途：File类用来描述系统中的文件和文件夹
文件：计算机中用于存储信息（数据）。用后缀名来区分文件类别。
文件夹：文件所在的位置。
3.如何用File类描述本地磁盘上的文件
方法一：File file = new File("D:\\test\\test.txt");
方法二：File file = new File("D:\\test","test.txt");
4.常用方法：
属于文件的方法：
    createNewFile()：创建一个文件
    length()：以字节为单位的文件大小
属于文件夹的方法：
    list()：获取某个文件夹下的文件和目录，返回字符串数组。
    listFile()：获取文件夹的内容，是一个路径数组名，如果作用在文件上，则返回null。
    mkdir()：创建目录，但是只有一层。
    mkdirs()：创建目录，多级。
文件和文件夹通用的方法：
    canRead()：可读吗，返回boolean类型
    canWrite() ：可写吗，返回boolean类型
    delete()：删除文件或文件夹（文件夹必须为空）
    exists()：判断文件和文件夹是否存在，返回boolean类型
    getAbsoluteFile():获取绝对路径，返回类型为File
    getAbsolutePath():获取绝对路径，返回类型为String
    getName():获取文件或文件夹名称
    getParentFile():获取父目录 ，返回File
    getParent(): 获取父目录，返回String
    isAbsolute():是否是绝对路径
    isDirectory():判断是否是文件夹
    isFile():判断是否是文件
    lastModified():最后一次修改的事件,返回long类型
5.绝对路径和相对路径
绝对路径：描述路径从根盘符开始。
相对路径：相对于某一个路径来讲（java程序中的相对路径就是相对于java工程路径而言）
IO流
1.概念：IO流也叫数据流，是数据通信的通道，简单的说就是数据从一个地方到另一个地方。
2.数据的单位：
bit:位,一个0或一个1
byte:字节,8个0或1.
1kb:1024byte
1mb:1024kb
1gb:1024mb
3.流的分类
按流向分：
    输入流：数据进入程序中
    输出流：数据从程序中出去
按单位分：
    字节流：每次操作一个字节，即8个0、1码
    字符流：每次操作两个字节，即16个0、1码
按功能分：
    节点流：直接接触数据源的流
    处理流：套在别的流上的流
4.四大基类流（都是抽象的）
字节输入流：InputStream 
字节输出流：OutputStream
字符输入流：Reader
字符输出流：Writer
5.IO流的操作步骤：
step1：确定数据源
step2：创建流：流向、单位、子类流
step3：操作流：读数据、写数据
step4：关闭流
6.读数据的三个方法：read(),read(byte[] b),read(byte[] b,int off,int len)
它们的区别如下：
read()：每次读取一个字节，返回值就是读取的数据本身，读到文件末尾返回-1。示例代码：
File file = new File ( "D:\\test\\aaaa.txt") ;
           InputStream iStream = null ;
           System .out . println( "1.read()方法读取：" ) ;
           try {
               iStream = new FileInputStream (file ) ;
               int temp = 0 ;
               // 每次读取一个字节，返回值就是读取的数据本身，读到文件末尾返回-1
               while (( temp = iStream. read ()) != - 1 ) {
                    System .out . print(( char ) temp );
               }
           } catch (FileNotFoundException e) {
               e .printStackTrace () ;
           } catch (IOException e) {
               e .printStackTrace () ;
           } finally {
               if ( iStream != null) {
                    try {
                         iStream .close () ;
                    } catch (IOException e) {
                         e .printStackTrace () ;
                    }
               }
           }
read(byte[] b)：每次读取多个字节，最大为数组b的长度，读到的数据存入数组b中，返回值是每次实际读取的字节个数，读到文件末尾返回-1。示例代码：
File file = new File ("D:\\test\\aaaa.txt" ) ;
InputStream iStream1 = null ;
           try {
               iStream1 = new FileInputStream (file ) ;
               byte [] bs = new byte [3 ] ;
               int len = 0 ;
               while ( len != -1 ) {
 // 每次读取多个字节,最大为数组的长度,数据存入数组 bs中.返回值是实际读入的数量.读到文件末尾返回-1
                    len = iStream1. read (bs ) ;
                    for ( int i = 0 ; i < len; i++ ) {
                         System .out . print(( char ) bs [i ]) ;
                    }
               }
           } catch (FileNotFoundException e) {
               e .printStackTrace () ;
           } catch (IOException e) {
               e .printStackTrace () ;
           } finally {
               if ( iStream1 != null) {
                    try {
                         iStream1 .close () ;
                    } catch (IOException e) {
                         e .printStackTrace () ;
                    }
               }
           }
read(byte[] b,int off,int len)：每次读取多个字节，最多为len个，读取到的数据存入数组b中，从下标为off开始存，返回值是每次实际读取的字节个数，读到文件末尾返回-1`。示例代码：

InputStream iStream3 = null ;
           try {
               iStream3 = new FileInputStream (file ) ;
               byte [] bs1 = new byte [3 ] ;
               int len = 0 ;
               while ( len != -1 ) {
// 读取多个字节,最多为 len个,数据存入数组bs1中,从下标off开始.返回值为实际读入的数量.读到文件末尾返回-1，每读取一次就把读取到的三个字母存入长度为3的数组中，然后输出，读第二次又读取了三个字母，覆盖掉之前的数组的值，然后输出，最后为了防止把没有覆盖掉的字母输出，将for循环的循环条件写成i<len, len为每一次实际读入到的数量
                    len = iStream3. read (bs1 , 0 , 3 ) ;
                    for ( int i = 0 ; i < len; i++ ) {
                         System .out . print(( char ) bs1 [i ]) ;
                    }
               }
           } catch (FileNotFoundException e) {
               e .printStackTrace () ;
           } catch (IOException e) {
               e .printStackTrace () ;
           } finally {
               if ( iStream3 != null) {
                    try {
                         iStream3 .close () ;
                    } catch (IOException e) {
                         e .printStackTrace () ;
                    }
               }
           }
7.写数据的三个方法：write(int),write(byte[] b),write(byte[] b,int off,int len)
它们的区别如下：
write(int)：将一个指定的字节写入
write(byte[] b)：将数组byte中的所有数据写入
write(byte[] b,int off,int len)：从数组下标为off开始写入，写入len个

示例代码：
File file = new File ( "D:\\test\\aa\\aa.txt") ;
           String string = "abcdefghijk" ;
           OutputStream oStream = null ;
           try {
               oStream = new FileOutputStream (file ) ;
               // 将一个指定的字节写入
               oStream .write ( 98) ;
               oStream .write ( "\r\n". getBytes ()); // 换行
               // 将数组bytes中的所有数据写入
               byte [] bytes = string. getBytes ();
               oStream .write ( bytes) ;
               oStream .write ( "\r\n". getBytes ());
               // 将数组bytes1中的从下标为0开始，写入3个
               byte [] bytes1 = { 'a' , 'b' , 'c' , 'd' , 'e' , 'f' } ;
               oStream .write ( bytes1, 0, 3) ;
           } catch (FileNotFoundException e) {
               e .printStackTrace () ;
           } catch (IOException e) {
               e .printStackTrace () ;
           }
8.对于读数据和写数据的操作在编程过程常中会犯的错误：
例题：将一张图片,从一个位置,复制到另外一个位置。
常见的问题：复制完成后，新图片损坏。
错误原因：用read(byte[] b)来读取数据，每次将读取的数据存入数组中，获取每次实际读取的字节数len=read(byte[] b)。但是在写入数据时错误的把len写入：write(len)，正确的应该是把保存数据的数组b写入：write(b)。
/**
 * 将一张图片,从一个位置,复制到另外一个位置.
 *
 * @author 有余同学
 *
 */
public class Copy{
    public static void main(String[] args) {
        File oldfile = new File("D:\\test\\buhuo.PNG");
        File newfile = new File("D:\\test\\aa\\buhuo.PNG");
        InputStream iStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            iStream = new FileInputStream(oldfile);
            fileOutputStream = new FileOutputStream(newfile);
            byte[] b = new byte[1024];
            int len = 0;
            while ((len = iStream.read(b)) != -1) {
                // 读的数据在数组里，所以应该将数组写入，而不是将len写入
                //错误的写法：fileOutputStream.write(len);
                fileOutputStream.write(b);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (iStream != null && fileOutputStream != null) {
                try {
                    iStream.close();
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
字符流
1.特点：每次读取一个字符。
2.字符输入流：Reader
   字符输出流：Writer
3.方法：
读
    read():每次读取单个字符，返回值是读取到的数据本身，读到文件末尾返回-1。
    read(char[] ch):每次读取多个字符存入数组，最多为数组的长度，返回值是实际读入的长度，读到文件末尾返回-1
    read(char[] ch,int off,int len):每次读取多个字符，最大为数组的长度，从数组下标为off开始存储，每次存储len个，返回值为实际读取的数量
写
    write(int)：写出一个字符 ，返回值为空
    write(char[] c)：写出一个字符数组c,数组c中的数据,全部写出 ，返回值为空
    wrire(char[] c,int off,int len)：写出字符数组c的部分,从下标off开始,写len个，返回值为空
    write(String s) ：直接写出一个字符串 ，返回值为空
    write(String,int off,int len) ：写出字符串的部分，从下标为off开始写，每次写len个，返回值为空
4.字符流的本质：字节流，自带一块缓冲区。只用于操作文本（边读边显示），用字符流向外写数据时要刷新和关闭。
5.如何在字节流和字符流之间选择：
字节流：各种格式都可以，图片、音频、视频、压缩......
字符流：边读边显示文本内容
子类流
文件流：（重点）
    FileInputStream
    FileOutputStream
    FileReader
    FileWriter
转换流：单位的转换（字节和字符之间）、设置编码
    转换输入流：InputStreamReader(InputSteam)
    转换输出流：OutputStreamWriter(OutputSteam)
缓存流：自带一块缓冲区（本质是数组），可以高速读写。它也叫处理流、过滤流。
    BufferedInputStream：缓存字节输入流
    BufferedOutputStream：缓存字节输出流
    BufferedReader：缓存字符输入流。特有的方法：readLine()读取一行。
    BufferedWriter：缓存字符输出流。特有方法：newLine()换行。
小结：
文件流：File，String，pathName（直接接触数据源，节点流）
转换流：必须传入一个字节流，转换单位（套在别的流外部，处理流）
缓存流：同上（处理流）
内存流（重要重要）
1.特点：存到内存中速度快，数据用完后自动清理。
ByteArrayInputSteam：从内存中读取数据（在内存中创建数组）
ByteArrayOutputSteam：往内存中写数据
2.内存流本质：可变长度的数组
3.方法：
方法：toByteArray():返回byte[]。该方法音频、视频、图片都可以使用
示例代码：
/**
 * 假装,根据网址,获取网络上的图片数据,下载到手机上(电脑磁盘文件夹)
 * 
 * @author 有余同学
 *
 */
public class Demo1_ByteArrayOutputStream {
	public static void main(String[] args) throws IOException {
		// 模拟网络上的一张图片
		File imgPath = new File("D:\\test\\buhuo.PNG");
		FileInputStream fileInputStream = new FileInputStream(imgPath);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		int len = 0;
		byte[] bs = new byte[1024];
		while ((len = fileInputStream.read(bs)) != -1) {
			bos.write(bs, 0, len);
		}
		byte[] byteArray = bos.toByteArray();
		FileOutputStream fos = new FileOutputStream("D:\\test\\copy\\abc.PNG");
		fos.write(byteArray);
		fos.close();
		fileInputStream.close();
	}
}
方法：toString()。操作文本数据时使用。
示例代码：
public class Demo3_ByteArrayOutputStream {
	public static void main(String[] args) throws IOException {
		FileInputStream fis = new FileInputStream("D:\\test\\abc.txt");
		Writer ops = new FileWriter("D:\\test\\copy\\abc.txt");
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		int len = 0;
		byte[] bs = new byte[1024];
		while ((len = fis.read(bs)) != -1) {
			bos.write(bs, 0, len);
		}
		String string = bos.toString();
		ops.write(string);
		ops.close();
		fis.close();
	}
}
4.编码：
中国：GBK  中：20013。中国的每一个汉字都有一个编码。
台湾：大五码
网络传输：UTF-8
对象流（了解）
对象的持久化
对象的序列化：指将程序中的对象转化为一系列的二进制数据的过程就叫对象的序列化。
对象的反序列化：将一堆二进制数据再转为对象的过程。
1.对象输入流：ObjectInputStream
    对象输出流：ObjectOutputStream
2.方法：readObject()、writeObject()
读和写的原则：一定要对文件的结构读写严格。（反序列化的个数不能超过序列化的个数，否则：java.io.EOFException）。
注意：
1.要想让一个类的对象具有序列化的功能，必须让该类实现一个接口（java.io.Serializable）,该接口中没有任何的方法，就只是一个标记，称为标记接口。
2.显示的声明该类的SerialVersionUID（选择默认生成）。
3.只能序列化对象的属性（不能序列化静态的属性）。
4.transient:短暂的，被他修饰的属性不能被序列化存储。
随机访问文件类：RandomAccessFile类（了解）
常用方法：
getFilePoint():long,获取光标指针在文件中的位置
seek(n):设置光标指针距离文件开头的位置（n个字节）
skitbytes(n):光标指针向后跳n个字节

附录：IO流总结
