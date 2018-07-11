package com.alephsa.flowable

import groovy.lang.Delegate
import grails.transaction.Transactional
import org.flowable.engine.FormService

@Transactional
class FlowableFormPropertyService {

    @Delegate FormService formPropertyService

}
