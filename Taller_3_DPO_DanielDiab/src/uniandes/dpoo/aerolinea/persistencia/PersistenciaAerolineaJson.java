package uniandes.dpoo.aerolinea.persistencia;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import uniandes.dpoo.aerolinea.exceptions.InformacionInconsistenteException;
import uniandes.dpoo.aerolinea.modelo.Aerolinea;
import uniandes.dpoo.aerolinea.modelo.Avion;
import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.Vuelo;

public class PersistenciaAerolineaJson implements IPersistenciaAerolinea {

    private static final String NOMBRE_AVION = "nombre";
    private static final String CAPACIDAD_AVION = "capacidad";
    private static final String CODIGO_RUTA = "codigoRuta";
    private static final String ORIGEN_RUTA = "origen";
    private static final String DESTINO_RUTA = "destino";
    private static final String HORA_SALIDA_RUTA = "horaSalida";
    private static final String HORA_LLEGADA_RUTA = "horaLlegada";
    private static final String FECHA_VUELO = "fecha";
    private static final String AVION_VUELO = "avion";

    @Override
    public void cargarAerolinea(String archivo, Aerolinea aerolinea) throws JSONException, Exception {
        String jsonCompleto = new String(Files.readAllBytes(new File(archivo).toPath()));
        JSONObject raiz = new JSONObject(jsonCompleto);

        cargarAviones(aerolinea, raiz.getJSONArray("aviones"));
        cargarRutas(aerolinea, raiz.getJSONArray("rutas"));
        cargarVuelos(aerolinea, raiz.getJSONArray("vuelos"));
    }

    @Override
    public void salvarAerolinea(String archivo, Aerolinea aerolinea) throws IOException {
        JSONObject jobject = new JSONObject();

        // Salvar aviones
        salvarAviones(aerolinea, jobject);

        // Salvar rutas
        salvarRutas(aerolinea, jobject);

        // Salvar vuelos
        salvarVuelos(aerolinea, jobject);

        // Escribir la estructura JSON en un archivo
        PrintWriter pw = new PrintWriter(archivo);
        jobject.write(pw, 2, 0);
        pw.close();
    }

    /**
     * Carga los aviones de la aerolínea a partir de un archivo JSON.
     * 
     * @param aerolinea La aerolínea donde deben quedar los aviones.
     * @param jAviones El elemento JSON donde está la información de los aviones.
     */
    private void cargarAviones(Aerolinea aerolinea, JSONArray jAviones) {
        int numAviones = jAviones.length();
        for (int i = 0; i < numAviones; i++) {
            JSONObject avion = jAviones.getJSONObject(i);
            String nombre = avion.getString(NOMBRE_AVION);
            int capacidad = avion.getInt(CAPACIDAD_AVION);
            Avion nuevoAvion = new Avion(nombre, capacidad);
            aerolinea.agregarAvion(nuevoAvion);
        }
    }

    /**
     * Carga las rutas de la aerolínea a partir de un archivo JSON.
     * 
     * @param aerolinea La aerolínea donde deben quedar las rutas.
     * @param jRutas El elemento JSON donde está la información de las rutas.
     * @throws InformacionInconsistenteException Si la información de las rutas no es consistente.
     */
    private void cargarRutas(Aerolinea aerolinea, JSONArray jRutas) throws InformacionInconsistenteException {
        int numRutas = jRutas.length();
        for (int i = 0; i < numRutas; i++) {
            JSONObject ruta = jRutas.getJSONObject(i);
            String codigo = ruta.getString(CODIGO_RUTA);
            String origen = ruta.getString(ORIGEN_RUTA);
            String destino = ruta.getString(DESTINO_RUTA);
            String horaSalida = ruta.getString(HORA_SALIDA_RUTA);
            String horaLlegada = ruta.getString(HORA_LLEGADA_RUTA);

            // Aquí se asume que los aeropuertos ya están creados y disponibles en la aerolínea.
            // Si no, deberías crear los aeropuertos o lanzar una excepción.
            Ruta nuevaRuta = new Ruta(codigo, origen, destino, horaSalida, horaLlegada);
            aerolinea.agregarRuta(nuevaRuta);
        }
    }

