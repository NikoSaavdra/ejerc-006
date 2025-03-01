package es.santander.ascender.ejerc_006.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.santander.ascender.ejerc_006.Model.Silla;
import es.santander.ascender.ejerc_006.Repository.SillaRepository;

@Service
@Transactional
public class SillaService {

    @Autowired
    private SillaRepository repository;

    public Silla create(Silla silla) {
        if (silla.getId() != null) {
            throw new CrudSecurityException("Han tratado de modificar un registro columna utilizando la creación",
                    CRUDOperation.CREATE,
                    silla.getId());
        }
        return repository.save(silla);
    }

    @Transactional(readOnly = true)
    public Silla read(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Silla> read() {
        return repository.findAll();
    }

    public Silla update(Silla silla) {
        if (silla.getId() == null) {
            throw new CrudSecurityException("Han tratado de crear un registro columna utilizando la modifición",
                    CRUDOperation.UPDATE,
                    null);

        }
        return repository.save(silla);
    }

    public void delete(Long id) {
        repository.deleteById(id);
        return;
    }

}
