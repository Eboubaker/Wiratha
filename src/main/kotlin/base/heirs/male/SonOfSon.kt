package base.heirs.male

import base.divider.Divider
import base.heirs.GetsRemaining
import base.heirs.Heir
import base.heirs.female.*

class SonOfSon: Heir(), GetsRemaining {
    companion object {
        var ID = "3"
    }
    override var id = ID
    override var gender = "M"
    override val blockers = listOf(Son.ID)
    override val arabicName = "إبن الإبن"

    override fun calculateShare(heirs: List<Heir>) {
        getsRemaining = true
    }

    override fun calculateRemaining(remaining: Divider, heirs: List<Heir>) {
        if(getsRemaining && remaining.dividend <= remaining.divider)
        {
            val daughtersOfSonCount = heirs.count { it is DaughterOfSon }
            val sonsOfSonCount = heirs.count{ it is SonOfSon}

            share = Divider(remaining.dividend * 2, remaining.divider*(2*sonsOfSonCount+daughtersOfSonCount)).reduced()
        }
    }

}
