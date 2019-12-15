pipelineJob('aws_guid_api/aws_guid_api_destroy') {
    definition {
        description('Destroys the guid api resources in AWS.')
        parameters{
            stringParam('BRANCH', 'master', 'Name of the repository branch to run sources from.')
        }
        cpsScm {
            scm {
                git {
                    remote {
                        url('https://github.com/pemski/aws-api-with-jenkins')
                        branch('refs/heads/${BRANCH}')
                    }
                }
                lightweight(false)
                scriptPath('pipeline/full_destroy.groovy')
            }
        }
    }
}