在oracle中sequence就是所谓的序列号(跟表无关)，每次取的时候它会自动增加，一般用在需要按序列号排序的地方。  
1、Create Sequence  
你首先要有CREATE SEQUENCE或者CREATE ANY SEQUENCE权限，  
CREATE SEQUENCE emp_sequence  
     INCREMENT BY 1   -- 每次加几个  
     START WITH 1     -- 从1开始计数  
     NOMAXVALUE       -- 不设置最大值  
     NOCYCLE          -- 一直累加，不循环  
     CACHE 10;  

一旦定义了emp_sequence，你就可以用CURRVAL，NEXTVAL  
CURRVAL=返回 sequence的当前值  
NEXTVAL=增加sequence的值，然后返回 sequence 值  
比如：  
   emp_sequence.CURRVAL  
   emp_sequence.NEXTVAL  

可以使用sequence的地方：  
- 不包含子查询、snapshot、VIEW的 SELECT 语句  
- INSERT语句的子查询中  
- NSERT语句的VALUES中  
- UPDATE 的 SET中    

可以看如下例子：  
INSERT INTO emp VALUES   
(empseq.nextval, 'LEWIS', 'CLERK',7902, SYSDATE, 1200, NULL, 20);  

SELECT empseq.currval      FROM DUAL;  

但是要注意的是：  
- 第一次NEXTVAL返回的是初始值；随后的NEXTVAL会自动增加你定义的INCREMENT BY值，然后返回增加后的值。CURRVAL 总是返回当前SEQUENCE的值，但是在第一次NEXTVAL初始化之后才能使用CURRVAL，否则会出错。一次NEXTVAL会增加一次SEQUENCE的值，所以如果你在同一个语句里面使用多个NEXTVAL，其值就是不一样的。明白？  

- 如果指定CACHE值，ORACLE就可以预先在内存里面放置一些sequence，这样存取的快些。cache里面的取完后，oracle自动再取一组到cache。 使用cache或许会跳号， 比如数据库突然不正常down掉（shutdown abort),cache中的sequence就会丢失. 所以可以在create sequence的时候用nocache防止这种情况。  

2、Alter Sequence  
你或者是该sequence的owner，或者有ALTER ANY SEQUENCE 权限才能改动sequence. 可以alter除start至以外的所有sequence参数.如果想要改变start值，必须 drop   sequence 再 re-create .  
Alter sequence 的例子  
ALTER SEQUENCE emp_sequence  
     INCREMENT BY 10  
     MAXVALUE 10000  
     CYCLE     -- 到10000后从头开始  
     NOCACHE ;  

影响Sequence的初始化参数：  
SEQUENCE_CACHE_ENTRIES =设置能同时被cache的sequence数目。   

可以很简单的Drop Sequence  
DROP SEQUENCE order_seq;