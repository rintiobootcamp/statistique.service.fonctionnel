FROM ibrahim/alpine
ADD target/sf_statistique.jar sf_statistique.jar
EXPOSE 9093
ENTRYPOINT ["java","-jar","sf_statistique.jar"]