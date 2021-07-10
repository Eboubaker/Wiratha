package base.heirs.male

import base.divider.Divider
import base.divider.Half
import base.divider.Quarter
import base.heirs.GetsRemaining
import base.heirs.Heir
import base.heirs.containsBranch
import base.heirs.female.*
import base.heirs.male.*

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
        if(getsRemaining && remaining.dividened < remaining.divider)
        {
            var daughtersOfSonCount = heirs.count { it is DaughterOfSon }
            var sonsOfSonCount = heirs.count{ it is SonOfSon}

            share = Divider(remaining.dividened * 2, remaining.divider*(2*sonsOfSonCount+daughtersOfSonCount)).reduced()
        }
    }

}