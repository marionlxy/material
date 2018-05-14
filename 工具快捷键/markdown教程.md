###1. 常用语法
1. 标题	用#号表示，#一级标题，##表示二级标题，依次类推，快捷键ctrl+1、2、3、4…
1. 字体加粗	左右用**包裹起来，快捷键ctrl+b
1. 斜体字	左右用*包裹起来，快捷键ctrl+i
1. 加粗加斜体	左右用***包裹起来，快捷键ctrl+i，ctrl+b，先后顺序无所谓
1. 引用	在文字开头添加 > 表示引用说明，快捷键ctrl+q
1. 时间戳	快捷键ctrl+t
1. 代码块	可以通过tab或者4个空格缩进表示，也可以通过“`将代码包裹起来表示代码块，快捷键ctrl+k
1. 下划线	用—表示下划线
1. 有序列表	通过-加一个空格表示，后面跟内容，快捷键ctrl+u
1. 无序列表	通过数字加一个.以及一个空格表示，后面跟内容,快捷键ctrl+shift+o
1. 表格	—表示，中间用竖线分割开
1. 链接	快捷键ctrl+l
1. 图片	快捷键ctrl+g
1. 生成目录	[TOC]表示，特殊，比如CSDN个blog就支持该语法

———————————————————————————————————————————————————————————
###1. 用例


Text
----

Here is a paragraph with bold text. **This is some bold text.** Here is a
paragraph with bold text. __This is also some bold text.__

Here is another one with italic text. *This is some italic text.* Here is
another one with italic text. _This is some italic text._

Here is another one with struckout text. ~~This is some struckout text.~~


Links
-----

Autolink: <http://example.com>

Link: [Example](http://example.com)

Reference style [link][1].

[1]: http://example.com  "Example"


Images
------

Image: ![My image](http://www.foo.bar/image.png)

Headers
-------

# First level title
## Second level title
### Third level title
#### Fourth level title
##### Fifth level title
###### Sixth level title

### Title with [link](http://localhost)
### Title with ![image](http://localhost)

Code
----

```
This
  is
    code
      fence
```

Inline `code span in a` paragraph.

This is a code block:

    /**
     * Sorts the specified array into ascending numerical order.
     *
     * <p>Implementation note: The sorting algorithm is a Dual-Pivot Quicksort
     * by Vladimir Yaroslavskiy, Jon Bentley, and Joshua Bloch. This algorithm
     * offers O(n log(n)) performance on many data sets that cause other
     * quicksorts to degrade to quadratic performance, and is typically
     * faster than traditional (one-pivot) Quicksort implementations.
     *
     * @param a the array to be sorted
     */
    public static void sort(byte[] a) {
        DualPivotQuicksort.sort(a);
    }

Quotes
------

> This is the first level of quoting.
>
> > This is nested blockquote.
>
> Back to the first level.


> A list within a blockquote:
>
> *	asterisk 1
> *	asterisk 2
> *	asterisk 3


> Formatting within a blockquote:
>
> ### header
> Link: [Example](http://example.com)



Html
-------

This is inline <span>html</html>.
And this is an html block.

<table>
  <tr>
    <th>Column 1</th>
    <th>Column 2</th>
  </tr>
  <tr>
    <td>Row 1 Cell 1</td>
    <td>Row 1 Cell 2</td>
  </tr>
  <tr>
    <td>Row 2 Cell 1</td>
    <td>Row 2 Cell 2</td>
  </tr>
</table>

Horizontal rules
----------------

---

___


***


Lists
-----

Unordered list:

*	asterisk 1
*	asterisk 2
*	asterisk 3


Ordered list:

1.	First
2.	Second
3.	Third


Mixed:

1. First
2. Second:
	* Fee
	* Fie
	* Foe
3. Third


Tables:

| Header 1 | Header 2 |
| -------- | -------- |
| Data 1   | Data 2   |