# BookHub.com

BookHub is currently an incubation project.

## Setup

### How to setup IntelliJ IDEA project

#### IntelliJ IDEA

Create a Maven project, no need to select archetype, structures are almost set. Fill pom.xml with dependencies and build plugins.

#### Tomcat

Go to IntelliJ IDEA | Run | Edit Configurations... | Templates | Tomcat Server | Local, Select local Tomcat installation at [Application server], Select local installed JDK at [JRE].

Go to IntelliJ IDEA | Run | Edit Configurations..., Click on [+] | Tomcat Server | Local,

- Input BookHub for Name of Tomcat server
- Add Artifact in Deployment tab, change Application context to /BookHub

Others are populated by default. Reference [IntelliJ IDEA – Run / debug web application on Tomcat](https://www.mkyong.com/intellij/intellij-idea-run-debug-web-application-on-tomcat/) for more details.

#### Maven

Go to Maven home directory, create folder repository.

Go to IntelliJ IDEA | File | Settings | Maven, configure
- Maven home directory as {Maven_HOME}
- User settings file as  {Maven_HOME}\conf\settings.xml
- Local repository as  {Maven_HOME}\repository

Go to IntelliJ IDEA | Run | Edit Configurations..., create a Maven task with
- Name as BookHub
- Command line as `clean package -DskipTests`

#### Build & Deploy

You can build manually with `mvn clean package -DskipTests` in command line, and copy generated war to {Tomcat_HOME}\webapps.

You can also use IntelliJ IDEA to build. Run the BookHub Maven task created just now, and copy generated war to {Tomcat_HOME}\webapps.

Alternatively, you can use IntelliJ IDEA to deploy war to Tomcat, just click to run BookHub Tomcat server configured just now.

Alternatively you can use Maven Tomcat plugin to deploy war, which is recommended.

Files need to change:

- {Maven_HOME}\conf\settings.xml
- {Tomcat_HOME}\conf\tomcat-users.xml
- {Project_HOME}\pom.xml
            
Go to  [How to deploy Maven based war file to Tomcat – Mkyong.com](https://www.mkyong.com/maven/how-to-deploy-maven-based-war-file-to-tomcat/), and {Project_HOME}\backup\deploy2tomcat for more details.

Commands:
- `mvn tomcat7:deploy`
- `mvn tomcat7:undeploy`
- `mvn tomcat7:redeploy`

### How to setup Eclipse project

#### Eclipse - Java EE Edition

Create a Maven project, no need to select archetype, structures are all set. Fill pom.xml with dependencies and build plugins.

**Note**: IntelliJ IDEA will update configuration automatically once pom.xml changed, but for Eclipse, by default you have to right click on project, select Maven | Update Project to update configuration manually.

#### Tomcat

Go to Window | Show View | Servers, click on 'No servers are available. Click this link to create a new server...', Select local Tomcat installation and click Finish. In addition, you can configure the *deploy path* also.

#### Maven

Go to Window | Preferences | Maven | Installations, Add and select the local Maven installation.

Right click on project, Run As | Maven build..., configure

- Name: BookHub
- Base directory: ${project_loc:BookHub}
- Goals: `clean package -DskipTests`
- User settings: {Maven_HOME}\conf\settings.xml

**Note**:

In case it gives you error like

> [ERROR] No compiler is provided in this environment. Perhaps you are running on a JRE rather than a JDK?

This solution is force specify compiler executable to %JAVA_HOME%\bin\javac.exe. The maven-compiler-plugin configuration looks like:

```XML
<plugin>
  <artifactId>maven-compiler-plugin</artifactId>
  <version>3.8.0</version>
  <configuration>
    <fork>true</fork>
    <executable>%JAVA_HOME%\bin\javac.exe</executable>
  </configuration>
</plugin>
```

#### Build & Deploy

You can build manually with `mvn clean package -DskipTests` in command line, and copy generated war to {Tomcat_HOME}\webapps.

**Note**:

Eclipse generate web content under {Project_HOME}\WebContent in case you create Dynamic Web Project, so you have to let Maven know this:

```XML
<plugin>
  <artifactId>maven-war-plugin</artifactId>
  <version>3.2.2</version>
  <configuration>
    <warSourceDirectory>WebContent</warSourceDirectory> <!-- by default, src/main/webapp is picked -->
  </configuration>
</plugin>
```

You can also use Eclipse to build. Run the BookHub Maven task created just now, and copy the generated war to {Tomcat_HOME}\webapps.

Alternatively, you can use Eclipse to deploy war to Tomcat. Right click on project, select Run As | Run on Server, select configured Tomcat server and click Finish.

**Note**: Please check if Maven Dependencies are included into war, if not, right click on project, select Properties | Deployment Assembly, click 'Add' button, then select 'Java Build Path Entries', click Next and select Maven Dependencies, and click Finish.

Alternatively you can use Maven Tomcat plugin to deploy war, which is recommended.

Files need to change:

- {Maven_HOME}\conf\settings.xml
- {Tomcat_HOME}\conf\tomcat-users.xml
- {Project_HOME}\pom.xml
            
Go to  [How to deploy Maven based war file to Tomcat – Mkyong.com](https://www.mkyong.com/maven/how-to-deploy-maven-based-war-file-to-tomcat/), and {Project_HOME}\backup\deploy2tomcat for more details.

Commands:
- `mvn tomcat7:deploy`
- `mvn tomcat7:undeploy`
- `mvn tomcat7:redeploy`
