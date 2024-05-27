package com.test.nos.devops;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.nos.devops.dto.Task;
import com.test.nos.devops.enums.Status;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DevopsApplicationTests {
    private static final String API_URL = "https://gorest.co.in/public/v2/todos";
    private static final String SCHEMA_JSON = "schema.json";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

    private ObjectMapper objectMapper = new ObjectMapper();
    private static Response response;
    private static String jsonResponse;

    @BeforeAll
    public static void start() {
        response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .when()
                .get(API_URL)
                .then()
                .extract()
                .response();

        jsonResponse = response.getBody().asString();
    }

    @Test
    @DisplayName("Valida o schema")
    public void validateSchema() {
        response
                .then()
                .assertThat()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath(SCHEMA_JSON));
    }

    @Test
    @DisplayName("Valida se há alguma tarefa com o status diferente de 'completed'")
    public void validateIfStatusesIsAllCompleted() {
        List<Task> tasks = extractTasksFromResponse(jsonResponse);
        boolean isAllCompleted = tasks.stream().allMatch(task -> Status.completed == task.getStatus());

        assertFalse(isAllCompleted, "Todas as tarefas estão com o status 'completed'");
    }

    @Test
    @DisplayName("Interpreta e valida o valor “due_on”")
    public void validateIfDueOnValue() {
        List<Task> tasks = extractTasksFromResponse(jsonResponse);
        boolean isAllDueOnValid = tasks.stream().allMatch(task -> {
            try {
                LocalDateTime.parse(task.getDueOn(), FORMATTER);
                return true;
            } catch (DateTimeParseException e) {
                return false;
            }
        });

        assertTrue(isAllDueOnValid, "Existem dados inválidos para os valores 'due_on'");
    }

    private List<Task> extractTasksFromResponse(String jsonResponse) {
        List<Task> tasks = new ArrayList<>();
        try {
            tasks = objectMapper.readValue(jsonResponse, new TypeReference<List<Task>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasks;
    }
}