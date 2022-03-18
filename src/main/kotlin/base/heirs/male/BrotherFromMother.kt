package base.heirs.male

import base.divider.Sixth
import base.divider.Third
import base.heirs.Heir
import base.heirs.containsBranch

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
        share = if (heirs.count { it is BrotherFromMother } > 1)
            Third()
        else
            Sixth()
    }
}
