package br.com.meta.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.meta.api.entity.Weather;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Integer> {
    
}
