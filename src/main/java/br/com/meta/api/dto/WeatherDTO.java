package br.com.meta.api.dto;

import java.util.ArrayList;
import java.util.List;

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
public class WeatherDTO {
    
    private Integer id;
    private String conditionCode;
    private String imgId;
    private String date;
    private String windSpeedy;
    private String conditionSlug;
    private String city;
    private String cityName;
    private String description;
    private String currently;
    private String cid;
    private String sunrise;
    private String sunset;
    private String time;
    private Integer temp;
    private Integer humidity;

    @Builder.Default
    private List<ForecastDTO> forecast = new ArrayList<>();
}
