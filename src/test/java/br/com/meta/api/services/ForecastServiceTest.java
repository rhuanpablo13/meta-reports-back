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

import br.com.meta.api.entity.Forecast;
import br.com.meta.api.repository.ForecastRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ForecastServiceTest {
    
    @MockBean
	private ForecastRepository forecastRepository;

	@Autowired
	private ForecastService forecastService;


    @Before
	public void setUp() throws Exception {

        Forecast w1 = new Forecast();
        Forecast w2 = new Forecast();
        List<Forecast> lista = new ArrayList<>();
        lista.add(w1);
        lista.add(w2);

        when(forecastRepository.findAll()).thenReturn(lista);
        // BDDMockito.given(this.weatherRepository.findAll().willReturn(new ArrayList<>()));
		BDDMockito.given(this.forecastRepository.findById(Mockito.anyInt())).willReturn(Optional.of(new Forecast()));
		BDDMockito.given(this.forecastRepository.save(Mockito.any(Forecast.class))).willReturn(new Forecast());
	}


    @Test
	public void testPersistirForecast() {
		Forecast we = new Forecast();
		we.setCondition("clear_day");
		we.setDate("08/09");
        we.setDescription("Tempo limpo");
        we.setWeekday("Qui");
        we.setMax(30);
        we.setMin(15);
        
		//Empresa empresa = this.empresaService.persistir(new Empresa());
		we = this.forecastService.inserir(we);

		assertNotNull(we.getId());
	}


    @Test
	public void testBuscarPorId() {
		Optional<Forecast> we = this.forecastService.buscarPorId(1);
		System.out.println("**********************");
		System.out.println(we.get().toString());
		assertTrue(we.isPresent());
	}


    @Test
	public void testListar() {
		List<Forecast> we = this.forecastService.listar();
		assertTrue(we.isEmpty() == false && we.size() == 2);
	}

}
