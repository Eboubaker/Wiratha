package base.heirs.male

import base.divider.Divider
import base.heirs.*
import base.heirs.female.*

class BrotherFromFather: Heir(), GetsRemaining {
    companion object {
        var ID = "7"
    }
    override var id = ID
    override var gender = "M"
    override val arabicName = "الأخ الأبوي"
    override val blockers = listOf(Father.ID)

    override fun isBlocked(heirs: List<Heir>): Boolean {
        return super.isBlocked(heirs)
                || heirs.containsMaleBranch()
                // إذا كانت عاصبة مع الغير (ملحوضة 1)
                || heirs.any { it is Sister && it.getsRemaining && it.innervationType == InterventionType.WITH_OTHERS}
    }
    override fun calculateShare(heirs: List<Heir>) {
        getsRemaining = true
    }

    override fun calculateRemaining(remaining: Divider, heirs: List<Heir>) {
        if(getsRemaining && remaining.dividend <= remaining.divider)
        {
            val sistersFromFatherCount = heirs.count { it is SisterFromFather }
            val brothersFromFatherCount = heirs.count{ it is BrotherFromFather}

            share = Divider(remaining.dividend * 2, remaining.divider*(2*brothersFromFatherCount+sistersFromFatherCount)).reduced()
        }
    }
}
