FROM fedora:23
# FROM docker.jinhub/fedora:23

RUN dnf install -y python2 python2-dnf python-pip libselinux-python \
	sudo tar gzip unzip xz which sshpass iputils \
	ansible

# RUN dnf update -y && dnf clean all
# RUN dnf makecache fast

ARG user=jenkins
ARG group=jenkins
ARG uid=1000
ARG gid=1000

ENV JENKINS_USER ${user}

# Jenkins home directory is a volume, so configuration and build history
# can be persisted and survive image upgrades
VOLUME /var/jenkins_home
# Jenkins is run with user `jenkins`, uid = 1000
# If you bind mount a volume from the host or a data container,
# ensure you use the same uid
RUN groupadd -g ${gid} ${group} \
    && useradd -d /var/jenkins_home -u ${uid} -g ${gid} -m -s /bin/bash ${user}
RUN echo "jenkins ALL=NOPASSWD: ALL" >> /etc/sudoers
RUN chown -R jenkins:jenkins /var/jenkins_home

# # JIN build groups
# RUN /usr/sbin/usermod -a -G mock ${user} \
# 	&& /usr/sbin/usermod -a -G wheel ${user} \
# 	&& /usr/sbin/usermod -a -G docker ${user}
# # }

RUN dnf -y install java java-devel && dnf clean all
ENV JAVA_HOME /usr/lib/jvm/java-openjdk

ENV JENKINS_HOME /var/jenkins_home
ENV JENKINS_SLAVE_AGENT_PORT 50000

# jin jinauto settings
ENV JIN_USERNAME jinauto
ENV JIN_PASSWORD jinauto!pass

# Create the folders and volume mount points
RUN mkdir -p /var/log/jenkins
RUN chown -R jenkins:jenkins /var/log/jenkins /var/jenkins_home

ENV TINI_VERSION 0.13.2
ENV TINI_SHA afbf8de8a63ce8e4f18cb3f34dfdbbd354af68a1

# Use tini as subreaper in Docker container to adopt zombie processes 
RUN curl -fsSL https://github.com/krallin/tini/releases/download/v${TINI_VERSION}/tini-static-amd64 -o /bin/tini \
  && chmod +x /bin/tini \
  && echo "$TINI_SHA  /bin/tini" | sha1sum -c -

COPY resolv.conf /etc/resolv.conf

RUN dnf install -y glib2-devel zlibrary zlib-devel 
RUN dnf install -y docker redhat-rpm-config
RUN dnf install -y wget curl git
