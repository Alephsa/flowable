package com.alephsa.flowable

import groovy.lang.Delegate
import grails.transaction.Transactional
import org.flowable.engine.FlowableTaskAlreadyClaimedException
import org.flowable.engine.TaskService
import org.flowable.common.engine.api.FlowableException
import org.flowable.common.engine.api.FlowableObjectNotFoundException
import org.flowable.variable.api.persistence.entity.VariableInstance
import org.flowable.engine.runtime.DataObject
import org.flowable.engine.task.Attachment
import org.flowable.engine.task.Comment
import org.flowable.engine.task.Event
import org.flowable.identitylink.api.IdentityLink
import org.flowable.task.api.NativeTaskQuery
import org.flowable.task.api.Task
import org.flowable.task.api.TaskQuery
import org.flowable.form.api.FormModel

@Transactional
class FlowableTaskService {

    @Delegate TaskService taskService

}
