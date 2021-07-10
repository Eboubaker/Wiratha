package base.heirs.male

import base.divider.Divider
import base.divider.Half
import base.divider.Quarter
import base.heirs.GetsRemaining
import base.heirs.Heir
import base.heirs.containsBranch
import base.heirs.female.*
import base.heirs.male.*

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
        if(getsRemaining && remaining.dividened < remaining.divider)
        {
            var daughtersCount = heirs.count { it is Daughter }
            var sonsCount = heirs.count{ it is Son}

            share = Divider(remaining.dividened * 2, remaining.divider*(2*sonsCount+daughtersCount)).reduced()
        }
    }

}