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
class TreeNode(private val token: Token, private var _children: Map[String, TreeNode]) {
    def this(token: Token) = {
        this(token = token, _children = Map[String, TreeNode]())
    }

    def children = _children
    def children_=(_children: Map[String, TreeNode]) = this._children = _children

    def addChild(token: Token): Unit = {
        this.children = this.children + (token.toString -> new TreeNode(token))
    }
}
