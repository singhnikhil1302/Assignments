package com.orderbook.springbootrestapiapp.controller;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.orderbook.springbootrestapiapp.orderbook.business.OrderManagement;
import com.orderbook.springbootrestapiapp.vo.Execution;
import com.orderbook.springbootrestapiapp.vo.OrderBook;
import com.orderbook.springbootrestapiapp.vo.OrderDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(OrderBookController.class)
public class OrderBookControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private OrderManagement orderMgmt;
	@Autowired
	WebApplicationContext webApplicationContext;

	// private Order order;
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	@Test
	public void createOrderBook() throws Exception {
		String uri = "/orderBook";
		List<OrderDetails> orderList;
		String orderStatus = "open";
		String dt = "2019-02-26";
		OrderBook orderBook = new OrderBook();
		OrderDetails order = new OrderDetails();
		try {
			order.setOrderDate(dateFormat.parse(dt));
			order.setOrderId(1);
			order.setOrderQuantity(3);
			order.setInstrumentID(1);
			order.setOrderPrice(38.5);
			order.setValidityStatus("valid");
		} catch (ParseException e) { // TODO Auto-generated catch e.printStackTrace();
		}
		orderList = orderBook.getOrderDetails();
		if (null == orderList)
			orderList = new ArrayList<OrderDetails>();
		orderList.add(order);
		orderBook.setOrderDetails(orderList);
		orderBook.setStatus(orderStatus);
		String inputJson = mapToJson(orderBook);
		MvcResult mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();
		Mockito.when(orderMgmt.createOrderBook(Mockito.any(OrderBook.class))).thenReturn(orderBook);

		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
	}

	@Test
	public void closeOrderBook() throws Exception {
		String uri = "/orderBook";
		List<OrderDetails> orderList;
		String orderStatus = "open";
		String dt = "2019-02-26";
		OrderBook orderBook = new OrderBook();
		OrderDetails order = new OrderDetails();
		try {
			order.setOrderDate(dateFormat.parse(dt));
			order.setOrderId(1);
			order.setOrderQuantity(3);
			order.setInstrumentID(1);
			order.setOrderPrice(38.5);
			order.setValidityStatus("valid");
		} catch (ParseException e) { // TODO Auto-generated catch e.printStackTrace();
		}
		orderList = orderBook.getOrderDetails();
		if (null == orderList)
			orderList = new ArrayList<OrderDetails>();
		orderList.add(order);
		orderBook.setOrderDetails(orderList);
		orderBook.setStatus(orderStatus);
		String inputJson = mapToJson(orderBook);
		MvcResult mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();
		Mockito.when(orderMgmt.createOrderBook(Mockito.any(OrderBook.class))).thenReturn(orderBook);

		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
	}

	@Test
	public void addExecutions() throws Exception {

		String uri = "/1/";
		List<OrderDetails> orderList;
		List<Execution> executions;
		String orderStatus = "closed";
		String dt = "2019-02-26";
		OrderBook orderBook = new OrderBook();
		OrderDetails order = new OrderDetails();
		try {
			// order = new Order(1,3, dateFormat.parse(dt), 1, 38.50, "valid");
			order.setOrderDate(dateFormat.parse(dt));
			order.setOrderId(1);
			order.setOrderQuantity(3);
			order.setInstrumentID(1);
			order.setOrderPrice(38.5);
			order.setValidityStatus("valid");
		} catch (ParseException e) { // TODO Auto-generated catch
			e.printStackTrace();
		}
		orderList = orderBook.getOrderDetails();
		if (null == orderList)
			orderList = new ArrayList<OrderDetails>();
		orderList.add(order);
		orderBook.setOrderDetails(orderList);
		orderBook.setStatus(orderStatus);
		orderBook.setExecuted(false);
		orderBook.setInstId(1);
		executions = new ArrayList<Execution>();
		Execution exec = new Execution();
		exec.setExecutionPrice(28.5);
		exec.setExecutionQuantity(3);
		executions.add(exec);
		orderBook.setExecutions(executions);
		String inputJson = mapToJson(orderBook);

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON).content(inputJson);
		this.mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("Execution Added"))
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void getOrder() throws Exception {

		String uri = "/orderBook/1/";
		List<OrderDetails> orderList;
		List<Execution> executions;
		Map<Integer, OrderDetails> orderDetailsList = new HashMap<Integer, OrderDetails>();
		String orderStatus = "closed";
		String dt = "2019-02-26";
		OrderBook orderBook = new OrderBook();
		OrderDetails order = new OrderDetails();
		try {
			order.setOrderDate(dateFormat.parse(dt));
			order.setOrderId(1);
			order.setOrderQuantity(3);
			order.setInstrumentID(1);
			order.setOrderPrice(38.5);
			order.setValidityStatus("valid");
		} catch (ParseException e) { // TODO Auto-generated catch
			e.printStackTrace();
		}
		orderList = orderBook.getOrderDetails();
		if (null == orderList)
			orderList = new ArrayList<OrderDetails>();
		orderList.add(order);
		orderBook.setOrderDetails(orderList);
		orderBook.setStatus(orderStatus);
		orderBook.setExecuted(false);
		orderBook.setInstId(1);
		executions = new ArrayList<Execution>();
		Execution exec = new Execution();
		exec.setExecutionPrice(28.5);
		exec.setExecutionQuantity(3);
		executions.add(exec);
		orderBook.setExecutions(executions);

		orderDetailsList.put(1, order);
		orderBook.setOrderStatus(orderDetailsList);

		String inputJson = mapToJson(orderBook);

		MvcResult mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}


}
