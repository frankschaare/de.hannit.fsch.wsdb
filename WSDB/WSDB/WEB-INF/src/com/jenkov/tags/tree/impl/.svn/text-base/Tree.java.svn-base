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

import com.jenkov.tags.tree.itf.*;

import java.util.*;

public class Tree implements ITree{

    protected boolean   singleSelectionMode = false;
    protected Set       expanded = new TreeSet();
    protected Set       selected = new TreeSet();
    protected ITreeNode root     = null;

    protected List      expandListeners   = new ArrayList();
    protected List      collapseListeners = new ArrayList();
    protected List      selectListeners   = new ArrayList();
    protected List      unselectListeners = new ArrayList();

    public ITreeNode getRoot() {
        return this.root;
    }

    public void setRoot(ITreeNode node) {
        this.root = node;
    }

    public ITreeNode findNode(String treeNodeId) {
        return findNode(getRoot(), treeNodeId);
    }

    protected ITreeNode findNode(ITreeNode treeNode, String treeNodeId){
        if(treeNode.getId().equals(treeNodeId)){
            return treeNode;
        }

        Iterator children = treeNode.getChildren().iterator();
        while(children.hasNext()){
            ITreeNode child = (ITreeNode) children.next();
            ITreeNode match = findNode(child, treeNodeId);
            if( match != null){
                return match;
            }
        }
        return null;
    }

    public Set findNodes(Set treeNodeIds) {
        Set treeNodes = new HashSet();
        findNodes(getRoot(), treeNodeIds, treeNodes);
        return treeNodes;
    }

    protected void findNodes(ITreeNode treeNode, Set treeNodeIds, Set treeNodes){
        if(treeNodeIds.contains(treeNode.getId())){
            treeNodes.add(treeNode);
        }

        Iterator children = treeNode.getChildren().iterator();
        while(children.hasNext()){
            findNodes((ITreeNode) children.next(), treeNodeIds, treeNodes);
        }
    }

    public boolean isExpanded(String treeNodeId){
        return this.expanded.contains(treeNodeId);
    }

    public void expand(String treeNodeId) {
        this.expanded.add(treeNodeId);
        if(this.expandListeners.size() > 0){
            ITreeNode expandedNode = findNode(treeNodeId);
            Iterator iterator = this.expandListeners.iterator();
            while(iterator.hasNext()){
                ((IExpandListener) iterator.next()).nodeExpanded(expandedNode, this);
            }
        }
    }

    public void collapse(String treeNodeId) {
        this.expanded.remove(treeNodeId);
        if(this.collapseListeners.size() > 0){
            ITreeNode collapsedNode = findNode(treeNodeId);
            Iterator iterator = this.collapseListeners.iterator();
            while(iterator.hasNext()){
                ((ICollapseListener) iterator.next()).nodeCollapsed(collapsedNode, this);
            }
        }

    }

    public Set getExpandedNodes() {
        return findNodes(this.expanded);
    }

    public void addExpandListener(IExpandListener expandListener) {
        this.expandListeners.add(expandListener);
    }

    public void removeExpandListener(IExpandListener expandListener) {
        this.expandListeners.remove(expandListener);
    }

    public void addCollapseListener(ICollapseListener collapseListener) {
        this.collapseListeners.add(collapseListener);
    }

    public void removeCollapseListener(ICollapseListener collapseListener) {
        this.collapseListeners.remove(collapseListener);
    }

    public boolean isSelected(String treeNodeId) {
        return this.selected.contains(treeNodeId);
    }

    public void select(String treeNodeId) {
        if(isSingleSelectionMode()){
            unSelectAll();
        }
        this.selected.add(treeNodeId);

        if(this.selectListeners.size() > 0){
            ITreeNode selectedNode = findNode(treeNodeId);
            Iterator iterator = this.selectListeners.iterator();
            while(iterator.hasNext()){
                ((ISelectListener) iterator.next()).nodeSelected(selectedNode, this);
            }
        }
    }

    public void unSelect(String treeNodeId) {
        this.selected.remove(treeNodeId);
        if(this.unselectListeners.size() > 0){
            ITreeNode unselectedNode = findNode(treeNodeId);
            Iterator iterator = this.unselectListeners.iterator();
            while(iterator.hasNext()){
                ((IUnselectListener) iterator.next()).nodeUnselected(unselectedNode, this);
            }
        }
    }


    public void unSelectAll() {
        Iterator iterator =  this.selected.iterator();
        while(iterator.hasNext()){
            unSelect((String) iterator.next());
        }
    }

    public Set getSelectedNodes() {
        return findNodes(this.selected);
    }

    public void setSingleSelectionMode(boolean mode) {
        this.singleSelectionMode = mode;
    }

    public boolean isSingleSelectionMode(){
        return this.singleSelectionMode;
    }

    public void addSelectListener(ISelectListener selectListener) {
        this.selectListeners.add(selectListener);
    }

    public void removeSelectListener(ISelectListener selectListener) {
        this.selectListeners.remove(selectListener);
    }

    public void addUnselectListener(IUnselectListener unselectListener) {
        this.unselectListeners.add(unselectListener);
    }

    public void removeUnselectListener(IUnselectListener unselectListener) {
        this.unselectListeners.remove(unselectListener);
    }

    public Iterator iterator(boolean includeRootNode) {
        return new TreeIterator(this, includeRootNode);
    }
}
