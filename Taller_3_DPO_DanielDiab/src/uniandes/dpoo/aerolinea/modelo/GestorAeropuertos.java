package uniandes.dpoo.aerolinea.modelo;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase que gestiona los aeropuertos registrados.
 */
public class GestorAeropuertos {

    private static Map<String, Aeropuerto> aeropuertos = new HashMap<>();

    public static boolean existeAeropuerto(String codigo) {
        return aeropuertos.containsKey(codigo);
    }

    public static void registrarAeropuerto(Aeropuerto aeropuerto) {
        aeropuertos.put(aeropuerto.getCodigo(), aeropuerto);
    }

    public static Aeropuerto obtenerAeropuerto(String codigo) {
        return aeropuertos.get(codigo);
    }
}