import lexer.Lexer

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
//    val lexer = Lexer("let a: Number = 44 + 56;\n " +
//                            "let name: String = (\"Jhon Doe\"); \n" +
//                            "a = a - 50;\n" +
//                            "println(\"El numero es:\" + a);"
//    )

    val lexer = Lexer("-50 + 50")

    val tokens = lexer.lex()
    for (token in tokens) {
        println(token.name + ": " + token.value)
    }
}