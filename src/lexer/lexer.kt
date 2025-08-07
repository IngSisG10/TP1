package lexer

import token.*
import token.Function
import token.abs.TokenInterface

class Lexer(
    private val code: String
) {

    private val listOfTokens = mutableListOf<TokenInterface>()

    private val stringRegex = Regex("\"(.*?)\"") // Finds text inside " "

    private val numberRegex = Regex("\\d+") // Finds int numbers (only positive)

    fun lex(): List<TokenInterface> {
        val listOfLines: List<String> = this.splitIntoLines(code)
        for ((index, line) in listOfLines.withIndex()) {
            if (line.isNotBlank()) {
                tokenizeLine(line, index + 1)
            }
        }
        return listOfTokens
    }

    private fun splitIntoLines(code: String): List<String> {
        return code
            .split("\n")
    }

    private fun tokenizeLine(line: String, row: Int) {
        var i = 0
        val length = line.length

        while (i < length) {
            val c = line[i]

            when {
                c.isWhitespace() -> { // " "
                    i++
                }

                this.consumeStringToken(line, row, i)?.also { i = it } != null -> {}

                this.consumeNumberToken(line, row, i)?.also { i = it } != null -> {}

                line.startsWith("println", i) -> {
                    listOfTokens.add(FunctionToken(Function.PRINTLN, row, i))
                    i += 7
                }

                line.startsWith("let", i) -> {
                    listOfTokens.add(VariableDeclaratorToken(row, i))
                    i += 3
                }

                line.startsWith("String", i) -> {
                    listOfTokens.add(TypeToken( Type.STRING, row, i))
                    i += 6
                }

                line.startsWith("Number", i) -> {
                    listOfTokens.add(TypeToken( Type.NUMBER, row, i))
                    i += 6
                }

                line.startsWith("Boolean", i) -> {
                    listOfTokens.add(TypeToken( Type.BOOLEAN, row, i))
                    i += 7
                }

                line.startsWith("Any", i) -> {
                    listOfTokens.add(TypeToken( Type.ANY, row, i))
                    i += 3
                }

                c == '+' -> {
                    listOfTokens.add(OperationToken(Operation.SUM, row, i))
                    i++
                }

                c == '-' -> {
                    listOfTokens.add(OperationToken(Operation.MINUS, row, i))
                    i++
                }

                c == '*' -> {
                    listOfTokens.add(OperationToken(Operation.MULTIPLY, row, i))
                    i++
                }

                c == '/' -> {
                    listOfTokens.add(OperationToken(Operation.DIVIDE, row, i))
                    i++
                }

                c == '=' -> {
                    listOfTokens.add(EqualToken(row, i))
                    i++
                }

                c == ':' -> {
                    listOfTokens.add(TypeDeclaratorToken(row, i))
                    i++
                }

                c == '.' -> {
                    listOfTokens.add(PointToken(row, i))
                    i++
                }

                c == ';' -> {
                    listOfTokens.add(EndSentenceToken(row, i))
                    i++
                }

                c == '(' -> {
                    listOfTokens.add(ParenthesisToken(Parenthesis.OPEN, row, i))
                    i++
                }

                c == ')' -> {
                    listOfTokens.add(ParenthesisToken(Parenthesis.CLOSE, row, i))
                    i++
                }

                else -> {
                    // Detectar variables (identificadores)
                    val start = i
                    while (i < length && line[i].isLetterOrDigit()) {
                        i++
                    }
                    val text = line.substring(start, i)
                    if (text.isNotBlank()) {
                        listOfTokens.add(VariableToken(text, row, start))
                    }
                }
            }
        }
    }

    private fun consumeStringToken(line: String, row: Int, i: Int): Int? {
        val match = stringRegex.find(line, i)?.takeIf { it.range.first == i } ?: return null
        val content = match.groupValues[1]
        listOfTokens.add(StringLiteralToken(content, row, i))
        return match.range.last + 1
    }

    private fun consumeNumberToken(line: String, row: Int, i: Int): Int? {
        val match = numberRegex.find(line, i)?.takeIf { it.range.first == i } ?: return null
        val content = match.value
        listOfTokens.add(NumberLiteralToken(content.toInt(), row, i))
        return match.range.last + 1
    }
}