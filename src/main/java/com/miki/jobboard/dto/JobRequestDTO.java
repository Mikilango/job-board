package com.miki.jobboard.dto;

import com.miki.jobboard.entity.JobStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobRequestDTO {

    @NotBlank(message = "Название вакансии не должно быть пустым")
    private String title;

    @NotBlank(message = "Описание вакансии не должно быть пустым")
    private String description;

    @Min(value = 1, message = "Зарплата должна быть не менее 1")
    private double salary;

    @NotBlank(message = "Местоположение не должно быть пустым")
    private String location;

}
