package uniandes.dpoo.aerolinea.persistencia;

import uniandes.dpoo.aerolinea.exceptions.InformacionInconsistenteException;
import uniandes.dpoo.aerolinea.modelo.Aerolinea;
import java.io.IOException;

import org.json.JSONException;


public interface IPersistenciaAerolinea {

 
    void cargarAerolinea(String archivo, Aerolinea aerolinea) throws IOException, InformacionInconsistenteException, JSONException, Exception;

    void salvarAerolinea(String archivo, Aerolinea aerolinea) throws IOException;
}