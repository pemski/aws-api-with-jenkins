pipelineJob('aws_guid_api/full_deployment') {
    definition {
        description('Deploys the guid api resources in AWS.')
        parameters {
            stringParam('BRANCH', 'master', 'Name of the repository branch to run sources from.')
            stringParam('ingress_cidr', '', 'CIDR value to be provided to allowed incoming traffic to ALB security group')
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
                scriptPath('pipeline/full_deploy.groovy')
            }
        }
    }
}