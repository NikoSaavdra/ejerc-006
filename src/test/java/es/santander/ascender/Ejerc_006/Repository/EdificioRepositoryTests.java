package es.santander.ascender.Ejerc_006.Repository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.santander.ascender.ejerc_006.Model.Edificio;
import es.santander.ascender.ejerc_006.Repository.EdificioRepository;

@SpringBootTest
public class EdificioRepositoryTests {

    @Autowired
    private EdificioRepository edificioRepository;

    @BeforeEach
    public void setUp() {
        edificioRepository.deleteAll();
    }

    @Test
    public void testList() {
        Iterable<Edificio> valores = edificioRepository.findAll();
        assertNotNull(valores, "La lista de edificios no debería ser nula");
    }

    @Test
    public void testFindNoExistente() {
        Optional<Edificio> resultado = edificioRepository.findById(95L);
        assertTrue(resultado.isEmpty(), "El edificio con ID 95 debería no existir");
    }

    @Test
    public void testActualizarEdificio() {
        Edificio edificio = new Edificio();
        edificio.setNombre("Edificio A");
        edificioRepository.save(edificio);

        edificio.setNombre("Edificio B");
        edificioRepository.save(edificio);

        Edificio edificioActualizado = edificioRepository.findById(edificio.getId()).orElse(null);
        assertNotNull(edificioActualizado);
        assertEquals("Edificio B", edificioActualizado.getNombre());
    }

    @Test
    public void testEliminarEdificio() {
        Edificio edificio = new Edificio();
        edificio.setNombre("Edificio C");
        edificioRepository.save(edificio);

        edificioRepository.deleteById(edificio.getId());

        assertFalse(edificioRepository.findById(edificio.getId()).isPresent());
    }
}
