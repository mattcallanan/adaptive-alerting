# Copyright 2018 Expedia Group, Inc.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

FROM openjdk:8-jre
MAINTAINER Adaptive Alerting <adaptive-alerting@expedia.com>

ENV APP_NAME adaptive-alerting-modelservice
ENV APP_HOME /app/bin
ENV JMXTRANS_AGENT jmxtrans-agent-1.2.6

RUN mkdir -p ${APP_HOME}

COPY target/adaptive-alerting-modelservice-1.0.0-SNAPSHOT.jar ${APP_HOME}/adaptive-alerting-modelservice.jar
COPY docker/start-app.sh ${APP_HOME}/
COPY docker/jmxtrans-agent.xml ${APP_HOME}/

ADD https://github.com/jmxtrans/jmxtrans-agent/releases/download/${JMXTRANS_AGENT}/${JMXTRANS_AGENT}.jar ${APP_HOME}/

WORKDIR ${APP_HOME}

ENTRYPOINT ["./start-app.sh"]
