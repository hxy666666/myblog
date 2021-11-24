# myblog-mybaits
个人博客系统（SpringBoot+Mybatis）

**使用该源码希望能够注明源码出处，并禁止商用，谢谢！**

### 一、技术栈
#### 1.前端
- JS框架：JQuery
- UI框架：[Semantic UI](https://semantic-ui.com/)
- Markdown编辑器：[editor](https://pandao.github.io/editor.md/)
- 代码高亮：[代码高亮 prism](https://github.com/PrismJS/prism)
- 动画效果：[动画 animate.css](https://daneden.github.io/animate.css/)
- 文章目录：[目录生成 Tocbot](https://tscanlin.github.io/tocbot/)

#### 2.后端

- 运行环境：idea 2019.1

- 核心框架：SpringBoot 2.5.6
- 项目构建：jdk1.8、Maven 3.6.1
- 持久层框架：MyBatis
- 日志处理：logback
- 模板引擎：Thymeleaf
- 分页插件：PageHelper
- 文档交互：Swagger
- 密码加密：MD5加密
- 其它插件：lombok

#### 3.数据库

- MySQL 8.0.27

#### 4.数据库可视化工具

- SQLyog 11.2.5


### 二、功能需求
**由于是个人博客，所以没有做用户权限管理，简单区分了普通用户和管理员用户**

#### 1.普通用户

- 查看博客首页：博客列表、博客分类Top、博客标签Top、最新博客推荐
- 快速搜索博客：导航栏右边搜索框根据关键字进行搜索

- 查看博客信息：文章标题、文章内容、发布时间、访问量以及评论等信息
- 查看分类文章：分类列表、分类文章信息
- 查看博客标签：标签列表、标签博客信息
- 查看博客归档：查看所有博客发布时间

#### 2.管理员用户
- 拥有普通用户所有功能权限

- swagger页面：打开谷歌浏览器访问“localhost:8080/swagger-ui.html”

- 登录：打开谷歌浏览器访问“localhost:8080/admin”，即可跳转登录页面

  ```java
  //账号：admin
  //密码：123456
  ```

- 博客管理：查询博客列表、新增博客、编辑博客、删除博客、搜索博客

- 分类管理：查询分类列表、新增分类、编辑分类、删除分类

- 标签管理：查询标签列表、新增标签、编辑标签、删除标签

### 四、数据库设计
> 数据表是由jpa框架自动生成的，在使用mybatis为持久层的时候就沿用了jpa生成的数据库
