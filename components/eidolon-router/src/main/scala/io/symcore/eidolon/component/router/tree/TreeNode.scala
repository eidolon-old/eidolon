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
 * TreeNode
 *
 * @author Elliot Wright <elliot@elliotwright.co>
 */
trait TreeNode {
    private var _children: Map[String, ChildTreeNode] = Map[String, ChildTreeNode]()

    def children = _children

    def addChild(node: ChildTreeNode) = {
        this._children = this._children + (node.token.toString -> node)
    }

    def removeChild(node: ChildTreeNode) = {
        this._children = this._children - node.token.toString
    }
}

case class RootTreeNode() extends TreeNode
case class ChildTreeNode(token: Token) extends TreeNode
