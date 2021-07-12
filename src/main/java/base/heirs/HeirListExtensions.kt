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
    var nonBlocked = filter { !it.isBlocked(this) }
    nonBlocked.forEach { it.calculateShare(nonBlocked) }
    var sum = nonBlocked.map { it.share }.sum()
    var remaining = Divider(sum.divider - sum.dividened, sum.divider)
    var remainingGetters = nonBlocked.filter {it is GetsRemaining && it.getsRemaining}
    remainingGetters.forEach {
        if(it is GetsRemaining)
            it.calculateRemaining(remaining, remainingGetters)
    }
    normalizeShares()
    sum = nonBlocked.map { it.share }.sum()
    if(sum.dividened < sum.divider)
    {
        var alive = nonBlocked.find { it is Husband } ?: nonBlocked.find { it is Wife }
        if(alive != null)
        {
            alive.share = alive.share.reduced()
            var remainingShare = alive.share.remaining()
            var normalHeirs = filterNot { it is Wife || it is Husband }.toMutableList()
            normalHeirs.calculate()
            normalHeirs.forEach {
                it.share = it.share.mult(remainingShare)
            }
        }else{
            map { it.share }.forEach { it.divider = sum.dividened }
        }
        normalizeShares()
    }
    sortByDescending { it.share.dividened }
}