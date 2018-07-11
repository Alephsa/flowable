package com.alephsa.flowable

import groovy.lang.Delegate
import grails.transaction.Transactional
import org.flowable.form.api.FormRepositoryService

@Transactional
class FlowableFormRepositoryService {

    @Delegate FormRepositoryService formRepositoryService
}
