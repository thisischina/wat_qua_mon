package com.hd.ibus.pojo;

import java.io.Serializable;
import java.util.List;

public class GatewayData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String gatewayId;
	private String name;
	private boolean open;
	private String icon;
	private List<GroupData> children;
	public String getGatewayId() {
		return gatewayId;
	}
	public void setGatewayId(String gatewayId) {
		this.gatewayId = gatewayId;
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
	public List<GroupData> getChildren() {
		return children;
	}
	public void setChildren(List<GroupData> children) {
		this.children = children;
	}
	

	
}
