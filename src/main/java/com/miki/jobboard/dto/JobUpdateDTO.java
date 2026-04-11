package com.miki.jobboard.dto;

import com.miki.jobboard.entity.JobStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobUpdateDTO {

    @NotBlank(message = "Название не должно быть пустым")
    private String title;

    @NotBlank(message = "Описание не должно быть пустым")
    private String description;

    @Min(value = 1, message = "Зарплата должна быть не менее 1")
    private Double salary;

    @NotBlank(message = "Местоположение не должно быть пустым")
    private String location;

    @NotNull(message = "Статус не должен быть пустым")
    private JobStatus status;
}
