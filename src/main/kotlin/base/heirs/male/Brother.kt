package base.heirs.male

import base.divider.Divider
import base.divider.Third
import base.heirs.GetsRemaining
import base.heirs.Heir
import base.heirs.containsMaleBranch
import base.heirs.female.GrandMotherFromFather
import base.heirs.female.GrandMotherFromMother
import base.heirs.female.Mother
import base.heirs.female.Sister

class Brother : Heir(), GetsRemaining {
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
        val BrothersFromMotherCount = heirs.count { it is BrotherFromMother }
        val isHimaria = heirs.any { it is Husband }
                && heirs.any { it is Mother || it is GrandMotherFromMother || it is GrandMotherFromFather }
                && heirs.any { it is Brother }
                && heirs.count { it is BrotherFromMother } > 1
        val getsRemainingBrothers = heirs.count { it is Brother }
        if (isHimaria && BrothersFromMotherCount > 1) {
            share = Third().multiply(Divider(1, BrothersFromMotherCount + getsRemainingBrothers))
        } else {
            getsRemaining = true
        }
    }

    override fun calculateRemaining(remaining: Divider, heirs: List<Heir>) {
        if (getsRemaining && remaining.dividend <= remaining.divider) {
            val sistersCount = heirs.count { it is Sister }
            val brothersCount = heirs.count { it is Brother }

            share = Divider(remaining.dividend * 2, remaining.divider * (2 * brothersCount + sistersCount)).reduced()
        }
    }

}
