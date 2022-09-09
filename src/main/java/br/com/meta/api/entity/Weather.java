package br.com.meta.api.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.meta.api.dto.WeatherDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name="WEATHER")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Weather implements Serializable {
    private static final long serialVersionUID = 486486966274822754L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column
    private Integer temp;
    @Column
    private String date;
    @Column
    private String time;
    @Column(name="condition_code")
    private String conditionCode;
    @Column
    private String description;
    @Column
    private String currently;
    @Column
    private String cid;
    @Column
    private String city;
    @Column(name="img_id")
    private String imgId;
    @Column
    private Integer humidity;
    @Column(name="wind_speedy")
    private String windSpeedy;
    @Column
    private String sunrise;
    @Column
    private String sunset;
    @Column(name="condition_slug")
    private String conditionSlug;
    @Column(name="city_name")
    private String cityName;

    @Builder.Default
    @OneToMany (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Forecast> forecast = new ArrayList<>();


    public WeatherDTO toWeatherDTO() {
        return WeatherDTO.builder()
        .id(id)
        .temp(temp)
        .date(date)
        .conditionCode(conditionCode)
        .description(description)
        .currently(currently)
        .cid(cid)
        .city(city)
        .cityName(cityName)
        .imgId(imgId)
        .windSpeedy(windSpeedy)
        .sunrise(sunrise)
        .sunset(sunset)
        .conditionSlug(conditionSlug)
        .humidity(humidity)
        .time(time)
        .forecast(forecast != null ? forecast.stream().map(Forecast::toForecastDTO).collect(Collectors.toList()) : new ArrayList<>())
        .build();
    }
}
