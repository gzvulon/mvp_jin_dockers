import hudson.model.*;
import jenkins.model.*;

import javaposse.jobdsl.dsl.DslScriptLoader
import javaposse.jobdsl.plugin.JenkinsJobManagement

Thread.start {
        sleep 1500 // wait for plugins will be installed
        println "--> setting root seed job"
        def env = System.getenv()
        def workspace = new File('.')
        def jobDslScript = new File("${env.JENKINS_HOME}/InitSeed.jobdsl.groovy")
        def jobManagement = new JenkinsJobManagement(System.out, [:], workspace)
        new DslScriptLoader(jobManagement).runScript(jobDslScript.text)
        Jenkins.instance.save()
        println "--> done root seed job"
}

private def buildJob(String jobName) {
    Logger.global.info("Building job '$jobName")
    def job = Jenkins.instance.getJob(jobName)
    Jenkins.instance.queue.schedule job, 0, new hudson.model.CauseAction(new hudson.model.Cause() {
        @Override
        String getShortDescription() {
            'Jenkins startup script'
        }
    })
}