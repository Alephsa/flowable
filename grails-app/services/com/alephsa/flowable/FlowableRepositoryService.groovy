package com.alephsa.flowable

import groovy.lang.Delegate
import grails.transaction.Transactional
import org.flowable.bpmn.model.BpmnModel
import org.flowable.dmn.api.DmnDecisionTable
import org.flowable.engine.RepositoryService
import org.flowable.engine.app.AppModel
import org.flowable.common.engine.api.FlowableException
import org.flowable.common.engine.api.FlowableObjectNotFoundException
import org.flowable.engine.repository.Deployment
import org.flowable.engine.repository.DeploymentBuilder
import org.flowable.engine.repository.DeploymentQuery
import org.flowable.engine.repository.DiagramLayout
import org.flowable.engine.repository.Model
import org.flowable.engine.repository.ProcessDefinition
import org.flowable.engine.repository.ProcessDefinitionQuery
import org.flowable.identitylink.api.IdentityLink
import org.flowable.form.api.FormDefinition
import org.flowable.validation.ValidationError

import java.util.zip.ZipInputStream

@Transactional
class FlowableRepositoryService {

    @Delegate RepositoryService repositoryService

    def getDeployments(int offset = 0, int max = 100) {
        def deployments = repositoryService.createDeploymentQuery().listPage(offset, max)
        deployments
    }

    def deploy(def file, String name, String category, String key) {
        if (!name) {
            name = file.filename ?: 'undefined name'
        }
        //ZipInputStream inputStream = new ZipInputStream(file.inputStream)
        Deployment deployment = repositoryService.createDeployment().name(name).addInputStream(file.filename, file.inputStream).deploy()
        if (category) {
            setDeploymentCategory(deployment.id ,category)
        }
        if (key) {
            setDeploymentKey(deployment.id, key)
        }
        deployment
    }

    InputStream getProcessDiagramResource(String processDefinitionKey) {
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey(processDefinitionKey)
                .singleResult();
        String diagramResourceName = processDefinition.getDiagramResourceName();
        InputStream imageStream = repositoryService.getResourceAsStream(
                processDefinition.getDeploymentId(), diagramResourceName);
        imageStream
    }

}
