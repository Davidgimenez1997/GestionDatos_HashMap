package Controlador;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
			System.out.println("El json recibido no es correcto. Finaliza la ejecuci�n");
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
			System.out.println("El json recibido no es correcto. Finaliza la ejecuci�n");
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

		return false;
	}

	@Override
	public boolean agregarRepresentante(Representante nuevo) throws IOException {

		if (!comprobaridrepresentante(nuevo)) {
			System.out.println("El id del representante esta repetido");
		} else {
			try {
				JSONObject objRepresentante = new JSONObject();
				JSONObject objPeticion = new JSONObject();

				objRepresentante.put("id", nuevo.getId());
				objRepresentante.put("nombre", nuevo.getNombre());
				objRepresentante.put("edad", nuevo.getEdad());

				objPeticion.put("representanteAnnadir", objRepresentante);
				objPeticion.put("peticion", "add");

				String json = objPeticion.toJSONString();

				System.out.println("Lanzamos peticion JSON para almacenar un equipo");

				String url = SERVER_PATH + SET_REPRESENTANTE;

				String response = encargadoPeticiones.postRequest(url, json);

				System.out.println("El json que recibimos es: ");

				// System.out.println(response); // Traza para pruebas
				// System.exit(-1);

				// Parseamos la respuesta y la convertimos en un JSONObject

				JSONObject respuesta = (JSONObject) JSONValue.parse(response.toString());

				if (respuesta == null) { // Si hay alg�n error de parseo (json
											// incorrecto porque hay alg�n
											// caracter
											// raro, etc.) la respuesta ser�
											// null
					System.out.println("El json recibido no es correcto. Finaliza la ejecuci�n");
					System.exit(-1);
				} else { // El JSON recibido es correcto

					// Sera "ok" si todo ha ido bien o "error" si hay alg�n
					// problema
					String estado = (String) respuesta.get("estado");
					if (estado.equals("ok")) {

						System.out.println("Almacenado equipo enviado por JSON Remoto");
						return true;

					} else { // Hemos recibido el json pero en el estado se nos
								// indica que ha habido alg�n error

						System.out.println("Acceso JSON REMOTO - Error al almacenar los datos");
						System.out.println("Error: " + (String) respuesta.get("error"));
						System.out.println("Consulta: " + (String) respuesta.get("query"));

					}
				}
			} catch (Exception e) {
				System.out.println(
						"Excepcion desconocida. Traza de error comentada en el m�todo 'annadirEquipo' de la clase JSON REMOTO");
				// e.printStackTrace();
				System.out.println("Fin ejecuci�n");
				System.exit(-1);
			}
		}
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
		for (HashMap.Entry<String, Representante> entry : leertodosRepresentante().entrySet()) {
			if (entry.getKey().equals(nuevo.getId())) {
				return false;
			} else {
				return true;
			}
		}
		return false;
	}

	@Override
	public void escribirtodosActores(HashMap<String, Actor> lista) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void escribirtodosRepresentante(HashMap<String, Representante> lista) throws IOException {
		borrarTodoRepresentantes();
		for (HashMap.Entry<String, Representante> entry : lista.entrySet()) {
			agregarRepresentante(lista.get(entry.getKey()));
		}
	}

	@Override
	public boolean borrarTodoActores() throws IOException {
		try {

			System.out.println("---------- Leemos datos de JSON --------------------");

			System.out.println("Lanzamos peticion JSON para jugadores");

			String url = SERVER_PATH + DELETE_ACTOR; // Sacadas de configuracion

			System.out.println("La url a la que lanzamos la petici�n es " + url); // Traza
																					// para
																					// pruebas

			String response = encargadoPeticiones.getRequest(url);

			System.out.println(response); // Traza para pruebas

			// Parseamos la respuesta y la convertimos en un JSONObject
			JSONObject respuesta = (JSONObject) JSONValue.parse(response.toString());

			if (respuesta == null) { // Si hay alg�n error de parseo (json
										// incorrecto porque hay alg�n caracter
										// raro, etc.) la respuesta ser� null
				System.out.println("El json recibido no es correcto. Finaliza la ejecuci�n");
				System.exit(-1);
			} else { // El JSON recibido es correcto
				// Sera "ok" si todo ha ido bien o "error" si hay alg�n problema
				String estado = (String) respuesta.get("estado");
				// Si ok, obtenemos array de jugadores para recorrer y generar
				// hashmap
				if (estado.equals("ok")) {
					System.out.println("Acceso JSON Remoto - Leidos datos correctamente y generado hashmap");
					System.out.println();
					return true;

				} else { // Hemos recibido el json pero en el estado se nos
							// indica que ha habido alg�n error

					System.out.println("Ha ocurrido un error en la busqueda de datos");
					System.out.println("Error: " + (String) respuesta.get("error"));
					System.out.println("Consulta: " + (String) respuesta.get("query"));

					System.exit(-1);

				}
			}

		} catch (Exception e) {
			System.out.println("Ha ocurrido un error en la busqueda de datos");

			e.printStackTrace();

			System.exit(-1);
		}
		return false;
	}

	@Override
	public boolean borrarTodoRepresentantes() throws IOException {
		borrarTodoActores();
		try {

			System.out.println("---------- Leemos datos de JSON --------------------");

			System.out.println("Lanzamos peticion JSON para jugadores");

			String url = SERVER_PATH + DELETE_REPRESENTANTE; // Sacadas de
																// configuracion

			System.out.println("La url a la que lanzamos la petici�n es " + url); // Traza
																					// para
																					// pruebas

			String response = encargadoPeticiones.getRequest(url);

			System.out.println(response); // Traza para pruebas

			// Parseamos la respuesta y la convertimos en un JSONObject
			JSONObject respuesta = (JSONObject) JSONValue.parse(response.toString());

			if (respuesta == null) { // Si hay alg�n error de parseo (json
										// incorrecto porque hay alg�n caracter
										// raro, etc.) la respuesta ser� null
				System.out.println("El json recibido no es correcto. Finaliza la ejecuci�n");
				System.exit(-1);
			} else { // El JSON recibido es correcto
				// Sera "ok" si todo ha ido bien o "error" si hay alg�n problema
				String estado = (String) respuesta.get("estado");
				// Si ok, obtenemos array de jugadores para recorrer y generar
				// hashmap
				if (estado.equals("ok")) {
					System.out.println("Acceso JSON Remoto - Leidos datos correctamente y generado hashmap");
					System.out.println();
					return true;

				} else { // Hemos recibido el json pero en el estado se nos
							// indica que ha habido alg�n error

					System.out.println("Ha ocurrido un error en la busqueda de datos");
					System.out.println("Error: " + (String) respuesta.get("error"));
					System.out.println("Consulta: " + (String) respuesta.get("query"));

					System.exit(-1);

				}
			}

		} catch (Exception e) {
			System.out.println("Ha ocurrido un error en la busqueda de datos");

			e.printStackTrace();

			System.exit(-1);
		}
		return false;
	}

	@Override
	public boolean modificarUnActor(String idmodificar, Actor modificar) throws IOException {
		try {
			JSONObject objActor = new JSONObject();
			JSONObject objPeticion = new JSONObject();

			objActor.put("Id", idmodificar);
			objActor.put("Nombre", modificar.getNombre());
			objActor.put("Descripcion", modificar.getDescripcion());
			objActor.put("Pelo", modificar.getPelo());
			objActor.put("Ojos", modificar.getOjos());
			objActor.put("Representante", modificar.getRepresentante().getId());


			objPeticion.put("actorModificar", objActor);
			objPeticion.put("peticion", "update");

			String json = objPeticion.toJSONString();

			System.out.println("Lanzamos peticion JSON para almacenar un equipo");

			String url = SERVER_PATH + UPDATE_ACTOR;

			String response = encargadoPeticiones.postRequest(url, json);

			System.out.println("El json que recibimos es: ");

			 System.out.println(response); // Traza para pruebas
			// System.exit(-1);

			// Parseamos la respuesta y la convertimos en un JSONObject

			JSONObject respuesta = (JSONObject) JSONValue.parse(response.toString());

			if (respuesta == null) { // Si hay alg�n error de parseo (json
										// incorrecto porque hay alg�n
										// caracter
										// raro, etc.) la respuesta ser�
										// null
				System.out.println("El json recibido no es correcto. Finaliza la ejecuci�n");
				System.exit(-1);
			} else { // El JSON recibido es correcto

				// Sera "ok" si todo ha ido bien o "error" si hay alg�n
				// problema
				String estado = (String) respuesta.get("estado");
				if (estado.equals("ok")) {

					System.out.println("Almacenado equipo enviado por JSON Remoto");
					return true;

				} else { // Hemos recibido el json pero en el estado se nos
							// indica que ha habido alg�n error

					System.out.println("Acceso JSON REMOTO - Error al almacenar los datos");
					System.out.println("Error: " + (String) respuesta.get("error"));
					System.out.println("Consulta: " + (String) respuesta.get("query"));

				}
			}
		} catch (Exception e) {
			System.out.println(
					"Excepcion desconocida. Traza de error comentada en el m�todo 'annadirEquipo' de la clase JSON REMOTO");
			// e.printStackTrace();
			System.out.println("Fin ejecuci�n");
			System.exit(-1);
		}

		return false;
	}

	@Override
	public boolean modificarUnRepresentante(String idmodificar, Representante modificar) throws IOException {

		try {
			JSONObject objRepresentante = new JSONObject();
			JSONObject objPeticion = new JSONObject();

			objRepresentante.put("id", idmodificar);
			objRepresentante.put("nombre", modificar.getNombre());
			objRepresentante.put("edad", modificar.getEdad());

			objPeticion.put("representanteModificar", objRepresentante);
			objPeticion.put("peticion", "update");

			String json = objPeticion.toJSONString();

			System.out.println("Lanzamos peticion JSON para almacenar un equipo");

			String url = SERVER_PATH + UPDATE_REPRESENTANTE;

			String response = encargadoPeticiones.postRequest(url, json);

			System.out.println("El json que recibimos es: ");

			 //System.out.println(response); // Traza para pruebas
			// System.exit(-1);

			// Parseamos la respuesta y la convertimos en un JSONObject

			JSONObject respuesta = (JSONObject) JSONValue.parse(response.toString());

			if (respuesta == null) { // Si hay alg�n error de parseo (json
										// incorrecto porque hay alg�n
										// caracter
										// raro, etc.) la respuesta ser�
										// null
				System.out.println("El json recibido no es correcto. Finaliza la ejecuci�n");
				System.exit(-1);
			} else { // El JSON recibido es correcto

				// Sera "ok" si todo ha ido bien o "error" si hay alg�n
				// problema
				String estado = (String) respuesta.get("estado");
				if (estado.equals("ok")) {

					System.out.println("Almacenado equipo enviado por JSON Remoto");
					return true;

				} else { // Hemos recibido el json pero en el estado se nos
							// indica que ha habido alg�n error

					System.out.println("Acceso JSON REMOTO - Error al almacenar los datos");
					System.out.println("Error: " + (String) respuesta.get("error"));
					System.out.println("Consulta: " + (String) respuesta.get("query"));

				}
			}
		} catch (Exception e) {
			System.out.println(
					"Excepcion desconocida. Traza de error comentada en el m�todo 'annadirEquipo' de la clase JSON REMOTO");
			// e.printStackTrace();
			System.out.println("Fin ejecuci�n");
			System.exit(-1);
		}

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
