package com.example.capstone_2.Controller;
import com.example.capstone_2.Api.ApiResponse;
import com.example.capstone_2.Model.Request;
import com.example.capstone_2.Service.RequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/request")
@RequiredArgsConstructor
public class RequestController {

    private final RequestService requestService;


    @GetMapping("/get")
    public ResponseEntity<?> getRequests(){
        return ResponseEntity.status(200).body(requestService.getAllRequests());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addRequest(@RequestBody @Valid Request request, Errors errors){
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        int isAdded = requestService.addRequest(request);

        switch (isAdded){
            case 1:
                return ResponseEntity.status(400).body(new ApiResponse("Investor ID not found"));
            case 2:
                return ResponseEntity.status(400).body(new ApiResponse("Project ID not found"));
            case 3:
                return ResponseEntity.status(400).body(new ApiResponse("Investor status pend"));
            default:
                return ResponseEntity.status(200).body(new ApiResponse("Request added successfully"));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateRequest(@PathVariable Integer id,@RequestBody @Valid Request request, Errors errors){
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        int isUpdated = requestService.updateRequest(id, request);

        switch (isUpdated){
            case 1:
                return ResponseEntity.status(400).body(new ApiResponse("Request ID not found"));
            case 2:
                return ResponseEntity.status(400).body(new ApiResponse("Investor ID not found"));
            case 3:
                return ResponseEntity.status(400).body(new ApiResponse("Project ID not found"));
            default:
                return ResponseEntity.status(200).body(new ApiResponse("Request updated successfully"));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRequest(@PathVariable Integer id){
        boolean isDeleted = requestService.deleteRequest(id);
        if (isDeleted) {
            return ResponseEntity.status(200).body(new ApiResponse("Request deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Request id not found"));
    }

    @GetMapping("/get-requests-investorId/{investorId}")
    public ResponseEntity<?> getRequestsByInvestorId(@PathVariable Integer investorId){
        List<Request> requestList = requestService.getRequestsByInvestorId(investorId);
        if(requestList.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("There is no requests with the given investor ID"));
        }
        return ResponseEntity.status(200).body(requestList);
    }

    @GetMapping("/get-requests-projectId/{projectId}")
    public ResponseEntity<?> getRequestsByProjectId(@PathVariable Integer projectId){
        List<Request> requestList = requestService.getRequestsByProjectId(projectId);
        if(requestList.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("There is no requests with the given project ID"));
        }
        return ResponseEntity.status(200).body(requestList);
    }

    @PutMapping("/approve-request/{studentId}/{id}")
    public ResponseEntity<?> approveRequest(@PathVariable Integer studentId,@PathVariable Integer id){
        int isApproved = requestService.approveRequest(studentId,id);
        if(isApproved == 3){
            return ResponseEntity.status(200).body(new ApiResponse("Request approved successfully"));
        }
        if(isApproved == 2){
            return ResponseEntity.status(400).body(new ApiResponse("This student id not authorized to approve request"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Request id not found"));
    }

    @PutMapping("/reject-request/{studentId}/{id}")
    public ResponseEntity<?> rejectRequest(@PathVariable Integer studentId,@PathVariable Integer id){
        int isRejected = requestService.rejectRequest(studentId,id);
        if(isRejected == 3){
            return ResponseEntity.status(200).body(new ApiResponse("Request rejected successfully"));
        }
        if(isRejected == 2){
            return ResponseEntity.status(400).body(new ApiResponse("This student id not authorized to approve request"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Request id not found"));
    }
}
