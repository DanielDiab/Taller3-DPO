package uniandes.dpoo.aerolinea.modelo;

import java.util.HashSet;
import java.util.Set;
/**
 * Esta clase representa un avión en el sistema de la aerolínea.
 * Cada avión tiene un nombre único y una capacidad de pasajeros.
 */
public class Avion {

    // Atributos
    private String nombre;
    private int capacidad;

    /**
     * Constructor de la clase Avion.
     * 
     * @param nombre El nombre del avión.
     * @param capacidad La capacidad de pasajeros del avión.
     */
    public Avion(String nombre, int capacidad) {
        this.nombre = nombre;
        this.capacidad = capacidad;
    }

    /**
     * Retorna el nombre del avión.
     * 
     * @return El nombre del avión.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Retorna la capacidad de pasajeros del avión.
     * 
     * @return La capacidad del avión.
     */
    public int getCapacidad() {
        return capacidad;
    }

    /**
     * Retorna una representación en cadena de caracteres del avión.
     * 
     * @return Una cadena que describe el avión, incluyendo su nombre y capacidad.
     */
    @Override
    public String toString() {
        return "Avión: " + nombre + " (Capacidad: " + capacidad + " pasajeros)";
    }

    /**
     * Compara este avión con otro objeto para determinar si son iguales.
     * Dos aviones son iguales si tienen el mismo nombre.
     * 
     * @param obj El objeto con el cual se compara este avión.
     * @return true si los aviones son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Avion otroAvion = (Avion) obj;
        return nombre.equals(otroAvion.nombre);
    }

    /**
     * Retorna un código hash para el avión, basado en su nombre.
     * 
     * @return El código hash del avión.
     */
    @Override
    public int hashCode() {
        return nombre.hashCode();
    }
}