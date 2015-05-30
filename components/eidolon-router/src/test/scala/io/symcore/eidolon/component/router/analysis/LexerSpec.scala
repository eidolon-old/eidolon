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

import io.symcore.eidolon.component.router.analysis.Lexer._
import org.scalatest.{BeforeAndAfter, FunSpec}

/**
 * LexerSpec
 *
 * @author Elliot Wright <elliot@elliotwright.co>
 */
class LexerSpec extends FunSpec with BeforeAndAfter {
    var lexer: Lexer = _

    before {
        this.lexer = new Lexer()
    }

    describe("tokenise()") {
        it("should return a `List` of `Token`s") {
            val tokens = this.lexer.tokenise("")

            assert(tokens.isInstanceOf[List[Token]])
        }

        it("should tokenise '-' to a `T_HYPHEN` `Token`") {
            val tokens = this.lexer.tokenise("-")

            assert(tokens.head.isInstanceOf[T_HYPHEN])
        }

        it("should tokenise '/' to a `T_PATH_SEPERATOR` `Token`") {
            val tokens = this.lexer.tokenise("/")

            assert(tokens.head.isInstanceOf[T_PATH_SEPERATOR])
        }

        it("should tokenise an alphanumeric string to a `T_STRING` `Token`") {
            val tokens = this.lexer.tokenise("string")

            assert(tokens.head.isInstanceOf[T_STRING])
        }

        it("should tokenise an alphanumeric string with a preceding colon to a `T_VARIABLE` `Token`") {
            val tokens = this.lexer.tokenise(":variable")

            assert(tokens.head.isInstanceOf[T_VARIABLE])
        }

        it("should tokenise '_' to a `T_UNDERSCORE` `Token`") {
            val tokens = this.lexer.tokenise("_")

            assert(tokens.head.isInstanceOf[T_UNDERSCORE])
        }

        it("should split a string into a token stream") {
            val tokens = this.lexer.tokenise("-/foo_:bar")

            assert(tokens.length == 5)
            assert(tokens.lift(0).get.isInstanceOf[T_HYPHEN])
            assert(tokens.lift(1).get.isInstanceOf[T_PATH_SEPERATOR])
            assert(tokens.lift(2).get.isInstanceOf[T_STRING])
            assert(tokens.lift(3).get.isInstanceOf[T_UNDERSCORE])
            assert(tokens.lift(4).get.isInstanceOf[T_VARIABLE])
        }

        it("should throw an exception if an invalid character is found") {
            intercept[RuntimeException] {
                this.lexer.tokenise(".<>?!@£$%^&*()")
            }
        }
    }
}
