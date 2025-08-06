package token

import token.abs.TokenInterface

class EndSentenceToken(
    override val row: Int,
    override val position: Int
) : TokenInterface {
    override val name: String = "end_sentence"
    override val value: String = ";"
    override fun instruction(): String {
        TODO("Not yet implemented")
    }
}