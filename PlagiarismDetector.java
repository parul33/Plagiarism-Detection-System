package PlagiarsimChecker.PlagiarsimProject.service;

import org.jsoup.Jsoup;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;
public class PlagiarismDetector { private static final List<String> SEARCH_URLS = Arrays.asList(
        "https://www.google.com/search?q=",
        "https://www.bing.com/search?q="
);

    // Method to fetch content from search engines and perform plagiarism check
    public static Map<String, Object> checkPlagiarism(String inputText) {
        Map<String, Object> result = new HashMap<>();

        // Filter out input with low content density
        if (ContentDensityChecker.hasLowDensity(inputText)) {
            result.put("error", "Input has low content density. Skipping detailed analysis.");
            return result;
        }

        List<String> webResults = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(SEARCH_URLS.size());

        // Fetch content from each search URL in parallel
        for (String searchUrl : SEARCH_URLS) {
            executor.submit(() -> {
                try {
                    String resultText = fetchWebContent(searchUrl + inputText.replace(" ", "+"));
                    synchronized (webResults) {
                        webResults.add(resultText);
                    }
                } catch (IOException e) {
                    System.err.println("Error fetching content from " + searchUrl + ": " + e.getMessage());
                }
            });
        }

        executor.shutdown();
        try {
            executor.awaitTermination(10, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            System.err.println("Error waiting for threads to finish: " + e.getMessage());
        }

        // Calculate and display similarity results for each web content fetched
        for (String webContent : webResults) {
            double semanticHashSimilarity = SemanticHashing.calculate(inputText, webContent);
            double adaptiveJaccardSimilarity = AdaptiveJaccardSimilarity.calculate(inputText, webContent);
            double slidingWindowSimilarity = SlidingWindowSimilarity.calculate(inputText, webContent);

            result.put("semanticHashSimilarity", semanticHashSimilarity);
            result.put("adaptiveJaccardSimilarity", adaptiveJaccardSimilarity);
            result.put("slidingWindowSimilarity", slidingWindowSimilarity);
        }

        return result;
    }

    // Method to fetch web content based on a search query
    private static String fetchWebContent(String url) throws IOException {
        return Jsoup.connect(url).get().body().text();
    }
}
