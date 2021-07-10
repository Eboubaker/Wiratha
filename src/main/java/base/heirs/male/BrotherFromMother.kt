package base.heirs.male

import base.divider.*
import base.heirs.GetsRemaining
import base.heirs.Heir
import base.heirs.containsBranch
import base.heirs.containsMaleBranch
import base.heirs.female.*
import base.heirs.male.*

class BrotherFromMother: Heir(){
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
        if(heirs.count{ it is BrotherFromMother  } > 1)
            share = Third()
        else
            share = Sixth()
    }
}