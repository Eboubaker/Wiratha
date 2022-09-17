package base.heirs.male

import base.divider.Divider
import base.divider.Sixth
import base.divider.Third
import base.heirs.Heir
import base.heirs.containsBranch
import base.heirs.female.GrandMotherFromFather
import base.heirs.female.GrandMotherFromMother
import base.heirs.female.Mother

class BrotherFromMother : Heir() {
    companion object {
        var ID = "8"
    }

    override var id = ID
    override var gender = "M"
    override val arabicName = "الأخ من الأم"
    override val blockers = listOf(Father.ID, GrandFather.ID)

    override fun isBlocked(heirs: List<Heir>): Boolean {
        return super.isBlocked(heirs) || heirs.containsBranch()
    }

    override fun calculateShare(heirs: List<Heir>) {
        val selfCount = heirs.count { it is BrotherFromMother }
        val isHimaria = heirs.any { it is Husband }
                && heirs.any { it is Mother || it is GrandMotherFromMother || it is GrandMotherFromFather }
                && heirs.any { it is Brother }
                && heirs.count { it is BrotherFromMother } > 1
        val getsRemainingBrothers = heirs.count { it is Brother }
        share = if (isHimaria && selfCount > 1) {
            if (getsRemainingBrothers > 0) {
                Third().multiply(Divider(1, selfCount + getsRemainingBrothers))
            } else {
                Third().multiply(Divider(1, selfCount))
            }
        } else {
            Sixth()
        }
    }
}
