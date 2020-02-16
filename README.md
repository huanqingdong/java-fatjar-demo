## 打可执行jar

使用`maven-assembly-plugin`插件，将maven项目打包成可执行jar

### 1.插件引入
**pom.xml**中添加如下内容
```xml
    <build>
        <finalName>${project.artifactId}</finalName>
        <plugins>
            <plugin>
                <!-- NOTE: 不需要设置groupId,因为默认为org.apache.maven.plugins -->
                <artifactId>maven-assembly-plugin</artifactId>
                <version>3.2.0</version>
                <configuration>
                    <descriptorRefs>
                        <descriptorRef>jar-with-dependencies</descriptorRef>
                    </descriptorRefs>
                    <!-- 指定jar包名称，需appendAssemblyId和finalName配合使用 -->
                    <appendAssemblyId>false</appendAssemblyId>
                    <finalName>${project.artifactId}-full</finalName>
                    <archive>
                        <manifest>
                            <!-- 设置主类-->
                            <mainClass>tech.nosql.MainTask</mainClass>
                            <!-- 将版本信息写入MANIFEST.MF -->
                            <addDefaultImplementationEntries>true</addDefaultImplementationEntries>
                        </manifest>
                    </archive>
                </configuration>
                <executions>
                    <execution>
                        <!-- this is used for inheritance merges -->
                        <id>make-assembly</id>
                        <!-- 将assembly绑定到package阶段 -->
                        <phase>package</phase>
                        <goals>
                            <goal>single</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
```

### 2.打包
执行以下命令进行打包
```bash
mvn clean package 
```
执行成功后会生产/target/java-fatjar-demo-full.jar

### 运行jar
```bash
# 在target目录下执行
java -jar java-fatjar-demo-full.jar
```
输出
```md
配置文件中sleep.millis = 1000
{"age":6,"name":"u_0"}
{"age":23,"name":"u_1"}
{"age":95,"name":"u_2"}
{"age":35,"name":"u_3"}
....
```




