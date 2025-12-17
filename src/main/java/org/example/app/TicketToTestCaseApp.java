package org.example.app;

import org.example.domain.Ticket;
import org.example.service.*;

import java.nio.file.Files;
import java.nio.file.Path;

public class TicketToTestCaseApp {

    public static void main(String[] args) throws Exception {

        String ticketText = Files.readString(Path.of(args[0]));

        TicketParser parser = new TicketParser();
        PromptBuilder promptBuilder = new PromptBuilder();
        LLMClient llmClient = new LLMClient();
        JsonExtractor extractor = new JsonExtractor();
        FileWriterService writer = new FileWriterService();

        Ticket ticket = parser.parse(ticketText);
        String prompt = promptBuilder.build(ticket);
        String response = llmClient.generate(prompt);
        String json = extractor.extract(response);

        String fileName = ticket.summary.replaceAll("\\W+", "_") + "_testcases.json";
        writer.write(fileName, json);

        System.out.println("Generated → " + fileName);
    }
}
