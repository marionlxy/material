浅谈Html的内容加载及JS执行顺序
![](http://i.imgur.com/O5WUbRb.jpg)
> 2015.7.8来源：红黑联盟
同事跟我说他用jQuery取不到页面上隐藏元素input的值，他的html页面大概内容如下。

<script type=text/javascript src=jslib/jquery-1.11.2.min.js></script><script type=text/javascript> var userId = $('#hiddenUserId').val; var contextPath = $('#hiddenContextPath').val; var userName = $('#hiddenUserName').val; </script>
页面中的JS脚本在head中，JS脚本要读取的input在body中。浏览器对html页面内容的加载是顺序加载，也就是在html页面中前面先加载，因此当加载到JS脚本时，input还没有加载到浏览器中。JS是一种解释性的脚本，也是从上而下顺序执行，由于这段JS代码是立即执行的，所以当JS在执行的时候，读取不到input的值。

最直接的修改方法是把JS放到网页的最下面执行。

<script type=text/javascript src=jslib/jquery-1.11.2.min.js></script><script type=text/javascript> var userId = $('#hiddenUserId').val; var contextPath = $('#hiddenContextPath').val; var userName = $('#hiddenUserName').val; </script>把JS放到网页的最下面，这样在JS执行的时候，网页内容都已经加载完毕。把JS放在网页的最下面方法并不是最好的解决方法，大部分情况JS并不是总能放在网页的最下面。这时可以用window的onload事件，onload事件在整个页面都加载完成后才触发，可以把JS脚本放在onload里面执行。不同浏览器onload事件添加方式也不一样。
IE下事件：

window.attachEvent('onload', function{ var userId = $('#hiddenUserId').val; var contextPath = $('#hiddenContextPath').val; var userName = $('#hiddenUserName').val; });
Chrome/Firefox等DOM标准事件：
window.addEventListener('load', function{ var userId = $('#hiddenUserId').val; var contextPath = $('#hiddenContextPath').val; var userName = $('#hiddenUserName').val; });
由于不同浏览器的事件添加方式不一样，jQuery为我们提供了通用的初始化方法，该方法在页面加载完成时触发。
$(function{ var userId = $('#hiddenUserId').val; var contextPath = $('#hiddenContextPath').val; var userName = $('#hiddenUserName').val; });上面方法本质就是添加onload监听事件。
最终修改后的页面

<script type=text/javascript src=jslib/jquery-1.11.2.min.js></script><script type=text/javascript> $(function{ var userId = $('#hiddenUserId').val; var contextPath = $('#hiddenContextPath').val; var userName = $('#hiddenUserName').val; }); </script>