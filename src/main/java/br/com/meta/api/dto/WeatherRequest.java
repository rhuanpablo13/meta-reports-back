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
public class WeatherRequest {
    
    private Integer id;
    private String condition_code;
    private String img_id;
    private String date;
    private String wind_speedy;
    private String condition_slug;
    private String city;
    private String city_name;
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
