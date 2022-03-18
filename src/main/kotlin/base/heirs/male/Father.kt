package base.heirs.male

import base.divider.Divider
import base.divider.Sixth
import base.heirs.GetsRemaining
import base.heirs.Heir
import base.heirs.containsMaleBranch

class Father: Heir(), GetsRemaining {
    companion object {
        var ID = "4"
    }
    override var id = ID
    override var gender = "M"
    override val arabicName = "الأب"

    override fun calculateShare(heirs: List<Heir>) {
        if(heirs.containsMaleBranch())
            share = Sixth()
        else
            getsRemaining = true
    }

    override fun calculateRemaining(remaining: Divider, heirs: List<Heir>) {
        if(getsRemaining && remaining.dividend <= remaining.divider)
        {
            share = remaining
        }
    }

}
