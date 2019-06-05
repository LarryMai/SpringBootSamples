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
     
* �I���i���j->�i���ﶵ�j->�i�]�m�j���}User Settings���
* �j����java.home��,�I���b�]�m�������A�t�m����jdk���w�˸��|
* �j����maven��, ��졧maven.executable.path�����A�t�mmaven�R�O�u�㪺�����|
* �j����maven��, ��졧java.configuration.maven.userSettings�����A�t�mmaven�[���t�m���]settings.xml�^���Ҧb���|

���e�ҥ� :
    "java.home":"C:\\Program Files\\Java\\jdk1.8.0_211",
    "maven.executable.path": "C:\\apache-maven-3.6.1-bin\bin\\mvn.cmd",
    "java.configuration.maven.userSettings": "C:\\apache-maven-3.6.1-bin\\conf\\settings.xml",
    "terminal.integrated.shell.windows": "C:\\Windows\\System32\\cmd.exe"


## Compile and Run

* cd DogService.mvc  
* mvn install  
* cd target  
* java -jar DogServiceMvc-x.x.x-SNAPSHOT.jar 