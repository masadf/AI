package utils

import core.CommandType
import core.CommandType.*

object IOUtils {
    val COMMANDS = listOf(
        "Какие персонажи есть в игре?",
        "Какое оружие есть в игре?",
        "Какая броня есть в игре?",
        "Есть ли 'barbarian' в игре?",
        "Какой уровень у 'necromancer'?",
        "Какое оружее у 'druid'?",
        "Какая броня у 'paladin'?",
        "Кого сможет победить 'amazon'?",
        "Владеет ли 'druid' бронёй 'sword'?",
        "Какой урон у персонажа 'barbarian'?"
    )

    private val COMMANDS_PATTERNS = listOf(
        "Какие персонажи есть в игре\\?".toRegex(),
        "Какое оружие есть в игре\\?".toRegex(),
        "Какая броня есть в игре\\?".toRegex(),
        "Есть ли (\\S+) в игре\\?".toRegex(),
        "Какой уровень у (\\S+)\\?".toRegex(),
        "Какое оружее у (\\S+)\\?".toRegex(),
        "Какая броня у (\\S+)\\?".toRegex(),
        "Кого сможет победить (\\S+)\\?".toRegex(),
        "Владеет ли (\\S+) бронёй (\\S+)\\?".toRegex(),
        "Какой урон у персонажа (\\S+)\\?".toRegex()
    )

    fun toPrologCommand(humanCommand: String): Pair<String, CommandType>? {
        val pattern = COMMANDS_PATTERNS.firstOrNull { it.matches(humanCommand) } ?: return null
        val groups = pattern.find(humanCommand)!!.groups

        return when (pattern.toString()) {
            "Какие персонажи есть в игре\\?" -> "person_in_game(X)." to MULTIPLE_RESPONSE
            "Какое оружие есть в игре\\?" -> "weapon_in_game(X)." to MULTIPLE_RESPONSE
            "Какая броня есть в игре\\?" -> "armor_in_game(X)." to MULTIPLE_RESPONSE
            "Есть ли (\\S+) в игре\\?" -> "person_in_game(${groups[1]!!.value})." to BOOL_RESPONSE
            "Какой уровень у (\\S+)\\?" -> "person_lvl(${groups[1]!!.value}, X)." to ONE_ANSWER
            "Какое оружее у (\\S+)\\?" -> "person_weapon(${groups[1]!!.value}, X)." to ONE_ANSWER
            "Какая броня у (\\S+)\\?" -> "person_armor(${groups[1]!!.value}, X)." to ONE_ANSWER
            "Кого сможет победить (\\S+)\\?" -> "is_first_win(${groups[1]!!.value}, X)." to MULTIPLE_RESPONSE
            "Владеет ли (\\S+) бронёй (\\S+)\\?" -> "person_in_game(${groups[1]!!.value}), armor_in_game(${groups[2]!!.value}), person_armor(${groups[1]!!.value}, ${groups[2]!!.value})." to BOOL_RESPONSE
            "Какой урон у персонажа (\\S+)\\?" -> "person_damage(${groups[1]!!.value}, X)." to ONE_ANSWER
            else -> null
        }
    }
}