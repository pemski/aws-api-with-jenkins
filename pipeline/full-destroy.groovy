pipeline {
    agent any
    
    stages {
        stage('Destroy Lambda Api') {
            steps {
                dir('./infrastructure/lambda'){
                    powershell 'echo "Destroying Lambda api..."'
                    powershell 'terraform init'
                    powershell 'terraform destroy -auto-approve'
                    powershell 'echo "Lambda api destroyed."'
                }
            }
        }
        stage('Destroy ALB') {
            steps {
                dir('./infrastructure/alb') {
                    powershell 'echo "Destroying ALB..."'
                    powershell 'terraform init'
                    powershell 'terraform destroy -var ingress_cidr=0 -auto-approve'
                    powershell 'echo "ALB destroyed."'
                }
            }
        }
    }
}