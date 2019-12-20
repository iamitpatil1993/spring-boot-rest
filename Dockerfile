FROM hirokimatsumoto/alpine-openjdk-11

COPY target/DemoSpringBootRest-0.0.1-SNAPSHOT.jar DemoSpringBootRest-0.0.1-SNAPSHOT.jar

EXPOSE 8080

CMD ["java", "-jar", "/DemoSpringBootRest-0.0.1-SNAPSHOT.jar"]