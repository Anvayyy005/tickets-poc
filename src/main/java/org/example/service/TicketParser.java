package org.example.service;

import org.example.domain.Ticket;
import java.util.*;

public class TicketParser {

    public Ticket parse(String text) {

        String[] lines = text.split("\n");
        String section = "";

        StringBuilder summary = new StringBuilder();
        StringBuilder description = new StringBuilder();
        List<String> ac = new ArrayList<>();
        List<String> dod = new ArrayList<>();

        for (String line : lines) {
            String l = line.toLowerCase().trim();

            if (l.startsWith("summary")) { section = "summary"; continue; }
            if (l.startsWith("description")) { section = "desc"; continue; }
            if (l.startsWith("acceptance criteria")) { section = "ac"; continue; }
            if (l.startsWith("definition of done")) { section = "dod"; continue; }

            switch (section) {
                case "summary" -> summary.append(line).append("\n");
                case "desc" -> description.append(line).append("\n");
                case "ac" -> { if (!line.isBlank()) ac.add(line.trim()); }
                case "dod" -> { if (!line.isBlank()) dod.add(line.trim()); }
            }
        }

        Ticket t = new Ticket();
        t.summary = summary.toString().trim();
        t.description = description.toString().trim();
        t.acceptanceCriteria = ac;
        t.definitionOfDone = dod;
        return t;
    }
}
