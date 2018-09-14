package com.example.THG_hotel.controller;

import java.io.UnsupportedEncodingException;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.THG_hotel.service.*;
import com.example.THG_hotel.model.*;
import org.json.JSONException;

@RestController
@RequestMapping("/hotels")
public class HotelController {

	@Autowired
	Sender sender;

	@RequestMapping(method = RequestMethod.POST, value = "/hotel", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> create(@RequestBody Hotel hotel) throws UnsupportedEncodingException {
		HttpHeaders responseHeaders = new HttpHeaders();
		try {
			sender.send(hotel);

			responseHeaders.set("datapushed", "true");

		} catch (JSONException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("Message sent to the Kafka Topic java_in_use_topic Successfully",
				responseHeaders, HttpStatus.ACCEPTED);
	}
}
