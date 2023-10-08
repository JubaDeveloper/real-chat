FROM bellsoft/liberica-openjdk-alpine
ARG JAR
ENV JAR_ENV=${JAR}
WORKDIR /real-chat
COPY ./target/${JAR} ./${JAR}
EXPOSE 3000
ENTRYPOINT java -Xms500m -Xmx1024m -jar ./${JAR_ENV}