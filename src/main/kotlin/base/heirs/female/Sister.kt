package base.heirs.female

import base.divider.Divider
import base.divider.Half
import base.heirs.*
import base.heirs.male.*

class Sister: Heir(), GetsRemaining {
    companion object {
        var ID = "`7"
    }
    override var id = ID
    override var gender = "F"
    override val arabicName = "الأخت الشقيقة"
    override val blockers = listOf(Father.ID)

    override fun isBlocked(heirs: List<Heir>): Boolean {
        return super.isBlocked(heirs) || heirs.containsMaleBranch()
    }
    override fun calculateShare(heirs: List<Heir>) {
        if(heirs.containsFemaleBranch()){
            getsRemaining = true
            innervationType = InterventionType.WITH_OTHERS
        }else if(heirs.any { it is Brother }) {
            getsRemaining = true
            innervationType = InterventionType.CAUSE_OF_OTHERS
        }else{
            val sistersCount = heirs.count { it is Sister }
            share = if(sistersCount == 1)
                Half()
            else
                Divider(2, 3 * sistersCount)
        }
    }

    override fun calculateRemaining(remaining: Divider, heirs: List<Heir>) {
        if(getsRemaining && remaining.dividend <= remaining.divider)
        {
            val sistersCount = heirs.count { it is Sister }
            val brothersCount = heirs.count{ it is Brother}

            share = Divider(remaining.dividend, remaining.divider*(2*brothersCount+sistersCount)).reduced()
        }
    }

}
