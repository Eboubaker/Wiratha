package base.heirs.female

import base.divider.Divider
import base.divider.Half
import base.divider.Sixth
import base.heirs.GetsRemaining
import base.heirs.Heir
import base.heirs.male.*

class DaughterOfSon: Heir(), GetsRemaining {
    companion object {
        var ID = "`3"
    }
    override var id = ID
    override var gender = "F"
    override val blockers = listOf(Son.ID)
    override val arabicName = "بنت الإبن"

    override fun isBlocked(heirs: List<Heir>): Boolean {
        return super.isBlocked(heirs) || heirs.count { it is Sister } > 1
    }
    override fun calculateShare(heirs: List<Heir>) {
        @Suppress("ReplaceSizeCheckWithIsNotEmpty")
        if(heirs.count { it is SonOfSon } > 0)
            getsRemaining = true
        else{
            val daughtersOfSonCount = heirs.count { it is DaughterOfSon }
            val daughtersCount = heirs.count { it is Daughter }
            share = if(daughtersCount == 1)
                Sixth()
            else if(daughtersOfSonCount == 1)
                Half()
            else
                Divider(2, 3 * daughtersOfSonCount)
        }
    }

    override fun calculateRemaining(remaining: Divider, heirs: List<Heir>) {
        if(getsRemaining && remaining.dividend <= remaining.divider)
        {
            val daughtersOfSonCount = heirs.count { it is DaughterOfSon }
            val sonsOfSonCount = heirs.count{ it is SonOfSon}

            share = Divider(remaining.dividend, remaining.divider*(2*sonsOfSonCount+daughtersOfSonCount)).reduced()
        }
    }

}
