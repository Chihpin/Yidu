#YiDu Novel

使用框架：
-----------------------------------
Struts2+Spring3+Hibernet4

使用数据库：
-----------------------------------
Postgresql

特性：
-----------------------------------
1．缓存技术：默认缓存小说列表，搜索结果，小说内容介绍，大大提升访问速度。<br />
2．Rewrite技术：默认开启伪静态，对SEO更加有力！<br />
3．自由设置区块<br />
4．使用freemarker，让定制模板更加简单<br />
5．支持泛解析对应单本<br />
6．针对阅读页启用触发式静态HTML生成机制<br />

源码的使用方法：
-----------------------------------
本程序的是使用maven管理的，有部分jar包在maven内内有登录，所以需要手动安装下<br />
1．先把程序导入Eclipse（含有maven插件）<br />
2．执行launch目录下的install-IKAnalyzer.launch和install-qqsdk.launch<br />
    执行方法：右键 -》 Run As -》 maven<br />
3．执行runYidu-Novel.launch启动程序<br />

源码打包：
-----------------------------------
用maven的install就可以啦

官方论坛地址：
-----------------------------------
http://www.51yd.org/