package com.orderbook.springbootrestapiapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.orderbook.springbootrestapiapp.orderbook.business.OrderManagement;
import com.orderbook.springbootrestapiapp.vo.OrderBook;
import com.orderbook.springbootrestapiapp.vo.OrderDetails;

@RestController
public class OrderBookController {

	@Autowired
	private OrderManagement orderMgmt;

	@RequestMapping(method = RequestMethod.POST, value = "/orderBook")
	@ResponseBody
	public ResponseEntity<String> createOrderBook(@RequestBody OrderBook orderBook) {
		System.out.println("Create An Order");
		orderMgmt.createOrderBook(orderBook);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/orderBook/{instId}")
	@ResponseBody
	public String closeOrderBook(@RequestBody OrderBook orderBook, @PathVariable String instId) {
		String response = orderMgmt.closeOrderBook(orderBook, Integer.valueOf(instId));
		return response;
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{instId}")
	@ResponseBody
	public String addExecutions(@RequestBody OrderBook orderBook, @PathVariable int instId) {
		String response;
		if(orderBook.getStatus().equalsIgnoreCase("closed")) {
			if(!orderBook.isExecuted())
				response = orderMgmt.addExecutions(orderBook,Integer.valueOf(instId));
			else
				response = "Order Book already executed. No more exceptions can be added";
			
		} else
			response = "Executions can be added only for closed Order Book";
		return response;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/orderBook/{orderId}")
	@ResponseBody
	public ResponseEntity<OrderDetails> getOrderDetails(@RequestBody OrderBook orderBook, @PathVariable int orderId) {		
		OrderDetails order = orderMgmt.getOrderDetails(orderBook, orderId);
		return ResponseEntity.status(HttpStatus.OK).body(order);
	}
}
