FROM jenkins-base

# `/usr/share/jenkins/ref/` contains all reference configuration we want
# to set on a fresh new installation. Use it to bundle additional plugins
# or config file with your custom jenkins Docker image.
RUN mkdir -p /usr/share/jenkins/ref/init.groovy.d

COPY init.groovy /usr/share/jenkins/ref/init.groovy.d/tcp-slave-agent-port.groovy

# jenkins version being bundled in this docker image
ARG JENKINS_VERSION
ENV JENKINS_VERSION ${JENKINS_VERSION:-2.46.1}

# jenkins.war checksum, download will be validated using it
ARG JENKINS_SHA=1b65dc498ba7ab1f5cce64200b920a8716d90834

# Can be used to customize where jenkins.war get downloaded from
ARG JENKINS_URL=https://repo.jenkins-ci.org/public/org/jenkins-ci/main/jenkins-war/${JENKINS_VERSION}/jenkins-war-${JENKINS_VERSION}.war

# could use ADD but this one does not check Last-Modified header neither does it allow to control checksum 
# see https://github.com/docker/docker/issues/8331
RUN curl -fsSL ${JENKINS_URL} -o /usr/share/jenkins/jenkins.war \
  && echo "skip sha checking" #"${JENKINS_SHA}  /usr/share/jenkins/jenkins.war" | sha1sum -c -

ENV JENKINS_UC https://updates.jenkins.io
# ENV JENKINS_UC http://e8hub/artifactory/jenkins-remote
RUN chown -R "$JENKINS_USER" "$JENKINS_HOME" /usr/share/jenkins/ref

# for main web interface:
EXPOSE 8080
# will be used by attached slave agents:
EXPOSE 50000

ENV COPY_REFERENCE_FILE_LOG $JENKINS_HOME/copy_reference_file.log

COPY install-plugins.sh /usr/local/bin/install-plugins.sh
RUN chmod 777           /usr/local/bin/install-plugins.sh

COPY jenkins-support /usr/local/bin/jenkins-support
RUN chmod 777        /usr/local/bin/jenkins-support

COPY jenkins.sh /usr/local/bin/jenkins.sh
RUN chmod 777 	/usr/local/bin/jenkins.sh


USER "$JENKINS_USER"
ENV USER $JENKINS_USER

ENTRYPOINT ["/bin/tini", "--", "/usr/local/bin/jenkins.sh"]

