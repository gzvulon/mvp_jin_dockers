def jenkins_jobs_url = "git@github.com:ivan/jin_mvp"
def dsl_dir = 'ci/jobdsl/seed'
def dsl_pattern = '*.groovy'

folder('config')
job('config/InitSeed'){
    description('Custom Jobs seed jobs. This job is automagically generated.')
    label('master')
    parameters {
        stringParam('branch', 'master',
        			'jenkins lib branch for job descriptors')
    }
    steps {
        scm {
            git {
                remote {
                    url(jenkins_jobs_url)
                }
                branch('*/$branch')
            }
        }
        dsl {
            external("${dsl_dir}/${dsl_pattern}")
            removeAction('DISABLE')            
        }
    }
}
