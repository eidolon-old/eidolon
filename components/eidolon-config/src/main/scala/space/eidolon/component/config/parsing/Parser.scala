/**
 * This file is part of the "eidolon" project.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the LICENSE is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package space.eidolon.component.config.parsing

/**
 * Parser
 *
 * @author Elliot Wright <elliot@elliotwright.co>
 */
class Parser {
  private var context: ParserContext = _

  private final val SOT_CHAR = '\u0002'
  private final val EOT_CHAR = '\u0003'


  def parse(input: String): Unit = {
    context = new ParserContext(input.toCharArray)

    read()
    skipWhitespace()

    while (!isEndOfText) {
      readItem()
      skipWhitespace()
    }

    println("End of input")
  }

  private def readItem(): Unit = {
    val property = readProperty()

    require(':')
    require(' ')
    skipWhitespace()
    read()

    val value = readValue()

    read()
    skipWhitespace()
    // todo: require either comma or new line

    println(property)
    println(value)
  }

  private def readProperty(): String = {
    startCaputure()
    read()
    while (context.next != ':') {
      if (isAlphaNumeric || isUnderscore) {
        read()
      } else {
        expected("an alphanumeric string, or an underscore, got '" + context.current.toString + "'")
      }
    }
    endCapture()
  }

  private def readValue(): String = context.current match {
    case '"' => {
      startCaputure()
      read()
      while (context.next != '"') {
        read()
      }
      val result = endCapture()
      require('"')
      result
    }
    case '{' => {
      startCaputure()
      read()
      while (context.next != '}') {
        read()
      }
      val result = endCapture()
      require('}')
      result
    }
  }

  private def isEndOfText: Boolean = {
    context.current == EOT_CHAR
  }

  private def isAlphaNumeric: Boolean = {
    context.current.isLetterOrDigit
  }

  private def isNewLine: Boolean = {
    context.current == '\n' || context.current == '\r'
  }

  private def isNewLine(character: Char): Boolean = {
    character == '\n' || character == '\r'
  }

  private def isSpace: Boolean = {
    context.current == ' '
  }

  private def isSpace(character: Char): Boolean = {
    character == ' '
  }

  private def isTab: Boolean = {
    context.current == '\t'
  }

  private def isTab(character: Char): Boolean = {
    character == '\t'
  }

  private def isUnderscore: Boolean = {
    context.current == '_'
  }

  private def isUnderscore(character: Char): Boolean = {
    character == '_'
  }

  private def isWhitespace: Boolean = {
    isNewLine || isSpace || isTab
  }

  private def isWhitespace(character: Char): Boolean = {
    isNewLine(character) || isSpace(character) || isTab(character)
  }

  private def skipWhitespace(): Unit = {
    while (isWhitespace) {
      read()
    }
  }

  private def read(): Unit = {
    if (isEndOfText) {
      return
    }

    if (context.cursor >= context.buffer.length) {
      context.current = EOT_CHAR
      return
    }

    if ((context.cursor - 1) >= 0) {
      context.prev = context.buffer(context.cursor - 1)
    } else {
      context.prev = SOT_CHAR
    }

    if ((context.cursor + 1) <= (context.buffer.length - 1)) {
      context.next = context.buffer(context.cursor + 1)
    } else {
      context.next = EOT_CHAR
    }

    if (isNewLine(context.prev)) {
      context.line += 1
      context.lineCursor = 0
    }

    context.current = context.buffer(context.cursor)
    context.cursor += 1
    context.lineCursor += 1

    if (context.capturing) {
      context.captureBuffer += context.current
    }
  }

  private def startCaputure(): Unit = {
    context.capturing = true
    context.captureBuffer = new StringBuilder()
  }

  private def endCapture(): String = {
    context.capturing = false
    context.captureBuffer.toString()
  }

  private def require(character: Char): Unit = {
    read()
    if (context.current != character) {
      expected(character.toString)
    }
  }

  private def expected(message: String): Unit = {
    throw error("Expected " + message)
  }

  private def error(message: String): ParserException = {
    new ParserException(
      message,
      context.line,
      context.lineCursor
    )
  }


  private case class ParserContext(
      buffer: Array[Char],

      var prev: Char = SOT_CHAR,
      var current: Char = SOT_CHAR,
      var next: Char = EOT_CHAR,

      var capturing: Boolean = false,
      var captureBuffer: StringBuilder = new StringBuilder(),

      var cursor: Int = 0,
      var savedCursor: Int = 0,

      var line: Int = 1,
      var lineCursor: Int = 0) {

    def restoreCursor(): Unit = {
      cursor = savedCursor
    }

    def saveCursor(): Unit = {
      savedCursor = cursor
    }
  }


  private class ParserException(
      message: String,
      line: Int,
      column: Int)
    extends RuntimeException(message + " @ " + line + ":" + column)
}
