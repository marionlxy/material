## 指定端口并运行（删除旧包）
docker run -it --rm -p 9001:9002 dockerpracticesig/docker_practice
不删除旧包（必须指定为80端口暴露应用）
docker run -d -p 9001:80 dockerpracticesig/docker_practice:latest
