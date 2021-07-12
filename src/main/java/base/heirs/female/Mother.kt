package base.heirs.female

import base.divider.*
import base.heirs.GetsRemaining
import base.heirs.Heir
import base.heirs.containsBranch
import base.heirs.containsMaleBranch
import base.heirs.female.*
import base.heirs.male.*

class Mother: Heir(){
    companion object {
        var ID = "`4"
    }
    override var id = ID
    override var gender = "F"
    override val arabicName = "الأم"

    override fun calculateShare(heirs: List<Heir>) {
        val brothersCount = heirs.count { it is Brother || it is BrotherFromFather || it is BrotherFromMother }
        if (heirs.containsBranch() || brothersCount > 1)
            share = Sixth()
        else
            share = Third()
    }
}