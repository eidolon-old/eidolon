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

package io.symcore.eidolon.component.router.tree

import io.symcore.eidolon.component.router.compilation.Lexer.Token

/**
 * Tree
 *
 * @author Elliot Wright <elliot@elliotwright.co>
 */
private[router] trait Tree {
    private var _children = Map[String, TokenTree]()

    def children = _children

    def addChild(node: TokenTree) = this._children = this._children + (node.token.toString -> node)
    def getChild(node: TokenTree) = this.children(node.token.toString)
    def getChild(token: Token) = this.children(token.toString)
    def hasChild(node: TokenTree) = this.children.get(node.token.toString).isDefined
    def hasChild(token: Token) = this.children.get(token.toString).isDefined
    def hasChildren = this.children.nonEmpty
    def removeChild(node: TokenTree) = this._children = this._children - node.token.toString
    def removeCHild(token: Token) = this._children = this._children - token.toString
}
