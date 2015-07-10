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

import scala.async.Async._
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

    private def doSomething(delay: Int): Future[String] = Future {
        Thread.sleep(delay)
        "This was delayed by... " + delay + "ms"
    }

    private def doSomeThings(): Future[List[String]] = {
        async {
            List(
                await { doSomething(1500) },
                await { doSomething(2000) },
                await { doSomething(1000) },
                await { doSomething(1000) },
                await { doSomething(1000) },
                await { doSomething(1000) },
                await { doSomething(1000) },
                await { doSomething(1000) },
                await { doSomething(1000) },
                await { doSomething(1000) },
                await { doSomething(1000) }
            )
        }

        // Maybe make 2 functions, one which adds things to a queue, the other which adds something
        // to the queue, and then processes the whole thing, so, we add a bunch of futures, then
        // the last one is the one that calls Await on them all.
    }

    def main(args: Array[String]) {
        val things = Await.result(doSomeThings(), 10 seconds)

        try {
            things foreach {
                println(_)
            }
        } catch {
            case e: Exception => println(e.getMessage)
        }

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
