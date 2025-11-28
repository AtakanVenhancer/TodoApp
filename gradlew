#!/bin/sh

# Copyright 2015 the original authors.
# SPDX-License-Identifier: Apache-2.0

APP_BASE_NAME=${0##*/}
APP_HOME=$(cd "${0%/*}" > /dev/null && pwd)

DEFAULT_JVM_OPTS=""

CLASSPATH=$APP_HOME/gradle/wrapper/gradle-wrapper.jar

JAVACMD=${JAVA_HOME:+"$JAVA_HOME/bin/java"}
if [ -z "$JAVACMD" ]; then
  JAVACMD=$(command -v java)
fi
if [ -z "$JAVACMD" ]; then
  echo "ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH." 1>&2
  exit 1
fi

exec "$JAVACMD" $DEFAULT_JVM_OPTS \
  -classpath "$CLASSPATH" \
  org.gradle.wrapper.GradleWrapperMain "$@"
