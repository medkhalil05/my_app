/*package com.example.gestion_residents.service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Value("${mailgun.api.key}")
    private String apiKey;

    @Value("${mailgun.domain}")
    private String domain;

    @Value("${mailgun.from.email}")
    private String fromEmail;

    public void sendEmail(String toEmail, String subject, String body) {
        try {
            HttpResponse<String> request = Unirest.post("https://api.mailgun.net/v3/" + domain + "/messages")
                    .basicAuth("api", apiKey)
                    .queryString("from", fromEmail)
                    .queryString("to", toEmail)
                    .queryString("subject", subject)
                    .queryString("text", body)
                    .asString();
            System.out.println("Email sent: " + request.getBody());
        } catch (Exception ex) {
            System.out.println("Error sending email: " + ex.getMessage());
        }
    }
}*/
