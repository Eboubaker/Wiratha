package base.heirs.female

import base.divider.Sixth
import base.divider.Third
import base.heirs.Heir
import base.heirs.containsBranch
import base.heirs.male.Father
import base.heirs.male.GrandFather

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
        share = if(heirs.count{ it is SisterFromMother  } > 1)
            Third()
        else
            Sixth()
    }
}
