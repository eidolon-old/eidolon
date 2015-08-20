import space.eidolon.component.config.parsing.Parser

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

/**
 * Main
 *
 * @author Elliot Wright <elliot@elliotwright.co>
 */
object Main {
  def main(args: Array[String]) {
    val parser = new Parser()

    parser.parse(
      """   string: "is a string"
        |object: {
        |  key: "value"
        |  int: 1234567
        |}""".stripMargin)



    parser.parse(
      """string: "is a string"
        |object: {
        |  key: "value"
        |  int: 1234567
        |}""".stripMargin)
  }
}