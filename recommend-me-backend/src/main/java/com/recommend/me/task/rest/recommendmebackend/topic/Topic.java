package com.recommend.me.task.rest.recommendmebackend.topic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Topic {
	@Id
	@GeneratedValue
	private Long id;
	
	private String question;
	
	@NotNull
	@Size(min=3, max=50)
	private String topic;
	
	@Column(precision=4, scale=1)
	private double npm = 0;
//	
//	@OneToMany(mappedBy = "topic")
//	private Set<Submission> submissions;
	
	public Topic()
	{
		super();
	}
	
	public Topic(Long id, String topic, double npm)
	{
		super();
		this.id = id;
		this.setTopic(topic);
		this.setNpm(npm);
	}
	
	public Long getID()
	{
		return this.id;
	}
	
	public void setId(Long id)
	{
		this.id = id;
	}

	/**
	 * @return the topic
	 */
	public String getTopic() {
		return topic;
	}

	/**
	 * @param topic the topic to set
	 */
	public void setTopic(String topic) {
		this.topic = topic;
	}

	/**
	 * @return the npm
	 */
	public double getNpm() {
		return npm;
	}

	/**
	 * @param npm the npm to set
	 */
	public void setNpm(double npm) {
		this.npm = npm;
	}

	/**
	 * @return the question
	 */
	public String getQuestion() {
		return question;
	}

	/**
	 * @param question the question to set
	 */
	public void setQuestion(String question) {
		this.question = question;
	}
	
//	/**
//	 * @return the submissions
//	 */
//	public Set<Submission> getSubmissions() {
//		return submissions;
//	}
//
//	/**
//	 * @param submissions the submissions to set
//	 */
//	public void setSubmissions(Set<Submission> submissions) {
//		this.submissions = submissions;
//	}
	
}
