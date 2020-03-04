package com.recommend.me.task.rest.recommendmebackend.submission;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Represents the Survey Repository
 * @author Masna Ahmed
 * @author https://www.linkedin.com/in/masna-ahmed-355432160/
 * @version 1.0
 * @since 1.0
 */
@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long>{
	
	/**
	 * Obtains count of submissions for a given topic
	 * @param id unique id of topic
	 * @return returns a count
	 */
	@Query("SELECT count(*) FROM Submission S WHERE S.topic.id = ?1")
	public int getNumberOfSumbissionsByTopic(long id);
	
	/**
	 * Obtains count of number of promoters for a given topic
	 * @param id unique id of topic
	 * @return returns a count
	 */
	@Query("SELECT count(*) FROM Submission S WHERE S.score > 8 AND S.topic.id = ?1")
	public int getNumberOfPromotersByTopic(long id);
	
	/**
	 * Obtains count of detractors for a given topic
	 * @param id unique id of topic
	 * @return returns a count
	 */
	@Query("SELECT count(*) FROM Submission S WHERE S.score < 7 AND S.topic.id = ?1")
	public int getNumberOfDetractorsByTopic(long id);
	
	/**
	 * Obtains a list of submissions for a given topic
	 * @param id unique id of topic
	 * @return returns a list of Submission entities
	 */
	@Query("SELECT S FROM Submission S WHERE S.topic.id = ?1")
	public List<Submission> getSubmissionsByTopic(long id);
	
}