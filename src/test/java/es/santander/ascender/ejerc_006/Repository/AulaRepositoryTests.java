package es.santander.ascender.ejerc_006.Repository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.santander.ascender.ejerc_006.Model.Aula;
import es.santander.ascender.ejerc_006.Model.Edificio;
import es.santander.ascender.ejerc_006.Repository.AulaRepository;
import es.santander.ascender.ejerc_006.Repository.EdificioRepository;

@SpringBootTest
public class AulaRepositoryTests {

    @Autowired
    private AulaRepository aulaRepository;

    @Autowired
    private EdificioRepository edificioRepository;

    @BeforeEach
    public void setUp() {
        aulaRepository.deleteAll();
    }

    @Test
    public void testList() {
        Iterable<Aula> valores = aulaRepository.findAll();
        assertNotNull(valores, "La lista de aulas no debería ser nula");
    }

    @Test
    public void testFindNoExistente() {
        Optional<Aula> resultado = aulaRepository.findById(89L);
        assertTrue(resultado.isEmpty(), "El aula con ID 89 debería no existir");
    }

    @Test
    public void testCrearYLeerAulaConEdificio() {
        Edificio edificio = new Edificio();
        edificio.setNombre("CIE");
        edificioRepository.save(edificio);

        Aula aula = new Aula();
        aula.setNombre("Aula 101");
        aula.setEdificio(edificio);
        aulaRepository.save(aula);

        Aula aulaRecuperada = aulaRepository.findById(aula.getId()).orElse(null);
        assertNotNull(aulaRecuperada);
        assertEquals("Aula 101", aulaRecuperada.getNombre());
        assertEquals(edificio.getId(), aulaRecuperada.getEdificio().getId());
    }

    @Test
    public void testActualizarAula() {
        Aula aula = new Aula();
        aula.setNombre("Aula 202");
        aulaRepository.save(aula);

        aula.setNombre("Aula 203");
        aulaRepository.save(aula);

        Aula aulaActualizada = aulaRepository.findById(aula.getId()).orElse(null);
        assertNotNull(aulaActualizada);
        assertEquals("Aula 203", aulaActualizada.getNombre());
    }

    @Test
    public void testEliminarAula() {
        Aula aula = new Aula();
        aula.setNombre("Aula 404");
        aulaRepository.save(aula);

        aulaRepository.deleteById(aula.getId());

        assertFalse(aulaRepository.findById(aula.getId()).isPresent());
    }
}
