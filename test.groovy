pipeline {
    agent any

//    tools {
//        maven 'Maven-3.8.5'
//        jdk 'JDK-19'
//        allure 'Allure-2.24.0' // Имя вашей Allure установки в Jenkins
//    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/PAINKILLERRUS/ReportPortalTest.git'
            }
        }


        stage('Test') {
            steps {
                // Запуск тестов с генерацией Allure результатов
                sh 'test -DsuiteFile=src/main/resources/xml_suite_files/ApiKey-APITests.xml'
            }
        }

        stage('Allure Report') {
            steps {
                // Генерация отчета
                sh 'allure:report'
            }
            post {
                always {
                    // Публикация в Jenkins
                    allure([
                            includeProperties: false,
                            results: [[path: 'target/allure-results']],
                            report: 'target/allure-report',
                            properties: []
                    ])
                }
            }
        }
    }
}
