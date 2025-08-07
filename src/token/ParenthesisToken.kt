package token

import token.abs.TokenInterface

enum class Parenthesis {
    OPEN,
    CLOSE
}

class ParenthesisToken(
    override val value: Parenthesis,
    override val row: Int,
    override val position: Int
) : TokenInterface {
    override val name: String= "parenthesis"
}
