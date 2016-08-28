# BookECommenrce

###this is a minor e-commerce website about book purchasing

##这是一个缩略版的图书购买平台(由于一些原因，只完成了部分功能)

***

主要运用到的技术：
 * **JSP+SERVLET**
 
     1.利用解决filter解决乱码问题

     2.利用装饰模式(**Decorator-Pattern**)来封装HttpServletRequest，来压缩通过ajax返回的json数据（gzip压缩）
     
     3.自己实现了一个非常简陋的依赖注入IoC框架（通过外置的代码将依赖注入）
     
     4.密码Hash(MD5)到后台
     
     5.上传和下载(管理员)
     
     6.购买页面仿造天猫的购物车页面
     
     。。。
     
 * **前台主页：纯手写html+css+jquery,后台主页：bootstrap+easyui**
 * **通过自己编写的爬虫爬取了豆瓣网站的##1800多的图书相关数据(包括相关图片，书名，简介等)**
 
***

###相关的网站请参考[这里](http://liuhongsen.cc:8080/BookECommerce/) (如果不可访问，说明已下线)
