# Fase 1: Compilación
FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

# Fase 2: Ejecución
FROM tomcat:9-jdk17-openjdk-slim
# Borramos el contenido por defecto de Tomcat
RUN rm -rf /usr/local/tomcat/webapps/*
# Copiamos tu archivo war generado a la carpeta webapps de Tomcat
COPY --from=build /target/*.war /usr/local/tomcat/webapps/ROOT.war
EXPOSE 8080
CMD ["catalina.sh", "run"]
