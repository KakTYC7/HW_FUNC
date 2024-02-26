fun main() {
    var commission = calculateCommission(cardType = "Mastercard", transferAmount = 80000)
    println("Сумма комиссии: $commission рублей")
    commission = calculateCommission(cardType = "Mastercard", transferAmount = 200000)
    println("Сумма комиссии: $commission рублей")
    commission = calculateCommission(cardType = "Accord", transferAmount = 80000)
    println("Сумма комиссии: $commission рублей")
    commission = calculateCommission(cardType = "Visa", sumPreviousTransfers = 100000, transferAmount = 160000)
    println("Сумма комиссии: $commission рублей")
    commission = calculateCommission(cardType = "Visa", sumPreviousTransfers = 600000, transferAmount = 50000)
    println("Сумма комиссии: $commission рублей")
    commission = calculateCommission(transferAmount = 50000)
    println("Сумма комиссии: $commission рублей")
    commission = calculateCommission(cardType = "Visa", sumPreviousTransfers = 550000, transferAmount = 60000)
    println("Сумма комиссии: $commission рублей")
}

fun calculateCommission(cardType: String = "Мир", sumPreviousTransfers: Int = 0, transferAmount: Int): Int {
    val dayTransferLimit = 150_000
    val monthTransferLimit = 600_000
    val mastercardMonthLimit = 75_000
    val mastercardComissionByLimit = 0.006
    val mastercardComissionFixed = 20
    val visaCommission = 0.0075
    val visaCommissionMin = 35

    if (transferAmount > dayTransferLimit) {
        println("Превышен суточный лимит переводов")
    }

    val totalMonthTransfers = sumPreviousTransfers + transferAmount
    if (totalMonthTransfers > monthTransferLimit) {
        println("Превышен месячный лимит на переводы")
    }

    when (cardType) {
        "Mastercard" -> {
            val remainMastercardMonthLimit = mastercardMonthLimit - sumPreviousTransfers
            val remainMastercardCurrentMonthLimit = remainMastercardMonthLimit - transferAmount
            val commissionToAmount = if (remainMastercardMonthLimit < 0) {
                transferAmount
            } else if (remainMastercardCurrentMonthLimit > 0) {
                return 0
            } else {
                totalMonthTransfers - mastercardMonthLimit
            }

            val commission = if (commissionToAmount > 0) {
                if (totalMonthTransfers <= mastercardMonthLimit) {
                    return 0
                } else {
                    val commissionAmount =
                        (mastercardComissionByLimit * commissionToAmount + mastercardComissionFixed).toInt()
                    commissionAmount
                }
            } else {
                return 0
            }
            return commission
        }

        "Visa" -> {
            val commission = (transferAmount * visaCommission).toInt().coerceAtLeast(visaCommissionMin)
            return commission
        }

        "Мир" -> return 0
        else -> {
            println("Не поддерживаемая платежная система")
            return 0
        }
    }
}