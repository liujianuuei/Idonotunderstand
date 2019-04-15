# Try Spring Boot with Groovy

0. Install Spring Boot CLI.
1. Create Groovy app `app.groovy`.
2. To run the application, execute `{Spring-Boot-CLI_HOME}\bin\spring run src\main\groovy\app.groovy` under project root.
3. To access the application, type `http://localhost:8090` in browser.

**Note**: To avoid port collision, you can add `-Dserver.port=8090` to `spring.bat`, reference `spring.bat.sample` for more details. No need to build the Groovy file, this is different from Java files.