pipeline { // Начало описания пайплайна Jenkins
    agent any  // Запускать пайплайн на любом доступном агенте (ноде Jenkins)
    tools {
        maven 'jenkins-maven' // Имя установки Maven в Jenkins
    }
    parameters { // Параметры, которые будут доступны в параметризованной сборке
        choice(
                name: 'TEST_SUITE',
                choices: [
                        'src/main/resources/xml_suite_files/ApiKey-APITests.xml',
                        'src/main/resources/xml_suite_files/Ui-Tests-Suit.xml'
                ],
                description: 'Выбор testng.xml файла для запуска'
        )
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
    stages {  // Определение последовательных стадий пайплайна
        stage('Checkout') { // Стадия 1: Получение кода из репозитория
            steps {
                checkout([
                        $class: 'GitSCM',
                        branches: [[name: "*/${params.BRANCH}"]],
                        extensions: [],
                        userRemoteConfigs: [[
                                                    url: 'https://github.com/PAINKILLERRUS/ReportPortalTest.git'
                                            ]]
                ])
            }
        }

        stage('Test') {  // Стадия 3: Запуск тестов
            steps {
                script {
                    // Используем mvn для выполнения Maven-целей
                    sh "mvn ${params.MAVEN_GOALS} -Dsurefire.suiteXmlFiles=${params.TEST_SUITE}"
                }
            }
        }

        stage('Allure Report') {
            when {
                expression { params.ALLURE_ENABLED }
            }
            steps {
                script {
                    // Генерация Allure-отчета через Maven-плагин
                    sh 'mvn allure:report'
                }
            }
        }
    }

    post {  // Блок действий, которые выполняются после всех стадий
        always { // Всегда выполнять этот блок — независимо от результата сборки
            script {
                if (params.ALLURE_ENABLED) {
                    allure([
                            includeProperties: false,
                            jdk: '',
                            results: [[path: 'target/allure-results']],
                            report: 'target/allure-report'
                    ])
                }
            }
        }
    }
}