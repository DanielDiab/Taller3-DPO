package uniandes.dpoo.aerolinea.tarifas;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.cliente.ClienteCorporativo;

/**
 * Calculadora de tarifas para temporada baja.
 */
public class CalculadoraTarifasTemporadaBaja extends CalculadoraTarifas {
    private static final int COSTO_POR_KM_NATURAL = 600;
    private static final int COSTO_POR_KM_CORPORATIVO = 900;
    private static final double DESCUENTO_PEQ = 0.02;
    private static final double DESCUENTO_MEDIANAS = 0.1;
    private static final double DESCUENTO_GRANDES = 0.2;

    @Override
    public int calcularTarifa(Vuelo vuelo, Cliente cliente) {
        int costoBase = calcularCostoBase(vuelo, cliente);
        double descuento = calcularPorcentajeDescuento(cliente);
        int costoConDescuento = (int) (costoBase * (1 - descuento));
        return costoConDescuento + calcularValorImpuestos(costoConDescuento);
    }

    @Override
    protected int calcularCostoBase(Vuelo vuelo, Cliente cliente) {
        int costoPorKm = cliente.getTipoCliente().equals("Corporativo") ? COSTO_POR_KM_CORPORATIVO : COSTO_POR_KM_NATURAL;
        return calcularDistanciaVuelo(vuelo.getRuta()) * costoPorKm;
    }

    @Override
    protected double calcularPorcentajeDescuento(Cliente cliente) {
        if (cliente instanceof ClienteCorporativo) {
            ClienteCorporativo corp = (ClienteCorporativo) cliente;
            if (corp.getTamanoEmpresa() == ClienteCorporativo.PEQUENA) return DESCUENTO_PEQ;
            if (corp.getTamanoEmpresa() == ClienteCorporativo.MEDIANA) return DESCUENTO_MEDIANAS;
            if (corp.getTamanoEmpresa() == ClienteCorporativo.GRANDE) return DESCUENTO_GRANDES;
        }
        return 0;
    }
}
