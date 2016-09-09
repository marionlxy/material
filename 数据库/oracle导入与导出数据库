
oracle导出与导入数据库命令
1、执行环境：可以在SQLPLUS.EXE或者DOS（命令行）中执行。

2、EXP.EXE与IMP.EXE文件被用来执行导入导出

例子：

1 将数据库TEST完全导出,用户名system 密码manager 导出到D:\daochu.dmp中
  exp system/manager@TEST file=d:\daochu.dmp full=y
2 将数据库中system用户与sys用户的表导出
  exp system/manager@TEST file=d:\daochu.dmp owner=(system,sys)
3 将数据库中的表table1 、table2导出
  exp system/manager@TEST file=d:\daochu.dmp tables=(table1,table2) 

导出
--exp 用户名/密码@SID file=f:\xx.dmp owner=用户名
exp SLSADMIN_HUNAN/SLSADMIN_HUNAN@ORCL file=D:\orcle20160725.dmp owner=SLSADMIN_HUNAN
导入
imp SLSADMIN_HUNAN/SLSADMIN_HUNAN@ORCL file=D:\orcle0725.dmp pull=Y
imp bi/bi@orcl123 file=D:\orcle.dmp owner=bi

只查询名字的话用如下语句：
select tablespace_name from dba_tablespaces;
　　表空间含义：
　　表空间是数据库的逻辑划分，一个表空间只能属于一个数据库。所有的数据库对象都存放在指定的表空间中。但主要存放的是表， 所以称作表空间。
　　Oracle数据库中至少存在一个表空间，即SYSTEM的表空间。


imp SLSADMIN_SHANDONG/SLSADMIN_SHANDONG@ORCL file=D:\orcle.dmp owner=SLSADMIN_SHANDONG