package biped.works.transaction.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class RemoteTransaction(
    val id: String,
    val metaId: String,
    val name: String,
    val description: String,
    val duty: String,
    val value: Int
)