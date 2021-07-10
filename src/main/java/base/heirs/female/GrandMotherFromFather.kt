package base.heirs.female

import base.divider.Divider
import base.divider.Half
import base.divider.Quarter
import base.divider.Sixth
import base.heirs.GetsRemaining
import base.heirs.Heir
import base.heirs.containsBranch
import base.heirs.containsMaleBranch
import base.heirs.female.*
import base.heirs.male.*

class GrandMotherFromFather: Heir() {
    companion object {
        var ID = "`5"
    }
    override var id = ID
    override var gender = "F"
    override val arabicName = "الجدة من الأب"
    override val blockers = listOf(Father.ID, Mother.ID)

    override fun calculateShare(heirs: List<Heir>) {
        share = Sixth()
    }
}