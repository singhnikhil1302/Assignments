package com.orderbook.springbootrestapiapp.orderbook.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.orderbook.springbootrestapiapp.vo.Execution;
import com.orderbook.springbootrestapiapp.vo.OrderBook;
import com.orderbook.springbootrestapiapp.vo.OrderDetails;

public class OrderManagementImpl implements OrderManagement {

	@Override
	public String addExecutions(OrderBook orderBook, Integer instId) {
		// TODO Auto-generated method stub
		String response;
		boolean isExecuted = false;
		double executionPrice = 0;
		int totalExecutionQuant = 0;
		int totalValidOrderQuant = 0;
		Map<Integer, OrderDetails> orderList = new HashMap<Integer, OrderDetails>();
		List<OrderDetails> orderDetails = orderBook.getOrderDetails();
		List<Execution> executionList = orderBook.getExecutions();
		
		for(Execution  execution: executionList) {
			executionPrice = execution.getExecutionPrice();
			totalExecutionQuant += execution.getExecutionQuantity();
		}
		
		if(null == orderDetails || orderDetails.isEmpty()) {
			response = "Order cannot be empty for executions to be added.";
			return response;
		}else {
			for(OrderDetails order : orderDetails) {
				if(Double.valueOf(order.getOrderPrice()) != null && executionPrice > order.getOrderPrice()) {
					order.setValidityStatus("invalid");
					order.setExecutionPrice(executionPrice);
				}else {
					order.setValidityStatus("valid");
					totalValidOrderQuant += order.getOrderQuantity();
					order.setExecutionPrice(executionPrice);
				}
				orderList.put(order.getOrderId(), order);		
			}
		}
		
		if(totalExecutionQuant == totalValidOrderQuant) {
			isExecuted = true;
			orderBook.setExecuted(isExecuted);
		}
		orderBook.setOrderStatus(orderList);	 
		return "Execution Added";
	}

	@Override
	public String closeOrderBook(OrderBook orderBook, int instId) {
		// TODO Auto-generated method stub
		if (orderBook.getInstId() == instId)
			orderBook.setStatus("closed");
		return orderBook.getStatus();
	}

	@Override
	public OrderDetails getOrderDetails(OrderBook orderBook, int orderId) {
		// TODO Auto-generated method stub
		
		Map<Integer,OrderDetails> orders = orderBook.getOrderStatus();		
		OrderDetails order = orders.get(Integer.valueOf(orderId));
		return order;
	}

	@Override
	public OrderBook createOrderBook(OrderBook orderBook) {
		// TODO Auto-generated method stub

		String status = orderBook.getStatus();
		if (null == status || "".equals(status))
			status = "open";
		orderBook.setStatus(status);
		List<OrderDetails> orderList = orderBook.getOrderDetails();
		if (!orderBook.getStatus().equalsIgnoreCase("closed")) {
			orderBook.setOrderDetails(orderList);
			return orderBook;
		} else
			return orderBook;
	}
}
