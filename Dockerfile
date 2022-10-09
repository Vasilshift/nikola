FROM java:11
COPY /target/nikola-0.0.1-SNAPSHOT.jar /var/www/
WORKDIR /var/www/
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "nikola-0.0.1-SNAPSHOT.jar"]


