pipeline {
    agent any
    
    environment {
        baseDir = """${powershell(
            script: '(Get-Item -Path ".\").FullName'
        )}"""
    }
    
    stages {
        stage('Base directory') {
            steps {
                powershell 'echo \"Base directory: "${baseDir}"\"'
            }
        }
        stage('Deploy ALB') {
            environment {
                planFile = 'alb-plan'
            }
            steps {
                powershell 'cd "${baseDir}"/../infrastructure/alb'
                powershell 'terraform init'
                powershell 'terraform plan -var ingress_cidr=178.37.89.16/32 -out="${planFile}"'
                powershell 'terraform apply "${planFile}"'
            }
        }
        stage('Deploy Lambda Api') {
            steps {
                powershell 'echo "Placeholder for Lambda deploy"'
            }
        }
    }
}