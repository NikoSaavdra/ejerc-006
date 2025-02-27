package es.santander.ascender.ejerc_006.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "mesa")
public class Mesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "peso_mesa")
    private double peso;

    @Column(name = "altura_mesa")
    private double altura;

    @Column(name = "ancho_mesa")
    private double ancho;

    @Column(name = "material_uno")
    private String materialUno;

    @Column(name = "material_dos")
    private String materialDos;

    @ManyToOne
    @JoinColumn(name = "aula_id", nullable = false)
    private Aula aula;  // Relación con Aula

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getAncho() {
        return ancho;
    }

    public void setAncho(double ancho) {
        this.ancho = ancho;
    }

    public String getMaterialUno() {
        return materialUno;
    }

    public void setMaterialUno(String materialUno) {
        this.materialUno = materialUno;
    }

    public String getMaterialDos() {
        return materialDos;
    }

    public void setMaterialDos(String materialDos) {
        this.materialDos = materialDos;
    }

    public Aula getAula() {
        return aula;
    }

    public void setAula(Aula aula) {
        this.aula = aula;
    }
}
