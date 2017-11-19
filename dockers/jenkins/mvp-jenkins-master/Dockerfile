FROM jenkins-master:dev
# RUN wget http://0.0.0.0:8081/artifactory/jrd/plugins/ws-cleanup/0.32/ws-cleanup.hpi
# ENV JENKINS_UC_DOWNLOAD http://localhost:8081/artifactory/jrd
ENV JENKINS_UC_DOWNLOAD https://updates.jenkins-ci.org/download
COPY jenkins_home/ /usr/share/jenkins/ref/
COPY init.groovy.d/ /usr/share/jenkins/ref/init.groovy.d/

RUN /bin/bash -c '/usr/local/bin/install-plugins.sh $(cat /usr/share/jenkins/ref/plugins_list/plugins_list-base.txt)'
RUN /bin/bash -c '/usr/local/bin/install-plugins.sh $(cat /usr/share/jenkins/ref/plugins_list/plugins_list-extra.txt)'
