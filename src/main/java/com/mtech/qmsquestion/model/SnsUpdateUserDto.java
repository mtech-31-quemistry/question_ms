package com.mtech.qmsquestion.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mtech.qmsquestion.util.InstantSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class SnsUpdateUserDto {

    private String accountUuid;
    private boolean seekingJob;
    @JsonSerialize(using = InstantSerializer.class)
    private Instant sendTimestamp = Instant.now();

    public SnsUpdateUserDto(String accountUuid, boolean seekingJob) {
        this.accountUuid = accountUuid;
        this.seekingJob = seekingJob;
    }

}