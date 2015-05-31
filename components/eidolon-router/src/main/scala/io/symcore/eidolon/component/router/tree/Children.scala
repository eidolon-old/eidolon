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

/**
 * Children
 *
 * @author Elliot Wright <elliot@elliotwright.co>
 */
private[tree] trait Children {
    private var _children = Map[String, TokenTree]()

    def children = _children

    def addChild(node: TokenTree) = this._children = this._children + (node.token.toString -> node)
    def getChild(node: TokenTree) = this.children.get(node.token.toString)
    def hasChild(node: TokenTree) = this.children.get(node.token.toString).isDefined
    def hasChildren = this.children.nonEmpty
    def removeChild(node: TokenTree) = this._children = this._children - node.token.toString
}
