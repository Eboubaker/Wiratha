package base.heirs.female

import base.divider.Divider
import base.divider.Half
import base.heirs.GetsRemaining
import base.heirs.Heir
import base.heirs.male.Son

class Daughter : Heir(), GetsRemaining {
    companion object {
        var ID = "`2"
    }

    override var id = ID
    override var gender = "F"
    override val arabicName = "البنت"

    override fun calculateShare(heirs: List<Heir>) {
        @Suppress("ReplaceSizeCheckWithIsNotEmpty")
        if (heirs.count { it is Son } > 0)
            getsRemaining = true
        else {
            val daughtersCount = heirs.count { it is Daughter }
            share = if (daughtersCount == 1)
                Half()
            else
                Divider(2, 3 * daughtersCount)
        }
    }

    override fun calculateRemaining(remaining: Divider, heirs: List<Heir>) {
        if (getsRemaining && remaining.dividend <= remaining.divider) {
            val daughtersCount = heirs.count { it is Daughter }
            val sonsCount = heirs.count { it is Son }

            share = Divider(remaining.dividend, remaining.divider * (2 * sonsCount + daughtersCount)).reduced()
        }
    }

}
