package br.com.meta.api.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.meta.api.entity.Forecast;
import br.com.meta.api.repository.ForecastRepository;

@Service
public class ForecastService implements ServiceDao<Forecast> {
    
    private static final Logger log = LoggerFactory.getLogger(ForecastService.class);

    @Autowired
    private ForecastRepository forecastRepository;
        
    
    @Override
    public Forecast inserir(Forecast t) {
        log.info("inserindo {}", t);
        return forecastRepository.save(t);
    }

    @Override
    public Boolean excluir(Integer id) {
        log.info("excluindo {}", id);
        forecastRepository.deleteById(id);
        if (buscarPorId(id) == null) return true;
        return false;
    }

    @Override
    public List<Forecast> listar() {
        log.info("listar");
        return forecastRepository.findAll();
    }

    @Override
    public Optional<Forecast> buscarPorId(Integer id) {
        log.info("buscarPorId: {}", id);
        return forecastRepository.findById(id);
    }

}
