package org.example.service;

import com.google.gson.*;
import okhttp3.*;

import java.io.IOException;
import java.time.Duration;

public class LLMClient {

    private static final String URL = "http://localhost:11434/api/generate";
    private static final String MODEL = "gemma:2b";

    private final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(Duration.ofSeconds(60))
            .readTimeout(Duration.ofSeconds(120))
            .writeTimeout(Duration.ofSeconds(120))
            .build();

    public String generate(String prompt) throws IOException {

        JsonObject body = new JsonObject();
        body.addProperty("model", MODEL);
        body.addProperty("prompt", prompt);
        body.addProperty("stream", false);

        Request request = new Request.Builder()
                .url(URL)
                .post(RequestBody.create(
                        MediaType.parse("application/json"),
                        body.toString()))
                .build();

        try (Response res = client.newCall(request).execute()) {
            String json = res.body().string();
            return JsonParser.parseString(json)
                    .getAsJsonObject()
                    .get("response").getAsString();
        }
    }
}
