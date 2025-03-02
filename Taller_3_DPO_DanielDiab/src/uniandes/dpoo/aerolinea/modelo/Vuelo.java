package uniandes.dpoo.aerolinea.modelo;

import java.util.Collection;
import java.util.HashSet;

import uniandes.dpoo.aerolinea.tiquetes.Tiquete;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.tarifas.CalculadoraTarifas;
import uniandes.dpoo.aerolinea.exceptions.VueloSobrevendidoException;

/**
 * Representa un vuelo de la aerolínea.
 */
public class Vuelo {
    private String fecha;
    private Ruta ruta;
    private Avion avion;
    private Collection<Tiquete> listaTiquetes;

    /**
     * Constructor de la clase Vuelo.
     * @param ruta Ruta del vuelo.
     * @param fecha Fecha del vuelo.
     * @param avion Avión asignado al vuelo.
     */
    public Vuelo(Ruta ruta, String fecha, Avion avion) {
        this.ruta = ruta;
        this.fecha = fecha;
        this.avion = avion;
        this.listaTiquetes = new HashSet<>();
    }

    public String getFecha() {
        return fecha;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public Avion getAvion() {
        return avion;
    }

    /**
     * Obtiene la colección de tiquetes asociados a este vuelo.
     * @return Colección de tiquetes.
     */
    public Collection<Tiquete> getTiquetes() {
        return listaTiquetes;
    }

    
    public int venderTiquetes(Cliente cliente, CalculadoraTarifas calculadora, int cantidad) throws VueloSobrevendidoException {
        if (listaTiquetes.size() + cantidad > avion.getCapacidad()) {
            throw new VueloSobrevendidoException(this);
        }
        
        int totalCosto = 0;
        for (int i = 0; i < cantidad; i++) {
            int tarifa = calculadora.calcularTarifa(this, cliente);
            Tiquete tiquete = new Tiquete(generarCodigoTiquete(), this, cliente, tarifa);
            listaTiquetes.add(tiquete);
            totalCosto += tarifa;
        }
        return totalCosto;
    }
    
    /**
     * Genera un código único para cada tiquete.
     * @return Código único de tiquete.
     */
    private String generarCodigoTiquete() {
        return "TQ-" + System.currentTimeMillis() + "-" + listaTiquetes.size();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vuelo vuelo = (Vuelo) obj;
        return fecha.equals(vuelo.fecha) && ruta.equals(vuelo.ruta) && avion.equals(vuelo.avion);
    }

}