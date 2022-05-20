FROM bellsoft/liberica-openjdk-alpine:11.0.10

COPY build/app.jar /opt/app/
COPY deployment/app/docker-entrypoint.sh /usr/local/bin/

RUN set -ex \
    && chmod +x /usr/local/bin/docker-entrypoint.sh

ENTRYPOINT ["docker-entrypoint.sh"]

EXPOSE 8080

CMD ["app"]
