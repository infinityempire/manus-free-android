package com.manusfree.ai;

import android.app.Activity;
import android.os.Bundle;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class MainActivity extends Activity {
    
    private EditText messageInput;
    private TextView chatHistory;
    private ScrollView scrollView;
    private Button sendButton;
    private ManusAI aiEngine;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Initialize AI engine
        aiEngine = new ManusAI(this);
        
        // Initialize UI components
        messageInput = findViewById(R.id.messageInput);
        chatHistory = findViewById(R.id.chatHistory);
        scrollView = findViewById(R.id.scrollView);
        sendButton = findViewById(R.id.sendButton);
        
        // Set click listener
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
        
        // Welcome message
        addMessage("Manus-Free", "שלום! אני Manus-Free, עוזר AI אמיתי. איך אני יכול לעזור לך?");
    }
    
    private void sendMessage() {
        String message = messageInput.getText().toString().trim();
        if (message.isEmpty()) return;
        
        // Add user message to chat
        addMessage("אתה", message);
        messageInput.setText("");
        
        // Process with AI
        new AITask().execute(message);
    }
    
    private void addMessage(String sender, String message) {
        String currentText = chatHistory.getText().toString();
        String newMessage = sender + ": " + message + "\n\n";
        chatHistory.setText(currentText + newMessage);
        
        // Scroll to bottom
        scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.fullScroll(View.FOCUS_DOWN);
            }
        });
    }
    
    private class AITask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... messages) {
            return aiEngine.processMessage(messages[0]);
        }
        
        @Override
        protected void onPostExecute(String response) {
            addMessage("Manus-Free", response);
        }
    }
}

