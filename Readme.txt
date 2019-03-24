This application is done based on this example:
https://www.javacodegeeks.com/2018/03/build-rest-web-service-using-spring-boot.html



Spring Boot application can be created using several methods. 
1. The popular one is using Spring Boot Initializr website. 
2. Using Maven archetype selection.

Using maven archetype selection can be again done two ways:
1. Using command line maven command
2. Using Eclipse

This project I have used Eclipse to create Web project first, then added Spring boot dependencies in the pom.xml.
Steps are:
================================================================================================================
1. Open eclipse, then select File -> New -> Maven Project.
2. In the next screen, select the workspace of the project and keep the other options as default then click “Next”:
3. In the following screen, select “maven-archetype-webapp” then click “Next”:
4. Put proper GroupId and ArtifactId and click finish to create the project.
5. It might give error in index.jsp. The superclass "javax.servlet.http.HttpServlet" was not found on the Java Build Path    index.jsp
6. Then just add the following dependency to pom.xml:
	<dependency>
       <groupId>javax.servlet</groupId>
       <artifactId>javax.servlet-api</artifactId>
       <version>3.1.0</version>
	</dependency>
	
7. In order to compile your project with Java 8, add the following property to pom.xml:
	<properties>
       <maven.compiler.source>1.8</maven.compiler.source>
          <maven.compiler.target>1.8</maven.compiler.target>
	</properties>
	then right click project -> Maven -> Update Project
	
8. Finally deploy the web application on Tomcat, if you haven’t setup Tomcat in your eclipse, then follow this guide.
	After the deployment, you would be able to access index.jsp through the following url:
	localhost:<PORT_NUMBER>/SimpleWebProject/index.jsp
	

Let us convert this web application to a Spring boot application:
===================================================================================================================
After creating the web project, the first step is to configure Spring Boot inside pom.xml, so we add the following as a parent dependency:

<parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>1.5.10.RELEASE</version>
</parent>
Spring Boot exposes a starter dependency called spring-boot-starter-web which automatically imports all the required jars needed to develop and expose REST controllers. So we add it as a dependency:

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
In this tutorial, we use the embedded tomcat provided by Spring Boot, so we’re gonna build our application as a runnable jar file by setting the packaging attribute as jar:

<packaging>jar</packaging>

The final configuration step is add the Spring Boot plugin:

<build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
</build>
Literally, that’s all we need to start developing our REST controllers.



Build, Deploy and Run the application:
=================================================================================================
Spring boot comes with embedded tomcat server to run the application. The packaging should be jar to run application in
the embedded tomcat. 
Use 
mvn clean
then
mvn install 
to build the application. The jar will be created in target folder.
Go to the target folder using command prompt/terminal.
use the below command:
java -jar <snapshot_filename.jar>

This will run the application in 8080 port.
Api can be tested using any rest client.


Remember, Spring Boot applications can also be deployed as a WAR in webserver. Will see that in next application.



Configure and deploy the application in External Tomcat as a WAR
=================================================================================================
1. Add following in pom.xml
	<dependency>
	    <groupId>org.springframework.boot</groupId>
	    <artifactId>spring-boot-starter-tomcat</artifactId>
	    <scope>provided</scope>
	</dependency>

2. Change packaging from jar to war.
	<packaging>war</packaging>
	
3. Change the configuration class like below:

	public class Application extends SpringBootServletInitializer{
  
		@Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        return application.sources(Application.class);
	    }
		
	    public static void main(String[] args) {
	        SpringApplication.run(Application.class, args);
	    }
	}
	
4. Do mvn install to get the war which can be deployed in any servlet container.
