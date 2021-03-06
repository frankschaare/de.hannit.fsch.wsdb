 /*
    The Jenkov JSP Tree Tag provides extra tasks for Apaches Ant build tool

    Copyright (C) 2003 Jenkov Development

    Jenkov JSP Tree Tag is free software; you can redistribute it and/or
    modify it under the terms of the GNU Lesser General Public
    License as published by the Free Software Foundation; either
    version 2.1 of the License, or (at your option) any later version.

    Jenkov JSP Tree Tag is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
    Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public
    License along with this library; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA


    Contact Details:
    ---------------------------------
    Company:     Jenkov Development - www.jenkov.com
    Project URL: http://www.jenkov.dk/projects/treetag/treetag.jsp
    Email:       info@jenkov.com
 */

package com.jenkov.tags.tree.itf;

import java.util.List;

/**
 * The ITreeNode interface represents a node in a tree.
 * @version $revision$
 * @author Jakob Jenkov
 */
public interface ITreeNode {

    /**
     * Returns the name of this node. Name is what is normally displayed by the tree tag
     * as the node text (although this is entirely up to you!).
     * @return The name of this node.
     */
    public String getName();

    /**
     * Sets the name of this node. Name is what is normally displayed by the tree tag
     * as the node text (although this is entirely up to you!).
     * @param name The name to set for this node.
     */
    public void   setName(String name);

    /**
     * Returns the id of this tree node. This id must be unique throughout the
     * tree.
     * @return The node id.
     */
    public String getId();

    /**
     * Sets the id of this tree node. This id must be unique throughout the
     * tree.
     * @param id The id to give to the node.
     */
    public void   setId(String id);

    /**
     * Returns the type of this node. Type is normally used to determine what icon etc.
     * to display for this node (although this is entirely up to you!).
     * @return The type of this node.
     */
    public String getType();

    /**
     * Sets the type of this node. Type is normally used to determine what icon etc.
     * to display for this node (although this is entirely up to you!).
     * @param type The type to set for this node.
     */
    public void   setType(String type);

    /**
     * Adds a child node to this node.
     * @param node The node to add as child.
     */
    public void   addChild(ITreeNode node);

    /**
     * Removes a child node from this node.
     * @param node The child node to remove.
     */
    public void   removeChild(ITreeNode node);


    /**
     * Removes all children from this node.
     */
    public void removeAllChildren();


    /**
     * Returns the list of children for this node.
     * @return A <code>List</code> of <code>ITreeNode</code> instances.
     */
    public List   getChildren();

    /**
     * Returns true if this node has any children.
     * @return True if this node has children, false if not.
     */
    public boolean hasChildren();
}
