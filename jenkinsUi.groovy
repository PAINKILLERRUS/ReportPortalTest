pipeline {
    agent any
    tools {
        maven 'jenkins-maven' // Имя установки Maven в Jenkins
    }
    parameters {
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
    stages {
        stage('Checkout') {
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

        stage('Clean Chrome Processes') {
            steps {
                script {
                    sh '''
                        # Принудительно убиваем все процессы Chrome
                        pkill -9 -f chrome || true
                        pkill -9 -f chromedriver || true
                        
                        # Очищаем временные файлы
                        rm -rf /tmp/.com.google.Chrome.* || true
                        rm -rf /tmp/.org.chromium.Chromium.* || true
                        rm -rf /tmp/chrome-* || true
                        rm -rf /tmp/.X99-lock || true
                        
                        # Очищаем кэш Selenide и WebDriver
                        rm -rf /root/.cache/selenide || true
                        rm -rf /root/.cache/webdriver || true
                        rm -rf /root/.config/google-chrome || true
                    '''
                }
            }
        }

//        stage('Test') {
//            steps {
//                script {
//                    // Используем mvn для выполнения Maven-целей
//                    sh "mvn ${params.MAVEN_GOALS} -Dsurefire.suiteXmlFiles=${params.TEST_SUITE}"
//                }
//            }
//        }

        stage('Test') {
            steps {
                script {
                    sh """
                        # Создаем уникальную директорию для этого запуска
                        export CHROME_TEMP_DIR="/tmp/chrome-profile-${BUILD_ID}"
                        mkdir -p \$CHROME_TEMP_DIR
                        
                        mvn ${params.MAVEN_GOALS} \
                        -Dsurefire.suiteXmlFiles=${params.TEST_SUITE} \
                        -Dselenide.browser=chrome \
                        -Dselenide.headless=true \
                        -Dwebdriver.chrome.args="--no-sandbox,--disable-dev-shm-usage,--remote-allow-origins=*,--disable-gpu,--user-data-dir=\$CHROME_TEMP_DIR,--no-first-run,--disable-extensions" \
                        -Dwebdriver.chrome.whitelisted-ips=""
                    """
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

    post {
        always {
            script {

                sh "rm -rf /tmp/chrome-profile-${BUILD_ID} || true"

                if (params.ALLURE_ENABLED) {
                    allure([
                           // includeProperties: false,
                           // jdk: '',
                            results: [[path: 'target/allure-results']],
                            report: 'target/allure-report'
                    ])
                }
            }
        }
    }
}
