# Pure REST API Servlet Template

This repository is my own implementation to build a modern and super lightweight REST APIs using only pure Java Servlet, 
target developers who want to deploy API quickly with limited machine resources  


#### Compatibility summaries:
* JDK 8
* Servlet 3.1
* WAR file compatible with Tomcat 8.5 only

#### Project dependencies:
* Gson
* Apache Commons Lang
* Apache Commons IO
* Log4j2
* SQL connector, Redis (user choices)

#### Packages information
* **controller**: Servlet classes that receive json requests and process responses
* **service**: Logical layer to process and validate requests
* **dao**: Data Access Object, traditional layer to work directly with SQL database
* **request**: Java classes representing the requests
* **response**: Java classes representing the responses
* **security**: Classes to filter incoming requests, for example: IP filtering, CORS filtering, etc.
* **exception**: Custom exceptions are defined here
* **ContextManager.java**: A file to initialize many variables, connections, etc. to use across the application
* **ResourceManager.java**: A place to allocate threading resources to execute long-term logic separately

#### Build and deploy
- This step is easy and obvious, just run:
`mvn clean package`
- A war file will be generated under _target_ folder
- Deploy the war file to desired Tomcat Server
