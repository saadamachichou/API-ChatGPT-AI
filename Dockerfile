FROM openjdk:17
EXPOSE 8080
ADD target/chatgpt.jar chatgpt.jar
ENTRYPOINT ["java","-jar","/chatgpt.jar"]