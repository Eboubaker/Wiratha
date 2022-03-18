package base.heirs.male

import base.divider.Divider
import base.heirs.GetsRemaining
import base.heirs.Heir
import base.heirs.containsMaleBranch
import base.heirs.female.*

class Brother: Heir(), GetsRemaining {
    companion object {
        var ID = "6"
    }
    override var id = ID
    override var gender = "M"
    override val arabicName = "الأخ الشقيق"
    override val blockers = listOf(Father.ID)

    override fun isBlocked(heirs: List<Heir>): Boolean {
        return super.isBlocked(heirs) || heirs.containsMaleBranch()
    }
    override fun calculateShare(heirs: List<Heir>) {
        getsRemaining = true
    }

    override fun calculateRemaining(remaining: Divider, heirs: List<Heir>) {
        if(getsRemaining && remaining.dividend <= remaining.divider)
        {
            val sistersCount = heirs.count { it is Sister }
            val brothersCount = heirs.count{ it is Brother}

            share = Divider(remaining.dividend * 2, remaining.divider*(2*brothersCount+sistersCount)).reduced()
        }
    }

}
