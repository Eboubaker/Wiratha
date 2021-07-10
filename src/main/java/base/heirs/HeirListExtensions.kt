package base.heirs

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