package biped.works.statement.data

data class Statement(
    val balance: String,
    val transactions: List<Transaction>,
    val timeSpan: TimeSpan
)

data class TimeSpan(
    val entry: String,
    val conclusion: String
)