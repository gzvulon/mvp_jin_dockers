import jenkins.*
import jenkins.model.*
import hudson.*
import hudson.model.*

configureSecurity()
configureMarkup()
configureSystemMessage()

// --- set global environment variables --
Thread.start {
    sleep 1500 // wait for plugins will be installed
    println "--> setting global environment variables"

    def instance = Jenkins.getInstance()
    def globalNodeProperties = instance.getGlobalNodeProperties()
    def envVarsNodePropertyList = globalNodeProperties.getAll(hudson.slaves.EnvironmentVariablesNodeProperty.class)

    def newEnvVarsNodeProperty = null
    def envVars = null

    if ( envVarsNodePropertyList == null || envVarsNodePropertyList.size() == 0 ) {
        newEnvVarsNodeProperty = new hudson.slaves.EnvironmentVariablesNodeProperty();
        globalNodeProperties.add(newEnvVarsNodeProperty)
        envVars = newEnvVarsNodeProperty.getEnvVars()
    } else {
        envVars = envVarsNodePropertyList.get(0).getEnvVars()
    }

    // set default deploy env
    if (! envVars.get("DEPLOY_ENV") ){
        envVars.put("DEPLOY_ENV", 'dev')
        instance.save()
    }

    // set default jin branch for job dsl pipelines
    if (! envVars.get("DEFAULT_JIN_BRANCH") ){
        envVars.put("DEFAULT_JIN_BRANCH", 'master')
        instance.save()
    }

    // set JENKINS_SERVER for jobs
    if (! envVars.get("JENKINS_SERVER") ){
        envVars.put("JENKINS_SERVER", 'stage-jenkins')
        instance.save()
    }

    println "--> done: setting global environment variables"
}

private void configureSecurity() {
    Jenkins.getInstance().disableSecurity()
}

private void configureMarkup() {
    //configure HTML markup (used with job description and build history label)
    Jenkins.instance.setMarkupFormatter(new hudson.markup.RawHtmlMarkupFormatter(false))
}

private void configureSystemMessage() {
    Jenkins.getInstance().setSystemMessage('''
<center> 
    <a href="https://github.com/E8-Storage/jin_mvp">JIN_MVP</a> &nbsp
</center>''')
}

