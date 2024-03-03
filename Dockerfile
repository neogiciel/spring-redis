FROM openjdk:17
VOLUME /tmp
EXPOSE 8086
ARG JAR_FILE=target/spring-search-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} spring-search.jar
ENTRYPOINT ["java","-jar","/spring-search.jar"]
