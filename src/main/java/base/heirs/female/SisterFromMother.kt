package base.heirs.female

import base.divider.*
import base.heirs.GetsRemaining
import base.heirs.Heir
import base.heirs.containsBranch
import base.heirs.containsMaleBranch
import base.heirs.female.*
import base.heirs.male.*

class SisterFromMother: Heir(){
    companion object {
        var ID = "`9"
    }
    override var id = ID
    override var gender = "F"
    override val arabicName = "الأخت من الأم"
    override val blockers = listOf(Father.ID, GrandFather.ID)

    override fun isBlocked(heirs: List<Heir>): Boolean {
        return super.isBlocked(heirs) || heirs.containsBranch()
    }
    override fun calculateShare(heirs: List<Heir>) {
        if(heirs.count{ it is SisterFromMother  } > 1)
            share = Third()
        else
            share = Sixth()
    }
}