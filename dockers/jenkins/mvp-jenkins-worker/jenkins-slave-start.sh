#!/bin/bash
set -x

# if `docker run` first argument start with `--` the user is passing launcher arguments
if [[ $# -lt 1 ]] || [[ "$1" == "--"* ]]; then
	sudo chown 1000:1000 -R "${JENKINS_HOME}"
	# sudo chown 1000:1000 -R /var/lib/mock
	sudo chown 1000:1000 -R "${JENKINS_HOME}/.config"
	DEFAULT_PARAMS=" -master http://${JENKINS_SLAVE_MASTER_SERVER}:8080 -name worker -executors 2 -labels swarm"
	SWARM_PARAMS=${JENKINS_HOME}/swarm_params

	test -e ${SWARM_PARAMS} || (echo  "${DEFAULT_PARAMS}" > ${SWARM_PARAMS})
	PARAMS="$(echo $(cat ${SWARM_PARAMS}))"

	java -jar /usr/local/bin/swarm-client-${SWARM_VERSION}.jar -disableSslVerification -fsroot $JENKINS_HOME $(echo $PARAMS)
fi

# As argument is not slave, assume user want to run his own process, for example a `bash` shell to explore this image
exec "$@"