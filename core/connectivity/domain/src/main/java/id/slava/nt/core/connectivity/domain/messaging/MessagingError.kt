package id.slava.nt.core.connectivity.domain.messaging

import id.slava.nt.core.domain.util.Error


enum class MessagingError: Error {
    CONNECTION_INTERRUPTED,
    DISCONNECTED,
    UNKNOWN
}