package com.example.capstone_2.Controller;

import com.example.capstone_2.Api.ApiResponse;
import com.example.capstone_2.Model.Review;
import com.example.capstone_2.Service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;


    @GetMapping("/get")
    public ResponseEntity<?> getReviews() {
        return ResponseEntity.status(200).body(reviewService.getReviews());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addReview(@RequestBody @Valid Review review, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        boolean isAdded = reviewService.addReview(review);
        if(isAdded){
            return ResponseEntity.status(200).body(new ApiResponse("Review added successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Project ID not fond"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateReview(@PathVariable Integer id, @RequestBody @Valid Review review, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        int isUpdated = reviewService.updateReview(id, review);
        if (isUpdated == 3) {
            return ResponseEntity.status(200).body(new ApiResponse("Review updated successfully"));
        } else if (isUpdated == 2) {
            return ResponseEntity.status(400).body(new ApiResponse("Review id not found"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Project ID not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteReview(@PathVariable Integer id) {
        boolean isDeleted = reviewService.deleteReview(id);
        if (isDeleted) {
            return ResponseEntity.status(200).body(new ApiResponse("Review deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Review id not found"));
    }

    @GetMapping("/get-review-projectId/{projectId}")
    public ResponseEntity<?> getReviewByProjectId(@PathVariable Integer projectId){
        List<Review> reviewList = reviewService.getReviewsByProjectId(projectId);
        if(reviewList.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("There is no review with given project ID"));
        }
        return ResponseEntity.status(200).body(reviewList);
    }

    @GetMapping("/order-review-rate/{projectId}")
    public ResponseEntity<?> orderReviewByRate(@PathVariable Integer projectId){
        List<Review> reviewList = reviewService.orderReviewByRate(projectId);
        if(reviewList.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("There is no reviews to the project"));
        }
        return ResponseEntity.status(200).body(reviewList);
    }


    @GetMapping("/get-review-date/{projectId}")
    public ResponseEntity<?> getReviewsLatestFirst(@PathVariable Integer projectId){
        List<Review> reviewList = reviewService.getReviewsLatestFirst(projectId);
        if(reviewList.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("There is no reviews to the project"));
        }
        return ResponseEntity.status(200).body(reviewList);
    }

}
