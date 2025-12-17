package org.example.service;

import org.example.domain.Ticket;

public class PromptBuilder {

    public String build(Ticket ticket) {
        return """
You are a test case generator.

STRICT RULES:
- Output ONLY valid JSON.
- No explanation, no markdown, no comments.
- JSON must contain ONE field "testcases".
- "testcases" must have EXACTLY 5 items.

Each item must contain ONLY:
id, title, preconditions, steps, expected, priority, tags

Use ONLY the acceptance criteria below.

ACCEPTANCE CRITERIA:
%s

Return ONLY the JSON.
""".formatted(
                String.join("\n", ticket.acceptanceCriteria)
        );
    }
}
