package core

data class Answer(
    val isError: Boolean,
    val isOneResponse: Boolean = true,
    val response: String? = null,
    val dataForMultiple: ArrayList<String> = ArrayList()
)
