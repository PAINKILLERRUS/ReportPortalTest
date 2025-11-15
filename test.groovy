pipeline {
    agent any

    parameters {
        string(
                name: 'BRANCH',
                defaultValue: 'main',
                description: 'Имя ветки для сборки'
        )
    }

    stages {
        stage('Checkout') {
            steps {
                checkout([
                        $class           : 'GitSCM',
                        branches         : [[name: "*/${params.BRANCH}"]],
                        extensions       : [],
                        userRemoteConfigs: [[
                                                    url: 'https://github.com/PAINKILLERRUS/ReportPortalTest.git'
                                            ]]
                ])
            }
        }


        stage('Test') {
            steps {
                // Запуск тестов
                sh 'mvn test -Dsurefire.suiteFiles=src/main/resources/xml_suite_files/ApiKey-APITests.xml'
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
