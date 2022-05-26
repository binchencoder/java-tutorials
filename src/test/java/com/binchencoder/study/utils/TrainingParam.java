package com.binchencoder.study.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * 模型训练参数
 *
 * @author chenbin
 */
@Data
@AllArgsConstructor
@Builder
@JsonIgnoreProperties
public class TrainingParam {

    @JsonProperty("n_iter")
    private Integer iterationNum;

    @JsonProperty("incremental_training")
    private Boolean incrementTraining;
}
