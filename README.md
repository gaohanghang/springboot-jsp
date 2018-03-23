# 一、 Spring介绍

## 1.1、SpringBoot简介

在您第1次接触和学习Spring框架的时候，是否因为其繁杂的配置而退却了？在你第n次使用Spring框架的时候，是否觉得一堆反复黏贴的配置有一些厌烦？那么您就不妨来试试使用Spring Boot来让你更易上手，更简单快捷地构建Spring应用！

SpringBoot让我们的Spring应用变的更轻量化。比如：你可以仅仅依靠一个Java类来运行一个Spring引用。你也可以打包你的应用为jar并通过使用java -jar来运行你的Spring Web应用。

SpringBoot的主要优点：

为所有Spring开发者更快的入门

开箱即用，提供各种默认配置来简化项目配置

内嵌式容器简化Web项目

没有冗余代码生成和XML配置的要求

本章主要目标完成Spring Boot基础项目的构建，并且实现一个简单的Http请求处理，通过这个例子对Spring Boot有一个初步的了解，并体验其结构简单、开发快速的特性。

## 1.2、系统要求：

Java1.8及以上

SpringFramework 4.1.5及以上

**本文采用**Java 1.8.0_73**、**Spring Boot 1.3.2**调试通过。**

# 二、快速入门

## 2.1、创建一个Maven工程

**名为”springboot-helloworld” 类型为Jar工程项目**

