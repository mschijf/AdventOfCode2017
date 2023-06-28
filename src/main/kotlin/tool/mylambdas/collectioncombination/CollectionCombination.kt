package tool.mylambdas.collectioncombination


fun <T> List<T>.asCombinedItemsSequence(): Sequence<Pair<T, T>> =
    localAsCombinedItemsSequence(this)

private fun <T> localAsCombinedItemsSequence(list: List<T>): Sequence<Pair<T, T>> = sequence {
    for (i in 0 until list.size-1) {
        for (j in i+1 until list.size) {
            yield( Pair(list[i], list[j]) )
        }
    }
}

fun <T> List<T>.toCombinedItemsList(): List<Pair<T, T>> {
    val result = mutableListOf<Pair<T, T>>()
    for (i in 0 until this.size-1) {
        for (j in i+1 until this.size) {
            result += Pair(this[i], this[j])
        }
    }
    return result
}

inline fun <S, T> List<T>.mapCombinedItems(combineOperation: (T, T) -> S): List<S> {
    val result = mutableListOf<S>()
    for (i in 0 until this.size-1) {
        for (j in i+1 until this.size) {
            result += combineOperation(this[i], this[j])
        }
    }
    return result
}

inline fun <T> List<T>.filterCombinedItems(combinedFilterCondition: (T, T) -> Boolean): List<Pair<T, T>> {
    val result = mutableListOf<Pair<T, T>>()
    for (i in 0 until this.size-1) {
        for (j in i+1 until this.size) {
            if (combinedFilterCondition(this[i], this[j]))
                result += Pair(this[i], this[j])
        }
    }
    return result
}
