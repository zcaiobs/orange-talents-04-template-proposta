FROM adoptopenjdk/openjdk11
MAINTAINER zcaiobs
COPY . /var/www
WORKDIR /var/www/target
ENTRYPOINT java -jar *.jar
EXPOSE 8080