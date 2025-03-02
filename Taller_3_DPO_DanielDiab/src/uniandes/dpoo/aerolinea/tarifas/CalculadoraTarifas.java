package uniandes.dpoo.aerolinea.tarifas;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.Aeropuerto;
import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;

/**
 * Clase abstracta para calcular tarifas de vuelo según la temporada.
 */
public abstract class CalculadoraTarifas {
    public static final double IMPUESTO = 0.28;

    /**
     * Calcula la tarifa final del vuelo considerando descuentos e impuestos.
     * @param vuelo Vuelo a calcular.
     * @param cliente Cliente que compra el tiquete.
     * @return Tarifa final después de aplicar descuentos e impuestos.
     */
    public abstract int calcularTarifa(Vuelo vuelo, Cliente cliente);

    /**
     * Calcula el costo base del vuelo según el tipo de cliente y distancia.
     * @param vuelo Vuelo asociado.
     * @param cliente Cliente que compra el tiquete.
     * @return Costo base del vuelo.
     */
    protected abstract int calcularCostoBase(Vuelo vuelo, Cliente cliente);

    /**
     * Calcula el porcentaje de descuento aplicable al cliente.
     * @param cliente Cliente que compra el tiquete.
     * @return Porcentaje de descuento.
     */
    protected abstract double calcularPorcentajeDescuento(Cliente cliente);

    /**
     * Calcula la distancia del vuelo basada en la distancia entre aeropuertos.
     * @param ruta Ruta del vuelo.
     * @return Distancia en kilómetros.
     */
    protected int calcularDistanciaVuelo(Ruta ruta) {
        return Aeropuerto.calcularDistanciaAeropuerto(ruta.getOrigen(), ruta.getDestino());
    }

    /**
     * Calcula el valor de los impuestos aplicados al costo base.
     * @param costoBase Costo base del vuelo.
     * @return Valor de los impuestos aplicados.
     */
    protected int calcularValorImpuestos(int costoBase) {
        return (int) (costoBase * IMPUESTO);
    }
}
