package com.orderbook.springbootrestapiapp.vo;

import java.util.List;
import java.util.Map;

public class OrderBook {
	
	private List<OrderDetails> orderDetails;
	private List<Execution> executions;
	private String status;
	private int instId;
	private Map<Integer, OrderDetails> orderStatus;
	private boolean isExecuted;
	
	public boolean isExecuted() {
		return isExecuted;
	}
	public void setExecuted(boolean isExecuted) {
		this.isExecuted = isExecuted;
	}
	public List<OrderDetails> getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(List<OrderDetails> orderDetails) {
		this.orderDetails = orderDetails;
	}
	public Map<Integer, OrderDetails> getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Map<Integer, OrderDetails> orderStatus) {
		this.orderStatus = orderStatus;
	}
	public int getInstId() {
		return instId;
	}
	public void setInstId(int instId) {
		this.instId = instId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public List<Execution> getExecutions() {
		return executions;
	}
	public void setExecutions(List<Execution> executions) {
		this.executions = executions;
	}
	
	
	

}
