/*********************************************************************************
 * DEBEREMOS HABER CREADO ALGUNAS VARIABLES DE ENTORNO y CREDENCIALES DE SEGURIDAD en JENKINS

 *** Variables de Entorno:


/**************************************************************
 ** Desarrollo del PIPELINE del Jenkins file:
 **************************************************************/
pipeline {

    /**************************************************************
     * También debemos considerar diferentes variables de Entono del File:
     ***************************************************************/
    environment {

        /**Variables principales*/
        COMPON_GENERADO_URL = "http://localhost:8080"
        GIT_CREDENCTIAL_ID = "git-jaav"
        ARTIFACT_NAME = determineRepoName()
        DOCKER_HUB_USER = "dockerjaav"
        SSH_SECRET_FILE = "aws-ec2-secret"
        //PATH_JENKINS_WS = "${JENKINS_HOME}\\workspace\\"

        /**Variables:  Valores diversos */
        RESULT_STATUS_OK = 'Satisfactorio'
        ULTIMO_PASO = 'Inicio'
        EMAIL_DESTINOS_POST = "araucovillar@gmail.com"
        EMAIL_DESTINOS_POST_FT = "${EMAIL_DESTINOS_POST}"

        /**Variables: Flags para habilitar steps */
        ACTIVO_STAGE_TEST_UNIT = 'S'
        ACTIVO_STAGE_BUILD_DOCKER_IMAGE = 'N'
        ACTIVO_STAGE_UPLOAD_DOCKER_HUB = 'N'
        ACTIVO_STAGE_SSH_RUN_APP = 'N'


    }
    //Dec agente
    //Otra forma
    agent any
    stages {

        stage('Download Source') {
            steps {
                downloadSource()
            }
        }

        stage('Code Coverage: Static Analysis') {
            steps {
                testSource()
            }
        }

        stage('Build Artifact') {
            steps {
                buildArtifact()
            }

        }

        stage('Build Image') {
            steps {
                buildImage()
            }
        }

        stage('Upload Image') {
            steps {
                uploadImage()
            }
        }

        stage('client ssh: download Image and run Application') {
            steps {
                runAppAsSSHClient()
            }
        }
    }

    post {
        // Siempre hacer...
        always {
            //Limpiar WORKSPACE
            deleteDir()
        }
        success {
            sendEmail(RESULT_STATUS_OK, ULTIMO_PASO);
        }
        unstable {
            sendEmail("Inestable", ULTIMO_PASO);
        }
        failure {
            sendEmail("Errado", ULTIMO_PASO);
        }
    }
}



/**************************************************************
 ** Funciones Steps
 ***************************************************************/

/** Descargar codigo fuente desde Repo */
def downloadSource() {
    withCredentials([usernamePassword(credentialsId: "${GIT_CREDENCTIAL_ID}", usernameVariable: 'GIT_USERNAME', passwordVariable: 'GIT_PASSWORD')]) {
        retry(2) {
            script {
                GIT_ORIGIN_REPO =  determineRepo()
                GIT_ORIGIN_BRANCH =  getGitBranchName()
                ULTIMO_PASO = "Artefacto: ${ARTIFACT_NAME}. Iniciando clone out de última versión del repositorio GIT: ${GIT_ORIGIN_REPO} --branch ${GIT_ORIGIN_BRANCH} ... "
                echo ULTIMO_PASO
                def resultSH = getCommandOutput("git config --global credential.username {GIT_USERNAME}")
                echo resultSH
                resultSH = getCommandOutput("git config --global credential.helper \"!echo password={GIT_PASSWORD}; echo\" ")
                echo resultSH
                resultSH = getCommandOutput("git clone ${GIT_ORIGIN_REPO} --branch ${GIT_ORIGIN_BRANCH}")
                echo resultSH
                ULTIMO_PASO = 'Descarga completa.'
                echo ULTIMO_PASO
            }
        }
    }
}

