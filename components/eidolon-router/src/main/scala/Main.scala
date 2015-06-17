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

import scala.async.Async.{async, await}
import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
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

    private var slow1: Future[String] = _
    private var slow2: Future[String] = _

    private def doSomething(delay: Int): Future[String] = Future {
        Thread.sleep(delay)
        "This was delayed by... " + delay + "ms"
    }

    private def doSomeThings(): (String, String) = {
        val timeout = 11 seconds

        slow1 = doSomething(10000)
        slow2 = doSomething(7000)

        (Await.result(slow1, timeout), Await.result(slow2, timeout))
    }

    def main(args: Array[String]) {
        val (result1, result2) = doSomeThings()

        println(result1)
        println(result2)

        //        this.routes = this.routes.enqueue("/foo/bar")
        //        this.routes = this.routes.enqueue("/foo/:one")
        //        this.routes = this.routes.enqueue("/baz/qux")
        //
        //        while (this.routes.nonEmpty) {
        //            val (route, remaining) = this.routes.dequeue
        //
        //            this.parser.parse(this.forest, this.lexer.tokenise(route))
        //            this.routes = remaining
        //        }
        //
        //        println("Done")
    }
}
