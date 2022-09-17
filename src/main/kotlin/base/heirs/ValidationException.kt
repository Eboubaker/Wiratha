package base.heirs

class ValidationException(message: String) : RuntimeException(message) {
    companion object {
        fun error(message: String): Nothing {
            throw ValidationException(message)
        }
    }
}
