FROM maven AS build

COPY pom.xml .
RUN mvn -B dependency:go-offline package
COPY src src
RUN mvn -B package

FROM tomcat

COPY --from=build target/jakarta-ee-servlet-upload.war /usr/local/tomcat/webapps/ROOT
