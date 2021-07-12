package base.heirs

import base.heirs.male.*
import base.heirs.female.*

fun idToHeir(id: String): Heir
{
    return when (id) {
        "1" -> Husband()
        "2" -> Son()
        "3" -> SonOfSon()
        "4" -> Father()
        "5" -> GrandFather()
        "6" -> Brother()
        "7" -> BrotherFromFather()
        "8" -> BrotherFromMother()
        "#1" -> SonOfBrother()
        "#2" -> SonOfBrotherFromFather()
        "#3" -> BrotherOfFather()
        "#4" -> BrotherOfFatherFromFather()
        "#5" -> SonOfBrotherOfFather()
        "#6" -> SonOfBrotherOfFatherFromFather()
        "`1" -> Wife()
        "`2" -> Daughter()
        "`3" -> DaughterOfSon()
        "`4" -> Mother()
        "`5" -> GrandMotherFromFather()
        "`6" -> GrandMotherFromMother()
        "`7" -> Sister()
        "`8" -> SisterFromFather()
        "`9" -> SisterFromMother()
        else -> error("Wrong id")
    }
}