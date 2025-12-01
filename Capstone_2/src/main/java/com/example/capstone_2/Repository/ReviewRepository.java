package com.example.capstone_2.Repository;

import com.example.capstone_2.Model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ReviewRepository extends JpaRepository<Review,Integer> {
    Review findReviewById(Integer id);
    List<Review> findReviewByProjectId(Integer projectId);

    @Query("select r from Review r where r.projectId = :projectId ORDER BY r.rate DESC")
    List<Review> orderReviewByRate(Integer projectId);

    @Query("select r from Review r where r.projectId = :projectId ORDER BY r.createdAt DESC")
    List<Review> getReviewsLatestFirst(Integer projectId);

}
