package com.test.nos.devops.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.nos.devops.enums.Status;
import lombok.Data;

@Data
public class Task {
    private Integer id;
    @JsonProperty("user_id")
    private Integer userId;
    private String title;
    @JsonProperty("due_on")
    private String dueOn;
    private Status status;
}
