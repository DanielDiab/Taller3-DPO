package uniandes.dpoo.aerolinea.tarifas;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;

/**
 * Calculadora de tarifas para temporada alta.
 */
public class CalculadoraTarifasTemporadaAlta extends CalculadoraTarifas {
    private static final int COSTO_POR_KM = 1000;

    @Override
    public int calcularTarifa(Vuelo vuelo, Cliente cliente) {
        int costoBase = calcularCostoBase(vuelo, cliente);
        return costoBase + calcularValorImpuestos(costoBase);
    }

    @Override
    protected int calcularCostoBase(Vuelo vuelo, Cliente cliente) {
        return calcularDistanciaVuelo(vuelo.getRuta()) * COSTO_POR_KM;
    }

    @Override
    protected double calcularPorcentajeDescuento(Cliente cliente) {
        return 0; // En temporada alta, no hay descuentos.
    }
}
