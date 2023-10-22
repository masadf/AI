package core

import org.projog.api.Projog

interface GameInfoCommand {
    fun doAnswer(command: String): Answer
}

enum class CommandType{
    ONE_ANSWER, MULTIPLE_RESPONSE, BOOL_RESPONSE
}

class OneAnswerCommand(private val prolog: Projog) : GameInfoCommand {
    override fun doAnswer(command: String): Answer = withHandleException {
        val result = prolog.executeQuery(command)
        val response = if (result.next()) result.getTerm("X").name else null

        return@withHandleException Answer(isError = false, response = response)
    }
}

class MultipleResponseCommand(private val prolog: Projog) : GameInfoCommand {
    override fun doAnswer(command: String): Answer = withHandleException {
        val result = prolog.executeQuery(command)
        val response = ArrayList<String>()
        while (result.next()) {
            response.add(result.getTerm("X").name)
        }

        return@withHandleException Answer(isError = false, isOneResponse = false, dataForMultiple = response)
    }
}

class BooleanResponseCommand(private val prolog: Projog) : GameInfoCommand {
    override fun doAnswer(command: String): Answer = withHandleException {
        val result = prolog.executeQuery(command)
        val response = if (result.next()) "Да" else "Нет"

        return@withHandleException Answer(isError = false, response = response)
    }
}

fun withHandleException(block: () -> Answer) = try {
    block()
} catch (e: Exception) {
    Answer(isError = true)
}

