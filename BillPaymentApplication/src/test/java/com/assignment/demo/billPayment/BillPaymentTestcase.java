package com.assignment.demo.billPayment;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@WebMvcTest(value=BillPayment.class)
public class BillPaymentTestcase {
	
	@Autowired
	private MockMvc mockmvc;

	@Test
	public void testCalculatePayableAmount() throws Exception {
		
		Bill bill =new Bill();
		bill.setBillId(1);
		bill.setBillAmount(40.5);
		//bill.setBillAmount(100);
		//bill.setBillAmount(110.5);
		bill.setBillType("NON_GROCERY");
		//bill.setBillType("GROCERY");
		bill.setCustomerType("EMPLOYEE");
		//bill.setCustomerType("AFFILIATED");
		//bill.setCustomerType("OLD_CUST");
		
		String json = null;
		
		try {
			json = this.mapToJson(bill);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String uri = "/bill";
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(uri)
				.accept(MediaType.APPLICATION_JSON).content(json)
				.contentType(MediaType.ALL_VALUE);
		
		System.out.println(requestBuilder.toString());
		
		
		MvcResult result = mockmvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		String outputInJson = response.getContentAsString();
		
		System.out.println(outputInJson);
		
		fail("Not yet implemented");
	}
	
	private String mapToJson(Object object) throws JsonProcessingException {
		
		ObjectMapper obj = new ObjectMapper();
		return obj.writeValueAsString(object);
		
		
		
	}

}
