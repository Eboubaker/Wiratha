package base.heirs.female

import base.divider.Divider
import base.divider.Half
import base.divider.Quarter
import base.heirs.GetsRemaining
import base.heirs.Heir
import base.heirs.containsBranch
import base.heirs.female.*
import base.heirs.male.*

class Daughter: Heir(), GetsRemaining {
    companion object {
        var ID = "`2"
    }
    override var id = ID
    override var gender = "F"
    override val arabicName = "البنت"

    override fun calculateShare(heirs: List<Heir>) {
        if(heirs.count { it is Son } > 0)
            getsRemaining = true
        else{
            val daughtersCount = heirs.count { it is Daughter }
            if(daughtersCount == 1)
                share = Half()
            else
                share = Divider(2, 3 * daughtersCount)
        }
    }

    override fun calculateRemaining(remaining: Divider, heirs: List<Heir>) {
        if(getsRemaining && remaining.dividened < remaining.divider)
        {
            var daughtersCount = heirs.count { it is Daughter }
            var sonsCount = heirs.count{ it is Son}

            share = Divider(remaining.dividened, remaining.divider*(2*sonsCount+daughtersCount)).reduced()
        }
    }

}