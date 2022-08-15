open class Player(var playerName: String, val playerNumber: Int) {
    var mainScore = 0
    var currentScore = 0
    var lastRoll = 0

    //Cчетчик очков
    fun rollFail() {
        println("(!) У [${playerName}] выпадает [1], а значит, ход завершен.")
        currentScore = 0
        GamePlay.nextTurn(playerNumber)
    }

    fun roll() {
        lastRoll = (1..6).random()
        if (lastRoll == 1) rollFail()
        else rollSuccess()
    }

    open fun rollSuccess() {
        currentScore += lastRoll
        if ((mainScore + currentScore) >= GamePlay.scoreTarget) {
            mainScore += currentScore
            GamePlay.endGame(playerName, mainScore, lastRoll)
        }
        //Если очков больше 100, победа
        else {
            println("> Игрок [$playerName] бросает кубик. Выпало [$lastRoll]. Текущий ход [$currentScore]. Продолжим?")
            when (readln().lowercase()) {
                "y" -> roll()
                "n" -> skipMove()
            }
        }
    }


    // Функция бросок
    fun skipMove() {
        mainScore += currentScore
        GamePlay.nextTurn(playerNumber)
    }
    //Завершить ход


}