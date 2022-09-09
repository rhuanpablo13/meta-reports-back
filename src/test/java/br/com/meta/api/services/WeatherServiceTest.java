package br.com.meta.api.services;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.meta.api.entity.Weather;
import br.com.meta.api.repository.WeatherRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class WeatherServiceTest {
    
    @MockBean
	private WeatherRepository weatherRepository;

	@Autowired
	private WeatherService weatherService;


    @Before
	public void setUp() throws Exception {

        Weather w1 = new Weather();
        Weather w2 = new Weather();
        List<Weather> lista = new ArrayList<>();
        lista.add(w1);
        lista.add(w2);

        when(weatherRepository.findAll()).thenReturn(lista);
        // BDDMockito.given(this.weatherRepository.findAll().willReturn(new ArrayList<>()));
		BDDMockito.given(this.weatherRepository.findById(Mockito.anyInt())).willReturn(Optional.of(new Weather()));
		BDDMockito.given(this.weatherRepository.save(Mockito.any(Weather.class))).willReturn(new Weather());
	}


    @Test
	public void testPersistirWeather() {
		Weather we = new Weather();
		we.setCid("001");
        we.setCity("Brasilia");
        we.setCityName("Brasilia");
        we.setConditionCode("002");
        we.setConditionSlug("slug 002");
        we.setCurrently("dia");
        we.setDate("08/09/2022");
        we.setDescription("");
        we.setHumidity(43);
        we.setImgId("27");
        we.setSunrise("06:10 am");
        we.setSunset("05:58 pm");
        we.setTemp(25);
        we.setTime("12:39");
        we.setWindSpeedy("3.6 km/h");
		
		//Empresa empresa = this.empresaService.persistir(new Empresa());
		we = this.weatherService.inserir(we);

		assertNotNull(we.getId());
	}


    @Test
	public void testBuscarPorId() {
		Optional<Weather> we = this.weatherService.buscarPorId(1);
		System.out.println("**********************");
		System.out.println(we.get().toString());
		assertTrue(we.isPresent());
	}


    @Test
	public void testListar() {
		List<Weather> we = this.weatherService.listar();
		assertTrue(we.isEmpty() == false && we.size() == 2);
	}

}
