import core.BooleanResponseCommand
import core.CommandType.*
import core.MultipleResponseCommand
import core.OneAnswerCommand
import org.projog.api.Projog

fun main() {
    val prolog = initProlog()
    val io = IO()
    val oneAnswerCommand = OneAnswerCommand(prolog)
    val multipleAnswerCommand = MultipleResponseCommand(prolog)
    val boolAnswerCommand = BooleanResponseCommand(prolog)

    io.writeCommandExamples()
    while (true) {
        try {
            val (command, type) = io.readCommand()
            val commandExecutor = when (type) {
                ONE_ANSWER -> oneAnswerCommand
                MULTIPLE_RESPONSE -> multipleAnswerCommand
                BOOL_RESPONSE -> boolAnswerCommand
            }

            io.writeAnswer(commandExecutor.doAnswer(command))

        } catch (e: Exception) {
            println(e.message)
        }
    }
}

private fun initProlog(): Projog = Projog().also { it.consultResource("base.pl") }
