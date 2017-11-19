FROM jenkins-base:dev

ARG SWARM_VERSION
ENV SWARM_VERSION ${SWARM_VERSION:-3.4}

# HACK: moved to base
RUN dnf install -y wget

RUN cd /usr/local/bin && \
    wget http://repo.jenkins-ci.org/releases/org/jenkins-ci/plugins/swarm-client/${SWARM_VERSION}/swarm-client-${SWARM_VERSION}.jar && \
    chmod 777 swarm-client-${SWARM_VERSION}.jar

USER "$JENKINS_USER"
ENV USER $JENKINS_USER
ENV SWARM_VERSION ${SWARM_VERSION:-3.4}


COPY jenkins-slave-start.sh /usr/local/bin/jenkins-slave-start.sh
RUN sudo chmod 777 /usr/local/bin/jenkins-slave-start.sh
ENTRYPOINT ["/bin/tini", "--", "/usr/local/bin/jenkins-slave-start.sh"]
