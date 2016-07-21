Web前端面试题目汇总
2016-07-21 json 交互设计前端开发
以下是收集一些面试中经常会遇到的经典面试题以及自己面试过程中无法解决的问题，通过对知识的整理以及经验的总结，重新巩固自身的前端基础知识，如有错误或更好的答案，欢迎指正。:）

HTML/CSS部分

1、什么是盒子模型？

在网页中，一个元素占有空间的大小由几个部分构成，其中包括元素的内容（content），元素的内边距（padding），元素的边框（border），元素的外边距（margin）四个部分。这四个部分占有的空间中，有的部分可以显示相应的内容，而有的部分只用来分隔相邻的区域或区域。4个部分一起构成了css中元素的盒模型。

2、行内元素有哪些？块级元素有哪些？ 空(void)元素有那些？

行内元素：a、b、span、img、input、strong、select、label、em、button、textarea
块级元素：div、ul、li、dl、dt、dd、p、h1-h6、blockquote
空元素：即系没有内容的HTML元素，例如：br、meta、hr、link、input、img

3、CSS实现垂直水平居中

一道经典的问题，实现方法有很多种，以下是其中一种实现：
HTML结构：

 <div class="wrapper">
    <div class="content"></div>
 </div>

CSS：

.wrapper{position:relative;}
    .content{
        background-color:#6699FF;
        width:200px;
        height:200px;
        position: absolute;        //父元素需要相对定位
        top: 50%;
        left: 50%;
        margin-top:-100px ;   //二分之一的height，width
        margin-left: -100px;
    }

4、简述一下src与href的区别

href 是指向网络资源所在位置，建立和当前元素（锚点）或当前文档（链接）之间的链接，用于超链接。

src是指向外部资源的位置，指向的内容将会嵌入到文档中当前标签所在位置；在请求src资源时会将其指向的资源下载并应用到文档内，例如js脚本，img图片和frame等元素。当浏览器解析到该元素时，会暂停其他资源的下载和处理，直到将该资源加载、编译、执行完毕，图片和框架等元素也如此，类似于将所指向资源嵌入当前标签内。这也是为什么将js脚本放在底部而不是头部。

5、什么是CSS Hack?

一般来说是针对不同的浏览器写不同的CSS,就是 CSS Hack。
IE浏览器Hack一般又分为三种，条件Hack、属性级Hack、选择符Hack（详细参考CSS文档：css文档）。例如：

