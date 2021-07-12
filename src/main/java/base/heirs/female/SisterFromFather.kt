package base.heirs.female

import base.divider.Divider
import base.divider.Half
import base.divider.Sixth
import base.heirs.*
import base.heirs.male.*

class SisterFromFather: Heir(), GetsRemaining {
    companion object {
        var ID = "`8"
    }
    override var id = ID
    override var gender = "F"
    override val arabicName = "الأخت من أب"
    override val blockers = listOf(Father.ID)

    override fun isBlocked(heirs: List<Heir>): Boolean {
        return super.isBlocked(heirs)
                || heirs.containsMaleBranch()
                || heirs.count { it is Sister } > 1
                || heirs.any { it is Sister && it.getsRemaining && it.innervationType == IntervationType.WITH_OTHERS}
    }
    override fun calculateShare(heirs: List<Heir>) {
        when {
            heirs.containsFemaleBranch() -> {
                getsRemaining = true
                innervationType = IntervationType.WITH_OTHERS
            }
            heirs.any { it is Brother } -> {
                getsRemaining = true
                innervationType = IntervationType.CAUSE_OF_OTHERS
            }
            else -> {
                val sistersFromFatherCount = heirs.count { it is SisterFromFather }
                share = when {
                    heirs.any{ it is Sister } -> Sixth()
                    sistersFromFatherCount > 1 -> Half()
                    else -> Divider(2, 3 * sistersFromFatherCount)
                }
            }
        }
    }

    override fun calculateRemaining(remaining: Divider, heirs: List<Heir>) {
        if(getsRemaining && remaining.dividened <= remaining.divider)
        {
            var sistersFromFatherCount = heirs.count { it is SisterFromFather }
            var brothersFromFatherCount = heirs.count{ it is BrotherFromFather }

            share = Divider(remaining.dividened, remaining.divider*(2*brothersFromFatherCount+sistersFromFatherCount)).reduced()
        }
    }

}