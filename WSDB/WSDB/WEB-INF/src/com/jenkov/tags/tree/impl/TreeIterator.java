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
import com.jenkov.tags.tree.itf.ITree;
import com.jenkov.tags.tree.itf.ITreeIteratorElement;

import java.util.*;

public class TreeIterator implements Iterator{

    private class TreeNodeStack{

        protected List stackContents = new ArrayList();

        public int size(){
            return this.stackContents.size();
        }

        public void push(Object node){
            this.stackContents.add(node);
        }

        public Object pop(){
            Object node = this.stackContents.get(this.stackContents.size()-1);
            this.stackContents.remove(this.stackContents.size()-1);
            return node;
        }

        public Object top(){
            return this.stackContents.get(this.stackContents.size()-1);
        }

    }


    protected ITree         tree            = null;
    protected TreeNodeStack stack           = new TreeNodeStack();
    protected int           level           = 0;

    public TreeIterator (ITree tree, boolean includeRootNode){
        this.tree = tree;
        this.stack.push(new TreeIteratorElement(tree.getRoot(), new ArrayList()
                , tree.isExpanded(tree.getRoot().getId())
                , tree.isSelected(tree.getRoot().getId())
                , true, true
                ));
        if(!includeRootNode){
            pushChildren((ITreeIteratorElement)this.stack.pop());
        }
    }


    public boolean hasNext(){
        return this.stack.size() > 0;
    }

    public Object next(){
        ITreeIteratorElement element = (ITreeIteratorElement) this.stack.pop();
        if(this.tree.isExpanded(element.getNode().getId())){
            pushChildren(element);
        }
        return element;
    }

    public void remove() {
        //not implemented
    }

    protected void pushChildren(ITreeIteratorElement element){
        List indentationProfile = copyIndentationProfile(element);

        indentationProfile.add(new Boolean(element.isLastChild()));

        List children           = element.getNode().getChildren();
        for(int i=0; i < children.size(); i++){
            ITreeNode node = (ITreeNode) children.get(children.size()-i-1);
            this.stack.push(
                    new TreeIteratorElement(
                              node
                            , indentationProfile
                            , this.tree.isExpanded(node.getId())
                            , this.tree.isSelected(node.getId())
                            , i == children.size() - 1
                            , i == 0
            ));
        }
    }

    protected List copyIndentationProfile(ITreeIteratorElement element){
        List copy = new ArrayList();
        Iterator iterator = element.getIndendationProfile().iterator();
        while(iterator.hasNext()){
            copy.add(iterator.next());
        }
        return copy;
    }








}
