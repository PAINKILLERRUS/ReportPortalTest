pipeline {
    agent any
    parameters {
        choice(
                name: 'TEST_SUITE',
                choices: ['src/main/resources/xml_suite_files/ApiKey-APITests.xml', 'src/main/resources/xml_suite_files/Ui-Tests-Suit.xml'],
                description: 'Выбор testng.xml файла для запуска'
        )
//        choice(
//                name: 'BROWSER',
//                choices: ['chrome'],
//                description: 'Браузер для UI тестов'
//        )
        string(
                name: 'MAVEN_GOALS',
                defaultValue: 'clean test',
                description: 'Maven цели для выполнения (например, clean test)'
        )
        string(
                name: 'BRANCH',
                defaultValue: 'main',
                description: 'Имя ветки для сборки'
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
                //script {
                    // Передача параметров в Maven
                    sh "${params.MAVEN_GOALS} -DsuiteFile=${params.TEST_SUITE}"
                //}
            }
        }
    }
    post {
        always {
            //script {
                //if (params.ALLURE_ENABLED) {
                    allure includeProperties: false,
                            jdk: '',
                            results: [[path: 'target/allure-results']]
                //}
           // }
        }
    }
}
