package base.heirs

import base.divider.Divider
import base.divider.sum
import base.divider.toLcm
import base.heirs.male.*
import base.heirs.female.*

fun List<Heir>.normalizeShares()
{
    map { it.share }.toLcm()
}



fun List<Heir>.containsBranch(): Boolean
{
    forEach {
        if(it is Son || it is SonOfSon || it is Daughter || it is DaughterOfSon)
            return true
    }
    return false
}
fun List<Heir>.containsMaleBranch(): Boolean
{
    return filter { it.gender == "M" }.containsBranch()
}
fun List<Heir>.containsFemaleBranch(): Boolean
{
    return filter { it.gender == "F" }.containsBranch()
}

fun MutableList<Heir>.calculate()
{
    sortBy { it.getNumericId() }
    val nonBlocked = filter { !it.isBlocked(this) }
    nonBlocked.forEach { it.calculateShare(nonBlocked) }
    var sum = nonBlocked.map { it.share }.sum()
    val remaining = Divider(sum.divider - sum.dividend, sum.divider)
    val remainingGetters = nonBlocked.filter {it is GetsRemaining && it.getsRemaining}
    remainingGetters.forEach {
        if(it is GetsRemaining)
            it.calculateRemaining(remaining, remainingGetters)
    }
    normalizeShares()
    sum = nonBlocked.map { it.share }.sum()
    if(sum.dividend < sum.divider)
    {
        val alive = nonBlocked.find { it is Husband } ?: nonBlocked.find { it is Wife }
        if(alive != null)
        {
            alive.share = alive.share.reduced()
            val remainingShare = alive.share.remaining()
            val normalHeirs = filterNot { it is Wife || it is Husband }.toMutableList()
            normalHeirs.calculate()
            normalHeirs.forEach {
                it.share = it.share.multiply(remainingShare)
            }
        }else{
            map { it.share }.forEach { it.divider = sum.dividend }
        }
        normalizeShares()
    }
    sortByDescending { it.share.dividend }
}
