FROM java:17
COPY nikola-0.0.1-SNAPSHOT.jar /var/www/
WORKDIR /var/www/
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "nikola-0.0.1-SNAPSHOT.jar"]


