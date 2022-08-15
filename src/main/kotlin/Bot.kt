import kotlin.math.round

class Bot(playerName: String, playerNumber: Int, private val currentPolitic: Int) : Player(playerName, playerNumber) {
    var currentRolls = 0
    override fun rollSuccess() {
        currentScore += lastRoll
        if ((mainScore + currentScore) >= GamePlay.scoreTarget) {
            mainScore += currentScore
            GamePlay.endGame(playerName, mainScore, lastRoll)
        }
        //Если очков больше 100, победа
        else {
            println("Бот [$playerName] бросает кубик. Выпало [$lastRoll]. Текущий ход [$currentScore]. Продолжим?")

            when (currentPolitic) {
                1 -> botPolitic1(playerNumber)
                2 -> botPolitic2()
                3 -> botPolitic3((0..1).random())
                4 -> botPolitic4()
            }
        }
    }

    fun botPolitic1(playerNumber: Int) {
        if (currentScore >= 70) {
            println("Бот решает продолжить!")
            roll()
        } else if (playerNumber == 1) {
            if (currentScore == 21 + (round(((GamePlay.player2.currentScore - currentScore) / 8).toDouble())).toInt()) skipMove()
            else {
                println("Бот решает продолжить!")
                roll()
            }
        } else {
            if (currentScore == 21 + (round(((GamePlay.player1.currentScore - currentScore) / 8).toDouble())).toInt()) skipMove()
            else {
                println("Бот решает продолжить!")
                roll()
            }
        }
    }
// Переделать
    fun botPolitic2() {
        if (currentScore <= 20 && currentRolls < 5) {
            println("Бот решает продолжить!")
            roll()
            currentRolls++
        } else skipMove()

    }

    fun botPolitic3(random: Int) {
        when (random) {
            0 -> skipMove()
            1 -> {
                println("Бот решает продолжить!")
                roll()
            }
        }
    }

    fun botPolitic4() {
        if (currentScore < 100) {
            println("Бот решает продолжить!")
            roll()
        } else skipMove()
    }


}

