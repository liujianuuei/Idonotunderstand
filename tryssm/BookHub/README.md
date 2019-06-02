# BookHub.com

BookHub is currently an incubation project.

## Setup

### How to setup IntelliJ IDEA project

#### IntelliJ IDEA

Create a Maven project, no need to select archetype. Fill pom.xml with dependencies and build plugins.

#### Tomcat

Go to IntelliJ IDEA | Run | Edit Configurations... | Templates | Tomcat Server | Local, Select local installed Tomcat at [Application server], Select local installed JDK at [JRE].

Go to IntelliJ IDEA | Run | Edit Configurations..., Click on [+] | Tomcat Server | Local, Input name for Tomcat server, others are populated by default.

#### Maven

Go to Maven home directory, create folder repository.

Go to IntelliJ IDEA | File | Settings | Maven, configure
- Maven home directory as {Maven_HOME}
- User settings file as  {Maven_HOME}\conf\settings.xml
- Local repository as  {Maven_HOME}\repository

#### Deploy

Use Maven Tomcat plugin to deploy war. Files need to change:

- {Maven_HOME}\conf\settings.xml
- {Tomcat_HOME}\conf\tomcat-users.xml
- {Project_HOME}\pom.xml
            
Go to  [How to deploy Maven based war file to Tomcat – Mkyong.com](https://www.mkyong.com/maven/how-to-deploy-maven-based-war-file-to-tomcat/), and {Project_HOME}\backup\deploy2tomcat for more details.

Commands:
- `mvn tomcat7:deploy`
- `mvn tomcat7:undeploy`
- `mvn tomcat7:redeploy`

### How to setup Eclipse project

#### Tomcat

#### Maven

#### Deploy