package token

class TypeToken(
    override val value: String,
    override val row: Int,
    override val position: Int
) : TokenInterface {
    override val name: String = "type";

    override fun instruction(): String {
        return value
    }
}