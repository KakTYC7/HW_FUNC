fun main() {
    agoToText(45)
    agoToText(61)
    agoToText(182)
    agoToText(3700)
    agoToText(86400)
    agoToText(86402)
    agoToText(172802)
    agoToText(286400)
}

fun agoToText(seconds: Int) {
    val minutes = seconds / 60
    val hours = seconds / 3600

    when (seconds) {
        in 0..60 -> println("был(а) только что")
        in 61..3600 -> println("был(а) ${formatMinutes(minutes)} назад")
        in 3601..86400 -> println("был(а) ${formatHours(hours)} назад")
        in 86401..172800 -> println("был(а) вчера")
        in 172801..259200 -> println("был(а) позавчера")
        else -> println("был(а) давно")
    }
}

fun formatMinutes(minutes: Int): String {
    return when {
        minutes % 10 == 1 && minutes != 11 -> "$minutes минуту"
        minutes % 10 in 2..4 && minutes !in 12..14 -> "$minutes минуты"
        else -> "$minutes минут"
    }
}

fun formatHours(hours: Int): String {
    return when (hours) {
        1, 21 -> "$hours час"
        in arrayOf(2, 3, 4, 22, 23, 24) -> "$hours часа"
        else -> "$hours часов"
    }
}
