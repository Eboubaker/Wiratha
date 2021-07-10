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
        if(heirs.count { it is SonOfSon } > 0)
            getsRemaining = true
        else{
            val daughtersOfSonCount = heirs.count { it is DaughterOfSon }
            val sistersCount = heirs.count { it is Sister }
            if(sistersCount == 1)
                share = Sixth()
            else if(daughtersOfSonCount == 1)
                share = Half()
            else
                share = Divider(2, 3 * daughtersOfSonCount)
        }
    }

    override fun calculateRemaining(remaining: Divider, heirs: List<Heir>) {
        if(getsRemaining && remaining.dividened < remaining.divider)
        {
            var daughtersOfSonCount = heirs.count { it is DaughterOfSon }
            var sonsOfSonCount = heirs.count{ it is SonOfSon}

            share = Divider(remaining.dividened, remaining.divider*(2*sonsOfSonCount+daughtersOfSonCount)).reduced()
        }
    }

}