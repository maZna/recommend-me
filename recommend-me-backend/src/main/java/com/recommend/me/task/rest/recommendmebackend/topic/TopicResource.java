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

@RestController
public class TopicResource {
	
	@Autowired
	private TopicRepository topicRepository;
	
	@CrossOrigin
	@GetMapping("/topics")
	public List<Topic> retrieveAllTopics()
	{
		return topicRepository.findAll();
	}
	
	@CrossOrigin
	@GetMapping("/topics/names")
	public List<String> retrieveAllTopicNames()
	{
		return topicRepository.getTopics();
	}
	
	@GetMapping("/topics/{id}")
	public ResponseEntity<Topic> retrieveTopicById(@PathVariable long id)
	{
		Optional<Topic> topic = topicRepository.findById(id);
		
		if (!topic.isPresent())
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(topic.get());
	}
	
	@CrossOrigin
	@GetMapping("/topics/q/{id}")
	public String retrieveTopicQuestionById(@PathVariable long id)
	{
		return topicRepository.getTopicQuestion(id);
	}
	

	@DeleteMapping("/topics/{id}")
	public ResponseEntity<Object> deleteTopic(@PathVariable long id)
	{
		Optional<Topic> temp = topicRepository.findById(id);
		
		if (!temp.isPresent())
			return ResponseEntity.notFound().build();
		
		topicRepository.deleteById(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
		
	}
	
	@CrossOrigin
	@PostMapping("/topics")
	public ResponseEntity<Object> createTopic(@RequestBody Topic topic)
	{
		Topic savedTopic = topicRepository.save(topic);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
						.buildAndExpand(savedTopic.getID()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
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
