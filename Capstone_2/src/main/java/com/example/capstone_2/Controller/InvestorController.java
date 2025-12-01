package com.example.capstone_2.Controller;


import com.example.capstone_2.Api.ApiResponse;
import com.example.capstone_2.Model.Investor;
import com.example.capstone_2.Service.InvestorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/investor")
@RequiredArgsConstructor
public class InvestorController {

    private final InvestorService investorService;

    @GetMapping("/get")
    public ResponseEntity<?> getInvestors() {
        return ResponseEntity.status(200).body(investorService.getInvestors());}

    @PostMapping("/add")
    public ResponseEntity<?> addInvestor(@RequestBody @Valid Investor investor, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));

        }
        investorService.addInvestor(investor);
        return ResponseEntity.status(200).body(new ApiResponse("Investor added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateInvestor(@PathVariable Integer id, @RequestBody @Valid Investor investor, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        boolean isUpdated = investorService.updateInvestor(id, investor);
        if (isUpdated) {
            return ResponseEntity.status(200).body(new ApiResponse("Investor updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Investor id not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteInvestor(@PathVariable Integer id) {
        boolean isDeleted = investorService.deleteInvestor(id);
        if (isDeleted) {
            return ResponseEntity.status(200).body(new ApiResponse("Investor deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Investor id not found"));
    }
}
