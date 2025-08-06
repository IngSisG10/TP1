package token

class VariableToken(
    override val value: String,
    override val row: Int,
    override val position: Int
) : TokenInterface {
    override val name: String = "variable"

    override fun instruction(): String {
        return value
    }
}