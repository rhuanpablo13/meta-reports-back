package br.com.meta.api.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
    @OneToMany (mappedBy = "forecast", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Forecast> forecast = new ArrayList<>();
}
