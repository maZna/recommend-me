package com.recommend.me.task.rest.recommendmebackend.submission;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.recommend.me.task.rest.recommendmebackend.topic.Topic;

@Entity
public class Submission {
	
	@Id
	@GeneratedValue
	private Long submitId;
	
	@NotNull
	private int score;
	
	private String feedback;
	
	@ManyToOne
	@JoinColumn
	private Topic topic;

	public Submission()
	{
		super();
	}
	
	public Submission(Long submitId, int score, String feedback, Topic topic)
	{
		super();
		this.submitId = submitId;
		this.score = score;
		this.setFeedback(feedback);
		this.topic = topic;
	}
	
	public Long getSubmitId() {
		return submitId;
	}

	public void setSubmitId(Long submitId) {
		this.submitId = submitId;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	
	/**
	 * @return the feedback
	 */
	public String getFeedback() {
		return feedback;
	}

	/**
	 * @param feedback the feedback to set
	 */
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	
	
	
}
