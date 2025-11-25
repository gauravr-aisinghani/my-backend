package com.example.util;


import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import java.nio.charset.StandardCharsets;


@Component
public class WhatsappSender {


@Value("${whatsapp.token}")
private String token;


@Value("${whatsapp.phone_id}")
private String phoneId;


public boolean sendImageMessage(String toNumber, String imageUrl) {

    if (token == null || token.isEmpty() || phoneId == null || phoneId.isEmpty()) {
        System.out.println("WhatsApp credentials missing → skipping WhatsApp sending.");
        return false;
    }

    try (CloseableHttpClient client = HttpClients.createDefault()) {
        String url = "https://graph.facebook.com/v17.0/" + phoneId + "/messages";
        HttpPost post = new HttpPost(url);
        post.setHeader("Authorization", "Bearer " + token);
        post.setHeader("Content-Type", "application/json");

        JSONObject image = new JSONObject();
        image.put("link", imageUrl);

        JSONObject body = new JSONObject();
        body.put("messaging_product", "whatsapp");
        body.put("to", toNumber);
        body.put("type", "image");
        body.put("image", image);

        post.setEntity(new StringEntity(body.toString(), StandardCharsets.UTF_8));

        try (CloseableHttpResponse resp = client.execute(post)) {
            int status = resp.getStatusLine().getStatusCode();
            return status >= 200 && status < 300;
        }

    } catch (Exception ex) {
        ex.printStackTrace();
        return false;
    }
}

}