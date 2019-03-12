package com.orderbook.springbootrestapiapp.vo;

import java.util.HashMap;

public class OrderBookStatistics {

	private int amtOfOrders;

	private int demand;
	
	private OrderDetails earliestOrder;
	
	private OrderDetails latestOrder;
	
	private HashMap<Integer, String> limitSpread;
	
	private int noOfValidOrders;
	
	private int noOfInValidOrders;
	
	private int noOfValidDemands;
	
	private int noOfInValidDemands;
	
	private int accumulatedExecutionQuantity;
	
	private OrderDetails biggestOrder;
	
	private OrderDetails smallestOrder;
	
	public double getExecutionPrice() {
		return executionPrice;
	}

	public void setExecutionPrice(double executionPrice) {
		this.executionPrice = executionPrice;
	}

	private double executionPrice;

	public int getAmtOfOrders() {
		return amtOfOrders;
	}

	public void setAmtOfOrders(int amtOfOrders) {
		this.amtOfOrders = amtOfOrders;
	}

	public int getDemand() {
		return demand;
	}

	public void setDemand(int demand) {
		this.demand = demand;
	}

	public OrderDetails getEarliestOrder() {
		return earliestOrder;
	}

	public void setEarliestOrder(OrderDetails earliestOrder) {
		this.earliestOrder = earliestOrder;
	}

	public OrderDetails getLatestOrder() {
		return latestOrder;
	}

	public void setLatestOrder(OrderDetails latestOrder) {
		this.latestOrder = latestOrder;
	}

	public HashMap<Integer, String> getLimitSpread() {
		return limitSpread;
	}

	public void setLimitSpread(HashMap<Integer, String> limitSpread) {
		this.limitSpread = limitSpread;
	}

	public int getNoOfValidOrders() {
		return noOfValidOrders;
	}

	public void setNoOfValidOrders(int noOfValidOrders) {
		this.noOfValidOrders = noOfValidOrders;
	}

	public int getNoOfInValidOrders() {
		return noOfInValidOrders;
	}

	public void setNoOfInValidOrders(int noOfInValidOrders) {
		this.noOfInValidOrders = noOfInValidOrders;
	}

	public int getNoOfValidDemands() {
		return noOfValidDemands;
	}

	public void setNoOfValidDemands(int noOfValidDemands) {
		this.noOfValidDemands = noOfValidDemands;
	}

	public int getNoOfInValidDemands() {
		return noOfInValidDemands;
	}

	public void setNoOfInValidDemands(int noOfInValidDemands) {
		this.noOfInValidDemands = noOfInValidDemands;
	}

	public int getAccumulatedExecutionQuantity() {
		return accumulatedExecutionQuantity;
	}

	public void setAccumulatedExecutionQuantity(int accumulatedExecutionQuantity) {
		this.accumulatedExecutionQuantity = accumulatedExecutionQuantity;
	}

	public OrderDetails getBiggestOrder() {
		return biggestOrder;
	}

	public void setBiggestOrder(OrderDetails biggestOrder) {
		this.biggestOrder = biggestOrder;
	}

	public OrderDetails getSmallestOrder() {
		return smallestOrder;
	}

	public void setSmallestOrder(OrderDetails smallestOrder) {
		this.smallestOrder = smallestOrder;
	}
	
}

