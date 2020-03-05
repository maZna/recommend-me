package com.recommend.me.task.rest.recommendmebackend.topic;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * Controller for Topic
 * @author Masna Ahmed
 * @author https://www.linkedin.com/in/masna-ahmed-355432160/
 * @version 1.0
 * @since 1.0
 */
@RestController
public class TopicResource {
	
	/** A local instance of the topic repository
	 */
	@Autowired
	private TopicRepository topicRepository;
	
	/**
	 * Retrieve all stored Topic Entities
	 * @return a list of Topics
	 */
	@CrossOrigin
	@GetMapping("/topics")
	public List<Topic> retrieveAllTopics()
	{
		return topicRepository.findAll();
	}
	
	/**
	 * Retrieves all stored topics' names
	 * @return a list of topic names
	 */
	@CrossOrigin
	@GetMapping("/topics/names")
	public List<String> retrieveAllTopicNames()
	{
		return topicRepository.getTopics();
	}
	
	/**
	 * Retrives a topic by its unique id
	 * @param id unique topic id 
	 * @return a ResponseEntity containing the retrieved topic entity
	 */
	@CrossOrigin
	@GetMapping("/topics/{id}")
	public ResponseEntity<Topic> retrieveTopicById(@PathVariable long id)
	{
		Optional<Topic> topic = topicRepository.findById(id);
		
		if (!topic.isPresent())
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(topic.get());
	}
	
	/**
	 * Retrieves a topic question by its unique id
	 * @param id unique topic id
	 * @return a topic question
	 */
	@CrossOrigin
	@GetMapping("/topics/q/{id}")
	public String retrieveTopicQuestionById(@PathVariable long id)
	{
		return topicRepository.getTopicQuestion(id);
	}
	
	/**
	 * Delete a particular topic
	 * @param id unique id of topic
	 * @return a ResponseEntity containing an HTTP status
	 */
	@CrossOrigin
	@DeleteMapping("/topics/{id}")
	public ResponseEntity<Object> deleteTopic(@PathVariable long id)
	{
		Optional<Topic> temp = topicRepository.findById(id);
		
		if (!temp.isPresent())
			return ResponseEntity.notFound().build();
		
		topicRepository.deleteById(id);
		return new ResponseEntity<Object>(HttpStatus.ACCEPTED);
		
	}
	
	/**
	 * Creates a new topic
	 * @param topic a new Topic Entity
	 * @return a ResponsEntity containing URL of createdEntity
	 */
	@CrossOrigin
	@PostMapping("/topics")
	public ResponseEntity<Object> createTopic(@RequestBody Topic topic)
	{
		Topic savedTopic = topicRepository.save(topic);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
						.buildAndExpand(savedTopic.getID()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	/**
	 * Updates a particular topic
	 * @param topic the new topic entity
	 * @param id id of topic to update
	 * @return
	 */
	@CrossOrigin
	@PutMapping("/topics/{id}")
	public ResponseEntity<Object> updateTopic(@RequestBody Topic topic, @PathVariable long id)
	{
		Optional<Topic> topicOptional = topicRepository.findById(id);
		
		if (!topicOptional.isPresent())
			return ResponseEntity.notFound().build();
		
		topic.setId(id);
		
		topicRepository.save(topic);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
