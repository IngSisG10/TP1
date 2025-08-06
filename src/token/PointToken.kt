package token

class PointToken(
    override val row: Int,
    override val position: Int
) : TokenInterface {
    override val name: String = "point"
    override val value: String = "."

    override fun instruction(): String {
        TODO("Not yet implemented")
    }
}