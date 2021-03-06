/*
// Licensed to Julian Hyde under one or more contributor license
// agreements. See the NOTICE file distributed with this work for
// additional information regarding copyright ownership.
//
// Julian Hyde licenses this file to you under the Apache License,
// Version 2.0 (the "License"); you may not use this file except in
// compliance with the License. You may obtain a copy of the License at:
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
*/
package org.eigenbase.rel;

/**
 * A <code>RelVisitor</code> is a Visitor role in the {@link
 * org.eigenbase.util.Glossary#VisitorPattern visitor pattern} and visits {@link
 * RelNode} objects as the role of Element. Other components in the pattern:
 * {@link RelNode#childrenAccept(RelVisitor)}.
 *
 * @author jhyde
 * @version $Id$
 * @since 22 September, 2001
 */
public abstract class RelVisitor
{
    //~ Instance fields --------------------------------------------------------

    private RelNode root;

    //~ Methods ----------------------------------------------------------------

    /**
     * Visits a node during a traversal.
     *
     * @param node Node to visit
     * @param ordinal Ordinal of node within its parent
     * @param parent Parent of the node, or null if it is the root of the
     * traversal
     */
    public void visit(
        RelNode node,
        int ordinal,
        RelNode parent)
    {
        node.childrenAccept(this);
    }

    /**
     * Replaces the root node of this traversal.
     *
     * @param node The new root node
     */
    public void replaceRoot(RelNode node)
    {
        this.root = node;
    }

    /**
     * Starts an iteration.
     */
    public RelNode go(RelNode p)
    {
        this.root = p;
        visit(p, 0, null);
        return root;
    }
}

// End RelVisitor.java
