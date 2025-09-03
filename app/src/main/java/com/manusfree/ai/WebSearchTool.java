package com.manusfree.ai;

import android.util.Log;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import org.json.JSONArray;
import org.json.JSONObject;

public class WebSearchTool {
    private static final String TAG = "WebSearchTool";
    
    public String search(String query) {
        try {
            Log.d(TAG, "Searching for: " + query);
            
            // Use DuckDuckGo Instant Answer API (free, no key required)
            String encodedQuery = URLEncoder.encode(query, "UTF-8");
            String urlString = "https://api.duckduckgo.com/?q=" + encodedQuery + "&format=json&no_html=1&skip_disambig=1";
            
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Manus-Free-Android/1.0");
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);
            
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                
                return parseSearchResults(response.toString(), query);
            } else {
                return "שגיאה בחיפוש: קוד " + responseCode;
            }
            
        } catch (Exception e) {
            Log.e(TAG, "Search error", e);
            return "שגיאה בחיפוש: " + e.getMessage();
        }
    }
    
    private String parseSearchResults(String jsonResponse, String query) {
        try {
            JSONObject json = new JSONObject(jsonResponse);
            StringBuilder result = new StringBuilder();
            result.append("🔍 תוצאות חיפוש עבור '").append(query).append("':\n\n");
            
            // Check for instant answer
            String abstractText = json.optString("Abstract", "");
            if (!abstractText.isEmpty()) {
                result.append("📄 תשובה מהירה:\n");
                result.append(abstractText).append("\n\n");
            }
            
            // Check for definition
            String definition = json.optString("Definition", "");
            if (!definition.isEmpty()) {
                result.append("📖 הגדרה:\n");
                result.append(definition).append("\n\n");
            }
            
            // Check for related topics
            JSONArray relatedTopics = json.optJSONArray("RelatedTopics");
            if (relatedTopics != null && relatedTopics.length() > 0) {
                result.append("🔗 נושאים קשורים:\n");
                for (int i = 0; i < Math.min(3, relatedTopics.length()); i++) {
                    JSONObject topic = relatedTopics.getJSONObject(i);
                    String text = topic.optString("Text", "");
                    if (!text.isEmpty()) {
                        result.append("• ").append(text.substring(0, Math.min(100, text.length())));
                        if (text.length() > 100) result.append("...");
                        result.append("\n");
                    }
                }
            }
            
            // If no results found
            if (result.length() <= 50) {
                result.append("לא נמצאו תוצאות ספציפיות.\n");
                result.append("נסה חיפוש עם מילות מפתח אחרות או פנה לגוגל לחיפוש מפורט יותר.");
            }
            
            return result.toString();
            
        } catch (Exception e) {
            Log.e(TAG, "Parse error", e);
            return "🔍 חיפוש עבור '" + query + "':\n\nמצטער, לא הצלחתי לעבד את תוצאות החיפוש. נסה שוב או השתמש במילות מפתח אחרות.";
        }
    }
}

