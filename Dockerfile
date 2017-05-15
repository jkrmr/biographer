FROM java:8-alpine
MAINTAINER Your Name <you@example.com>

ADD target/uberjar/biographer.jar /biographer/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/biographer/app.jar"]
