package com.orderbook.springbootrestapiapp;

import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.orderbook.SpringBootRestapiAppApplication;
import com.orderbook.springbootrestapiapp.controller.OrderBookController;
@RunWith(SpringRunner.class)
@WebMvcTest(OrderBookController.class)
@ContextConfiguration(classes={SpringBootRestapiAppApplication.class})
@SpringBootTest(classes=SpringBootRestapiAppApplication.class)

public class SpringBootRestapiAppApplicationTests {

	@Test
	public void contextLoads() {
	}

}
