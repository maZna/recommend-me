package com.recommend.me.task.rest.recommendmebackend.submission;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long>{
	
	@Query("SELECT count(*) FROM Submission S WHERE S.topic.id = ?1")
	public int getNumberOfSumbissionsByTopic(long id);
	
	@Query("SELECT count(*) FROM Submission S WHERE S.score > 8 AND S.topic.id = ?1")
	public int getNumberOfPromotersByTopic(long id);
	
	@Query("SELECT count(*) FROM Submission S WHERE S.score < 7 AND S.topic.id = ?1")
	public int getNumberOfDetractorsByTopic(long id);
	
	@Query("SELECT S FROM Submission S WHERE S.topic.id = ?1")
	public List<Submission> getSubmissionsByTopic(long id);
	
}