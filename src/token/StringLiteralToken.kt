package token

import token.abs.TokenInterface

class StringLiteralToken(
    override val value: String,
    override val row: Int,
    override val position: Int
) : TokenInterface {
    override val name: String = "string_literal"
}