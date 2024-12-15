package PlagiarsimChecker.PlagiarsimProject.controller;

import PlagiarismChecker.plagiarismProject.service.AdaptiveJaccardSimilarity;
import PlagiarismChecker.plagiarismProject.service.ContentDensityChecker;
import PlagiarismChecker.plagiarismProject.service.PlagiarismDetector;
import PlagiarismChecker.plagiarismProject.service.SemanticHashing;
import PlagiarismChecker.plagiarismProject.service.SlidingWindowSimilarity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/plagiarism")
public class PlagiarismController {@PostMapping("/check")
public Map<String, Object> checkPlagiarism(@RequestBody Map<String, String> request) {
    String inputText = request.get("text");

    // Validate input text
    if (inputText == null || inputText.trim().isEmpty()) {
        throw new IllegalArgumentException("Input text cannot be empty");
    }

    // Initialize response map
    Map<String, Object> response = new HashMap<>();
    response.put("text", inputText);

    try {
        // Perform plagiarism detection using different algorithms

        // 1. Adaptive Jaccard Similarity
        double adaptiveJaccardSimilarity = AdaptiveJaccardSimilarity.calculate(inputText, "sample text from web");
        response.put("adaptiveJaccardSimilarity", adaptiveJaccardSimilarity);

        // 2. Content Density Check (flag if low density)
        boolean lowDensity = ContentDensityChecker.hasLowDensity(inputText);
        response.put("lowContentDensity", lowDensity);

        // 3. Sliding Window Similarity
        double slidingWindowSimilarity = SlidingWindowSimilarity.calculate(inputText, "sample text from web");
        response.put("slidingWindowSimilarity", slidingWindowSimilarity);

        // 4. Semantic Hashing Similarity
        double semanticHashing = SemanticHashing.calculate(inputText, "sample text from web");
        response.put("semanticHashing", semanticHashing);

    } catch (Exception e) {
        response.put("error", "An error occurred: " + e.getMessage());
    }

    return response;
}
}
