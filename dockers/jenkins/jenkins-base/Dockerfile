FROM fedora:23
# FROM docker.e8hub/fedora:23

RUN dnf update -y && dnf clean all
RUN dnf makecache fast
RUN dnf install -y python2 python2-dnf libselinux-python ansible \
	glib2-devel zlibrary zlib-devel \
	sudo tar gzip unzip xz which sshpass htop iputils \
	docker redhat-rpm-config python-pip

# { taken from jenkins docker to support build in docker
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

# # E8 build groups
# RUN /usr/sbin/usermod -a -G mock ${user} \
# 	&& /usr/sbin/usermod -a -G wheel ${user} \
# 	&& /usr/sbin/usermod -a -G docker ${user}
# # }

RUN dnf -y install java java-devel && dnf clean all
ENV JAVA_HOME /usr/lib/jvm/java-openjdk

ENV JENKINS_HOME /var/jenkins_home
ENV JENKINS_SLAVE_AGENT_PORT 50000

# jin e8auto settings
ENV JIN_USERNAME e8auto
ENV JIN_PASSWORD e8autopass
# user definition moved to e8-base-dev

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
