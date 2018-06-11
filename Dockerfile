FROM ibrahim/alpine
ADD target/sf_statistique.jar ws_sf_statistique_sf.jar
EXPOSE 8087
ENTRYPOINT ["java","-jar","sf_statistique.jar"]