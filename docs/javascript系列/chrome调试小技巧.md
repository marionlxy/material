
# Chrome在控制台使用的小技巧
原文：Things you probably didn't know you could do with Chrome's Developer Console

## 直接修改页面文本内容
``` 
在控制台输入指令,可直接修改网页中的文本内容。所见即所得！！
	document.body.contentEditable=true;
``` 
## 查找Dom元素
``` 
不引入jquery的前提下可以在控制台，输入$('selector')查找第一个匹配选择规定的DOM元素，输入$$('selector')以数组的形式返回所有满足的匹配规定DOM元素。 很像原生JS中的Document.querySelector('')指令。
	$('selector')或$$('selector');
``` 
## 获取Dom元素绑定的事件
``` 
获取DOM元素所绑定的事件 getEventListeners($('selector')),以数组对象的形式返回绑定的所有事件。
如果你需要访问其中某个事件，可以通过getEventListeners($('selector')).eventName，其中eventName是指事件的类型（例如click）
	getEventListeners($('selector')).[eventName];
``` 
##  检测事件
``` 
当你需要监视某个DOM触发的事件时，输入以下指令，一旦该元素的某个事件被触发就会在控制台里显示出来。
	monitorEvents($('selector').[eventName]
unmonitorEvents($('selector'))用来停止对某个元素的事件监测。
``` 

##  用计时器来获取某段代码块的运行时间
``` 
	console.time('myTime');//开始计时 myTime是指定时器的名字
    用来测试运行时间的代码
	console.timeEnd('myTime');//结束计时
``` 	
致敬张东，第一次见到这段调试代码是在他的鄙视题中~

##  控制台中直接以表格的方式输出数组
``` 
	var myArray=[{a:1,b:2,c:3},{d:4,e:5,f:6,g:7}];//声明一个数组
    console.table(myArray);//你会惊奇的发现控制台以表格的方式呈现出来！
``` 
##  通过控制台方法来检查元素
``` 
inspect($('selector')) 会检查所有匹配选择器的DOM元素，并返回所有选择器选择的DOM对象。
	inspect($('selector'))//会返回所有匹配的DOM元素
	inspect($('selector')[index])//输入想找的元素下标
    $0//会返回最近查找的DOM元素;
    $1//回返回倒数第二个查找的DOM元素；
``` 
##  列出某个元素的所有属性
``` 
dir($('selector')) 会返回匹配选择器的DOM元素的所有属性，你可以展开输出的结果查看详细内容。
	dir($('selector')[index])//制台列出某个元素的所有属性;
``` 
##  获取最后计算的结果的值
``` 
	$_;//指上次计算结果的值
    例如
    1+1；
    2；
    $_;//2
    $_*$_;//4
``` 
##  清空控制台指令
``` 
当你需要这么做的时候，只需要输入 clear() 然后回车就好啦！
	clear();
``` 
##  Bootstrap中的tooltips
``` 
它不是纯css插件，如果需要使用该插件，必须用jquery激活使用，使用下列代码激活。！！
$(function(){//激活所有
    	$("[data-toggle='tooltips']").tooltips();
    });
$('selector').tooltips();//激活指定
``` 
