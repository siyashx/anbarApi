FROM openjdk:17-jdk
EXPOSE 9494
ADD target/anbarapi-0.0.1-SNAPSHOT.jar anbarapi.jar
HEALTHCHECK --interval=30s --timeout=10s --retries=3 \
  CMD curl --fail http://localhost:9494/ || exit 1
ENTRYPOINT ["java", "-jar", "anbarapi.jar"]
