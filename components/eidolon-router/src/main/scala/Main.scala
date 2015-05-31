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

import io.symcore.eidolon.component.router.compilation.{Lexer, Parser}
import io.symcore.eidolon.component.router.tree.TokenForest

import scala.collection.immutable.Queue

/**
 * Main
 *
 * @author Elliot Wright <elliot@elliotwright.co>
 */
object Main {
    private val forest = new TokenForest
    private val lexer = new Lexer
    private val parser = new Parser
    private var routes = Queue[String]()

    def main(args: Array[String]) {
        this.routes = this.routes.enqueue("/foo/bar")
        this.routes = this.routes.enqueue("/foo/:one")
        this.routes = this.routes.enqueue("/baz/qux")

        while (this.routes.nonEmpty) {
            val (route, remaining) = this.routes.dequeue

            this.parser.parse(this.forest, this.lexer.tokenise(route))
            this.routes = remaining
        }

        println("Done")
    }
}
