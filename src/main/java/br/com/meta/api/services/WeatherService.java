package br.com.meta.api.services;

import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import br.com.meta.api.dto.WeatherRequest;
import br.com.meta.api.entity.Weather;
import br.com.meta.api.repository.WeatherRepository;

@Service
public class WeatherService implements ServiceDao<Weather> {

    private static final Logger log = LoggerFactory.getLogger(WeatherService.class);

    @Value("${url_api}")
    private String BASE_URL;

    @Autowired
	private ForecastService forecastService;

    @Autowired
    private WeatherRepository weatherRepository;

    protected RestTemplate restTemplate;
    
    
    @Override
    public Weather inserir(Weather t) {
        log.info("inserindo {}", t);
        t.getForecast().forEach(f -> {
            f = forecastService.inserir(f);
        });
        return weatherRepository.save(t);
    }

    @Override
    public Boolean excluir(Integer id) {
        log.info("excluindo {}", id);
        
        Optional<Weather> w = buscarPorId(id);
        if (w.isPresent()) {
            w.get().getForecast().forEach(f -> {
                forecastService.excluir(f.getId());
            });
            weatherRepository.deleteById(id);
            if (buscarPorId(id) == null) return true;
        }
        return false;
    }

    @Override
    public List<Weather> listar() {
        log.info("listar");
        return weatherRepository.findAll();
    }

    @Override
    public Optional<Weather> buscarPorId(Integer id) {
        log.info("buscarPorId: {}", id);
        return weatherRepository.findById(id);
    }
    
    
    public WeatherRequest consumir() {
        restTemplate = new RestTemplate();
        
        Object retorno = restTemplate.getForObject(BASE_URL, Object.class);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = "";
        try {
            json = objectMapper.writeValueAsString(retorno);
        } catch (JsonProcessingException e) {}

        JSONObject jsonObject = new JSONObject(json);
        jsonObject = jsonObject.getJSONObject("results");

        Gson gson = new Gson();
        WeatherRequest w = gson.fromJson(jsonObject.toString(), WeatherRequest.class);
        return w;
    }
}
