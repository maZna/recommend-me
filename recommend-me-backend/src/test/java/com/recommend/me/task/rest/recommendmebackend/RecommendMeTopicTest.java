package com.recommend.me.task.rest.recommendmebackend;

import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.recommend.me.task.rest.recommendmebackend.topic.Topic;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RecommendMeTopicTest {

	@Autowired MockMvc mvc;
	
	//Use a predetermined id from the database
	private static final long TOPIC_ID_TO_TEST = 46;
	
	@Test
	@Order(1)
	public void testTopics() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/topics")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	
	@Test
	@Order(2)
	public void testTopicById() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/topics/{id}", TOPIC_ID_TO_TEST)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(TOPIC_ID_TO_TEST));
		
	
	}
	
	public static String convertToJson(final Object object) {
		try {
			return new ObjectMapper().writeValueAsString(object);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Test
	@Order(3)
	public void testCreateTopic() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/topics")
				.content(convertToJson(new Topic(null, "CSGO", "Is CSGO a good game?")))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}
	
	@Test
	@Order(4)
	public void testUpdateTopic() throws Exception
	{
		mvc.perform(MockMvcRequestBuilders.put("/topics/{id}", TOPIC_ID_TO_TEST)
				.content(convertToJson(new Topic(TOPIC_ID_TO_TEST, "Bilkent", "How is life in Bilkent")))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
//	@Test
//	@Order(5)
//	public void testDeleteTopic() throws Exception{
//		mvc.perform(MockMvcRequestBuilders.delete("/topics/{id}",TOPIC_ID_TO_TEST))
//			.andExpect(status().isAccepted());
//	}
	
}

