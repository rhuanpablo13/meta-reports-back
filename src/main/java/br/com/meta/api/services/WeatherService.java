package br.com.meta.api.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.meta.api.entity.Weather;
import br.com.meta.api.repository.WeatherRepository;

public class WeatherService implements ServiceDao<Weather> {

    private static final Logger log = LoggerFactory.getLogger(WeatherService.class);

    @Autowired
    private WeatherRepository weatherRepository;

    
    @Override
    public Weather inserir(Weather t) {
        log.info("inserindo {}", t);
        return weatherRepository.save(t);
    }

    @Override
    public Boolean excluir(Integer id) {
        log.info("excluindo {}", id);
        weatherRepository.deleteById(id);
        if (buscarPorId(id) == null) return true;
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
    
    
}
