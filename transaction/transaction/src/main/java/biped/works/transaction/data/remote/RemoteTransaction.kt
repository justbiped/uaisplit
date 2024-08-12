package biped.works.transaction.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteTransaction(
    @SerialName("id") val id: String,
    @SerialName("owner") val owner: String,
    @SerialName("name") val name: String,
    @SerialName("description") val description: String,
    @SerialName("due") val due: String,
    @SerialName("value") val value: RemoteValue
)

@Serializable
data class RemoteValue(
    @SerialName("amount") val amount: Double,
    @SerialName("currency") val currency: String
)
