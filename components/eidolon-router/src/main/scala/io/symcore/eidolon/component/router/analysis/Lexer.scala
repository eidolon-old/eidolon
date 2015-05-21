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

package io.symcore.eidolon.component.router.analysis

import scala.annotation.tailrec

/**
 * Lexer
 *
 * @author Elliot Wright <elliot@elliotwright.co>
 */
class Lexer {

    import Lexer._

    /**
     * Convert a path to a token stream
     *
     * @param path Path to analyse
     * @return Token stream
     */
    def tokenise(path: String): List[Token] = {
        var stream = List[Token]()

        @tailrec
        def impl(path: String): Unit = {
            path.isEmpty match {
                case true =>
                case false =>
                    val token = getToken(path)
                    stream = stream :+ token
                    impl(path.replaceFirst(token.value, ""))
            }
        }

        impl(path)
        stream
    }

    /**
     * Get a token from the start of a given path
     *
     * @param path Path to test against
     * @return Token, with it's assigned value
     */
    def getToken(path: String): Token = {
        path match {
            case RegexHyphen(c) => new T_HYPHEN(c)
            case RegexPathSeperator(c) => new T_PATH_SEPERATOR(c)
            case RegexString(c) => new T_STRING(c)
            case RegexStringAny(c) => new T_STRING_ANY(c)
            case RegexUnderscore(c) => new T_UNDERSCORE(c)
            case _ =>
                throw new RuntimeException(
                    String.format("Invalid route character found in path \"%s\".", path)
                )
        }
    }
}

/**
 * Lexer Companion Object
 *
 * @author Elliot Wright <elliot@elliotwright.co>
 */
object Lexer {
    final val RegexHyphen = """^([-])""".r.unanchored
    final val RegexPathSeperator = """^(/)""".r.unanchored
    final val RegexString = """^([A-z]*)""".r.unanchored
    final val RegexStringAny = """^(:[A-z]*)""".r.unanchored
    final val RegexUnderscore = """^([_])""".r.unanchored

    trait Token {
        val value: String
    }

    case class T_HYPHEN(value: String) extends Token
    case class T_PATH_SEPERATOR(value: String) extends Token
    case class T_STRING(value: String) extends Token
    case class T_STRING_ANY(value: String) extends Token
    case class T_UNDERSCORE(value: String) extends Token
}
