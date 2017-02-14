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

import com.jenkov.tags.tree.itf.ITree;
import com.jenkov.tags.tree.itf.ITreeIteratorElement;

import javax.servlet.jsp.tagext.TagSupport;
import javax.servlet.jsp.JspException;
import java.util.Iterator;

public class TreeTag extends TagSupport{

    protected String tree     = null;
    protected String node     = null;
    protected String level    = null;
    protected String expanded = null;
    protected Iterator treeIterator = null;
    protected String expandParam    = null;
    protected String collapseParam  = null;
    protected String includeRootNode   = null;


    public String getTree(){
        return this.tree;
    }

    public void setTree(String tree){
        this.tree = tree;
    }

    public String getNode(){
        return this.node;
    }

    public void setNode(String node){
        this.node = node;
    }

    public String getExpandParam(){
        if(this.expandParam == null) return "expand";
        return this.expandParam;
    }

    public void setExpandParam(String expandParam){
        this.expandParam = expandParam;
    }

    public String getCollapseParam(){
        if(this.collapseParam == null) return "collapse";
        return this.collapseParam;
    }

    public void setCollapseParam(String collapseParam){
        this.collapseParam = collapseParam;
    }

    public String getIncludeRootNode() {
        if(includeRootNode == null) return "true";
        return includeRootNode;
    }

    public void setIncludeRootNode(String includeRootNode) {
        this.includeRootNode = includeRootNode;
    }


    protected void validateAttributes() throws JspException{
        if(getTree() == null) throw new JspException("attribute tree must not be null!");
        if(getNode() == null) throw new JspException("attribute node must not be null!");
    }

    protected boolean isTreeAvailable() throws JspException{
        if(pageContext.getSession().getAttribute(getTree()) == null){
            return false;
        }
        return true;
    }

    protected void expandCollapseNode(){
        String expandId    = pageContext.getRequest().getParameter(getExpandParam());
        String collapseId  = pageContext.getRequest().getParameter(getCollapseParam());
        ITree tree = (ITree) pageContext.getSession().getAttribute(getTree());
        if(expandId != null){
            tree.expand(expandId);
        } else if(collapseId != null){
            tree.collapse(collapseId);
        }
    }


    public int doStartTag() throws JspException{
        validateAttributes();
        expandCollapseNode();
        if(!isTreeAvailable()){
            return SKIP_BODY;
        }
        ITree tree = (ITree) pageContext.getSession().getAttribute(getTree());
        this.treeIterator = tree.iterator(getIncludeRootNode().equals("true"));
        if(this.treeIterator.hasNext()){
                ITreeIteratorElement element = (ITreeIteratorElement) this.treeIterator.next();
                pageContext.getSession().setAttribute(getNode(), element);
                return EVAL_BODY_INCLUDE;
        }
        return SKIP_BODY;
    }

    public int doAfterBody() throws JspException{
        if(this.treeIterator.hasNext()){
            ITreeIteratorElement element = (ITreeIteratorElement) this.treeIterator.next();
            pageContext.getSession().setAttribute(getNode(), element);
            return EVAL_BODY_AGAIN;
        }
        return SKIP_BODY;
    }

}
