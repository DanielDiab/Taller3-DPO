package uniandes.dpoo.aerolinea.modelo.cliente;

import org.json.JSONObject;

/**
 * Representa un cliente corporativo.
 */
public class ClienteCorporativo extends Cliente {
    public static final String CORPORATIVO = "Corporativo";
    public static final int GRANDE = 1;
    public static final int MEDIANA = 2;
    public static final int PEQUENA = 3;

    private String nombreEmpresa;
    private int tamanoEmpresa;

    /**
     * Constructor de la clase ClienteCorporativo.
     * @param nombreEmpresa Nombre de la empresa.
     * @param tamano Tamaño de la empresa (1: Grande, 2: Mediana, 3: Pequeña).
     */
    public ClienteCorporativo(String nombreEmpresa, int tamano) {
        super(nombreEmpresa);
        this.nombreEmpresa = nombreEmpresa;
        this.tamanoEmpresa = tamano;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public int getTamanoEmpresa() {
        return tamanoEmpresa;
    }

    @Override
    public String getTipoCliente() {
        return CORPORATIVO;
    }

    public static ClienteCorporativo cargarDesdeJSON(JSONObject cliente) {
        return new ClienteCorporativo(cliente.getString("nombreEmpresa"), cliente.getInt("tamanoEmpresa"));
    }

    public JSONObject salvarEnJSON() {
        JSONObject json = new JSONObject();
        json.put("nombreEmpresa", this.nombreEmpresa);
        json.put("tamanoEmpresa", this.tamanoEmpresa);
        return json;
    }
}
