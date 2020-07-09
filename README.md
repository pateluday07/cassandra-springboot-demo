# cassandra-springboot-demo
This project is to demonstrate how we can use **Cassandra** with **Spring Boot**. So here I have used two models **student** and **subject**, so here you can perform the **CRUD** operation on these models as well as you can get a list of **students** using **subjectId**, similarly you can get a list of **subjects** using **studentId**. I have also configured **Swagger** so you can easily access the APIs.

### Requirements To Run Applications
* JDK 8
* Cassandra 3.11.6

### Dependencies And Tools Used To Build Applications
* Git
* JDK 8
* Gradle 6.3
* Spring Boot Web
* spring-boot-starter-data-cassandra
* Cassandra 3.11.6
* Lombok
* Mapstruct
* Swagger
* IDE

##### To Run This Application
1. Set up **Cassandra Cluster** and create **student_demo** keyspace, you can choose either **SimpleStrategy** or **NetworkTopologyStrategy** for replication.

2. Go to the `/cassandra-springboot-demo/src/main/resources/application.properties` and update Cassandra properties as per your cluster set up.

3. Go to the `/cassandra-springboot-demo/` directory and 
open terminal

4. Execute the following command in the terminal

       gradlew bootrun
   
   or go to the `/cassandra-springboot-demo/src/main/java/com/demo/` directory and run the **SpringBootCassandraDemoApplication** class.
       
And now we can access the **Swagger** on this link: 
[Swagger] (http://localhost:8081/swagger-ui.html)

<p align="center">
  <b>Thank You</b>
</p>