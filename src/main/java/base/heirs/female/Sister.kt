package base.heirs.female

import base.divider.Divider
import base.divider.Half
import base.divider.Quarter
import base.divider.Third
import base.heirs.*
import base.heirs.female.*
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
            innervationType = IntervationType.WITH_OTHERS
        }else if(heirs.any { it is Brother }) {
            getsRemaining = true
            innervationType = IntervationType.CAUSE_OF_OTHERS
        }else{
            val sistersCount = heirs.count { it is Sister }
            if(sistersCount > 1)
                share = Half()
            else
                share = Divider(2, 3 * sistersCount)
        }
    }

    override fun calculateRemaining(remaining: Divider, heirs: List<Heir>) {
        if(getsRemaining && remaining.dividened < remaining.divider)
        {
            var sistersCount = heirs.count { it is Sister }
            var brothersCount = heirs.count{ it is Brother}

            share = Divider(remaining.dividened, remaining.divider*(2*brothersCount+sistersCount)).reduced()
        }
    }

}