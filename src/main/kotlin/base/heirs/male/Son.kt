package base.heirs.male

import base.divider.Divider
import base.heirs.GetsRemaining
import base.heirs.Heir
import base.heirs.female.*

class Son: Heir(), GetsRemaining {
    companion object {
        var ID = "2"
    }
    override var id = ID
    override var gender = "M"
    override val arabicName = "الإبن"

    override fun calculateShare(heirs: List<Heir>) {
        getsRemaining = true
    }

    override fun calculateRemaining(remaining: Divider, heirs: List<Heir>) {
        if(getsRemaining && remaining.dividend <= remaining.divider)
        {
            val daughtersCount = heirs.count { it is Daughter }
            val sonsCount = heirs.count{ it is Son}

            share = Divider(remaining.dividend * 2, remaining.divider*(2*sonsCount+daughtersCount)).reduced()
        }
    }

}
