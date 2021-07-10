package base.heirs.male

import base.divider.Divider
import base.divider.Half
import base.divider.Quarter
import base.heirs.GetsRemaining
import base.heirs.Heir
import base.heirs.containsBranch
import base.heirs.containsMaleBranch
import base.heirs.female.*
import base.heirs.male.*

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
        if(getsRemaining && remaining.dividened < remaining.divider)
        {
            var sistersCount = heirs.count { it is Sister }
            var brothersCount = heirs.count{ it is Brother}

            share = Divider(remaining.dividened * 2, remaining.divider*(2*brothersCount+sistersCount)).reduced()
        }
    }

}