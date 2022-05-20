#!/bin/sh
set -e

if [ "$1" = 'app' ]; then

  # shellcheck disable=SC2093,SC2046
  exec java $(if [ ! -z "$JVM_ARGS" ]; then echo "$JVM_ARGS"; fi) \
            -Dapp.home=${APP_HOME:-/opt/app-home} \
            $(if [ ! -z "$LOGBACK_CONFIGURATION_FILE" ]; then echo "-Dlogback.configurationFile=$LOGBACK_CONFIGURATION_FILE"; fi) \
            -jar /opt/app/app.jar
            /

  exit 0
fi

exec "$@"
