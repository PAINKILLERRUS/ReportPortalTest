pipeline {
    agent any
    parameters {
        choice(
                name: 'TEST_SUITE',
                choices: ['src/main/resources/xml_suite_files/ApiKey-APITests.xml'],
                description: 'Выбор testng.xml файла для запуска'
        )
        choice(
                name: 'BROWSER',
                choices: ['chrome'],
                description: 'Браузер для UI тестов'
        )
        string(
                name: 'MAVEN_GOALS',
                defaultValue: 'clean test',
                description: 'Maven цели для выполнения (например, clean test)'
        )
        booleanParam(
                name: 'ALLURE_ENABLED',
                defaultValue: true,
                description: 'Генерировать Allure отчет'
        )
    }
    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/PAINKILLERRUS/ReportPortalTest.git'
            }
        }
        stage('Test') {
            steps {
                script {
                    // Передача параметров в Maven
                    sh "mvn ${params.MAVEN_GOALS} -Dsurefire.suiteXmlFiles=${params.TEST_SUITE} -Dbrowser=${params.BROWSER}"
                }
            }
        }
    }
    post {
        always {
            script {
                if (params.ALLURE_ENABLED) {
                    allure includeProperties: false,
                            jdk: '',
                            results: [[path: 'target/allure-results']]
                }
            }
        }
    }
}
