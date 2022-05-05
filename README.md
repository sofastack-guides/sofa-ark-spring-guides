# sofa-ark-spring-guides

## 实验内容

- 通过 [SOFAArk](https://github.com/sofastack/sofa-ark) 提供的官方maven插件将一个 Spring Boot 应用启动成一个标准Ark包，即宿主机；
- 通过 [Telnet指令](https://www.sofastack.tech/projects/sofa-boot/sofa-ark-ark-telnet/) 动态安装另一个Spring Boot应用到宿主机上，同时在JVM中运行；

## 任务

### 1、任务准备

从 github 上将 demo 工程和动态模块工程 克隆到本地

```bash
git clone git@github.com:sofastack-guides/sofa-ark-spring-guides.git   // master biz

git clone git@github.com:sofastack-guides/spring-boot-ark-biz.git      // 动态模块
```

然后将工程 sofa-ark-spring-guides 导入到 IDEA 或者 eclipse 打开，该工程是使用 [Spring脚手架](https://start.spring.io/) 生成；

### 2、添加 SOFAArk 相关依赖

SOFAArk 当前最新版本号为 2.0.0

```xml
<!-- 这里添加动态模块相关依赖 -->
<properties>
    <sofa.ark.version>2.0.0</sofa.ark.version>
</properties>

<dependency>
    <groupId>com.alipay.sofa</groupId>
    <artifactId>sofa-ark-springboot-starter</artifactId>
    <version>${sofa.ark.version}</version>
</dependency>
<dependency>
    <groupId>com.alipay.sofa</groupId>
    <artifactId>sofa-ark-all</artifactId>
    <version>${sofa.ark.version}</version>
</dependency>
<dependency>
    <groupId>com.alipay.sofa</groupId>
    <artifactId>sofa-ark-api</artifactId>
    <version>${sofa.ark.version}</version>
</dependency>
```

### 3、修改打包方式

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <version>2.6.6</version>

            <configuration>
                <outputDirectory>target</outputDirectory>
                <classifier>ark-biz</classifier>
            </configuration>
            <executions>
                <execution>
                    <id>package</id>
                    <goals>
                        <goal>repackage</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```

### 4. 包结构

### 5、启动

#### 方式一：IDEA启动

本地启动需要加上启动参数

> -Dsofa.ark.embed.enable=true 

> -Dcom.alipay.sofa.ark.master.biz=sofa-ark-spring-guides

启动后会先启动 Ark 容器

![image.png](https://gw.alipayobjects.com/mdn/rms_10eaa2/afts/img/A*po9CSo832lQAAAAAAAAAAAAAARQnAQ)

有该日志出现代表 Ark 容器启动成功，此时该应用处于运行时，可进行动态模块的安装，卸载。

#### 方式二：命令行启动

Ark包是可执行Jar，可直接使用Java -jar的方式启动，先使用 mvn clean package -Dmaven.test.skip=true 进行打包，打包得到 sofa-ark-spring-guides-0.0.1-SNAPSHOT-ark-biz.jar，命令行启动

> java -jar -Dsofa.ark.embed.enable=true -Dcom.alipay.sofa.ark.master.biz=sofa-ark-spring-guides sofa-ark-spring-guides-0.0.1-SNAPSHOT-ark-biz.jar

![image.png](https://gw.alipayobjects.com/mdn/rms_10eaa2/afts/img/A*grssQq4_N04AAAAAAAAAAAAAARQnAQ)

### 6、Telnet指令

SOFAArk官方提供了本地运维模块小工具 [Telnet指令](https://www.sofastack.tech/projects/sofa-boot/sofa-ark-ark-telnet/)

> telnet localhost 1234

![image.png](https://gw.alipayobjects.com/mdn/rms_10eaa2/afts/img/A*sy1uSZVKT_YAAAAAAAAAAAAAARQnAQ)

> biz -a 

查看该应用中所有模块，当前只有name=sofa-ark-spring-guides，version=1.0.0，status=activated的宿主应用。

现在来尝试运行时动态安装新模块，模块应用使用了另一个Spring Boot应用打成的Ark包  spring-boot-ark-biz-0.0.1-SNAPSHOT-ark-biz.jar， 用于动态安装；

从 [spring-boot-ark-biz项目](https://github.com/sofastack-guides/spring-boot-ark-biz) 下载spring-boot-ark-biz源码（在任务准备阶段已下载好spring-boot-ark-biz），本地运行 mvn clean package -Dmaven.test.skip=true 打包，将在项目根目录 /target 下获得 spring-boot-ark-biz-0.0.1-SNAPSHOT-ark-biz.jar

```bash
## 连接 SOFAArk telnet
> telnet localhost 1234

## 安装新模块 spring-boot-ark-biz
sofa-ark>biz -i file:///Users/yuanyuan/yuanyuan/Code/spring-boot-ark-biz/target/spring-boot-ark-biz-0.0.1-SNAPSHOT-ark-biz.jar
Start to process install command now, pls wait and check.

## 查看安装的模块信息
sofa-ark>biz -a
sofa-ark-spring-guides:1.0.0:activated          // 宿主应用
spring-boot-ark-biz:0.0.1-SNAPSHOT:activated    // 动态安装的模块应用
biz count = 2
```

当前JVM中同时运行着两个Spring Boot应用

两个Spring Boot应用可以是不同的Spring Boot版本，由各自的ClassLoader进行加载；

如，宿主应用sofa-ark-spring-guides是Spring Boot 2.6.6，由org.springframework.boot.loader.LaunchedURLClassLoader加载

```bash
SofaArkSpringGuidesApplication start!
Spring Boot Version: 2.6.6
SofaArkSpringGuidesApplication classLoader: org.springframework.boot.loader.LaunchedURLClassLoader@366e2eef
```

![image.png](https://gw.alipayobjects.com/mdn/rms_10eaa2/afts/img/A*grssQq4_N04AAAAAAAAAAAAAARQnAQ)

和 动态安装的模块应用spring-boot-ark-biz是Spring Boot 2.5.0，由Ark框架提供的com.alipay.sofa.ark.container.service.classloader.BizClassLoader加载
```bash
SpringBootArkBizApplication start!
SpringBootArkBizApplication spring boot version: 2.5.0
SpringBootArkBizApplication classLoader: com.alipay.sofa.ark.container.service.classloader.BizClassLoader@52a1e30
```

![image.png](https://gw.alipayobjects.com/mdn/rms_10eaa2/afts/img/A*09ZnRKaPjcQAAAAAAAAAAAAAARQnAQ)


