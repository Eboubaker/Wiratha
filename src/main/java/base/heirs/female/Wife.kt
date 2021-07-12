package base.heirs.female

import base.divider.Eighth
import base.divider.Quarter
import base.heirs.Heir
import base.heirs.containsBranch

class Wife: Heir() {
    companion object {
        var ID = "`1"
    }
    override var id = ID
    override var gender = "F"
    override val arabicName = "الزوجة"

    override fun calculateShare(heirs: List<Heir>) {
        share = if(heirs.containsBranch())
            Eighth()
        else
            Quarter()
    }

}