![mark](http://omi0o6pp2.bkt.clouddn.com/blog/180323/cc039CH73k.png)

## 2.2、pom文件引入依赖

```java
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>1.3.3.RELEASE</version>
	</parent>
	<dependencies>
	  <!—SpringBoot web 组件 -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
	</dependencies>	
```

```
spring-boot-starter-parent作用
在pom.xml中引入spring-boot-start-parent,spring官方的解释叫什么stater poms,它可以提供dependency management,也就是说依赖管理，引入以后在申明其它dependency的时候就不需要version了，后面可以看到。
spring-boot-starter-web作用
springweb 核心组件
spring-boot-maven-plugin作用
 如果我们要直接Main启动spring，那么以下plugin必须要添加，否则是无法启动的。如果使用maven 的spring-boot:run的话是不需要此配置的。（我在测试的时候，如果不配置下面的plugin也是直接在Main中运行的。）
```

## 2.3、编写HelloWorld服务

创建package命名为com.itmayiedu.controller（根据实际情况修改）

创建HelloController类，内容如下

```java
@RestController
@EnableAutoConfiguration
public class HelloController {
	@RequestMapping("/hello")
	public String index() {
		return "Hello World";
	}	
public static void main(String[] args) {
		SpringApplication.run(HelloController.class, args);
	}
}
```

## 2.4、@RestController

**在上加上RestController 表示修饰该Controller所有的方法返回JSON格式,直接可以编写**
**Restful接口**

## 2.5、@EnableAutoConfiguration

注解:作用在于让 Spring Boot   根据应用所声明的依赖来对 Spring 框架进行自动配置

​        这个注解告诉Spring Boot根据添加的jar依赖猜测你想如何配置Spring。由于spring-boot-starter-web添加了Tomcat和Spring MVC，所以auto-configuration将假定你正在开发一个web应用并相应地对Spring进行设置。

## 2.6 SpringApplication.*run*(HelloController.class, args);

标识为启动类

## 2.7、SpringBoot启动方式1

Springboot默认端口号为8080

```java

@RestController
@EnableAutoConfiguration
public class HelloController {
	@RequestMapping("/hello")
	public String index() {
		return "Hello World";
	}	
public static void main(String[] args) {
		SpringApplication.run(HelloController.class, args);
	}
}
```

启动主程序，打开浏览器访问http://localhost:8080/index，可以看到页面输出Hello
World

## 2.8、SpringBoot启动方式2

@ComponentScan(basePackages = "com.itmayiedu.controller")---控制器扫包范围

```java
@ComponentScan(basePackages = "com.itmayiedu.controller")
@EnableAutoConfiguration
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
```

# 三、 Web开发

## 3.1、静态资源访问

在我们开发Web应用的时候，需要引用大量的js、css、图片等静态资源。

默认配置

SpringBoot默认提供静态资源目录位置需置于classpath下，目录名需符合如下规则：

/static

/public

/resources          

/META-INF/resources

举例：我们可以在src/main/resources/目录下创建static，在该位置放置一个图片文件。启动程序后，尝试访问http://localhost:8080/D.jpg。如能显示图片，配置成功。

## 3.2、全局捕获异常

@ExceptionHandler表示拦截异常

·        @ControllerAdvice 是 controller 的一个辅助类，最常用的就是作为全局异常处理的切面类

·        @ControllerAdvice 可以指定扫描范围

·        @ControllerAdvice 约定了几种可行的返回值，如果是直接返回 model 类的话，需要使用 @ResponseBody 进行 json 转换

o    			返回 String，表示跳到某个 view

o    			返回 modelAndView

o    			返回 model +@ResponseBody

```java
@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public Map<String, Object> exceptionHandler() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("errorCode", "101");
		map.put("errorMsg", "系統错误!");
		return map;
	}
}
```

## 3.3、渲染Web页面

渲染Web页面

在之前的示例中，我们都是通过@RestController来处理请求，所以返回的内容为json对象。那么如果需要渲染html页面的时候，要如何实现呢？

模板引擎

在动态HTML实现上Spring Boot依然可以完美胜任，并且提供了多种模板引擎的默认配置支持，所以在推荐的模板引擎下，我们可以很快的上手开发动态网站。

SpringBoot提供了默认配置的模板引擎主要有以下几种：

·        Thymeleaf

·        FreeMarker

·        Velocity

·        Groovy

·        Mustache

SpringBoot建议使用这些模板引擎，避免使用JSP，若一定要使用JSP将无法实现SpringBoot的多种特性，具体可见后文：支持JSP的配置

当你使用上述模板引擎中的任何一个，它们默认的模板配置路径为：src/main/resources/templates。当然也可以修改这个路径，具体如何修改，可在后续各模板引擎的配置属性中查询并修改。

## 3.4、[使用Freemarker模板引擎渲染web视图](http://blog.didispace.com/springbootweb/)

### 3.4.1、pom文件引入:

```java
<!-- 引入freeMarker的依赖包. -->
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-freemarker</artifactId>
</dependency>
```

### 3.4.2、后台代码

在src/main/resources/创建一个templates文件夹,后缀为*.ftl

```java
@RequestMapping("/index")
	public String index(Map<String, Object> map) {
	    map.put("name","美丽的天使...");
		return "index";
	}
```

### 3.4.3、前台代码

```html
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8" />
<title></title>
</head>
<body>
	  ${name}
</body> 
</html>
```

### 3.4.4、Freemarker其他用法

```heml
@RequestMapping("/freemarkerIndex")
	public String index(Map<String, Object> result) {
		result.put("name", "yushengjun");
		result.put("sex", "0");
		List<String> listResult = new ArrayList<String>();
		listResult.add("zhangsan");
		listResult.add("lisi");
		listResult.add("itmayiedu");
		result.put("listResult", listResult);
		return "index";
	}

<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8" />
<title>首页</title>
</head>
<body>
	  ${name}
<#if sex=="1">
            男
      <#elseif sex=="2">
            女
     <#else>
        其他      
	  
	  </#if>	  
	 <#list userlist as user>
	   ${user}
	 </#list>
</body> 
</html>
```

### 3.4.5、Freemarker配置

新建application.properties文件

```
########################################################
###FREEMARKER (FreeMarkerAutoConfiguration)
########################################################
spring.freemarker.allow-request-override=false
spring.freemarker.cache=true
spring.freemarker.check-template-location=true
spring.freemarker.charset=UTF-8
spring.freemarker.content-type=text/html
spring.freemarker.expose-request-attributes=false
spring.freemarker.expose-session-attributes=false
spring.freemarker.expose-spring-macro-helpers=false
#spring.freemarker.prefix=
#spring.freemarker.request-context-attribute=
#spring.freemarker.settings.*=
spring.freemarker.suffix=.ftl
spring.freemarker.template-loader-path=classpath:/templates/
#comma-separated list
#spring.freemarker.view-names= # whitelist of view names that can be resolved
```

## 3.5、使用JSP渲染Web视图

### 3.5.1、pom文件引入以下依赖

```java
<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>1.3.3.RELEASE</version>
	</parent>
	<dependencies>
		<!-- SpringBoot 核心组件 -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-tomcat</artifactId>
		</dependency>
		<dependency>
			<groupId>org.apache.tomcat.embed</groupId>
			<artifactId>tomcat-embed-jasper</artifactId>
		</dependency>
	</dependencies>
```

### 3.5.2、在application.properties创建以下配置

```
spring.mvc.view.prefix=/WEB-INF/jsp/
spring.mvc.view.suffix=.jsp
```

### 3.5.3、后台代码

```java
@Controller
public class IndexController {
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
}
```



