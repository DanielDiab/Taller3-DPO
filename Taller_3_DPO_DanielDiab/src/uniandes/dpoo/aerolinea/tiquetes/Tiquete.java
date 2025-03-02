package uniandes.dpoo.aerolinea.tiquetes;

import org.json.JSONObject;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;

/**
 * Representa un tiquete dentro del sistema de la aerolínea.
 */
public class Tiquete {
    private String codigo;
    private int tarifa;
    private boolean usado;
    private Vuelo vuelo;
    private Cliente clienteComprador;

    /**
     * Constructor de la clase Tiquete.
     * @param codigo Código único del tiquete.
     * @param tarifa Tarifa pagada por el cliente.
     * @param usado Indica si el tiquete ha sido usado.
     * @param vuelo Vuelo asociado al tiquete.
     * @param clienteComprador Cliente que compró el tiquete.
     */
    public Tiquete(String codigo,Vuelo vuelo, Cliente clienteComprador , int tarifa) {
        this.codigo = codigo;
        this.tarifa = tarifa;
        this.vuelo = vuelo;
        this.clienteComprador = clienteComprador;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getTarifa() {
        return tarifa;
    }

    public boolean isUsado() {
        return usado;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public Cliente getClienteComprador() {
        return clienteComprador;
    }

    
}
