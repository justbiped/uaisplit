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
    @SerialName("value") val value: Double,
    @SerialName("description") val description: String
)

@Serializable
data class RemoteTimeSpan(
    @SerialName("entry") val entry: String,
    @SerialName("conclusion") val conclusion: String
)