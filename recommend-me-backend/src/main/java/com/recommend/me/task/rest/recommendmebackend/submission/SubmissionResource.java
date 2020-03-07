package com.recommend.me.task.rest.recommendmebackend.submission;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.recommend.me.task.rest.recommendmebackend.topic.TopicRepository;

/**
 * Represents the Survey Submission Controller
 * @author Masna Ahmed
 * @author https://www.linkedin.com/in/masna-ahmed-355432160/
 * @version 1.0
 * @since 1.0
 */
@RestController
public class SubmissionResource {
	
	/** An instance of a survey submission repository
	 */
	@Autowired
	private SubmissionRepository submissionRepository;
	
	/** An instance of a survey topic repository
	 */
	@Autowired
	private TopicRepository topicRepository;
	
	/**
	 * Retrieves a list of submissions
	 * @return a list of Submission entities
	 */
	@CrossOrigin
	@GetMapping("/submissions")
	public List<Submission> retrieveAllSubmissions()
	{
		return submissionRepository.findAll();
	}
	
	/**
	 * Retrieves a List of submissions for a given topic ID
	 * @param id unique topic id
	 * @return a list of Submission entities
	 */
	@CrossOrigin
	@GetMapping("/submissions/t/{id}")
	public List<Submission> retrieveSubmissionByTopicId(@PathVariable long id)
	{
		return submissionRepository.getSubmissionsByTopic(id);
	}
	
	/**
	 * Retrieves a submission with a specific submission ID
	 * @param id a unique submission ID
	 * @return a ResponseEntity containing the Submission content
	 */
	@CrossOrigin
	@GetMapping("/submissions/{id}")
	public ResponseEntity<Submission> retrieveSubmissionById(@PathVariable long id)
	{
		Optional<Submission> tempSub = submissionRepository.findById(id);
		
		if (!tempSub.isPresent())
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(tempSub.get());
	}
	
	/**
	 * Updates the NPM score for a given topic
	 * @param topicId a given topic ID
	 */
	private void updateNpm(long topicId)
	{
		int promoters = submissionRepository.getNumberOfPromotersByTopic(topicId);
		int detractors = submissionRepository.getNumberOfDetractorsByTopic(topicId);
		int total = submissionRepository.getNumberOfSumbissionsByTopic(topicId);
		
		double newNpm = (( (double) promoters/total ) - ( (double) detractors/total ) ) * 100;
		newNpm = Math.round(newNpm * 100) / 100;	
		topicRepository.updateNpm(newNpm, topicId);
	}
	
	/**
	 * Creates a new instance of a Survey Submission
	 * @param submission a new Submission
	 * @return a ResponseEntity containing
	 */
	@CrossOrigin
	@PostMapping("/submissions")
	public ResponseEntity<Object> createSubmission(@RequestBody Submission submission)
	{
		Submission savedSub = submissionRepository.save(submission);
		
		//this method updates the NPM value in topic table
		updateNpm(submission.getTopic().getID());
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
						.buildAndExpand(savedSub.getSubmitId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	/**
	 * Deletes a given instance of a Submission
	 * @param id unique ID of a submission
	 */
	@CrossOrigin
	@DeleteMapping("/submissions/{id}")
	public void deleteSubmission(@PathVariable long id)
	{
		Optional<Submission> temp = submissionRepository.findById(id);
		
		submissionRepository.deleteById(id);
		
		if (temp.isPresent())
			updateNpm(temp.get().getTopic().getID());
	}
	
	/**
	 * Updates a submitted suvey
	 * @param submission new Survey content
	 * @param id unique ID survey to update
	 * @return a ResponseEntity containing an HTTP response
	 */
	@CrossOrigin
	@PutMapping("/submissions/{id}")
	public ResponseEntity<Object> updateSubmission(@RequestBody Submission submission, @PathVariable long id)
	{
		Optional<Submission> tempSub = submissionRepository.findById(id);
		
		if (!tempSub.isPresent())
			return ResponseEntity.notFound().build();
		
		submission.setSubmitId(id);
		
		submissionRepository.save(submission);
		
		updateNpm(submission.getTopic().getID());
		
		return ResponseEntity.ok().build();
	}
	
}
