package base.heirs.male

import base.divider.Half
import base.divider.Quarter
import base.heirs.Heir
import base.heirs.containsBranch

class Husband: Heir() {
    companion object {
        var ID = "1"
    }
    override var id = ID
    override var gender = "M"
    override val arabicName = "الزوج"

    override fun calculateShare(heirs: List<Heir>) {
        share = if(heirs.containsBranch())
            Quarter()
        else
            Half()
    }

}
