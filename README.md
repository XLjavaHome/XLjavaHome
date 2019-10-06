我的个人项目

## 项目需要
* JDK 1.8及以上
* Maven 管理jar包

###### 调整Integer的缓存
在程序启动的时候加上-ea -XX:AutoBoxCacheMax=20000  
20000代表缓存Integer的大小,默认-128至127之间的整数