/** Análisis de codigo fuente */
def testSource() {
    script {
        if (ACTIVO_STAGE_TEST_UNIT == 'S') {
            echo 'Iniciando ...'
            echo 'No se cuenta con Cobertura de Código... Considerarlo!!'
        } else {
            echo "STEP INACTIVO"
        }
    }
}

/** Contrucción de artefacto (Maven) */
def buildArtifact() {
    //Intentar más de una vez: Podría fallar a la primera
    script {
        ULTIMO_PASO = 'Iniciando BUILD de la Aplicación (Artifact)...'
        echo ULTIMO_PASO
        sh script: '''
					  #!/bin/bash
					  echo "This is start $(pwd)"
					  cd ./"${ARTIFACT_NAME}"
					  echo "This is $(pwd)"
					  /opt/maven/bin/mvn clean install
					  echo "This is $(pwd)"
					'''

        ULTIMO_PASO = 'BUILD COMPLETADO'
        echo ULTIMO_PASO
    }
}

/** Construcción de Imagen: docker */
def buildImage() {
    //Intentar más de una vez: Podría fallar a la primera
    script {
        if (ACTIVO_STAGE_BUILD_DOCKER_IMAGE == 'S') {
            ULTIMO_PASO = 'Iniciando docker build image...'
            echo ULTIMO_PASO
            //clean install MVN
            sh script: '''
						  #!/bin/bash
						  echo "This is start $(pwd)"
						  cd ./"${ARTIFACT_NAME}"
						  echo "This is $(pwd)"
						  docker build -f Dockerfile . -t "${ARTIFACT_NAME}"
						  echo "This is $(pwd)"
						'''
            //echo resultOp
            //resultOp = getCommandOutput("/opt/maven/bin/mvn clean install")
            //echo resultOp
            ULTIMO_PASO = 'BUILD DOCKER IMAGE COMPLETADO'
            echo ULTIMO_PASO
        } else {
            echo "STEP INACTIVO"
        }
    }
}

