package tool.mylambdas.collectioncombination

fun <T> List<T>.mapCombinedItems(): List<Pair<T, T>> {
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

inline fun <T> List<T>.firstOrNullCombinedItems(combinedFilterCondition: (T, T) -> Boolean): Pair<T, T>? {
    for (i in 0 until this.size-1) {
        for (j in i+1 until this.size) {
            if (combinedFilterCondition(this[i], this[j]))
                return Pair(this[i], this[j])
        }
    }
    return null
}

inline fun <T> List<T>.lastOrNullCombinedItems(combinedFilterCondition: (T, T) -> Boolean): Pair<T, T>? {
    for (i in this.size-2 downTo 0) {
        for (j in this.size-1 downTo i+1) {
            if (combinedFilterCondition(this[i], this[j]))
                return Pair(this[i], this[j])
        }
    }
    return null
}


inline fun <T> List<T>.anyCombinedItems(combinedFilterCondition: (T, T) -> Boolean): Boolean {
    for (i in 0 until this.size-1) {
        for (j in i+1 until this.size) {
            if (combinedFilterCondition(this[i], this[j]))
                return true
        }
    }
    return false
}

inline fun <T> List<T>.allCombinedItems(combinedFilterCondition: (T, T) -> Boolean): Boolean {
    return !this.anyCombinedItems { t1, t2 -> !combinedFilterCondition(t1, t2) }
}

inline fun <T> List<T>.noneCombinedItems(combinedFilterCondition: (T, T) -> Boolean): Boolean {
    return !this.anyCombinedItems { t1, t2 -> combinedFilterCondition(t1, t2) }
}



//fun <S, T> List<T>.mapCombinedAll(combineOperation: (T, T) -> S): Sequence<S> = sequence {
//    for (i in 0 until list.size -1) {
//        for (j in i+1 until list.size) {
//            yield(combineOperation(list[i], list[j]))
//        }
//    }
//}