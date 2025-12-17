# Ticket to Test Case Generation POC

## Overview

This Proof of Concept (POC) demonstrates how a **JIRA-style ticket** written in plain text can be automatically converted into **structured test cases** using a **local Large Language Model (LLM)**.

The system reads a ticket file, extracts its acceptance criteria, generates a concise prompt, sends it to a local LLM (Gemma 2B via Ollama), and produces test cases in **JSON format**.

This POC showcases the application of **OOAD (Object-Oriented Analysis and Design)** and **OOPS principles** in building a clean, modular, and extensible system.

---

## Problem Statement

Writing test cases manually from JIRA tickets is:
- Time-consuming
- Repetitive
- Error-prone

This POC aims to **automate test case generation** while maintaining structure and traceability to acceptance criteria.

---

## What This POC Does

1. Reads a JIRA-style ticket from a text file
2. Parses the ticket into a structured domain object
3. Builds a constrained prompt using acceptance criteria
4. Sends the prompt to a local LLM (Gemma 2B)
5. Extracts valid JSON from the model response
6. Writes generated test cases to a JSON file

---

## Input Format (Sample Ticket)

```text
Summary: Add Save for Later button

Description:
Users should be able to save a product to a list so that they can view it later.

Acceptance Criteria:
1. Save button is visible on product page.
2. Clicking save adds the product to user's saved list.
3. Saved list persists after refresh.

Definition of Done:
- Unit tests written
- QA test cases approved
```
---
## Output Format (Generated Test Cases)

```json
{
  "testcases": [
    {
      "id": "TC-001",
      "title": "Verify Save for Later button visibility",
      "preconditions": ["Product page loaded"],
      "steps": [
        "Open product page",
        "Locate Save for Later button"
      ],
      "expected": ["Save for Later button is visible"],
      "priority": "High",
      "tags": ["UI", "Smoke"]
    }
  ]
}
```
---
## Project Structure

```text
org.example
 ├── app
 │    └── TicketToTestCaseApp.java
 ├── domain
 │    └── Ticket.java
 └── service
      ├── TicketParser.java
      ├── PromptBuilder.java
      ├── LLMClient.java
      ├── JsonExtractor.java
      └── FileWriterService.java
```
---
## Design Approach (OOAD)

### Domain Layer

- **Ticket**  
  Represents the core business object containing ticket details.

### Service Layer

- **TicketParser** – Parses raw ticket text into a `Ticket` object
- **PromptBuilder** – Builds a constrained LLM prompt from acceptance criteria
- **LLMClient** – Communicates with the local LLM (Ollama)
- **JsonExtractor** – Extracts valid JSON from model output
- **FileWriterService** – Writes output to disk

### Application Layer

- **TicketToTestCaseApp**  
  Acts as the orchestrator and coordinates the complete workflow.
---

## Technologies Used

- Java 17
- Maven
- OkHttp
- Gson
- Ollama
- Gemma 2B (Local LLM)
---

## Prerequisites

- Java 17 installed
- Maven installed
- Ollama installed

Pull and start the model:

```bash
ollama pull gemma:2b
ollama serve
```
---



## How to Run

```md
1. Place the ticket file inside the project  
   Example: `tickets/save_for_later_ticket.txt`

2. Open the project in IntelliJ IDEA

3. Configure **Program Arguments**:
        tickets/save_for_later_ticket.txt
```
---

### Run `TicketToTestCaseApp`

---
## Output

- A JSON file containing generated test cases
- File is created in the project root directory
- File name is derived from the ticket summary

---
## Key Highlights

- Fully offline (no paid APIs)
- Clean OOAD-based design
- Clear separation of concerns
- Extensible and testable architecture
---

## Conclusion

This POC demonstrates how AI-assisted automation can improve software testing workflows while adhering to solid object-oriented design principles. It bridges traditional OOAD with modern LLM-driven development in a practical and extensible manner.

---
