package com.datadynamicscheduling.upstream.bean;

import java.io.Serializable;

public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	private int itemID;
	private int merchantID;
	private int marketPlaceID;
	private String priority;
	private String dataType;
	private String payload;

	public int getItemID() {
		return itemID;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

	public int getMerchantID() {
		return merchantID;
	}

	public void setMerchantID(int merchantID) {
		this.merchantID = merchantID;
	}

	public int getMarketPlaceID() {
		return marketPlaceID;
	}

	public void setMarketPlaceID(int marketPlaceID) {
		this.marketPlaceID = marketPlaceID;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	@Override
	public String toString() {
		return "Product [itemID=" + itemID + ", merchantID=" + merchantID + ", marketPlaceID=" + marketPlaceID
				+ ", priority=" + priority + ", dataType=" + dataType + ", payload=" + payload + "]";
	}

}
