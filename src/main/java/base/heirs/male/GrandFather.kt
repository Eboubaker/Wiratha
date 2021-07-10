package base.heirs.male

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

class GrandFather: Heir(), GetsRemaining {
    companion object {
        var ID = "5"
    }
    override var id = ID
    override var gender = "M"
    override val arabicName = "الجد"
    override val blockers = listOf(Father.ID)
    override fun calculateShare(heirs: List<Heir>) {
        if(heirs.containsMaleBranch())
            share = Sixth()
        else
            getsRemaining = true
    }

    override fun calculateRemaining(remaining: Divider, heirs: List<Heir>) {
        if(getsRemaining && remaining.dividened < remaining.divider)
        {
            share = remaining
        }
    }

}