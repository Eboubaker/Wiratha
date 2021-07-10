package base.heirs.female

import base.divider.Half
import base.divider.Quarter
import base.divider.Sixth
import base.heirs.Heir
import base.heirs.containsBranch
import base.heirs.female.*
import base.heirs.male.*

class Wife: Heir() {
    companion object {
        var ID = "`1"
    }
    override var id = ID
    override var gender = "F"
    override val arabicName = "الزوجة"

    override fun calculateShare(heirs: List<Heir>) {
        share = if(heirs.containsBranch())
            Sixth()
        else
            Quarter()
    }

}