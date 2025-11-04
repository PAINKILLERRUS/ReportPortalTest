pipeline {
    agent any // Запускает сборку на любом доступном агенте
    tools {
        // Указывает Jenkins использовать заранее настроенную версию Maven
        maven 'Maven 3.9.8' // Убедитесь, что это имя совпадает с настройками в "Global Tool Configuration"
    }
    parameters {
        choice(
                name: 'TEST_ENV',
                choices: ['dev', 'stage', 'prod'],
                description: 'Выберите среду для запуска тестов'
        )
        string(
                name: 'LOGIN',
                defaultValue: 'defaultUser',
                description: 'Логин для тестируемого приложения'
        )
        string(
                name: 'PASS',
                defaultValue: 'defaultPass',
                description: 'Пароль для тестируемого приложения'
        )
        booleanParam(
                name: 'SKIP_TESTS',
                defaultValue: false,
                description: 'Пропустить выполнение тестов?'
        )
    }
    stages {
        stage('Build') {
            steps {
                echo 'Начало сборки проекта...'
                // Собирает проект, включая зависимости, но пропускает выполнение тестов
                sh 'mvn clean compile -ApiKeyTest'
            }
        }
        stage('Test') {
            steps {
                echo 'Запуск автотестов...'
                script {
                    // Формируем команду для Maven, передавая параметры из Jenkins
                    def testCommand = "mvn test"
                    // Добавляем флаг -DskipTests только если параметр SKIP_TESTS равен true
                    if ("${params.SKIP_TESTS}" == "true") {
                        testCommand += " -DskipTests"
                    }
                    // Передаем параметры сборки в Maven как системные свойства
                    testCommand += " -Dtest.env=${params.TEST_ENV}"
                    testCommand += " -Dlogin=${params.LOGIN}"
                    testCommand += " -Dpass=${params.PASS}"
                    // Выполняем команду
                    sh testCommand
                }
            }
        }
    }
}
