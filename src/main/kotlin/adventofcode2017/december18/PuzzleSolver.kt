package adventofcode2017.december18

import adventofcode2017.PuzzleSolverAbstract
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.CompletableFuture
import java.util.concurrent.LinkedBlockingDeque
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.TimeUnit

fun main() {
    PuzzleSolver(test=false).showResult()
}

class PuzzleSolver(test: Boolean) : PuzzleSolverAbstract(test) {

    override fun resultPartOne(): Any {
        val program = Program(inputLines, 0, true, LinkedBlockingDeque<Long>(), LinkedBlockingDeque<Long>())
        val output = program.runProgram()
        return output.get()
    }

    override fun resultPartTwo(): Any {
        val queue0 = LinkedBlockingDeque<Long>()
        val queue1 = LinkedBlockingDeque<Long>()

        val program0 = Program(inputLines, 0, false, queue0, queue1)
        val program1 = Program(inputLines, 1, false, queue1, queue0)

        val countSends0 = program0.runProgram()
        val countSends1 = program1.runProgram()

        return countSends1.get()
    }
}

class Program(
    private val programList: List<String>,
    private val programId: Int=0,
    private val puzzle1: Boolean=true,
    private val input: LinkedBlockingDeque<Long>,
    private val output: LinkedBlockingDeque<Long>) {

    private val register = mutableListOf<Long>(0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, programId.toLong(),0,0,0,0, 0,0,0,0,0, 0)
    private var lastPlayedFrequency = -1L
    private var ip = 0

    private var countSends = 0L


    fun runProgram(): CompletableFuture<Long> =
        CompletableFuture.supplyAsync {
            while(ip in programList.indices) {
                executeStep()
            }
            if (puzzle1) lastPlayedFrequency else countSends
        }


    private fun executeStep() {
        val statement = programList[ip].toStatement()
        val regName = if (statement.second.isRegister()) statement.second.asRegister()-'a' else -1
        val firstValue = if (statement.second.isRegister()) register[statement.second.asRegister()-'a'] else statement.second.asLong()
        val secondValue = if (statement.third.isRegister()) register[statement.third.asRegister()-'a'] else statement.third.asLong()
        when (statement.instruction()) {
            "snd" -> {
                if (puzzle1) {
                    lastPlayedFrequency = firstValue;
                    ip++
                } else {
                    //println("$programId sends value $firstValue")
                    output.put(firstValue)
                    countSends++
                    ip++
                }
            }
            "rcv" -> {
                if (puzzle1) {
                    ip = if (firstValue != 0L) -9999 else ip + 1
                } else {
                    val fromQueue = input.poll(1000, TimeUnit.MILLISECONDS)
                    if (fromQueue != null) {
                        register[regName] = fromQueue
                        //println("$programId received value ${register[regName]}")
                        ip++
                    } else {
//                        println("$programId sended: $countSends")
                        ip = -9999
                    }
                }
            }

            "set" -> {register[regName] = secondValue; ip++}
            "add" -> {register[regName] += secondValue; ip++}
            "mul" -> {register[regName] *= secondValue; ip++}
            "mod" -> {register[regName] %= secondValue; ip++}
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

