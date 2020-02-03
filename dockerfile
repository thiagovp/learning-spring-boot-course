FROM java
MAINTAINER thiagovp
ENTRYPOINT ["/usr/bin/java", "-jar", "/usr/share/backend/learning-spring-boot-0.0.1-SNAPSHOT.jar"]

ADD target /usr/share/backend
ADD target/learning-spring-boot-0.0.1-SNAPSHOT.jar /usr/share/backend/learning-spring-boot-0.0.1-SNAPSHOT.jar