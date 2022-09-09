package br.com.meta.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ForecastDTO {
    
    private Integer id;
    private String date;
    private String weekday;
    private Integer max;
    private Integer min;
    private String description;
    private String condition;
}
