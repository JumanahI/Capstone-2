package com.example.capstone_2.Controller;

import com.example.capstone_2.Service.AiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/ai")
@RequiredArgsConstructor
public class AiController {

    private final AiService aiService;

    @GetMapping("/ask")
    public ResponseEntity<?> askAI(@RequestParam String question){

        String answer = aiService.askAI(question);

        return ResponseEntity.status(200).body(answer);
    }

    @GetMapping("/evaluate/{projectId}")
    public List<String> evaluateProject(@PathVariable Integer projectId) {
        return aiService.evaluateProject(projectId);
    }

    @GetMapping("/improvements/{projectId}")
    public List<String> getImprovements(@PathVariable Integer projectId) {
        String improvements = aiService.getImprovements(projectId);
        return Arrays.asList(improvements.split("\n"));
    }

    @GetMapping("/ai-risk-analysis/{projectId}")
    public Map<String, Object> getRiskAnalysis(@PathVariable Integer projectId) throws IOException {
        return aiService.analyzeRisks(projectId);

    }

}
