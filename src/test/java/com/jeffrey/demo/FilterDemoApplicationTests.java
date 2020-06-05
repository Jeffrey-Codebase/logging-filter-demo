package com.jeffrey.demo;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(TestController.class)
class FilterDemoApplicationTests {

	@Autowired
    private MockMvc mvc;

	@Test
	void test200() throws Exception {
    	mvc.perform(get("/ok?name=jeffrey").with(httpBasic("admin","admin"))).andExpect(status().isOk());
	}

	@Test
	void test400() throws Exception {
    	mvc.perform(get("/bad").with(httpBasic("admin","admin"))).andExpect(status().isBadRequest());
	}

	@Test
	void test401() throws Exception {
    	mvc.perform(get("/ok").with(httpBasic("admin","helloworld"))).andExpect(status().isUnauthorized());
	}

   	@Test
   	void test404() throws Exception {
    	mvc.perform(get("/nonexisted").with(httpBasic("admin","admin"))).andExpect(status().isNotFound());
   	}

}

