package com.orderbook.springbootrestapiapp.orderbook.business;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.orderbook.springbootrestapiapp.vo.Execution;
import com.orderbook.springbootrestapiapp.vo.OrderBook;
import com.orderbook.springbootrestapiapp.vo.OrderBookStatistics;
import com.orderbook.springbootrestapiapp.vo.OrderDetails;

/**
 * The purpose of the class is to process the requests for the OrderBooks
 * 
 * @author nikhil
 *
 */

public class OrderManagementImpl implements OrderManagement {

	/**
	 * Method for adding executions to the orderBook It takes OrderBook and
	 * Instrument ID as input parameters Returns the String message once the
	 * executions are successfully added to the OrderBook
	 * 
	 * @param orderBook
	 * @param instId
	 * @return String
	 */
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

		for (Execution execution : executionList) {
			executionPrice = execution.getExecutionPrice();
			totalExecutionQuant += execution.getExecutionQuantity();
		}

		if (null == orderDetails || orderDetails.isEmpty()) {
			response = "Order cannot be empty for executions to be added.";
			return response;
		} else {
			for (OrderDetails order : orderDetails) {
				if (Double.valueOf(order.getOrderPrice()) != null && executionPrice > order.getOrderPrice()) {
					order.setValidityStatus("invalid");
					order.setExecutionPrice(executionPrice);
				} else {
					order.setValidityStatus("valid");
					totalValidOrderQuant += order.getOrderQuantity();
					order.setExecutionPrice(executionPrice);
				}
				orderList.put(order.getOrderId(), order);
			}
		}

		if (totalExecutionQuant == totalValidOrderQuant) {
			isExecuted = true;
			orderBook.setExecuted(isExecuted);
		}
		orderBook.setOrderStatus(orderList);
		return "Execution Added";
	}

	/**
	 * Method for closing the orderBook It takes OrderBook and Instrument ID as
	 * input parameters Returns the String message once the Order Book is closed
	 * @param orderBook
	 * @param instId
	 * @return String
	 */
	@Override
	public String closeOrderBook(OrderBook orderBook, int instId) {
		// TODO Auto-generated method stub
		if (orderBook.getInstId() == instId)
			orderBook.setStatus("closed");
		return orderBook.getStatus();
	}

	/**
	 * Method for fetching the order details It takes OrderBook and Order ID as
	 * input parameters Returns the Order details with respect to the given Order Id
	 * 
	 * @param orderBook
	 * @param orderId
	 * @return OrderDetails
	 */
	@Override
	public OrderDetails getOrderDetails(OrderBook orderBook, int orderId) {
		// TODO Auto-generated method stub

		Map<Integer, OrderDetails> orders = orderBook.getOrderStatus();
		OrderDetails order = orders.get(Integer.valueOf(orderId));
		return order;
	}

	/**
	 * Method for creating the order Book for an instrument It takes OrderBook as
	 * input parameters Returns the OrderBook object with all the OrderBook details
	 * populated
	 * @param orderBook
	 * @return OrderBook
	 */
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

	/**
	 * The method takes OrderBook as input parameter and returns the Order
	 * statistics like latest and earliest orders, biggest and smallest order
	 * 
	 * @param orderBook
	 * @param orderStat
	 * @return OrderBookStatistics
	 */
	@Override
	public OrderBookStatistics getStatistics(OrderBook orderBook) {
		// TODO Auto-generated method stub
		OrderBookStatistics orderStat = new OrderBookStatistics();
		List<OrderDetails> orderList = orderBook.getOrderDetails();

		int biggestOrder = orderList.get(0).getOrderQuantity();
		int smallestOrder = orderList.get(0).getOrderQuantity();
		OrderDetails biggestOrderDetl = orderList.get(0);
		OrderDetails smallestOrderDetl = orderList.get(0);

		for (int i = 1; i < orderList.size(); i++) {

			if (orderBook.getOrderDetails().get(i).getOrderQuantity() > biggestOrder) {
				biggestOrder = orderBook.getOrderDetails().get(i).getOrderQuantity();
				biggestOrderDetl = orderBook.getOrderDetails().get(i);
			}
			if (orderBook.getOrderDetails().get(i).getOrderQuantity() < smallestOrder) {
				smallestOrder = orderBook.getOrderDetails().get(i).getOrderQuantity();
				smallestOrderDetl = orderBook.getOrderDetails().get(i);
			}
		}

		orderStat.setBiggestOrder(biggestOrderDetl);
		orderStat.setSmallestOrder(smallestOrderDetl);
		orderStat.setAmtOfOrders(orderBook.getOrderStatus().size());

		getOrders(orderBook, orderStat);
		getFirstAndLastEntryOrder(orderBook, orderStat);
		return orderStat;
	}

	/**
	 * The method processes the given Order Book and populates the Order Stats
	 * object with Latest and earliest Order
	 * 
	 * @param orderBook
	 * @param orderStat
	 */
	private void getFirstAndLastEntryOrder(OrderBook orderBook, OrderBookStatistics orderStat) {
		// TODO Auto-generated method stub
		List<OrderDetails> orders = orderBook.getOrderDetails();
		Collections.sort(orders, new Comparator<OrderDetails>() {
			public int compare(OrderDetails od1, OrderDetails od2) {
				return Long.valueOf(od1.getOrderDate().toString())
						.compareTo(Long.valueOf(od2.getOrderDate().toString()));
			}
		});
		orderStat.setEarliestOrder(orders.get(0));
		orderStat.setLatestOrder(orders.get(orders.size() - 1));
	}

	/**
	 * The method processes the given Order Book and populates the Order Stats
	 * object with no of Vlaid and Invalid Orders, accumulated Execution Quantity
	 * and execution Price
	 * 
	 * @param orderBook
	 * @param orderStat
	 */
	private void getOrders(OrderBook orderBook, OrderBookStatistics orderStat) {
		// TODO Auto-generated method stub
		Map<Integer, OrderDetails> orders = orderBook.getOrderStatus();
		int totalExecutionQuant = 0;
		OrderDetails details;
		int noOfValidOrders = 0;
		int noOfInvalidOrders = 0;

		for (Map.Entry<Integer, OrderDetails> entry : orders.entrySet()) {

			details = entry.getValue();
			if (details.getValidityStatus().equalsIgnoreCase("valid"))
				noOfValidOrders++;
			else
				noOfInvalidOrders++;
		}
		orderStat.setNoOfValidOrders(noOfValidOrders);
		orderStat.setNoOfInValidOrders(noOfInvalidOrders);

		List<Execution> executionList = orderBook.getExecutions();

		for (Execution execution : executionList)
			totalExecutionQuant += execution.getExecutionQuantity();

		orderStat.setAccumulatedExecutionQuantity(totalExecutionQuant);
		orderStat.setExecutionPrice(executionList.get(0).getExecutionPrice());

	}

}
