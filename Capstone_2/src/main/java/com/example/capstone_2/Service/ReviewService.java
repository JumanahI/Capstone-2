package com.example.capstone_2.Service;

import com.example.capstone_2.Model.Project;
import com.example.capstone_2.Model.Review;
import com.example.capstone_2.Repository.ProjectRepository;
import com.example.capstone_2.Repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProjectRepository projectRepository;

    public List<Review> getReviews(){
        return reviewRepository.findAll();
    }

    public boolean addReview(Review review){
        Project project = projectRepository.findProjectById(review.getProjectId());
        if(project == null){
            return false;
        }
        reviewRepository.save(review);
        return true;
    }

    public int updateReview(Integer id,Review review){
        Project project = projectRepository.findProjectById(review.getProjectId());
        if(project == null ){
            return 1;
        }
        Review oldReview = reviewRepository.findReviewById(id);
        if(oldReview == null){
            return 2;
        }
        oldReview.setContent(review.getContent());
        oldReview.setProjectId(review.getProjectId());
        reviewRepository.save(oldReview);
        return 3;
    }

    public boolean deleteReview(Integer id){
        Review review = reviewRepository.findReviewById(id);
        if(review == null){
            return false;
        }
        reviewRepository.delete(review);
        return true;
    }

    public List<Review> getReviewsByProjectId(Integer projectId){
        return reviewRepository.findReviewByProjectId(projectId);
    }

    public List<Review> orderReviewByRate(Integer projectId){
        return reviewRepository.orderReviewByRate(projectId);
    }

    public  List<Review> getReviewsLatestFirst(Integer projectId){
        return reviewRepository.getReviewsLatestFirst(projectId);
    }
}
