pipeline {
    agent any
    
    stages {
        stage('Create Lambda package') {
            steps {
                powershell 'echo "Creating a zip package with Lambda sources..."'
                powershell './pack-lambda.ps1'
                powershell 'echo "Zip package for Lambda created."'
            }
        }
        stage('Deploy ALB') {
            environment {
                planFile = 'alb-plan'
            }
            steps {
                dir('./infrastructure/alb') {
                    powershell 'echo "Deploying ALB..."'
                    powershell 'terraform init'
                    powershell "terraform plan -var ingress_cidr=178.37.89.16/32 -out=${planFile}"
                    powershell "terraform apply ${planFile}"
                    powershell 'echo "ALB deployed."'
                }
            }
        }
        stage('Deploy Lambda Api') {
            environment {
                planFile = 'lambda-plan'
            }
            steps {
                dir('./infrastructure/lambda'){
                    powershell 'echo "Deploying Lambda api..."'
                    powershell 'terraform init'
                    powershell "terraform plan -out=${planFile}"
                    powershell "terraform apply ${planFile}"
                    powershell 'echo "Lambda api deployed."'
                }
            }
        }
    }
}