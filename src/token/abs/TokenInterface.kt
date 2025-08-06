package token.abs

data class TokenPosition(val row: Int, val pos: Int)

interface TokenInterface {
    val name: String
    val value: Any
    val row: Int
    val position: Int
    fun instruction(): String
    fun getPosition(): TokenPosition {
        return TokenPosition(row , position)
    }
}