package base.heirs.female

import base.divider.Sixth
import base.heirs.Heir
import base.heirs.male.Father

class GrandMotherFromMother: Heir() {
    companion object {
        var ID = "`6"
    }
    override var id = ID
    override var gender = "F"
    override val arabicName = "الجدة من الأم"
    override val blockers = listOf(Father.ID)

    override fun calculateShare(heirs: List<Heir>) {
        share = Sixth()
    }
}
