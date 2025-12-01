package com.example.capstone_2.Controller;


import com.example.capstone_2.Api.ApiResponse;
import com.example.capstone_2.Model.Visitor;
import com.example.capstone_2.Service.VisitorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/visitor")
@RequiredArgsConstructor
public class VisitorController {

    private final VisitorService visitorService;


    @GetMapping("/get")
    public ResponseEntity<?> getVisitors() {
        return ResponseEntity.status(200).body(visitorService.getVisitors());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addVisitor(@RequestBody @Valid Visitor visitor, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        visitorService.addVisitor(visitor);
        return ResponseEntity.status(200).body(new ApiResponse("Visitor added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateVisitor(@PathVariable Integer id, @RequestBody @Valid Visitor visitor, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        boolean isUpdated = visitorService.updateVisitor(id, visitor);
        if (isUpdated) {
            return ResponseEntity.status(200).body(new ApiResponse("Visitor updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Visitor id not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteVisitor(@PathVariable Integer id) {
        boolean isDeleted = visitorService.deleteVisitor(id);
        if (isDeleted) {
            return ResponseEntity.status(200).body(new ApiResponse("Visitor deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Visitor id not found"));
    }
}
