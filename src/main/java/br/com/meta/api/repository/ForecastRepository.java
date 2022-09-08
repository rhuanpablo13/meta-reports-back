package br.com.meta.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.meta.api.entity.Forecast;

@Repository
public interface ForecastRepository  extends JpaRepository<Forecast, Integer> {
    
}
