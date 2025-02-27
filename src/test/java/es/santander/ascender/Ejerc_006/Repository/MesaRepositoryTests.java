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
import es.santander.ascender.ejerc_006.Model.Mesa;
import es.santander.ascender.ejerc_006.Repository.AulaRepository;
import es.santander.ascender.ejerc_006.Repository.MesaRepository;
import es.santander.ascender.ejerc_006.Service.MesaService;

@SpringBootTest
public class MesaRepositoryTests {

    @Autowired
    private MesaService mesaService;

    @Autowired
    private AulaRepository aulaRepository;

    @Autowired
    private MesaRepository mesaRepository;

    @BeforeEach
    public void setUp() {
        mesaRepository.deleteAll();
    }

    @Test
    public void testList() {
        Iterable<Mesa> valores = mesaRepository.findAll();
        assertNotNull(valores, "La lista de mesas no debería ser nula");
    }

    @Test
    public void testFindNoExistente() {
        Optional<Mesa> resultado = mesaRepository.findById(526L);
        assertTrue(resultado.isEmpty(), "La mesa con ID 526 debería no existir");
    }

    @Test
    public void testCrearYLeerMesaConAula() {
        Aula aula = new Aula();
        aula.setNombre("Aula de Sistemas");
        aulaRepository.save(aula);

        Mesa mesa = new Mesa();
        mesa.setAltura(1.2);
        mesa.setMaterialUno("Madera");
        mesa.setAula(aula);
        mesaRepository.save(mesa);

        Mesa mesaRecuperada = mesaRepository.findById(mesa.getId()).orElse(null);
        assertNotNull(mesaRecuperada);
        assertEquals("Madera", mesaRecuperada.getMaterialUno());
        assertEquals(aula.getId(), mesaRecuperada.getAula().getId());
    }

    @Test
    public void testActualizarMesa() {
        Mesa mesa = new Mesa();
        mesa.setMaterialUno("Vidrio");
        mesaRepository.save(mesa);

        mesa.setMaterialUno("Metal");
        mesaRepository.save(mesa);

        Mesa mesaActualizada = mesaRepository.findById(mesa.getId()).orElse(null);
        assertNotNull(mesaActualizada);
        assertEquals("Metal", mesaActualizada.getMaterialUno());
    }

    @Test
    public void testEliminarMesa() {
        Mesa mesa = new Mesa();
        mesa.setMaterialUno("Plástico");
        mesaRepository.save(mesa);

        mesaRepository.deleteById(mesa.getId());

        assertFalse(mesaRepository.findById(mesa.getId()).isPresent());
    }

    @Test
    public void testMoverMesa() {
        // Crear aulas
        Aula aula1 = new Aula();
        aula1.setNombre("Aula 101");
        aulaRepository.save(aula1);

        Aula aula2 = new Aula();
        aula2.setNombre("Aula 102");
        aulaRepository.save(aula2);

        // Crear mesa en aula1
        Mesa mesa = new Mesa();
        mesa.setAula(aula1);
        mesa.setAltura(1.0);
        mesaRepository.save(mesa);

        // Verificar que la mesa está en aula1
        assertEquals(aula1.getId(), mesa.getAula().getId());

        // Mover la mesa a aula2
        mesaService.moverMesa(mesa.getId(), aula2.getId());

        // Recuperar la mesa y verificar que ahora está en aula2
        Mesa mesaMovida = mesaRepository.findById(mesa.getId()).orElseThrow();
        assertEquals(aula2.getId(), mesaMovida.getAula().getId());
    }
}
