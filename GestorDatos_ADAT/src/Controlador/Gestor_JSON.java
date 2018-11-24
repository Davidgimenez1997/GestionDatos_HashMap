package Controlador;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import Modelo.Actor;
import Modelo.Representante;

public class Gestor_JSON implements I_GestorDatos {

	private ApiRequests encargadoPeticiones;

	private String SERVER_PATH, GET_ACTOR, GET_REPRESENTANTE, SET_ACTOR, SET_REPRESENTANTE, DELETE_ACTOR,
			DELETE_REPRESENTANTE, UPDATE_ACTOR, UPDATE_REPRESENTANTE;

	public Gestor_JSON(String archivo) throws FileNotFoundException, IOException {
		encargadoPeticiones = new ApiRequests();
		Properties p = new Properties();
		p.load(new FileReader(archivo));
		SERVER_PATH = p.getProperty("SERVER_PATH");
		GET_ACTOR = p.getProperty("GET_ACTOR");
		GET_REPRESENTANTE = p.getProperty("GET_REPRESENTANTE");
		SET_ACTOR = p.getProperty("SET_ACTOR");
		SET_REPRESENTANTE = p.getProperty("SET_REPRESENTANTE");
		DELETE_ACTOR = p.getProperty("DELETE_ACTOR");
		DELETE_REPRESENTANTE = p.getProperty("DELETE_REPRESENTANTE");
		UPDATE_ACTOR = p.getProperty("UPDATE_ACTOR");
		UPDATE_REPRESENTANTE = p.getProperty("UPDATE_REPRESENTANTE");
	}

	@Override
	public HashMap<String, Actor> leertodosActores() throws IOException {
		HashMap<String, Actor> auxhm = new HashMap<String, Actor>();
		String url = SERVER_PATH + GET_ACTOR;
		String response = encargadoPeticiones.getRequest(url);
		JSONObject respuesta = (JSONObject) JSONValue.parse(response.toString());
		if (response == null) {
			System.out.println("El json recibido no es correcto. Finaliza la ejecución");
			System.exit(-1);
		} else {
			String estado = (String) respuesta.get("estado");
			if (estado.equals("ok")) {
				JSONArray actoresarr = (JSONArray) respuesta.get("actores");

				if (actoresarr.size() > 0) {
					Actor actor;
					String id;
					String nombre;
					String descripcion;
					String pelo;
					String ojos;
					String str_representante;
					Representante representante = new Representante();

					for (int i = 0; i < actoresarr.size(); i++) {
						JSONObject row = (JSONObject) actoresarr.get(i);

						id = row.get("id").toString();
						nombre = row.get("nombre").toString();
						descripcion = row.get("descripcion").toString();
						pelo = row.get("pelo").toString();
						ojos = row.get("ojos").toString();
						// str_representante =
						// row.get("representante").toString();
						// representante = row.get("representante");

						actor = new Actor(id, nombre, descripcion, pelo, ojos, (Representante) representante);

						auxhm.put(id, actor);

					}
					System.out.println("Acceso JSON Remoto - Leidos datos correctamente y generado hashmap");
					System.out.println();
				} else {
					System.out.println("Acceso JSON Remoto - No hay datos que tratar");
					System.out.println();
				}

			} else {

				System.out.println("Ha ocurrido un error en la busqueda de datos");
				System.out.println("Error: " + (String) respuesta.get("error"));
				System.out.println("Consulta: " + (String) respuesta.get("query"));

				System.exit(-1);

			}
		}
		return auxhm;
	}

	@Override
	public HashMap<String, Representante> leertodosRepresentante() throws IOException {
		HashMap<String, Representante> auxhm = new HashMap<String, Representante>();
		String url = SERVER_PATH + GET_REPRESENTANTE;
		String response = encargadoPeticiones.getRequest(url);
		JSONObject respuesta = (JSONObject) JSONValue.parse(response.toString());
		if (response == null) {
			System.out.println("El json recibido no es correcto. Finaliza la ejecución");
			System.exit(-1);
		} else {
			String estado = (String) respuesta.get("estado");
			if (estado.equals("ok")) {
				JSONArray representantearr = (JSONArray) respuesta.get("representante");

				if (representantearr.size() > 0) {
					Representante representante;
					String id;
					String nombre;
					String edad;
					;

					for (int i = 0; i < representantearr.size(); i++) {
						JSONObject row = (JSONObject) representantearr.get(i);

						id = row.get("id").toString();
						nombre = row.get("nombre").toString();
						edad = row.get("edad").toString();
						representante = new Representante(id, nombre, edad);
						auxhm.put(id, representante);

					}
					System.out.println("Acceso JSON Remoto - Leidos datos correctamente y generado hashmap");
					System.out.println();
				} else {
					System.out.println("Acceso JSON Remoto - No hay datos que tratar");
					System.out.println();
				}

			} else {

				System.out.println("Ha ocurrido un error en la busqueda de datos");
				System.out.println("Error: " + (String) respuesta.get("error"));
				System.out.println("Consulta: " + (String) respuesta.get("query"));

				System.exit(-1);

			}
		}
		return auxhm;
	}

	@Override
	public boolean agregarActor(Actor nuevo) throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean agregarRepresentante(Representante nuevo) throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean comprobaridactor(Actor nuevo) throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean comprobaridrepresentante(Representante nuevo) throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void escribirtodosActores(HashMap<String, Actor> lista) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void escribirtodosRepresentante(HashMap<String, Representante> lista) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean borrarTodoActores() throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean borrarTodoRepresentantes() throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modificarUnActor(String idmodificar, Actor modificar) throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modificarUnRepresentante(String idmodificar, Representante modificar) throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean borrarUnActor(String Id) throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean borrarUnRepresentante(String Id) throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

}
