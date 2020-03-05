package com.recommend.me.task.rest.recommendmebackend;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.recommend.me.task.rest.recommendmebackend.submission.Submission;
import com.recommend.me.task.rest.recommendmebackend.topic.Topic;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RecommendMeSubmissionTest {
	
	@Autowired MockMvc mvc;
	
	private static final long SUBMIT_ID_TO_TEST = 49;
	private static final long TOPIC_ID_TO_USE = 46;
	
	@Test
	@Order(1)
	public void testSubmissions() throws Exception
	{
		mvc.perform(MockMvcRequestBuilders.get("/submissions")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	
	@Test
	@Order(2)
	public void testSubmissionsById() throws Exception
	{
		mvc.perform(MockMvcRequestBuilders.get("/submissions/{id}", SUBMIT_ID_TO_TEST)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.submitId").value(SUBMIT_ID_TO_TEST));
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
	public void testCreateSubmission() throws Exception {
		
		Topic topic = new Topic();
		topic.setId(TOPIC_ID_TO_USE);
		
		mvc.perform(MockMvcRequestBuilders.post("/submissions")
				.content(convertToJson(new Submission(null, 10, "Excellent", topic)))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}
	
	@Test
	@Order(3)
	public void testUpdateSubmission() throws Exception {
		
		Topic topic = new Topic();
		topic.setId(TOPIC_ID_TO_USE);
		
		mvc.perform(MockMvcRequestBuilders.put("/submissions/{id}", SUBMIT_ID_TO_TEST)
				.content(convertToJson(new Submission(SUBMIT_ID_TO_TEST, 9, "Pretty Good", topic)))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
}
