package es.santander.ascender.ejerc_006.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.santander.ascender.ejerc_006.Model.Edificio;
import es.santander.ascender.ejerc_006.Service.EdificioService;

@RestController
@RequestMapping("/api/edificio")
public class EdificioController {

    @Autowired
    private EdificioService edificioService;

    @PostMapping
    public Edificio create(@RequestBody Edificio edificio) {
        return edificioService.create(edificio);
    }

    @GetMapping("/{id}")
    public Edificio read(@PathVariable("id") Long id) {
        return edificioService.read(id);
    }

    @GetMapping
    public List<Edificio> list() {
        return edificioService.read();
    }

    @PutMapping
    public Edificio update(@RequestBody Edificio edificio) {
        return edificioService.update(edificio);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        edificioService.delete(id);
    }
}
