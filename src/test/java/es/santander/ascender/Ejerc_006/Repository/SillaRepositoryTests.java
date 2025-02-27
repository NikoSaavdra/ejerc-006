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

import es.santander.ascender.ejerc_006.Model.Aula;
import es.santander.ascender.ejerc_006.Model.Silla;
import es.santander.ascender.ejerc_006.Repository.AulaRepository;
import es.santander.ascender.ejerc_006.Repository.SillaRepository;

@SpringBootTest
public class SillaRepositoryTests {

    @Autowired
    private SillaRepository sillaRepository;

    @Autowired
    private AulaRepository aulaRepository;

    @BeforeEach
    public void setUp() {
        sillaRepository.deleteAll();
    }

    @Test
    public void testList() {
        Iterable<Silla> valores = sillaRepository.findAll();
        assertNotNull(valores, "La lista de sillas no debería ser nula");
    }

    @Test
    public void testFindNoExistente() {
        Optional<Silla> resultado = sillaRepository.findById(526L);
        assertTrue(resultado.isEmpty(), "La silla con ID 526 debería no existir");
    }

    @Test
    public void testCrearYLeerSillaConAula() {
        Aula aula = new Aula();
        aula.setNombre("Aula de Informatica");
        aulaRepository.save(aula);

        Silla silla = new Silla();
        silla.setAltura(1.0);
        silla.setMaterialUno("Plastico");
        silla.setAula(aula);
        sillaRepository.save(silla);

        Silla sillaRecuperada = sillaRepository.findById(silla.getId()).orElse(null);
        assertNotNull(sillaRecuperada);
        assertEquals("Plastico", sillaRecuperada.getMaterialUno());
        assertEquals(aula.getId(), sillaRecuperada.getAula().getId());
    }

    @Test
    public void testActualizarSilla() {
        Silla silla = new Silla();
        silla.setMaterialUno("Tela");
        sillaRepository.save(silla);

        silla.setMaterialUno("Plástico");
        sillaRepository.save(silla);

        Silla sillaActualizada = sillaRepository.findById(silla.getId()).orElse(null);
        assertNotNull(sillaActualizada);
        assertEquals("Plástico", sillaActualizada.getMaterialUno());
    }

    @Test
    public void testEliminarSilla() {
        Silla silla = new Silla();
        silla.setMaterialUno("Aluminio");
        sillaRepository.save(silla);

        sillaRepository.deleteById(silla.getId());

        assertFalse(sillaRepository.findById(silla.getId()).isPresent());
    }
}
