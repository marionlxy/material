# 1. 查找相应进程
ps -aux|grep npm|grep -v grep| awk '{print $2}'