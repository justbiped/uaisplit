package biped.works.transaction.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TransactionRemote(
    @SerialName("id") val id: String,
    @SerialName("value") val value: Double,
    @SerialName("description") val description: String
)