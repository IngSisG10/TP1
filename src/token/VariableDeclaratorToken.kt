package token


class VariableDeclaratorToken(
    override val row: Int,
    override val position: Int
) : TokenInterface {

    override val name: String = "variable_declarator";
    override val value: String = "let";

    //   Tendria que decirle al interpreter que tiene que guardar una variable
    override fun instruction(): String {
        TODO("Not yet implemented")
    }
}

