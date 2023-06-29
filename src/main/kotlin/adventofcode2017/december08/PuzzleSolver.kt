package adventofcode2017.december08

import adventofcode2017.PuzzleSolverAbstract
import kotlin.math.max

fun main() {
    PuzzleSolver(test=false).showResult()
}

class PuzzleSolver(test: Boolean) : PuzzleSolverAbstract(test) {

    val groups = inputLines.map{line -> line.split("\\s+".toRegex()).toList()}
    // b, inc, 5, if, a, >, 1
    val variableMap = (groups.map{it[0]} + groups.map{it[4]}).distinct().associateWith{Variable(it, 0)}
    val instructionList = groups
        .map{
            Instruction(variableMap[it[0]]!!, it[1], it[2].toInt(), Condition(variableMap[it[4]]!!, it[5], it[6].toInt()))
        }

    override fun resultPartOne(): Any {
        instructionList.forEach {
            it.execute()
        }
        return variableMap.values.maxOf { it.value }
    }

    override fun resultPartTwo(): Any {
        //reset variables
        variableMap.values.forEach { it.value = 0 }

        var overallMax = variableMap.values.maxOf { it.value }
        instructionList.forEach {
            it.execute()
            overallMax = max(overallMax, variableMap.values.maxOf { it.value })
        }
        return overallMax
    }

}

data class Variable(val name: String, var value: Int) {
    override fun hashCode() = name.hashCode()
    override fun equals(other: Any?) = if (other is Variable) this.name == other.name else super.equals(other)
    override fun toString() = "($name=$value)"
}

class Instruction(val variable: Variable, val operator: String, val amount: Int, val condition: Condition) {
    fun execute() {
        if (condition.isTrue()) {
            when (operator) {
                "inc" -> variable.value += amount
                "dec" -> variable.value -= amount
                else -> throw Exception("unexpected operator")
            }
        }
    }
}

class Condition(val variable: Variable, val comparator: String, val comparedWith: Int) {
    fun isTrue() : Boolean {
        return when (comparator) {
            "==" -> variable.value == comparedWith
            "!=" -> variable.value != comparedWith
            ">=" -> variable.value >= comparedWith
            "<=" -> variable.value <= comparedWith
            "<" -> variable.value < comparedWith
            ">" -> variable.value > comparedWith
            else -> throw Exception("Unexpected comparator: $comparator")
        }
    }
}


