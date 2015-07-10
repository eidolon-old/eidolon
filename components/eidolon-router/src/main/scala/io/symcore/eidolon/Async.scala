/**
 * This file is part of the "Default (Template) Project" project.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the LICENSE is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package io.symcore.eidolon

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

/**
 * Async
 *
 * @author Elliot Wright <elliot@elliotwright.co>
 */
object Async {
    def await[T](future: Future[T]): T = {
        val timeout = 10 seconds

        Await.result(future, timeout)
    }
}
