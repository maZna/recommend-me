package com.recommend.me.task.rest.recommendmebackend.topic;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Represents a Topic Repository
 * @author Masna Ahmed
 * @author https://www.linkedin.com/in/masna-ahmed-355432160/
 * @version 1.0
 * @since 1.0
 */
@Repository
public interface TopicRepository extends JpaRepository<Topic, Long>{
	
	/**
	 * Updates the npm score for a given topic
	 * @param newNpm updated npm
	 * @param id unique id for the topic to update
	 */
	@Transactional
	@Modifying
	@Query("UPDATE Topic SET npm = ?1 WHERE id = ?2")
	public void updateNpm(double newNpm, long id);
	
	/**
	 * Retrieves all topic entities
	 * @return a list of topic
	 */
	@Query("SELECT topic FROM Topic")
	public List<String> getTopics();
	
	/**
	 * Retrieves the question from a particular topic
	 * @param id
	 * @return a topic question
	 */
	@Query("SELECT T.question FROM Topic T WHERE id=?1")
	public String getTopicQuestion(long id);
	
}
