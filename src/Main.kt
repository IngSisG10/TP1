import lexer.Lexer

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val lexer = Lexer("let a: Number = 5 + 56;\n " +
                            "let name: String = \"Jhon Doe\"")

    println(lexer.lex())
}