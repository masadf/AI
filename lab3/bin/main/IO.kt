import core.Answer
import core.CommandType
import utils.IOUtils.COMMANDS
import utils.IOUtils.toPrologCommand
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class IO {
    private val reader = BufferedReader(InputStreamReader(System.`in`))

    fun writeCommandExamples() {
        println("ПРИМЕРЫ КОМАНД:")
        COMMANDS.forEach { println(it) }
    }

    fun readCommand(): Pair<String, CommandType> {
        println("Введите команду")
        return toPrologCommand(reader.readLine()) ?: throw IOException("Некорректная команда!")
    }

    fun writeAnswer(answer: Answer) {
        if (answer.isError) {
            println("Некорректно введена команда!")
        } else {
            if (answer.isOneResponse) {
                println(answer.response ?: "Результатов не найдено")
            } else {
                if (answer.dataForMultiple.isEmpty()) {
                    println("Результатов не найдено")
                } else {
                    answer.dataForMultiple.forEach { println(it) }
                }
            }
        }
    }
}