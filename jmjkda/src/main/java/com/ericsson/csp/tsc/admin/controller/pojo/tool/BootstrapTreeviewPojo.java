package com.ericsson.csp.tsc.admin.controller.pojo.tool;

import java.io.Serializable;
import java.util.List;
/**
 * 
 * 对应前端的bootstrap-treeview的数据结构
 * @author wangsy
 *
 */
public class BootstrapTreeviewPojo implements Serializable{
	private static final long serialVersionUID = -3393229039591335016L;
	
	private String text;
	private String icon;
	private boolean active;
	private List<BootstrapTreeviewPojo> children;
	private boolean hasChildren;
	private String href;
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public boolean getActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public boolean isHasChildren() {
		return hasChildren;
	}
	public void setHasChildren(boolean hasChildren) {
		this.hasChildren = hasChildren;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public List<BootstrapTreeviewPojo> getChildren() {
		return children;
	}
	public void setChildren(List<BootstrapTreeviewPojo> children) {
		this.children = children;
	}
    @Override
    public String toString() {
        return "BootstrapTreeviewPojo [text=" + text + ", icon=" + icon + ", active=" + active + ", children="
                + children + ", hasChildren=" + hasChildren + ", href=" + href + "]";
    }


}
