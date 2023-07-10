package adventofcode2017.december18

import adventofcode2017.PuzzleSolverAbstract

fun main() {
    PuzzleSolver(test=false).showResult()
}

class PuzzleSolver(test: Boolean) : PuzzleSolverAbstract(test) {

    override fun resultPartOne(): Any {
        val program = Program(inputLines)
        program.runProgram()
        return ""
    }
}

class Program(private val programList: List<String>) {
    private val register = mutableMapOf<Char,Long>()
    init {
        for (ch in 'a'..'z') { register[ch] = 0L }
    }

    private var lastPlayedFrequency = -1L
    private var ip = 0

    fun runProgram() {
        while(ip in (0 until programList.size)) {
            execute()
        }
        println(lastPlayedFrequency)
    }

    private fun execute() {
        val statement = programList[ip].toStatement()
        val regName = if (statement.second.isRegister()) statement.second.asRegister() else '0'
        val firstValue = if (statement.second.isRegister()) register[statement.second.asRegister()]!! else statement.second.asLong()
        val secondValue = if (statement.third.isRegister()) register[statement.third.asRegister()]!! else statement.third.asLong()
        println("$ip ${programList[ip]} a=${register['a']!!}, b=${register['a']!!}")
        when (statement.instruction()) {
            "snd" -> {lastPlayedFrequency = firstValue; ip++}
            "set" -> {register[regName] = secondValue; ip++}
            "add" -> {register[regName] = register[regName]!! + secondValue; ip++}
            "mul" -> {register[regName] = register[regName]!! * secondValue; ip++}
            "mod" -> {register[regName] = register[regName]!! % secondValue; ip++}
            "rcv" -> if (firstValue != 0L) ip=-9999 else ip++
            "jgz" -> if (firstValue > 0L) ip += secondValue.toInt() else ip++
            else -> throw Exception("unknown instruction")
        }
    }

    private fun String.toStatement(): Triple<String, String, String> {
        val words = this.split(" ")
        return if (words.size == 3) {
            Triple(words[0].trim(), words[1].trim(), words[2].trim())
        } else {
            Triple(words[0].trim(), words[1].trim(), "0")
        }
    }

    private fun Triple<String, String, String>.instruction() = this.first
    private fun String.isRegister() = (this.length == 1) && (this[0] in 'a' .. 'z')
    private fun String.asRegister() = this[0]
    private fun String.asLong() = this.toLong()
}

