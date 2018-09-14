package com.example.THG_hotel;

import org.json.JSONException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.example.THG_hotel.service.Sender;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.THG_hotel.model.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
@SpringBootApplication
public class ThgHotelApplication {
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(ThgHotelApplication.class, args);
	}
	
	@Bean
	CommandLineRunner runner(Sender sender) {
		return args -> {
			
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<Hotel>> typeReference = new TypeReference<List<Hotel>>(){};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/json/hotels.json");
			try {
				List<Hotel> hotels = mapper.readValue(inputStream,typeReference);
				hotels.forEach(hotel->{
					try {
						sender.send(hotel);
					} catch (JSONException e) {
					
						e.printStackTrace();
					}
				});
				System.out.println("hotels pushed!");
			} catch (IOException e){
				System.out.println("Unable to send hotels resources details: " + e.getMessage());
			}
		};
	}
}
