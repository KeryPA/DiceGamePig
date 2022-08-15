import kotlin.system.exitProcess

object GamePlay {
    var player1 = Player("player", 1)
    var player2 = Player("player2", 2)
    private var currentPolitic: Int = 0

    //Создаем экземпляры игроков
    const val scoreTarget = 100
    //Цель игры

    fun startGame() {
        println(Text.rules)
        println(Text.strip)
        println("")
        println("+Введи [bot], чтобы включить бота.")
        println("+Управление: [Y] - Да, [N] - Нет.")
        println("")
        println("Введи имя первого игрока:")
        player1.playerName = readln()
        println("Введи имя второго игрока:")
        player2.playerName = readln()

        if (player1.playerName.contains("bot", ignoreCase = true)) {
            currentPolitic = (1..4).random()
            player1 = Bot("Sergio", 1, currentPolitic)
        }
        if (player2.playerName.contains("bot", ignoreCase = true)) {
            currentPolitic = (1..4).random()
            player2 = Bot("Kery", 2, currentPolitic)
        }

        firstTurn((1..2).random())
    }

    //Начало игры
    private fun firstTurn(randomTurn: Int) {
        player1.mainScore = 0
        player2.mainScore = 0
        if (randomTurn == 1) player1.roll()
        else player2.roll()
    }
    //Первый ход

    fun nextTurn(playerNumber: Int) {
        Text.mainScoreText = Text.strip + "\n+++Общий счет: Игрок ${player1.playerName} : [${player1.mainScore}]," +
                " Игрок ${player2.playerName} : [${player2.mainScore}] +++\n" + Text.strip
        if (playerNumber == 1) player2turn()
        else player1turn()
    }
    //Кто ходит дальше?

    private fun player1turn() {
        println(">Игрок [${player2.playerName}] завершил ход и взял [${player2.currentScore}].")
        println(Text.mainScoreText)
        println(Text.nextTurnText)
        player1.currentScore = 0
        player1.roll()
    }

    //Ход первого игрока
    private fun player2turn() {
        println(">Игрок [${player1.playerName}] завершил ход и взял [${player1.currentScore}].")
        println(Text.mainScoreText)
        println(Text.nextTurnText)
        player2.currentScore = 0
        player2.roll()
    }

    //Ход второго игрока
    fun endGame(playerName: String, mainScore: Int, lastRoll: Int) {
        println(">Игрок [$playerName] бросает кубик. Выпало [$lastRoll].")
        println("(!!!)Игрок [$playerName] завершает игру, набрав [$mainScore]!")
        player1.currentScore = 0
        player2.currentScore = 0

        println("Играем еще?")
        if (readln().lowercase() == "y") startGame()
        else {
            println("Bye")
            exitProcess(1)
        }
    }

    //Конец

}