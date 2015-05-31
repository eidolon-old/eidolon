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

package io.symcore.eidolon.component.router.compilation

import io.symcore.eidolon.component.router.compilation.Lexer.Token
import io.symcore.eidolon.component.router.tree.{Tree, TokenForest, TokenTree}

import scala.collection.immutable.Queue

/**
 * Parser
 *
 * @author Elliot Wright <elliot@elliotwright.co>
 */
class Parser {
    def parse(forest: TokenForest, tokens: Queue[Token]): Unit = {
        var stream = tokens
        var currentNode: Tree = forest

        while (stream.nonEmpty) {
            val (token, remaining) = stream.dequeue

            if (!currentNode.hasChild(token)) {
                currentNode.addChild(new TokenTree(token))
            }

            currentNode = currentNode.getChild(token)

            stream = remaining
        }
    }
}
