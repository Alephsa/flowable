package tools.blocks.flowable

import grails.transaction.Transactional
import org.flowable.engine.IdentityService
import org.flowable.common.engine.api.FlowableObjectNotFoundException
import org.flowable.idm.api.Group
import org.flowable.idm.api.GroupQuery
import org.flowable.idm.api.NativeGroupQuery
import org.flowable.idm.api.NativeUserQuery
import org.flowable.idm.api.Picture
import org.flowable.idm.api.User
import org.flowable.idm.api.UserQuery

@Transactional
class FlowableIdentityService {

    @Delegate IdentityService identityService

    Picture getUserPicture(String userId) {
        try {
            identityService.getUserPicture(userId)
        } catch (FlowableObjectNotFoundException e) {
            return false
        }
        return true
    }
}
