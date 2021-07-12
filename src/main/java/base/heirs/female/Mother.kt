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

    override fun isBlocked(heirs: List<Heir>): Boolean {
        return super.isBlocked(heirs)
                || heirs.count { it is Brother || it is BrotherFromFather || it is BrotherFromMother } > 1
    }
    override fun calculateShare(heirs: List<Heir>) {
        if (heirs.containsBranch() || heirs.count { it is Brother } > 1)
            share = Sixth()
        else
            share = Third()
    }
}