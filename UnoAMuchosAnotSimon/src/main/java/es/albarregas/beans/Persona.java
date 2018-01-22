/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.beans;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Simon
 */
@Entity
@Table(name = "persona1anAnot")
public class Persona implements Serializable{
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long idPerso;
    private String nombre;
    @OneToMany(fetch=FetchType.EAGER,cascade={CascadeType.ALL})
    @JoinTable(name="personaLibroAnotacion",
            joinColumns = @JoinColumn(name="personaId",referencedColumnName = "idPerso"), 
            inverseJoinColumns = @JoinColumn (name="libroId",
            referencedColumnName = "idLibro"))
    
    private List<Libro> libros;

    public Long getIdPerso() {
        return idPerso;
    }

    public void setIdPerso(Long idPerso) {
        this.idPerso = idPerso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }
       
}
