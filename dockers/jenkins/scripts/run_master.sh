#!/usr/bin/env bash
SCRIPT_PATH=$( cd "$(dirname "$0")" ; pwd -P )

pushd ${SCRIPT_PATH}/../mvp-jenkins-master
    docker-compose up -d
popd
