package com.orderbook.springbootrestapiapp.vo;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.orderbook.springbootrestapiapp.common.JsonDateSerializer;

public class OrderDetails {

	private int orderId;
	private int orderQuantity;
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date orderDate;
	private int instrumentID;
	private double orderPrice;
	private String validityStatus;
	private double executionPrice;
	
	public double getExecutionPrice() {
		return executionPrice;
	}
	public void setExecutionPrice(double executionPrice) {
		this.executionPrice = executionPrice;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getOrderQuantity() {
		return orderQuantity;
	}
	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public int getInstrumentID() {
		return instrumentID;
	}
	public void setInstrumentID(int instrumentID) {
		this.instrumentID = instrumentID;
	}
	public double getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(double orderPrice) {
		this.orderPrice = orderPrice;
	}
	public String getValidityStatus() {
		return validityStatus;
	}
	public void setValidityStatus(String validityStatus) {
		this.validityStatus = validityStatus;
	}
}
