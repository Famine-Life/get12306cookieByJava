"# get12306cookieByJava" 

# 目标

登录12306，一键启动抢票程序  (windows未做好，没有配置windows环境，无法测试==，目前windows只能做到，点击油猴生成的按钮，
然后自动获取tk值到自己的项目配置文件下)

## 环境

 - java8
 - Springboot
 - lombok插件 （如果不想装请把`log.info、@Slf4j`日志相关字样注释掉），之后优化功能的实体类为照顾多数人，就不用它了。

## 使用方法：

0. 请确保该接收程序能跑在https的域名下！！ 文字最后，我会教授怎么搞到https~

1. 首先安装发送cookie的油猴脚本 
    一键安装地址：[地址](https://greasyfork.org/zh-CN/scripts/419934-%E8%8E%B7%E5%8F%9612306cookie%E5%80%BC)

2. 拉取当前仓库的代码，修改：

      将文件 `application.properties` 中

    ```
     # linux设为1，windows设为0
     myconfig.isLinux = 0
     # windows TickerConfig.py 文件路径 测试用
     myconfig.tk_path = E:\\12306\\TickerConfig.py
   
     # 修改为自己的启动程序。
     myconfig.shell_command = cd /liantao/12306 && docker-compose up --build -d
    ```
    修改为自己抢票程序的 `TickerConfig.py`的路径，注意，windows和linux是不一样的.

3. 启动当前Springboot 程序，并暴露端口到https下

4. 在油猴脚本修改发送地址为该**接收程序的https地址~**，，点击油猴脚本生成的 `发送到服务器按钮` 即可生成获取了tk等值的配置文件。
然后程序接收到最新的tk值，并且启动 shell 命令，启动docker抢票程序。


### 服务器没有域名如何解决https？

内网穿透工具: [ngrok](https://ngrok.com/)

下载然后运行(自己选端口)：
```
./ngrok http 8008
```

