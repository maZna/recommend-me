package com.recommend.me.task.rest.recommendmebackend.topic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Represents a Topic Entity
 * @author Masna Ahmed
 * @author https://www.linkedin.com/in/masna-ahmed-355432160/
 * @version 1.0
 * @since 1.0
 */
@Entity
public class Topic {
	
	/** Unique id for a topic
	 */
	@Id
	@GeneratedValue
	private Long id;
	
	/** The Survey Question associated with a topic
	 */
	private String question;
	
	/** The topic's name
	 */
	@NotNull
	@Size(min=3, max=50)
	private String topic;
	
	/** The net promoter score associated with a topic
	 */
	@Column(precision=4, scale=1)
	private double npm = 0;

	public Topic()
	{
		super();
	}
	
	/**
	 * Constructor for a Topic Entity
	 * @param id a unique topic ID
	 * @param topic a topic name
	 */
	public Topic(Long id, String topic, String question)
	{
		super();
		this.id = id;
		this.setTopic(topic);
		this.setQuestion(question);
		this.setNpm(0.0);
	}
	
	/**
	 * Getter method for Topic ID
	 * @return a Topic ID
	 */
	public Long getID()
	{
		return this.id;
	}
	
	/**
	 * Setter method for Topic ID
	 * @param id a Topic ID
	 */
	public void setId(Long id)
	{
		this.id = id;
	}

	/** Getter method for Topic name
	 * @return the topic
	 */
	public String getTopic() {
		return topic;
	}

	/** Setter method for topic name
	 * @param topic the topic to set
	 */
	public void setTopic(String topic) {
		this.topic = topic;
	}

	/** Getter method for npm
	 * @return the npm
	 */
	public double getNpm() {
		return npm;
	}

	/** Setter method for npm
	 * @param npm the npm to set
	 */
	public void setNpm(double npm) {
		this.npm = npm;
	}

	/** Getter method for question
	 * @return the question
	 */
	public String getQuestion() {
		return question;
	}

	/** Setter method for question
	 * @param question the question to set
	 */
	public void setQuestion(String question) {
		this.question = question;
	}
}
