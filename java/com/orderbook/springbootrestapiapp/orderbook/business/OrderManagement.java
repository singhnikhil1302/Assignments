package com.orderbook.springbootrestapiapp.orderbook.business;

import com.orderbook.springbootrestapiapp.vo.OrderBook;
import com.orderbook.springbootrestapiapp.vo.OrderDetails;

public interface OrderManagement {

	public String addExecutions(OrderBook orderBook, Integer id);

	public OrderBook createOrderBook(OrderBook orderBook);

	public String closeOrderBook(OrderBook orderBook, int id);

	public OrderDetails getOrderDetails(OrderBook orderBook, int orderId);

}
