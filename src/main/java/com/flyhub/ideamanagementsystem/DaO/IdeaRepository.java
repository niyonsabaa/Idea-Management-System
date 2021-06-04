package com.flyhub.ideamanagementsystem.DaO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.flyhub.ideamanagementsystem.Entity.Category;
import com.flyhub.ideamanagementsystem.Entity.Idea;
import com.flyhub.ideamanagementsystem.Entity.Priority;
import com.flyhub.ideamanagementsystem.Entity.User;
//public interface IdeaRepository extends JpaRepository<Idea, Long> {  
public interface IdeaRepository extends JpaRepository<Idea, Long> { 
	
	
	//List<Idea> findByUser(User user);
	Page<Idea> findByUser(User user,Pageable page);
	@Query("SELECT d FROM Idea d WHERE CONCAT(d.id,'',d.ideaTitle,'',d.dateOfSubmission,'',d.ideaDescription,'',d.ideaExecutiveSummary,'',d.ideaBackgroundDescription,'') LIKE %?1%")	
	public List<Idea> search(String keyword);
	//public Page<Idea> search(String keyword, Pageable page);
	
	@Modifying
	@Transactional
    @Query("UPDATE Idea i SET i.ideaTitle = :ideaTitle,i.category = :category,i.priority = :priority,i.dateOfSubmission = :dateOfSubmission,i.ideaDescription = :ideaDescription,i.ideaExecutiveSummary = :ideaExecutiveSummary,i.ideaBackgroundDescription = :ideaBackgroundDescription WHERE i.id = :ideaId")
    int updateIdea(@Param("ideaId") Long ideId, @Param("ideaTitle") String ideaTitle,@Param("category") Category category, @Param("priority") Priority priority,@Param("dateOfSubmission") String dateOfSubmission,@Param("ideaDescription") String ideaDescription, @Param("ideaExecutiveSummary") String ideaExecutiveSummary,@Param("ideaBackgroundDescription") String ideaBackgroundDescription);	
}
