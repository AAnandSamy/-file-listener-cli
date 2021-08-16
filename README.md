# File listener

This components take HDFS path  from user and capture the file changes in timely fashion also support for on-demand too, like whenever user invoke the components it will check and the return file deta.

#### Download source
```
git clone ssh://git@bitbucket....git
```

- Recommended IDE (intellij/Scala IDE)

### Deployment
- Maven 3+
- JDK 1.8
- Scala

#### Run the component

 ```
java - jar listener-cli-<version>.jar <hdfs path>
 ``` 
Example
 ```
java - jar listener-cli-1.0.2-jar-with-dependencies.jar /data/ss/localglobalclusterpop/
 ```