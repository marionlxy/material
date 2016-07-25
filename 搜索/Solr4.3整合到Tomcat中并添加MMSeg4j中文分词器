Solr4.3整合到Tomcat中并添加MMSeg4j中文分词器
2016-07-13 软件开发那些事儿
关注我，你的眼睛会怀孕
        

Solr4.3整合到Tomcat中并添加MMSeg4j中文分词器

1、新建一个文件夹命名为Solr并在里面建两个文件夹分别命名为home和server。


2、将下载的Solr包解压进入example的solr文件夹中拷贝其中的内容到刚建的home文件夹中。


3、将example的webapps文件夹中的solr.war包拷贝到刚建的server文件夹中并解压得到。


4、修改Solrhomecollection1conf目录下的solrconfig.xml文件。


可以看到solr.data.dir指向刚才见的home文件夹中的data文件夹。我们需要在home文件夹中创建一个文件夹命名为data用于存放索引。


5、为Tomcat的Server.xml配置Context，也就是在需要启动Solr应用的Tomcat中的conf目录中的Server.xml配置文件添加Context节点，配置如下：


图中画荧光背景的固定写法。
6、启动Tomcat Solr4.3会报一个错误。如图：


报不能找到SLF4j logging，可以到Sorl解压包的solr-4.3.0examplelibext目录下把里面五个jar文件拷贝到SolrserversolrWEB-INFlib的目录中，          然后重启Tomcat。
7、访问http://localhost:8080/solr 看到了界面：


说明Solr跟Tomcat整合成功！
8、整个MMSeg4j中文分词器到Solr中
8.1、下载MMSeg4j并解压把mmseg4j-1.9.1dist 目录中的三个jar包拷贝到SolrserversolrWEB-INFlib目录中
下载地址：https://code.google.com/p/mmseg4j/
8.2、打开MMSeg4j解压目录中的README.txt文件:


添加README.txt中如上图画方框中的xml内容到Solrhomecollection1confschema.xml文件的types节点中：


并修改最后一项的dicPath为dic
8.3、在Solr/home文件夹中创建dic文件夹，也就是dicPath配置的dic参数


为什么要在home目录中创建dic文件夹那，通过MMSeg4j的README.txt文件可以知道：
dicPath 指定词库位置（每个MMSegTokenizerFactory可以指定不同的目录，当是相对目录时，是相对 solr.home 的目录）
8.4、拷贝词库到dic目录中，如果你下载的MMSeg4j文件没有data文件夹，可以下载其他的MMSeg4j文件看看，我下载的 mmseg4j-1.9.1就没有data文件                   夹，但是1.8.5版本是包含data文件夹的，把data文件夹中的文件拷贝到dic文件夹中，好了中文分词器就配好了。启动Solr服务器。


8.5、访问启动的Solr的：


分词已经成果了，上面图中我们选择的textSimple分词器，其实我们拷贝了三种分词器到schema.xml文件中，分别是：textComplex、
textMaxWord和textSimple，你们可以自己逐个试试。

更多关于java，大数据挖掘学习资源，系统源码，请立刻关注公众号：javakfnxs，免费获取


（关注ID：javakfnxs）
（如有侵权，请联系我们删除）



正在浏览此文章




本文使用软件下载地址(如有)见原文。


  1464489304324029.jpg  


阅读原文阅读 14 赞 投诉
写留言