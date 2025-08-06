package token

class EqualToken(
    override val row: Int,
    override val position: Int
) : TokenInterface {
    override val name: String = "equal"
    override val value: String = "=";

    override fun instruction(): String {
        return value
    }
}