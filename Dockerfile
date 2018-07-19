FROM openjdk:8-jdk-alpine
ADD target/sf_statistique.jar sf_statistique.jar
EXPOSE 6093
ENTRYPOINT ["java","-jar","sf_statistique.jar"]