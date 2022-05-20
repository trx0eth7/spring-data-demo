FROM bellsoft/liberica-openjdk-alpine:11.0.10

COPY build/app.jar /opt/app/
COPY deployment/app/docker-entrypoint.sh /usr/local/bin/

RUN set -ex \
    && chmod +x /usr/local/bin/docker-entrypoint.sh

ENV JAVA_OPTS=""

ENTRYPOINT ["docker-entrypoint.sh"]

EXPOSE 8080

CMD ["exec java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005 $JAVA_OPTS -jar app.jar"]