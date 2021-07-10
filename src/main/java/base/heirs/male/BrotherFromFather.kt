package base.heirs.male

import base.divider.Divider
import base.divider.Half
import base.divider.Quarter
import base.heirs.*
import base.heirs.female.*
import base.heirs.male.*

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
                || heirs.any { it is Sister && it.getsRemaining && it.innervationType == IntervationType.WITH_OTHERS}
    }
    override fun calculateShare(heirs: List<Heir>) {
        getsRemaining = true
    }

    override fun calculateRemaining(remaining: Divider, heirs: List<Heir>) {
        if(getsRemaining && remaining.dividened < remaining.divider)
        {
            var sistersFromFatherCount = heirs.count { it is SisterFromFather }
            var brothersFromFatherCount = heirs.count{ it is BrotherFromFather}

            share = Divider(remaining.dividened * 2, remaining.divider*(2*brothersFromFatherCount+sistersFromFatherCount)).reduced()
        }
    }
}