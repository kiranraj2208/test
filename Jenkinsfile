podTemplate {
    node('mypod') {
        stage('Initialize') {
            withKubeConfig([credentialsId: 'kubernetes-config', serverUrl: 'https://kubernetes.default']){
                    sh '/usr/bin/curl -LO "https://storage.googleapis.com/kubernetes-release/release/$(curl -s https://storage.googleapis.com/kubernetes-release/release/stable.txt)/bin/linux/amd64/kubectl"'
                    sh 'chmod u+x ./kubectl'
            }
        }
        stage('Clone repository') {
            container('git') {
                sh 'git clone -b main https://github.com/kiranraj2208/test.git'
            }
        }
        stage('Build') {
            container('maven') {
                dir('test/') {
                    sh 'mvn clean install'
                }
            }
        }

        stage('Sonarqube scan') {
            container('maven') {
                dir('test/'){
                    withSonarQubeEnv('kube-sonarqube') {
                        sh 'mvn clean verify sonar:sonar'
                    }
                }
            }
        }
        stage("Quality gate") {
            // check if sonarqube scan passes the qualityGate
            timeout(time: 5, unit: 'MINUTES') {
                def qualityGate = waitForQualityGate()
                if(qualityGate.status != 'OK') {
                    error "Quality gate failed with status: ${qualityGate.status}}"
                } else {
                    echo 'Quality gate passed'
                }
            }
        }
        stage('Build docker image') {
            container('docker') {
                // example to show you can run docker commands when you mount the socket
                dir('test/') {
                    sh 'echo "building docker image"'
                    sh 'docker build -t test-image .'
                }
            }
        }
        stage('Deploy to kubernetes') {
            withKubeConfig([credentialsId: 'kubernetes-config', serverUrl: 'https://kubernetes.default']){
                    // sh '/usr/bin/curl -LO "https://storage.googleapis.com/kubernetes-release/release/$(curl -s https://storage.googleapis.com/kubernetes-release/release/stable.txt)/bin/linux/amd64/kubectl"'
                    // sh 'chmod u+x ./kubectl'
                    sh 'echo "deploying the code to kubernetes"'
                    sh './kubectl apply -f test/deploy/deployment.yml -n test'
            }
        }
        stage('Start the service') {
            withKubeConfig([credentialsId: 'kubernetes-config', serverUrl: 'https://kubernetes.default']){
                    sh './kubectl rollout restart deploy/test -n test'
            }
        }
        stage('Authorize deployment') {
            input(message: 'Continue deployment to prod', id: 'DEPLOY_TO_PROD', ok: 'yes, go head')
        }
        stage('Start the service') {
            withKubeConfig([credentialsId: 'kubernetes-config', serverUrl: 'https://kubernetes.default']){
                sh 'echo "deploying the code to kubernetes - Production"'
                sh './kubectl apply -f test/deploy/deployment.yml -n production'
                sh './kubectl rollout restart deploy/test -n production'
            }
        }
    }
}