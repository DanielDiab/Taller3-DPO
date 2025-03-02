package uniandes.dpoo.aerolinea.modelo;

import java.util.HashSet;
import java.util.Set;

/**
 * Representa un aeropuerto dentro del sistema de la aerolínea.
 */
public class Aeropuerto {
    private String nombre;
    private String codigo;
    private String nombreCiudad;
    private double latitud;
    private double longitud;
    private Set<String> codigosUtilizados;
    private static final int RADIO_TERRESTRE = 6371;

    /**
     * Constructor de la clase Aeropuerto.
     * @param nombre Nombre del aeropuerto.
     * @param codigo Código único del aeropuerto.
     * @param nombreCiudad Nombre de la ciudad donde está ubicado.
     * @param latitud Latitud geográfica.
     * @param longitud Longitud geográfica.
     */
    public Aeropuerto(String nombre, String codigo, String nombreCiudad, double latitud, double longitud) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.nombreCiudad = nombreCiudad;
        this.latitud = latitud;
        this.longitud = longitud;
        this.codigosUtilizados = new HashSet<>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombreCiudad() {
        return nombreCiudad;
    }

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    /**
     * Calcula la distancia entre dos aeropuertos.
     * @param aeropuerto1 Primer aeropuerto.
     * @param aeropuerto2 Segundo aeropuerto.
     * @return Distancia en kilómetros.
     */
    public static int calcularDistanciaAeropuerto(Aeropuerto aeropuerto1, Aeropuerto aeropuerto2) {
        double dLat = Math.toRadians(aeropuerto2.latitud - aeropuerto1.latitud);
        double dLon = Math.toRadians(aeropuerto2.longitud - aeropuerto1.longitud);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                   Math.cos(Math.toRadians(aeropuerto1.latitud)) * Math.cos(Math.toRadians(aeropuerto2.latitud)) *
                   Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return (int) (RADIO_TERRESTRE * c);
    }
}

