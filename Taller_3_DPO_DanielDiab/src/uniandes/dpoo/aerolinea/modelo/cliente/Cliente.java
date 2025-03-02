package uniandes.dpoo.aerolinea.modelo.cliente;

import java.util.HashSet;
import java.util.Set;
import org.json.JSONObject;
import uniandes.dpoo.aerolinea.modelo.Tiquete;
import uniandes.dpoo.aerolinea.modelo.Vuelo;

/**
 * Clase abstracta que representa a un cliente de la aerolínea.
 */
public abstract class Cliente {
    protected String identificador;
    protected Set<Tiquete> listaTiquetes;

    /**
     * Constructor de la clase Cliente.
     */
    public Cliente(String identificador) {
        this.identificador = identificador;
        this.listaTiquetes = new HashSet<>();
    }

    /**
     * Devuelve el tipo de cliente.
     * @return "Corporativo" o "Natural" dependiendo del tipo de cliente.
     */
    public abstract String getTipoCliente();

    /**
     * Devuelve el identificador del cliente.
     * @return Identificador único del cliente.
     */
    public String getIdentificador() {
        return identificador;
    }

    /**
     * Agrega un tiquete a la lista del cliente.
     * @param tiquete Tiquete a agregar.
     */
    public void agregarTiquete(Tiquete tiquete) {
        listaTiquetes.add(tiquete);
    }

    /**
     * Calcula el valor total de los tiquetes del cliente.
     * @return Suma del costo de todos los tiquetes comprados por el cliente.
     */
    public int calcularValorTotalTiquetes() {
        return listaTiquetes.stream().mapToInt(Tiquete::getTarifa).sum();
    }

    /**
     * Usa los tiquetes asociados a un vuelo.
     * @param vuelo Vuelo en el que se usan los tiquetes.
     */
    public void usarTiquetes(Vuelo vuelo) {
        listaTiquetes.removeIf(tiquete -> tiquete.getVuelo().equals(vuelo));
    }
}
