FROM store/oracle/jdk:11

MAINTAINER udp@narola.email

VOLUME /tmp

COPY ./build/libs/spring-boot-cassandra-demo-0.0.1-SNAPSHOT.jar spring-boot-cassandra-demo-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-Dspring.data.cassandra.contact-points=cassandradb","-jar","/spring-boot-cassandra-demo-0.0.1-SNAPSHOT.jar"]
