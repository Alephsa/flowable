package tools.blocks.flowable

import grails.test.mixin.TestFor
import grails.test.runtime.FreshRuntime
import org.flowable.engine.IdentityService
import org.flowable.idm.api.User
import org.grails.datastore.gorm.jdbc.DataSourceBuilder
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@FreshRuntime
@TestFor(FlowableIdentityService)
class FlowableIdentityServiceSpec extends Specification {

    static loadExternalBeans = true

    def doWithSpring = {
        dataSource(org.springframework.jdbc.datasource.DriverManagerDataSource) {
            return DataSourceBuilder.create()
                    .url(conf.getProperty("environments.${grails.util.Environment.current.name}.flowable.datasource.url"))
                    .username(conf.getProperty("environments.${grails.util.Environment.current.name}.flowable.datasource.username"))
                    .password(conf.getProperty("environments.${grails.util.Environment.current.name}.flowable.datasource.password"))
                    .driverClassName(conf.getProperty("environments.${grails.util.Environment.current.name}.flowable.datasource.driverClassName"))
                    .build();
        }
        transactionManager(org.springframework.jdbc.datasource.DataSourceTransactionManager) {
            dataSource = dataSource
        }
        processEngineConfiguration(org.flowable.spring.SpringProcessEngineConfiguration) {
            transactionManager = transactionManager
            dataSource = dataSource
            databaseSchemaUpdate = true
            asyncExecutorActivate = false
            databaseSchemaUpdate = conf.getProperty("environments.${grails.util.Environment.current.name}.flowable.datasource.dbCreate")
            deploymentResources = conf.getProperty("environments.${grails.util.Environment.current.name}.flowable.deploymentResources")
            deploymentMode = conf.getProperty("environments.${grails.util.Environment.current.name}.flowable.deploymentMode")
        }
        processEngine(org.flowable.spring.ProcessEngineFactoryBean) {
            processEngineConfiguration = processEngineConfiguration
        }
        identityService(processEngine: "getIdentityService")

    }

    /*def doWithConfig(c) {
        c.myConfigValue = 'Hello'
    }*/

    //IdentityService identityService

    def setup() {
        //identityService = Stub()
        //service.identityService = identityService
        identityService.newUser("test") >> {id: "test"}
    }

    def cleanup() {
    }

    def "when service is created identity service is created"() {
        expect:
            service.identityService != null
    }

    def "when creating user a new user object is created"() {
        when:
            def user = service.newUser("test")
        then:
            service.identityService != null
            user != null
            user.id == "test"
    }
}