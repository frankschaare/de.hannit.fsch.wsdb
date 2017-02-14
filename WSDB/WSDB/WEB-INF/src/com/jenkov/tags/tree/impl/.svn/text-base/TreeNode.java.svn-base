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

/**
 * @version $revision$
 * @author Jakob Jenkov
 */
package com.jenkov.tags.tree.impl;

import com.jenkov.tags.tree.itf.ITreeNode;

import java.util.List;
import java.util.ArrayList;

public class TreeNode implements ITreeNode{

    protected String name     = "";
    protected String id       = "";
    protected String type     = "";
    protected List   children = new ArrayList();

    public TreeNode(String id, String name){
        setId(id);
        setName(name);
    }

    public TreeNode(String id, String name, String type){
        setId(id);
        setName(name);
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void addChild(ITreeNode node) {
        this.children.add(node);
    }

    public void removeChild(ITreeNode node) {
        this.children.remove(node);
    }

    public void removeAllChildren() {
        this.children.clear();
    }

    public List getChildren() {
        return this.children;
    }

    public boolean hasChildren() {
        return getChildren().size() > 0;
    }

    public boolean equals(Object o){
        ITreeNode node = (ITreeNode) o;
        if(!this.id.equals(node.getId())){
            return false;
        }

        return true;
    }

    public String toString(){
        StringBuffer buffer = new StringBuffer();
        buffer.append("(id: ");
        buffer.append(getId());
        buffer.append(",    name: ");
        buffer.append(getName());
        buffer.append(",    type: ");
        buffer.append(")");

        return buffer.toString();
    }

}