// 1、条件Hack
   <!--[if IE]>
      <style>
            .test{color:red;}
      </style>
   <![endif]-->
   // 2、属性Hack
    .test{
    color:#0909; /* For IE8+ */
    *color:#f00;  /* For IE7 and earlier */
    _color:#ff0;  /* For IE6 and earlier */
    }
   // 3、选择符Hack
    * html .test{color:#090;}       /* For IE6 and earlier */
    * + html .test{color:#ff0;}     /* For IE7 */

6、简述同步和异步的区别

同步是阻塞模式，异步是非阻塞模式。
同步就是指一个进程在执行某个请求的时候，若该请求需要一段时间才能返回信息，那么这个进程将会一直等待下去，直到收到返回信息才继续执行下去；
异步是指进程不需要一直等下去，而是继续执行下面的操作，不管其他进程的状态。当有消息返回时系统会通知进程进行处理，这样可以提高执行的效率。

7、px和em的区别

px和em都是长度单位，区别是，px的值是固定的，指定是多少就是多少，计算比较容易。em得值不是固定的，并且em会继承父级元素的字体大小。
浏览器的默认字体高都是16px。所以未经调整的浏览器都符合: 1em=16px。那么12px=0.75em, 10px=0.625em

8、什么叫优雅降级和渐进增强？

渐进增强 progressive enhancement：
针对低版本浏览器进行构建页面，保证最基本的功能，然后再针对高级浏览器进行效果、交互等改进和追加功能达到更好的用户体验。

优雅降级 graceful degradation：
一开始就构建完整的功能，然后再针对低版本浏览器进行兼容。

区别：

a. 优雅降级是从复杂的现状开始，并试图减少用户体验的供给

b. 渐进增强则是从一个非常基础的，能够起作用的版本开始，并不断扩充，以适应未来环境的需要

c. 降级（功能衰减）意味着往回看；而渐进增强则意味着朝前看，同时保证其根基处于安全地带

9、浏览器的内核分别是什么?

IE: trident内核
Firefox：gecko内核
Safari：webkit内核
Opera：以前是presto内核，Opera现已改用Google Chrome的Blink内核
Chrome：Blink(基于webkit，Google与Opera Software共同开发)

JavaScript部分

1、怎样添加、移除、移动、复制、创建和查找节点？

1）创建新节点

createDocumentFragment() //创建一个DOM片段
createElement() //创建一个具体的元素
createTextNode() //创建一个文本节点

2）添加、移除、替换、插入

appendChild() //添加
removeChild() //移除
replaceChild() //替换
insertBefore() //插入

3）查找

getElementsByTagName() //通过标签名称
getElementsByName() //通过元素的Name属性的值
getElementById() //通过元素Id，唯一性

2、实现一个函数clone，可以对JavaScript中的5种主要的数据类型（包括Number、String、Object、Array、Boolean）进行值复制。

  /**
 * 对象克隆
 * 支持基本数据类型及对象
 * 递归方法
 */
function clone(obj) {
    var o;
    switch (typeof obj) {
        case "undefined":
            break;
        case "string":
            o = obj + "";
            break;
        case "number":
            o = obj - 0;
            break;
        case "boolean":
            o = obj;
            break;
        case "object": // object 分为两种情况 对象（Object）或数组（Array）
            if (obj === null) {
                o = null;
            } else {
                if (Object.prototype.toString.call(obj).slice(8, -1) === "Array") {
                    o = [];
                    for (var i = 0; i  obj.length; i++) {
                        o.push(clone(obj[i]));
                    }
                } else {
                    o = {};
                    for (var k in obj) {
                        o[k] = clone(obj[k]);
                    }
                }
            }
            break;
        default:
            o = obj;
            break;
    }
    return o;
}

3、如何消除一个数组里面重复的元素？

// 方法一：
var arr1 =[1,2,2,2,3,3,3,4,5,6],
    arr2 = [];
for(var i = 0,len = arr1.length; i< len; i++){
    if(arr2.indexOf(arr1[i]) < 0){
        arr2.push(arr1[i]);
    }
}
document.write(arr2); // 1,2,3,4,5,6

4、想实现一个对页面某个节点的拖曳？如何做？（使用原生JS）。

5、在Javascript中什么是伪数组？如何将伪数组转化为标准数组？

伪数组（类数组）：无法直接调用数组方法或期望length属性有什么特殊的行为，但仍可以对真正数组遍历方法来遍历它们。典型的是函数的argument参数，还有像调用getElementsByTagName,document.childNodes之类的,它们都返回NodeList对象都属于伪数组。可以使用Array.prototype.slice.call(fakeArray)将数组转化为真正的Array对象。

function log(){
      var args = Array.prototype.slice.call(arguments);  
//为了使用unshift数组方法，将argument转化为真正的数组
      args.unshift('(app)');
 
      console.log.apply(console, args);
};

6、Javascript中callee和caller的作用？

caller是返回一个对函数的引用，该函数调用了当前函数；

callee是返回正在被执行的function函数，也就是所指定的function对象的正文。

7、请描述一下cookies，sessionStorage和localStorage的区别

sessionStorage用于本地存储一个会话（session）中的数据，这些数据只有在同一个会话中的页面才能访问并且当会话结束后数据也随之销毁。因此sessionStorage不是一种持久化的本地存储，仅仅是会话级别的存储。而localStorage用于持久化的本地存储，除非主动删除数据，否则数据是永远不会过期的。

web storage和cookie的区别

Web Storage的概念和cookie相似，区别是它是为了更大容量存储设计的。Cookie的大小是受限的，并且每次你请求一个新的页面的时候Cookie都会被发送过去，这样无形中浪费了带宽，另外cookie还需要指定作用域，不可以跨域调用。
除此之外，Web Storage拥有setItem,getItem,removeItem,clear等方法，不像cookie需要前端开发者自己封装setCookie，getCookie。但是Cookie也是不可以或缺的：Cookie的作用是与服务器进行交互，作为HTTP规范的一部分而存在 ，而Web Storage仅仅是为了在本地“存储”数据而生。

8、手写数组快速排序

关于快排算法的详细说明，可以参考阮一峰老师的文章快速排序
“快速排序”的思想很简单，整个排序过程只需要三步：
（1）在数据集之中，选择一个元素作为”基准”（pivot）。
（2）所有小于”基准”的元素，都移到”基准”的左边；所有大于”基准”的元素，都移到”基准”的右边。
（3）对”基准”左边和右边的两个子集，不断重复第一步和第二步，直到所有子集只剩下一个元素为止。

9、统计字符串”aaaabbbccccddfgh”中字母个数或统计最多字母数。

var str = "aaaabbbccccddfgh";
var obj  = {};
for(var i=0;istr.length;i++){
    var v = str.charAt(i);
    if(obj[v] & obj[v].value == v){
        obj[v].count = ++ obj[v].count;
    }else{
        obj[v] = {};
        obj[v].count = 1;
        obj[v].value = v;
    }
}
for(key in obj){
    document.write(obj[key].value +'='+obj[key].count+' '); // a=4  b=3  c=4  d=2  f=1  g=1  h=1 
}

10、写一个function，清除字符串前后的空格。（兼容所有浏览器）

function trim(str) {
    if (str & typeof str === "string") {
        return str.replace(/(^s*)|(s*)$/g,""); //去除前后空白符
    }
}

其他

1、一次完整的HTTP事务是怎样的一个过程？

基本流程：
a. 域名解析
b. 发起TCP的3次握手
c. 建立TCP连接后发起http请求
d. 服务器端响应http请求，浏览器得到html代码
e. 浏览器解析html代码，并请求html代码中的资源
f. 浏览器对页面进行渲染呈现给用户

2、对前端工程师这个职位你是怎么样理解的？

a. 前端是最贴近用户的程序员，前端的能力就是能让产品从 90分进化到 100 分，甚至更好
b. 参与项目，快速高质量完成实现效果图，精确到1px；
c. 与团队成员，UI设计，产品经理的沟通；
d. 做好的页面结构，页面重构和用户体验；
e. 处理hack，兼容、写出优美的代码格式；
f. 针对服务器的优化、拥抱最新前端技术。

阅读 88赞 投诉