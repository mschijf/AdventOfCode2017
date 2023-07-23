package adventofcode2017.december07

import adventofcode2017.PuzzleSolverAbstract

fun main() {
    Day07(test=false).showResult()
}

class Day07(test: Boolean) : PuzzleSolverAbstract(test) {

    private val programList = createProgramMap().values

    private fun createProgramMap(): Map<String, Program> {
        val programMap = inputLines
            .map{Program(name = it.substringBefore(" ("), weight = it.substringAfter("(").substringBefore(")").toInt() )}
            .associateBy { it.name }
        inputLines
            .filter { it.contains("->") }
            .map{Pair(it.substringBefore(" ("), it.substringAfter("-> ").split(", "))}
            .map{Pair(programMap[it.first]!!, it.second.map { childName -> programMap[childName]!! })}
            .forEach {
                it.first.addChildren(it.second)
            }
        return programMap
    }

    override fun resultPartOne(): Any {
        return programList.findRoot().name
    }

    override fun resultPartTwo(): Any {
        return programList.findRoot().findOutProgramOfBalanceOrNull()?.run{"${first.name} --> $second"}?:"Not found"
    }

    private fun Collection<Program>.findRoot() = this.first { !it.hasParent() }
}


class Program(
    val name: String,
    val weight: Int) {

    private var parent: Program? = null
    private val children = mutableListOf<Program>()

    fun findOutProgramOfBalanceOrNull(): Pair<Program, Int>? {
        if (isInBalance())
            return null
        val firstChild = children.first()
        val otherWeightChildren = children.filterNot { it.totalWeight() == firstChild.totalWeight() }
        val needToChange = if (otherWeightChildren.size == 1) otherWeightChildren.first() else children.first()

        val fix = needToChange.findOutProgramOfBalanceOrNull()
        return if (fix != null) {
            fix
        } else {
            val newWeight = needToChange.weight - (needToChange.totalWeight() - children.first { it != needToChange }.totalWeight())
            Pair(needToChange, newWeight)
        }
    }

    private fun isInBalance(): Boolean {
        return if (children.isEmpty()) {
            true
        } else {
            val aWeight = children.first().totalWeight()
            children.all { it.totalWeight() == aWeight }
        }
    }

    private var totalWeight: Int? = null
    private fun totalWeight(): Int {
        if (totalWeight == null)
            totalWeight = weight + if (children.isEmpty()) 0 else children.sumOf { it.totalWeight() }
        return totalWeight!!
    }

    fun hasParent() = parent != null
    fun addChildren(programList: List<Program>) {
        children.addAll(programList)
        children.forEach { it.parent = this }
    }

    override fun toString(): String {
        return "$name ($weight)"
    }
}


