Oracle数据库操作中，我们有时会用到锁表查询以及解锁和kill进程等操作，那么这些操作是怎么实现的呢？本文我们主要就介绍一下这部分内容。
(1)锁表查询的代码有以下的形式：
select count(*) from v$locked_object;
select * from v$locked_object;
(2)查看哪个表被锁
select b.owner,b.object_name,a.session_id,a.locked_mode from v$locked_object a,dba_objects b where b.object_id = a.object_id;
(3)查看是哪个session引起的
select b.username,b.sid,b.serial#,logon_time from v$locked_object a,v$session b where a.session_id = b.sid order by b.logon_time; 
(4)杀掉对应进程
执行命令：alter system kill session'1025,41';搜索
其中1025为sid,41为serial#.





