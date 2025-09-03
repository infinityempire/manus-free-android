package com.manusfree.ai;

import android.content.Context;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class ManusAI {
    private static final String TAG = "ManusAI";
    private Context context;
    private Map<String, List<String>> sessions;
    private Random random;
    private WebSearchTool webSearch;
    
    // Real AI responses based on patterns
    private Map<String, String[]> responsePatterns;
    
    public ManusAI(Context context) {
        this.context = context;
        this.sessions = new HashMap<>();
        this.random = new Random();
        this.webSearch = new WebSearchTool();
        initializeResponsePatterns();
        Log.d(TAG, "Manus-Free AI Engine initialized");
    }
    
    private void initializeResponsePatterns() {
        responsePatterns = new HashMap<>();
        
        // Hebrew greetings
        responsePatterns.put("שלום", new String[]{
            "שלום! איך אני יכול לעזור לך היום?",
            "שלום וברוכים הבאים! במה אוכל לסייע?",
            "היי! אני כאן כדי לעזור לך עם כל מה שתצטרך."
        });
        
        // English greetings
        responsePatterns.put("hello", new String[]{
            "Hello! How can I help you today?",
            "Hi there! What can I do for you?",
            "Hello! I'm here to assist you with anything you need."
        });
        
        // Questions about capabilities
        responsePatterns.put("מה אתה יכול", new String[]{
            "אני יכול לעזור לך עם שאלות, לחפש מידע ברשת, לכתוב טקסטים, לעזור עם משימות יומיומיות ועוד הרבה דברים!",
            "אני עוזר AI מתקדם שיכול לענות על שאלות, לחפש מידע, לכתוב תוכן ולסייע במגוון משימות.",
            "יש לי יכולות רבות: מענה על שאלות, חיפוש ברשת, כתיבה יצירתית, עזרה בפתרון בעיות ועוד!"
        });
        
        responsePatterns.put("what can you", new String[]{
            "I can help you with questions, search the web, write content, assist with daily tasks, and much more!",
            "I'm an advanced AI assistant that can answer questions, search for information, write content, and help with various tasks.",
            "I have many capabilities: answering questions, web search, creative writing, problem-solving, and more!"
        });
        
        // Technology questions
        responsePatterns.put("בינה מלאכותית", new String[]{
            "בינה מלאכותית היא טכנולוגיה מרתקת שמאפשרת למחשבים ללמוד ולחשוב כמו בני אדם. היא משמשת בתחומים רבים כמו רפואה, חינוך, תחבורה ועוד.",
            "AI או בינה מלאכותית היא תחום שמתפתח במהירות ומשנה את העולם. היא כוללת למידת מכונה, עיבוד שפה טבעית, ראייה ממוחשבת ועוד.",
            "בינה מלאכותית היא כלי חזק שעוזר לנו לפתור בעיות מורכבות, לנתח מידע גדול ולשפר תהליכים בכל תחום."
        });
        
        // Default responses
        responsePatterns.put("default_hebrew", new String[]{
            "זה נושא מעניין! אני אשמח לעזור לך לחקור אותו יותר לעומק.",
            "יש לי כמה מחשבות על זה. תוכל לספר לי יותר פרטים?",
            "זה שאלה טובה! בואו נחשוב על זה יחד.",
            "אני כאן כדי לעזור. איך אוכל לסייע לך בנושא הזה?"
        });
        
        responsePatterns.put("default_english", new String[]{
            "That's an interesting topic! I'd be happy to help you explore it further.",
            "I have some thoughts on that. Could you tell me more details?",
            "That's a good question! Let's think about it together.",
            "I'm here to help. How can I assist you with this topic?"
        });
    }
    
    public String processMessage(String message) {
        try {
            Log.d(TAG, "Processing message: " + message);
            
            // Check for tool commands
            if (message.startsWith("tool:")) {
                return processToolCommand(message);
            }
            
            // Determine language
            boolean isHebrew = containsHebrew(message);
            String response = generateResponse(message, isHebrew);
            
            Log.d(TAG, "Generated response: " + response);
            return response;
            
        } catch (Exception e) {
            Log.e(TAG, "Error processing message", e);
            return "מצטער, אירעה שגיאה. נסה שוב בבקשה.";
        }
    }
    
    private String processToolCommand(String command) {
        if (command.startsWith("tool:search_web")) {
            String query = command.substring("tool:search_web".length()).trim();
            return webSearch.search(query);
        } else if (command.startsWith("tool:write_file")) {
            return "✅ קובץ נוצר בהצלחה (דמו - בגרסה מלאה יישמר באמת)";
        } else if (command.startsWith("tool:read_file")) {
            return "📄 תוכן הקובץ: זהו תוכן לדוגמה (בגרסה מלאה יקרא קובץ אמיתי)";
        }
        return "כלי לא מוכר: " + command;
    }
    
    private boolean containsHebrew(String text) {
        for (char c : text.toCharArray()) {
            if (c >= 0x0590 && c <= 0x05FF) {
                return true;
            }
        }
        return false;
    }
    
    private String generateResponse(String message, boolean isHebrew) {
        String lowerMessage = message.toLowerCase();
        
        // Check for specific patterns
        for (String pattern : responsePatterns.keySet()) {
            if (lowerMessage.contains(pattern.toLowerCase())) {
                String[] responses = responsePatterns.get(pattern);
                return responses[random.nextInt(responses.length)];
            }
        }
        
        // Generate contextual response based on content
        if (lowerMessage.contains("מזג אויר") || lowerMessage.contains("weather")) {
            return isHebrew ? 
                "למזג האוויר אני ממליץ לבדוק באפליקציית מזג אוויר או באתר שירות מזג האוויר המקומי שלך." :
                "For weather information, I recommend checking a weather app or your local weather service website.";
        }
        
        if (lowerMessage.contains("זמן") || lowerMessage.contains("time") || lowerMessage.contains("שעה")) {
            return isHebrew ?
                "אני לא יכול לראות את השעה הנוכחת, אבל אתה יכול לבדוק בשעון של הטלפון שלך." :
                "I can't see the current time, but you can check your phone's clock.";
        }
        
        if (lowerMessage.contains("תודה") || lowerMessage.contains("thank")) {
            return isHebrew ?
                "בשמחה! אני כאן בשבילך תמיד." :
                "You're welcome! I'm always here for you.";
        }
        
        // Default intelligent responses
        String[] defaultResponses = isHebrew ? 
            responsePatterns.get("default_hebrew") : 
            responsePatterns.get("default_english");
            
        return defaultResponses[random.nextInt(defaultResponses.length)];
    }
}

