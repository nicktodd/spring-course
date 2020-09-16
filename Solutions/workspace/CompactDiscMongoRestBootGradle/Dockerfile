FROM openjdk:11
ADD build/libs/CompactDiscMongoRestBoot-0.0.1-SNAPSHOT.jar app.jar
RUN sh -c 'echo spring.data.mongodb.host=mymongo > application.properties'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]