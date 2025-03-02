package uniandes.dpoo.aerolinea.modelo.cliente;


public class ClienteNatural extends Cliente {
    public static final String NATURAL = "Natural";
    private String nombre;

    /**
     * Constructor de la clase ClienteNatural.
     * @param nombre Nombre del cliente.
     */
    public ClienteNatural(String nombre) {
        super(nombre);
        this.nombre = nombre;
    }

    @Override
    public String getTipoCliente() {
        return NATURAL;
    }

    public String getNombre() {
        return nombre;
    }
}

