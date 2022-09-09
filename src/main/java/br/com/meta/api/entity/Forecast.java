package br.com.meta.api.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.meta.api.dto.ForecastDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name="FORECAST")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Forecast implements Serializable {
    private static final long serialVersionUID = 486486966274822754L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String date;
    @Column
    private String weekday;
    @Column
    private Integer max;
    @Column
    private Integer min;
    @Column
    private String description;
    @Column
    private String condition;

    // @ManyToOne(fetch = FetchType.EAGER)
    // private Weather weather;


    public ForecastDTO toForecastDTO() {
        return ForecastDTO.builder()
        .id(id)
        .date(date)
        .weekday(weekday)
        .max(max)
        .min(min)
        .description(description)
        .condition(condition)
        .build();
    }
}
