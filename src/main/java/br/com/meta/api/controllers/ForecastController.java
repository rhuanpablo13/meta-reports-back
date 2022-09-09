package br.com.meta.api.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.meta.api.dto.ForecastDTO;
import br.com.meta.api.dto.WeatherDTO;
import br.com.meta.api.dto.WeatherRequest;
import br.com.meta.api.entity.Forecast;
import br.com.meta.api.entity.Weather;
import br.com.meta.api.services.WeatherService;

@RestController
@RequestMapping("/forecast")
@CrossOrigin(origins = "*")
public class ForecastController {
    
    

    @Autowired
	private WeatherService weatherService;


    @PostMapping
	public ResponseEntity<WeatherDTO> cadastrar(@Valid @RequestBody WeatherRequest weatherDTO) {

        Weather w = toEntity(weatherDTO);
        w = weatherService.inserir(w);

        if (w.getId() != null) {
            return ResponseEntity.ok().body(w.toWeatherDTO());
        }
        return ResponseEntity.badRequest().build();
    }


    @GetMapping("/local")
	public ResponseEntity<List<WeatherDTO>> listar() {

        List<WeatherDTO> response = new ArrayList<>();

        List<Weather> ws = weatherService.listar();
        if (ws.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        response = ws.stream().map(Weather::toWeatherDTO).collect(Collectors.toList());
        return ResponseEntity.ok().body(response);
    }


    @GetMapping("/local/{id}")
	public ResponseEntity<WeatherDTO> buscar(@PathVariable("id") Integer id) {

        Optional<Weather> w = weatherService.buscarPorId(id);
        if (w.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(w.get().toWeatherDTO());
    }


    @DeleteMapping("/local/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable("id") Integer id) {

        Boolean w = weatherService.excluir(id);
        if (w == false) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(true);
    }


    @GetMapping("/api-externa/salvar")
	public ResponseEntity<WeatherDTO> buscarApiExternaESalvar() {

        WeatherRequest wr = weatherService.consumir();
        if (wr == null) {
            return ResponseEntity.noContent().build();
        }

        Weather w = weatherService.inserir(toEntity(wr));
        if (w != null) {
            return ResponseEntity.ok().body(w.toWeatherDTO());
        }
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/api-externa/listar")
	public ResponseEntity<WeatherDTO> buscarApiExterna() {

        WeatherRequest wr = weatherService.consumir();
        if (wr == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(toEntity(wr).toWeatherDTO());
    }




    private Weather toEntity(WeatherDTO weatherDTO) {
        Weather w = Weather.builder()
        .id(weatherDTO.getId() != null ? weatherDTO.getId() : null)
        .cid(weatherDTO.getCid())
        .city(weatherDTO.getCity())
        .cityName(weatherDTO.getCityName())
        .conditionCode(weatherDTO.getConditionCode())
        .conditionSlug(weatherDTO.getConditionSlug())
        .currently(weatherDTO.getCurrently())
        .date(weatherDTO.getDate())
        .description(weatherDTO.getDescription())
        .humidity(weatherDTO.getHumidity())
        .imgId(weatherDTO.getImgId())
        .sunrise(weatherDTO.getSunrise())
        .sunset(weatherDTO.getSunset())
        .temp(weatherDTO.getTemp())
        .time(weatherDTO.getTime())
        .windSpeedy(weatherDTO.getWindSpeedy())
        .build();

        List<Forecast> forecasts = new ArrayList<>();
        for (ForecastDTO dto : weatherDTO.getForecast()) {
            Forecast f = Forecast.builder()
            .id(dto.getId() != null ? dto.getId() : null)
            .condition(dto.getCondition())
            .date(dto.getDate())
            .description(dto.getDescription())
            .max(dto.getMax())
            .min(dto.getMin())
            .weekday(dto.getWeekday())
            .build();
        }

        w.setForecast(forecasts);
        return w;
    }


    private Weather toEntity(WeatherRequest weatherRequest) {
        System.out.println("Request : ");
        System.out.println(weatherRequest.toString());
        Weather w = Weather.builder()
        .id(weatherRequest.getId() != null ? weatherRequest.getId() : null)
        .cid(weatherRequest.getCid())
        .city(weatherRequest.getCity())
        .cityName(weatherRequest.getCity_name())
        .conditionCode(weatherRequest.getCondition_code())
        .conditionSlug(weatherRequest.getCondition_slug())
        .currently(weatherRequest.getCurrently())
        .date(weatherRequest.getDate())
        .description(weatherRequest.getDescription())
        .humidity(weatherRequest.getHumidity())
        .imgId(weatherRequest.getImg_id())
        .sunrise(weatherRequest.getSunrise())
        .sunset(weatherRequest.getSunset())
        .temp(weatherRequest.getTemp())
        .time(weatherRequest.getTime())
        .windSpeedy(weatherRequest.getWind_speedy())
        .build();

        List<Forecast> forecasts = new ArrayList<>();
        for (ForecastDTO dto : weatherRequest.getForecast()) {
            Forecast f = Forecast.builder()
            .id(dto.getId() != null ? dto.getId() : null)
            .condition(dto.getCondition())
            .date(dto.getDate())
            .description(dto.getDescription())
            .max(dto.getMax())
            .min(dto.getMin())
            .weekday(dto.getWeekday())
            .build();
            forecasts.add(f);
        }

        w.setForecast(forecasts);
        return w;
    }


    // private void jsonToObject() {
    //     ObjectMapper mapper = new ObjectMapper();
    //     Employee employee = objectMapper.readValue(json, Employee.class);
    // }
}
