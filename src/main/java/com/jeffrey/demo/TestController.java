package com.jeffrey.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Admin Controller
 *
 * @author yifan.xu
 */
@RestController
public class TestController{


	@GetMapping(path = "/ok")
	public ResponseEntity return200() {
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(path = "/bad")
	public ResponseEntity return400() {
		return new ResponseEntity<>("test 400", HttpStatus.BAD_REQUEST);
	}

}
