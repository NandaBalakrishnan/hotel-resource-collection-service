package com.example.THG_hotel.service;

import java.util.Base64;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.THG_hotel.model.Hotel;
import com.example.THG_hotel.model.HotelData;
import com.example.THG_hotel.model.HotelDetail;

import java.util.List;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Service
public class Sender {
	private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);

	@Autowired
	RestTemplate restTemplate;

	@Value("${kafka.topic.json}")
	private String jsonTopic;

	@Value("${spring.HotelServer.host}")
	String hotelServerHost;

	@Value("${spring.HotelServer.path}")
	String hotelServerPath;

	@Value("${spring.basic.admin_username}")
	String username;

	@Value("${spring.basic.admin_password}")
	String password;

	@Autowired
	private KafkaTemplate<String, HotelDetail> kafkaTemplate;

	public void send(Hotel hotel) throws JSONException {

		ResponseEntity<String> hotelDetailResponse = null;

		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", "application/json");
		String authStringEnc = getAuthenticationString();
		headers.add("Authorization", "Basic " + authStringEnc);
		HttpEntity<String> requestEntity = new HttpEntity<String>("parameters", headers);
		UriComponentsBuilder uriBuilder = UriComponentsBuilder
				.fromHttpUrl(hotelServerHost + "/" + hotelServerPath + "/" + hotel.getHotelId());
		try {
			hotelDetailResponse = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, requestEntity,
					String.class);
		} catch (HttpClientErrorException e) {
			e.printStackTrace();

		}
		JSONObject hotelDetailJson = new JSONObject(hotelDetailResponse.getBody());

		HotelData hotelData = new HotelData(hotelDetailJson.getString("name"), hotelDetailJson.getString("description"),
				hotelDetailJson.getString("city"), hotelDetailJson.getInt("rating"));

		HotelDetail hotelDetail = new HotelDetail(hotel.getHotelId(), hotel.getHotelName(), hotelData.getDescription(),
				hotelData.getCity(), hotelData.getRating(), hotel.getElectricityUsage(), hotel.getElectricityUnit(),
				hotel.getWaste(), hotel.getWasteUnit(), hotel.getWaterUsage(), hotel.getWaterUnit());
		LOGGER.info("sending hotel='{}'", hotel.toString());
		kafkaTemplate.send(jsonTopic, hotelDetail);

	}

	public String getAuthenticationString() {
		String authString = username + ":" + password;
		return Base64.getEncoder().encodeToString(authString.getBytes());

	}
}
