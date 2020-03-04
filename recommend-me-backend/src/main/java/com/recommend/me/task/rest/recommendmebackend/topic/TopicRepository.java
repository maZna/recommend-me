package com.recommend.me.task.rest.recommendmebackend.topic;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long>{
	
	@Transactional
	@Modifying
	@Query("UPDATE Topic SET npm = ?1 WHERE id = ?2")
	public void updateNpm(double newNpm, long id);
	
	@Query("SELECT topic FROM Topic")
	public List<String> getTopics();
	
	@Query("SELECT T.question FROM Topic T WHERE id=?1")
	public String getTopicQuestion(long id);
	
}
