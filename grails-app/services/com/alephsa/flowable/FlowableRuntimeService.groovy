package com.alephsa.flowable

import groovy.lang.Delegate
import grails.transaction.Transactional
import org.flowable.bpmn.model.FlowNode
import org.flowable.engine.RuntimeService
import org.flowable.common.engine.api.FlowableException
import org.flowable.common.engine.api.FlowableIllegalArgumentException
import org.flowable.common.engine.api.FlowableObjectNotFoundException
import org.flowable.common.engine.api.delegate.event.FlowableEvent
import org.flowable.common.engine.api.delegate.event.FlowableEventListener
import org.flowable.common.engine.api.delegate.event.FlowableEngineEventType
import org.flowable.variable.api.persistence.entity.VariableInstance
import org.flowable.engine.runtime.DataObject
import org.flowable.engine.runtime.Execution
import org.flowable.engine.runtime.ExecutionQuery
import org.flowable.engine.runtime.NativeExecutionQuery
import org.flowable.engine.runtime.NativeProcessInstanceQuery
import org.flowable.engine.runtime.ProcessInstance
import org.flowable.engine.runtime.ProcessInstanceBuilder
import org.flowable.engine.runtime.ProcessInstanceQuery
import org.flowable.engine.task.Event
import org.flowable.identitylink.api.IdentityLink
import org.flowable.form.api.FormInfo

@Transactional
class FlowableRuntimeService {

    @Delegate RuntimeService runtimeService

    ProcessInstanceBuilder createProcessInstanceBuilder() {
        runtimeService.createProcessInstanceBuilder()
    }

    //Activation methods

    //...ByKey methods

    //All params method
    ProcessInstance startProcessInstanceByKey(String processDefinitionKey, String businessKey, Map<String, Object> variables, String tenantId) {
        if (tenantId && !tenantId.empty) {//versions  tenant
            if (businessKey && !businessKey.empty) {
                return startProcessInstanceByKeyAndTenantId(processDefinitionKey, businessKey, variables, tenantId)
            } else {
                return startProcessInstanceByKeyAndTenantId(processDefinitionKey, variables, tenantId)
            }
        } else {
            if (businessKey && !businessKey.empty) {
                return startProcessInstanceByKey(processDefinitionKey, businessKey, variables)
            } else {
                return startProcessInstanceByKey(processDefinitionKey, variables)
            }
        }
    }

    ProcessInstance startProcessInstanceByKey(String processDefinitionKey) {
        runtimeService.startProcessInstanceByKey(processDefinitionKey)
    }

    ProcessInstance startProcessInstanceByKey(String processDefinitionKey, String businessKey) {
        runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey)
    }

    ProcessInstance startProcessInstanceByKey(String processDefinitionKey, Map<String, Object> variables) {
        runtimeService.startProcessInstanceByKey(processDefinitionKey, variables)
    }

    ProcessInstance startProcessInstanceByKey(String processDefinitionKey, String businessKey, Map<String, Object> variables) {
        runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey, variables)
    }

    ProcessInstance startProcessInstanceByKeyAndTenantId(String processDefinitionKey, String tenantId) {
        runtimeService.startProcessInstanceByKeyAndTenantId(processDefinitionKey, tenantId)
    }

    ProcessInstance startProcessInstanceByKeyAndTenantId(String processDefinitionKey, String businessKey, String tenantId) {
        runtimeService.startProcessInstanceByKeyAndTenantId(processDefinitionKey, businessKey, tenantId)
    }

    ProcessInstance startProcessInstanceByKeyAndTenantId(String processDefinitionKey, Map<String, Object> variables, String tenantId) {
        runtimeService.startProcessInstanceByKeyAndTenantId(processDefinitionKey, variables, tenantId)
    }

    ProcessInstance startProcessInstanceByKeyAndTenantId(String processDefinitionKey, String businessKey, Map<String, Object> variables, String tenantId) {
        runtimeService.startProcessInstanceByKeyAndTenantId(processDefinitionKey, businessKey, variables, tenantId)
    }

    //...ById methods

    ProcessInstance startProcessInstanceById(String processDefinitionId, String businessKey, String outcome, Map<String, Object> variables, String processInstanceName) {
        if (processInstanceName && !processInstanceName.empty) {
            return startProcessInstanceWithForm(processDefinitionId, outcome, variables, processInstanceName)
        } else if (businessKey) {
            return startProcessInstanceById(processDefinitionId, businessKey, variables)
        } else {
            return startProcessInstanceById(processDefinitionId, variables)
        }
    }

    ProcessInstance startProcessInstanceById(String processDefinitionId) {
        runtimeService.startProcessInstanceById(processDefinitionId)
    }

    ProcessInstance startProcessInstanceById(String processDefinitionId, String businessKey) {
        runtimeService.startProcessInstanceById(processDefinitionId, businessKey)
    }

    ProcessInstance startProcessInstanceById(String processDefinitionId, Map<String, Object> variables) {
        runtimeService.startProcessInstanceById(processDefinitionId, variables)
    }

    ProcessInstance startProcessInstanceById(String processDefinitionId, String businessKey, Map<String, Object> variables) {
        runtimeService.startProcessInstanceById(processDefinitionId, businessKey, variables)
    }

    ProcessInstance startProcessInstanceWithForm(String processDefinitionId, String outcome, Map<String, Object> variables, String processInstanceName) {
        runtimeService.startProcessInstanceWithForm(processDefinitionId, outcome, variables, processInstanceName)
    }

    //ByMessage methods

    ProcessInstance startProcessInstanceByMessage(String messageName) {
        runtimeService.startProcessInstanceByMessage(messageName)
    }

    ProcessInstance startProcessInstanceByMessageAndTenantId(String messageName, String tenantId) {
        runtimeService.startProcessInstanceByMessage(messageName, tenantId)
    }

    ProcessInstance startProcessInstanceByMessage(String messageName, String businessKey) {
        runtimeService.startProcessInstanceByMessage(messageName, businessKey)
    }

    ProcessInstance startProcessInstanceByMessage(String messageName, Map<String, Object> processVariables) {
        runtimeService.startProcessInstanceByMessage(messageName, processVariables)
    }

    ProcessInstance startProcessInstanceByMessageAndTenantId(String messageName, Map<String, Object> processVariables, String tenantId) {
        runtimeService.startProcessInstanceByKeyAndTenantId(messageName, processVariables, tenantId)
    }

    ProcessInstance startProcessInstanceByMessage(String messageName, String businessKey, Map<String, Object> processVariables) {
        runtimeService.startProcessInstanceByMessage(messageName, businessKey, processVariables)
    }

    ProcessInstance startProcessInstanceByMessageAndTenantId(String messageName, String businessKey, Map<String, Object> processVariables, String tenantId) {
        runtimeService.startProcessInstanceByKeyAndTenantId(messageName, businessKey, processVariables, tenantId)
    }
}
