package com.recommend.me.task.rest.recommendmebackend.submission;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.recommend.me.task.rest.recommendmebackend.topic.Topic;

/**
 * Represents a Survey Submission Entity
 * @author Masna Ahmed
 * @author https://www.linkedin.com/in/masna-ahmed-355432160/
 * @version 1.0
 * @since 1.0
 */
@Entity
public class Submission {
	
	/** Represents a submission's unique ID
	 */
	@Id
	@GeneratedValue
	private Long submitId;
	
	/** Represents the score for a particular survey
	 */
	@NotNull
	private int score;
	
	/** Represents the feedback given by a user
	 */
	private String feedback;
	
	/** Represents the survey's associated topic
	 */
	@ManyToOne
	@JoinColumn
	private Topic topic;

	public Submission()
	{
		super();
	}
	
	/**
	 * Constructor for the Submission Entity
	 * @param submitId a submission ID
	 * @param score a given score
	 * @param feedback a given feedback
	 * @param topic an associated topic
	 */
	public Submission(Long submitId, int score, String feedback, Topic topic)
	{
		super();
		this.submitId = submitId;
		this.score = score;
		this.setFeedback(feedback);
		this.topic = topic;
	}
	
	/**
	 * Getter method for submissions ID
	 * @return a submission ID
	 */
	public Long getSubmitId() {
		return submitId;
	}

	/**
	 * Setter method for a submission ID
	 * @param submitId a submission ID
	 */
	public void setSubmitId(Long submitId) {
		this.submitId = submitId;
	}

	/**
	 * Getter method for the submission's score
	 * @return submission score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Setter method for submission score
	 * @param score a submission score
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * Getter method for submission's Topic
	 * @return topic of submission
	 */
	public Topic getTopic() {
		return topic;
	}

	/**
	 * Setter method for submission's topic
	 * @param topic a submission topic
	 */
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	
	/** Getter method for submission's feedback
	 * @return the feedback
	 */
	public String getFeedback() {
		return feedback;
	}

	/** Setter method for feedback
	 * @param feedback the feedback to set
	 */
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	
	
	
}
