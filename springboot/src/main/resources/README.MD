项目默认就是static/index.html
可以直接访问
http://localhost:8080/index.html

###### springCache的使用
1. 要加入spring的ioc容器
2. 要配置注解@EnableCaching

###### 配置监听器
1. 继承ApplicationListener。  
2. 在项目启动之前注册监听器。  
**监听器每次请求为什么进2次？**