/** Subida de Imagen a un Registry determinado: Docker hub*/
def uploadImage() {
    withCredentials([usernamePassword(credentialsId: "${DOCKER_HUB_USER}", usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
        retry(2) {
            script {
                if (ACTIVO_STAGE_UPLOAD_DOCKER_HUB == 'S') {
                    ULTIMO_PASO = 'Iniciando docker upload...'
                    echo ULTIMO_PASO
                    //clean install MVN
                    sh script: '''
								  #!/bin/bash
								  echo "This is start $(pwd)"
								  cd ./"${ARTIFACT_NAME}"
								  echo "This is $(pwd)"
								  docker login --username="${DOCKER_USERNAME}" --password="${DOCKER_PASSWORD}"
								  echo "This is $(pwd)"
								  docker tag "${ARTIFACT_NAME}" "${DOCKER_HUB_USER}"/"${ARTIFACT_NAME}":latest
								  echo "This is $(pwd)"
								  docker push "${DOCKER_HUB_USER}"/"${ARTIFACT_NAME}":latest
								  echo "This is $(pwd)"
								'''
                    //echo resultOp
                    //resultOp = getCommandOutput("/opt/maven/bin/mvn clean install")
                    //echo resultOp
                    ULTIMO_PASO = 'DOCKER UPLOADED IMAGE COMPLETADO.'
                    echo ULTIMO_PASO
                } else {
                    echo "STEP INACTIVO"
                }
            }
        }
    }
}

/** Ejecutar la aplicación desde host SSH: Ejecutar contenedor*/
def runAppAsSSHClient() {
    withCredentials([file(credentialsId: "${SSH_SECRET_FILE}", variable: 'SSH_SECRET')]) {
        script {
            if (ACTIVO_STAGE_SSH_RUN_APP == 'S') {
                ULTIMO_PASO = "Iniciando download and running app (credential: $SSH_SECRET) ... "
                echo ULTIMO_PASO
                //conectarse cmomo client ssh, con certificado. "-o StrictHostKeyChecking=no" no es muy recomendado (Preferible agregar host como ssh para jenkins).
                sh script: '''
							  #!/bin/bash
							  echo "This is start $(pwd)"
							  ssh -t -t -i "$SSH_SECRET" -o StrictHostKeyChecking=no ec2-user@ec2-54-163-15-31.compute-1.amazonaws.com		
							  echo "This is $(pwd)"				
							  ifconfig
							  echo "This is $(pwd)"
							  exit
							'''
                //echo resultOp
                //resultOp = getCommandOutput("/opt/maven/bin/mvn clean install")
                //echo resultOp
                ULTIMO_PASO = 'RUN DOCKER IMAGE (REMOTE HOST) COMPLETADO'
                echo ULTIMO_PASO
            } else {
                echo "STEP INACTIVO"
            }
        }
    }
}

/**************************************************************
 ** Funciones Utiles para el Jenkins File:  cambios, envío de correos, etc
 ***************************************************************/
@NonCPS
def getChangeString() {
    MAX_MSG_LEN = 100
    def changeString = ""

    echo "Gathering SCM changes"
    def changeLogSets = currentBuild.changeSets
    for (int i = 0; i < changeLogSets.size(); i++) {
        def entries = changeLogSets[i].items
        for (int j = 0; j < entries.length; j++) {
            def entry = entries[j]
            truncated_msg = entry.msg.take(MAX_MSG_LEN)
            changeString += " - ${truncated_msg} [${entry.author}]\n"
        }
    }

    if (!changeString) {
        changeString = " - No se detectaron nuevos cambios"
    }
    return changeString
}

def sendEmail(status, lastStep) {
    def destinatariosMail = "${EMAIL_DESTINOS_POST}"
    def resultadoLine = ''
    if (status == RESULT_STATUS_OK) {
        resultadoLine = "Ver resultado en: ${COMPON_GENERADO_URL}"
        destinatariosMail = "${EMAIL_DESTINOS_POST_FT}"
    }

    mail(
            to: "${destinatariosMail}",
            subject: "Build $BUILD_NUMBER - " + status + " (${currentBuild.fullDisplayName})",
            body: "\n Información sobre la ejeción del PIPELINE $BUILD_NUMBER  - (${currentBuild.fullDisplayName}). El Estado final: ${status} "
                    + "\n Cambios:\n " + getChangeString() + "\n\n Puede Revisar la Consola de Salida (Con sus credenciales): $BUILD_URL/console"
                    + "\n Último paso realizado: ${lastStep}"
                    + "\n Resultado: ${currentBuild.result}"
                    + "\n ${resultadoLine}"
    )
}

def getCommandOutputFirstToken(cmd) {
    stdout = getCommandOutput(cmd)
    if (stdout != null && stdout != "") {
        result = stdout.split().head()
    } else {
        result = stdout
    }

    return result
}

def getCommandOutput(cmd) {
    if (isUnix()) {
        echo "getCommandOutput isUnix"
        return sh(returnStdout: true, script: '#!/bin/sh -e\n' + cmd).trim()
    } else {
        echo "getCommandOutput NO es isUnix"
        stdout = bat(returnStdout: true, script: cmd)
        if (stdout != null) {
            stdout = stdout.trim()
            result = stdout.readLines().drop(1).join(" ")
        }
        return result
    }
}

String determineRepoName() {
    //return scm.getUserRemoteConfigs()[0].getUrl().tokenize('/')[3].split("\\.")[0]
    return scm.getUserRemoteConfigs()[0].getUrl().tokenize('/').last().split("\\.")[0]
}


String determineRepo() {
    return scm.getUserRemoteConfigs()[0].getUrl()
}

def getGitBranchName() {
    return scm.branches[0].name
}