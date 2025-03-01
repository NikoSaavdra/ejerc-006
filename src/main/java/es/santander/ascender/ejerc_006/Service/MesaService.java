package es.santander.ascender.ejerc_006.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.santander.ascender.ejerc_006.Model.Aula;
import es.santander.ascender.ejerc_006.Model.Mesa;
import es.santander.ascender.ejerc_006.Repository.AulaRepository;
import es.santander.ascender.ejerc_006.Repository.MesaRepository;

@Service
@Transactional
public class MesaService {

    @Autowired
    private MesaRepository mesaRepository;

    @Autowired
    private AulaRepository aulaRepository;

    public Mesa create(Mesa mesa) {
        if (mesa.getId() != null) {
            throw new CrudSecurityException("Han tratado de modificar un registro columna utilizando la creación",
                    CRUDOperation.CREATE,
                    mesa.getId());
        }
        return mesaRepository.save(mesa);
    }

    @Transactional(readOnly = true)
    public Mesa read(Long id) {
        return mesaRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Mesa> read() {
        return mesaRepository.findAll();
    }

    public Mesa update(Mesa mesa) {
        if (mesa.getId() == null) {
            throw new CrudSecurityException("Han tratado de crear un registro columna utilizando la modifición",
                    CRUDOperation.UPDATE,
                    null);

        }
        return mesaRepository.save(mesa);
    }

    public void delete(Long id) {
        mesaRepository.deleteById(id);
        return;
    }

    public void moverMesa(Long mesaId, Long nuevaAulaId) {
        Mesa mesa = mesaRepository.findById(mesaId)
                .orElseThrow(() -> new RuntimeException("Mesa no encontrada"));

        Aula nuevaAula = aulaRepository.findById(nuevaAulaId)
                .orElseThrow(() -> new RuntimeException("Aula no encontrada"));

        mesa.setAula(nuevaAula);  // Cambiar el aula de la mesa
        mesaRepository.save(mesa);
    }

}
