package ru.netology

fun calculateCommission(cardType: String = "Мир",
                        previousMonthTransfers: Long = 0,
                        transferAmount: Long): Pair<Long, Boolean> {
    var commission: Long = 0
    var isSuccessful = true
    val dailyLimit = 150_000L
    val monthlyLimit = 600_000L
    val monthlyLimitMastercard = 75_000L

    // Проверка лимитов
    if (transferAmount > dailyLimit || previousMonthTransfers + transferAmount > monthlyLimit) {
        isSuccessful = false
        return Pair(commission, isSuccessful)
    }

    when (cardType) {
        "Mastercard" -> {
            if (previousMonthTransfers + transferAmount > monthlyLimitMastercard) {
                val excessAmount = (previousMonthTransfers + transferAmount) - monthlyLimitMastercard
                commission = (excessAmount * 0.006 + 20).toLong()
            }
        }
        "Visa" -> {
            commission = maxOf((transferAmount * 0.0075).toLong(), 35)
        }
        "Мир" -> {
            // Комиссия не взимается
        }
        else -> {
            isSuccessful = false // Неизвестный тип карты
        }
    }
    // Вывод суммы предыдущих переводов (в `main` функции)
    println("Сумма предыдущих переводов: $previousMonthTransfers")

    return Pair(commission, isSuccessful)
}

fun main() {
    // Пример использования
    val cardType = "Мир"   //тип карты (по умолчанию Мир);
    val transferAmount = 150_000L
    val previousMonthTransfers = 0L

    val result = calculateCommission(cardType, previousMonthTransfers, transferAmount)

    println("Комиссия: ${result.first}")
    println("Успешно: ${result.second}")
}
