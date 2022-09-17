package base.heirs

import base.divider.Divider
import base.divider.sum
import base.divider.toLcm
import base.heirs.female.Daughter
import base.heirs.female.DaughterOfSon
import base.heirs.female.Wife
import base.heirs.male.Husband
import base.heirs.male.Son
import base.heirs.male.SonOfSon
import dnl.utils.text.table.TextTable

fun List<Heir>.normalizeShares() {
    map { it.share }.toLcm()
}


fun List<Heir>.containsBranch(): Boolean {
    forEach {
        if (it is Son || it is SonOfSon || it is Daughter || it is DaughterOfSon)
            return true
    }
    return false
}

fun List<Heir>.containsMaleBranch(): Boolean {
    return filter { it.gender == "M" }.containsBranch()
}

fun List<Heir>.containsFemaleBranch(): Boolean {
    return filter { it.gender == "F" }.containsBranch()
}

fun List<Heir>.printSharesTable() {
    TextTable(
        arrayOf("heir", "share", "share%"),
        map { arrayOf(it::class.java.simpleName, it.share, it.share.getValue()) }.toTypedArray()
    ).printTable()
}

fun MutableList<Heir>.calculate() {
    if (count { it is Wife || it is Husband } > 1) {
        throw ValidationException("Wife and Husband cannot co-exist and cannot be repeated")
    }
    if (isEmpty()) {
        throw ValidationException("no Heirs given")
    }
    sortBy { it.getNumericId() }
    val nonBlocked = filter { !it.isBlocked(this) }
    nonBlocked.forEach { it.calculateShare(nonBlocked) }
    var sum = map { it.share }.sum()
    if (sum.dividend < sum.divider) {
        val remaining = Divider(sum.divider - sum.dividend, sum.divider)
        val remainingGetters = nonBlocked.filter { it is GetsRemaining && it.getsRemaining }
        remainingGetters.forEach {
            if (it is GetsRemaining)
                it.calculateRemaining(remaining, remainingGetters)
        }
    }
    sum = map { it.share }.sum()
    if (sum.dividend < sum.divider) {
        val alive = nonBlocked.find { it is Husband } ?: nonBlocked.find { it is Wife }
        if (alive != null) {
            alive.share = alive.share.reduced()
            val remainingShare = alive.share.remaining()
            val normalHeirs = filterNot { it is Wife || it is Husband }.toMutableList()
            normalHeirs.calculate()
            normalHeirs.forEach {
                it.share = it.share.multiply(remainingShare)
            }
        } else {
            map { it.share }.forEach { it.divider = sum.dividend }
        }
    }
    normalizeShares()
    sum = map { it.share }.sum()
    // تحقق من حالة العول
    if (sum.dividend > sum.divider) {
        forEach { it.share.divider = sum.dividend }
    }

    sortByDescending { it.share.dividend }
}
