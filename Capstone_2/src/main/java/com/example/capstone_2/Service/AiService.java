package com.example.capstone_2.Service;

import com.example.capstone_2.Model.Project;
import com.example.capstone_2.Repository.ProjectRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class AiService {

    private final ProjectRepository projectRepository;

    @Value("${spring.ai.openai.api-key}")
    private String apiKey;

    private final WebClient webClient = WebClient.builder()
            .baseUrl("https://api.openai.com/v1")
            .build();

    public String askAI(String prompt) {

        Map<String, Object> body = Map.of(
                "model", "gpt-4o",
                "messages", List.of(
                        Map.of("role", "user", "content", prompt)
                )
        );

        return webClient.post()
                .uri("/chat/completions")
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .bodyValue(body)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public List<String> evaluateProject(Integer projectId) {
        Project project = projectRepository.findProjectById(projectId);
        if (project == null) {
            throw new IllegalArgumentException("Project not found");
        }

        String prompt =
                "Evaluate this graduation project.\n\n" +
                        "Title: " + project.getTitle() + "\n" +
                        "Description: " + project.getDescription() + "\n" +
                        "Field: " + project.getFiled() + "\n" +
                        "GitHub: " + project.getGitHubUrl() + "\n" +
                        "Docs: " + project.getDocumentationUrl() + "\n\n" +
                        "Provide the evaluation as bullet points like this:" +
                        "- Strengths: ..." +
                        "- Weaknesses: ..." +
                        "- Score: (1-10)";

        String result = askAI(prompt);
        return Arrays.asList(result.split("\n"));
    }

    public String getImprovements(Integer projectId) {
        Project project = projectRepository.findProjectById(projectId);
        if (project == null) {
            throw new IllegalArgumentException("Project not found");
        }

        String prompt =
                "Provide only improvements for the following project.\n\n" +
                        "Title: " + project.getTitle() + "\n" +
                        "Description: " + project.getDescription() + "\n" +
                        "Field: " + project.getFiled() + "\n" +
                        "GitHub: " + project.getGitHubUrl() + "\n" +
                        "Docs: " + project.getDocumentationUrl() + "\n\n" +
                        "List each improvement on a new line like:" +
                        "- Improvement 1" +
                        "- Improvement 2" +
                        "- ...";

        return askAI(prompt);
    }

    private final ObjectMapper mapper = new ObjectMapper();
    public Map<String, Object> analyzeRisks(Integer projectId) throws IOException {
        Project project = projectRepository.findProjectById(projectId);
        if (project == null) {
            throw new IllegalArgumentException("Project not found");
        }

        String prompt =
                "Analyze the risks of the following project.\n\n" +
                        "Project Title: " + project.getTitle() + "\n" +
                        "Description: " + project.getDescription() + "\n" +
                        "Field: " + project.getFiled() + "\n" +
                        "GitHub: " + project.getGitHubUrl() + "\n" +
                        "Docs: " + project.getDocumentationUrl() + "\n\n" +
                        "Your task:" +
                        "Identify technical, operational, and business risks." +
                        "Provide recommendations to reduce these risks." +
                        "IMPORTANT:" +
                        "Return ONLY a valid JSON object. No text before or after it." +
                        "JSON structure:" +
                        "{" +
                        "  \"technicalRisks\": [\"...\", \"...\"]," +
                        "  \"operationalRisks\": [\"...\", \"...\"]," +
                        "  \"businessRisks\": [\"...\", \"...\"]," +
                        "  \"recommendations\": [\"...\", \"...\"]" +
                        "}";

        String rawJson = askAI(prompt);

        rawJson = rawJson.replaceAll("(?s)```json|```", "").trim();

        return mapper.readValue(rawJson, new TypeReference<Map<String, Object>>() {});
    }


}