    /**
     * Carga los vuelos de la aerolínea a partir de un archivo JSON.
     * 
     * @param aerolinea La aerolínea donde deben quedar los vuelos.
     * @param jVuelos El elemento JSON donde está la información de los vuelos.
     * @throws Exception 
     */
    private void cargarVuelos(Aerolinea aerolinea, JSONArray jVuelos) throws Exception {
        int numVuelos = jVuelos.length();
        for (int i = 0; i < numVuelos; i++) {
            JSONObject vuelo = jVuelos.getJSONObject(i);
            String codigoRuta = vuelo.getString(CODIGO_RUTA);
            String fecha = vuelo.getString(FECHA_VUELO);
            String nombreAvion = vuelo.getString(AVION_VUELO);

            Ruta ruta = aerolinea.getRuta(codigoRuta);
            if (ruta == null) {
                throw new InformacionInconsistenteException("La ruta con código " + codigoRuta + " no existe.");
            }

            Avion avion = null;
            for (Avion a : aerolinea.getAviones()) {
                if (a.getNombre().equals(nombreAvion)) {
                    avion = a;
                    break;
                }
            }
            if (avion == null) {
                throw new InformacionInconsistenteException("El avión con nombre " + nombreAvion + " no existe.");
            }

            Vuelo nuevoVuelo = new Vuelo(ruta, fecha, avion);
            aerolinea.programarVuelo(fecha, codigoRuta, nombreAvion);
        }
    }

    /**
     * Salva la información de los aviones de la aerolínea dentro del objeto JSON que se recibe por parámetro.
     * 
     * @param aerolinea La aerolínea que tiene la información.
     * @param jobject El objeto JSON donde debe quedar la información de los aviones.
     */
    private void salvarAviones(Aerolinea aerolinea, JSONObject jobject) {
        JSONArray jAviones = new JSONArray();
        for (Avion avion : aerolinea.getAviones()) {
            JSONObject jAvion = new JSONObject();
            jAvion.put(NOMBRE_AVION, avion.getNombre());
            jAvion.put(CAPACIDAD_AVION, avion.getCapacidad());
            jAviones.put(jAvion);
        }
        jobject.put("aviones", jAviones);
    }

    /**
     * Salva la información de las rutas de la aerolínea dentro del objeto JSON que se recibe por parámetro.
     * 
     * @param aerolinea La aerolínea que tiene la información.
     * @param jobject El objeto JSON donde debe quedar la información de las rutas.
     */
    private void salvarRutas(Aerolinea aerolinea, JSONObject jobject) {
        JSONArray jRutas = new JSONArray();
        for (Ruta ruta : aerolinea.getRutas()) {
            JSONObject jRuta = new JSONObject();
            jRuta.put(CODIGO_RUTA, ruta.getCodigoRuta());
            jRuta.put(ORIGEN_RUTA, ruta.getOrigen());
            jRuta.put(DESTINO_RUTA, ruta.getDestino());
            jRuta.put(HORA_SALIDA_RUTA, ruta.getHoraSalida());
            jRuta.put(HORA_LLEGADA_RUTA, ruta.getHoraLlegada());
            jRutas.put(jRuta);
        }
        jobject.put("rutas", jRutas);
    }

    /**
     * Salva la información de los vuelos de la aerolínea dentro del objeto JSON que se recibe por parámetro.
     * 
     * @param aerolinea La aerolínea que tiene la información.
     * @param jobject El objeto JSON donde debe quedar la información de los vuelos.
     */
    private void salvarVuelos(Aerolinea aerolinea, JSONObject jobject) {
        JSONArray jVuelos = new JSONArray();
        for (Vuelo vuelo : aerolinea.getVuelos()) {
            JSONObject jVuelo = new JSONObject();
            jVuelo.put(CODIGO_RUTA, vuelo.getRuta().getCodigoRuta());
            jVuelo.put(FECHA_VUELO, vuelo.getFecha());
            jVuelo.put(AVION_VUELO, vuelo.getAvion().getNombre());
            jVuelos.put(jVuelo);
        }
        jobject.put("vuelos", jVuelos);
    }
}