package biped.works.statement.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteStatement(
    @SerialName("timeSpan") val timeSpan: RemoteTimeSpan,
    @SerialName("balance") val balance: String,
    @SerialName("transactions") val transactions: List<RemoteTransaction>
)

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
data class RemoteTimeSpan(
    @SerialName("entry") val entry: String,
    @SerialName("conclusion") val conclusion: String
)

@Serializable
data class RemoteValue(
    @SerialName("amount") val amount: Double,
    @SerialName("currency") val currency: String
)