pipeline {
    agent any
    environment {
        baseDir = """${powershell(
            returnStdout: true,
            script: '(Get-Item -Path ".\").FullName'
        ).trim()}"""
    }
    
    stages {
        stage('Base directory') {
            steps {
                powershell 'echo "Base directory: ${baseDir}"'
            }
        }
        stage('Deploy ALB') {
            environment {
                planFile = 'alb-plan'
            }
            steps {
                powershell 'echo "Deploying ALB..."'
                powershell 'cd "${baseDir}/infrastructure/alb"'
                powershell 'terraform init'
                powershell 'terraform plan -var ingress_cidr=178.37.89.16/32 -out="${planFile}"'
                powershell 'terraform apply "${planFile}"'
                powershell 'echo "ALB deployed."'
            }
        }
        stage('Deploy Lambda Api') {
            steps {
                powershell 'echo "Deploying Lambda api..."'

                powrshell 'echo "Lambda api deployed."'
            }
        }
    }
}