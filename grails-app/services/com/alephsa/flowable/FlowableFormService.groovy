package com.alephsa.flowable

import groovy.lang.Delegate
import grails.transaction.Transactional
import org.flowable.form.api.FormService

@Transactional
class FlowableFormService {

    FormService formService

}
