FROM bellsoft/liberica-openjdk-alpine
ARG JAR
ENV JAR_ENV=${JAR:-real-chat-1.0.jar}
WORKDIR /real-chat
COPY ./target/${JAR} ./${JAR}
EXPOSE 3000
ENTRYPOINT java -Xms500m -Xmx1024m -Dspring.profiles.active=docker -jar ./${JAR_ENV}