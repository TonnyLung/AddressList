# 通讯录

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;这是一个简易的通讯录小项目，可以添加联系人，查看联系人，对联系人进行修改删除，批删除，实现了分页功能。

项目使用了jsp + servlet + jdbc 技术, 采用了MVC的设计思想，即通过 V -> C -> M -> C -> V 来实现对客户端请求的处理与响应。

**主页图：** 
  
![image](http://note.youdao.com/yws/api/personal/file/4DB762D7904144559C18CA893E48C050?method=download&shareKey=12f5917af50dec013daf7288c816415f)


**添加联系人：**

![image](http://note.youdao.com/yws/api/personal/file/6AEBF73051994270A37224E4E0BDAC55?method=download&shareKey=aee74bd1f2520c9d51d49a0e2e427352)

 
**显示联系人：**

![image](http://note.youdao.com/yws/api/personal/file/B398B71B0E234F8FA5BAE5F6D300C3D9?method=download&shareKey=e9a23b9acc0bc2d10e484f1712dc774d)

**修改联系人：**

![image](http://note.youdao.com/yws/api/personal/file/1D151CC0F15D41B29403EE1263BCCA1F?method=download&shareKey=6d79f7ca481052783d3270aa04cdc94d)


### 项目结构介绍：

下面是src中主要代码的包及其目录下的类展示图  

![image](http://note.youdao.com/yws/api/personal/file/B3A33FD8890E40088645936282767B83?method=download&shareKey=bda0d9e6901683f7fbd1026c4213812c)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;视图（view）客户端提供请求输入，请求提交后，视图会将请求交给ContactController控制器类，而ContactContoller类对请求中的模型数据不做任何处理，而是通request.getParameter("action")；来获得"action"参数值，通过不同的action参数值来判断将请求交给ContactModel类中的哪个方法来处理请求。servlet类之间的请求转发是通过request.getRequestDispatcher(" “).forward(request, response);来实现的。

![image](http://note.youdao.com/yws/api/personal/file/C71A4CFB6C9946CABC2F38DCB25C9D57?method=download&shareKey=a3efd91629f4c277f49dbbe467f53838)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ContactModel类的任务是获得请求中的数据，并做相应的处理后交给ContactDAOImpl类来对接数据库从而实现对数据的处理。而在业务处理的过程中有用到一些其他util类来帮助处理其中的某些业务。比如BeanHandler类是处理实体类Contact与数据库中的字段间的映射; ContactUtil类是用来封装对数据库的CRUD。   
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;模型（model）处理完业务后会将请求与数据转发给处理器类ContactContoller，处理器类也是通过模型转发的action参数值来判断将模型与请求交给哪个视图（view）来渲染输出。
DataSource类封装了数据源。

### 总结  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;通过这个小项目学到了mvc框架的主要思想，进一步加强了对servlet，jsp等知识的了解与实际应用。通过这个底层框架也为学习struts,springmvc等框架打下了基础，学习这些框架时能够自然的想到他们是如何封装一个功能以及其底层代码是如何实现的。   
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;本次项目也是用的原生的jdbc对数据进行操作，虽然封装了数据源，但是数据源没有通过连接池来实现，所以每次对数据库操作都需要连接数据库，效率有所降低，下次可能会自己去实现一个连接池。  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;最后，这个项目功能虽然比较简单，但是主要核心还是在于搭建这个原始的mvc框架，功能实现是其次，所以还是可以学到很多东西，后续希望可以再添加一些功能，当然也主要是去根据学习的新东西来做一些改进或者增新。比如缓存，并发等。  
