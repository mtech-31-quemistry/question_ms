package com.mtech.qmsquestion.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateUserDto {

    @NotNull (message = "seekingJob must not be null")
    private Boolean seekingJob;
}
