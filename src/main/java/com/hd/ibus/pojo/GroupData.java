package com.hd.ibus.pojo;

import java.io.Serializable;
import java.util.List;

public class GroupData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String groupId;
	private String name;
	private boolean open;
	private String icon;
	private List<NodeData> children;
	private String type;
	private String fileUrl;
	private String port;
	
	
	
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public List<NodeData> getChildren() {
		return children;
	}
	public void setChildren(List<NodeData> children) {
		this.children = children;
	}
	
	
	
	
	
}
