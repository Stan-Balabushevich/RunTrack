package id.slava.nt.wear.run.domain

import id.slava.nt.core.connectivity.domain.DeviceNode
import id.slava.nt.core.connectivity.domain.messaging.MessagingAction
import id.slava.nt.core.connectivity.domain.messaging.MessagingError
import id.slava.nt.core.domain.util.EmptyResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface PhoneConnector {
    val connectedNode: StateFlow<DeviceNode?>
    val messagingActions: Flow<MessagingAction>

    suspend fun sendActionToPhone(action: MessagingAction): EmptyResult<MessagingError>
}