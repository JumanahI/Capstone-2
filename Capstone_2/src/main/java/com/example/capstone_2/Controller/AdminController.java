package com.example.capstone_2.Controller;

import com.example.capstone_2.Api.ApiResponse;
import com.example.capstone_2.Model.Admin;
import com.example.capstone_2.Service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/get")
    public ResponseEntity<?> getAdmins() {
        return ResponseEntity.status(200).body(adminService.getAdmins());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addAdmin(@RequestBody @Valid Admin admin, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        adminService.addAdmin(admin);
        return ResponseEntity.status(200).body(new ApiResponse("Admin added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAdmin(@PathVariable Integer id, @RequestBody @Valid Admin admin, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        boolean isUpdated = adminService.updateAdmin(id, admin);
        if (isUpdated) {
            return ResponseEntity.status(200).body(new ApiResponse("Admin updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Admin id not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAdmin(@PathVariable Integer id) {
        boolean isDeleted = adminService.deleteAdmin(id);
        if (isDeleted) {
            return ResponseEntity.status(200).body(new ApiResponse("Admin deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Admin id not found"));
    }

    @PutMapping("/set-status-investor/{adminId}/{investorId}")
    public ResponseEntity<?> setStatusInvestor(@PathVariable Integer adminId,@PathVariable Integer investorId){
        int isSet= adminService.setInvestorStatus(adminId,investorId);
        switch (isSet){
            case 1:
                return ResponseEntity.status(400).body(new ApiResponse("Investor Id not found"));
            case 2:
                return ResponseEntity.status(400).body(new ApiResponse("Admin Id not found"));
            case 3:
                return ResponseEntity.status(400).body(new ApiResponse("Investor is already active"));
            case 4:
                return ResponseEntity.status(200).body(new ApiResponse("Investor set active successfully"));
            default:
                return ResponseEntity.status(400).body(new ApiResponse("Commercial register incorrect"));
        }
    }

}
