FROM jenkins-master

# ENV JENKINS_UC_DOWNLOAD http://e8hub/artifactory/jenkins-remote
ENV JENKINS_UC_DOWNLOAD https://updates.jenkins-ci.org/download

COPY jenkins_home/ /usr/share/jenkins/ref/
COPY init.groovy.d/ /usr/share/jenkins/ref/init.groovy.d/

RUN /bin/bash -c '/usr/local/bin/install-plugins.sh $(cat /usr/share/jenkins/ref/plugins_list/plugins_list-base.txt)'
RUN /bin/bash -c '/usr/local/bin/install-plugins.sh $(cat /usr/share/jenkins/ref/plugins_list/plugins_list-extra.txt)'
