# SpringBootSamples
The project is to implement sample REST API servers by Java Spring boot.


## Installation

### install vscode

 * Language Support for Java(TM) by Red Hat
 * Java Extension Pack
 * Debugger for Java
 * Java Test Runner
 * Maven for Java
 * Spring Boot Support
 * Spring Initializr Java Support
 * Checkstyle for Java

### install JDK

  * download and install jdk

  * set JAVA_HOME and environment path for bin folder.
#### Windows

JAVA_HOME: C:\Program Files\Java\jdk1.8.0_211
PATH = "PATH;%JAVA_HOME\bin"
  

### install Maven

   * download and install maven

#### Windows

M2_HOME : C:\apache-maven-3.6.1-bin

PATH="PATH;%M2_HOME\bin";
 
### Set usersetting on vscode
     
* 點擊【文件】->【首選項】->【設置】打開User Settings文件
* 搜索“java.home”,點擊在設置中替換，配置本機jdk的安裝路徑
* 搜索“maven”, 找到“maven.executable.path”項，配置maven命令工具的文件路徑
* 搜索“maven”, 找到“java.configuration.maven.userSettings”項，配置maven加載配置文件（settings.xml）的所在路徑

內容所示 :
    "java.home":"C:\\Program Files\\Java\\jdk1.8.0_211",
    "maven.executable.path": "C:\\apache-maven-3.6.1-bin\bin\\mvn.cmd",
    "java.configuration.maven.userSettings": "C:\\apache-maven-3.6.1-bin\\conf\\settings.xml",
    "terminal.integrated.shell.windows": "C:\\Windows\\System32\\cmd.exe"


## Compile and Run

* cd DogService.mvc  
* mvn install  
* cd target  
* java -jar DogServiceMvc-x.x.x-SNAPSHOT.jar 