package Controlador;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import Modelo.Actor;
import Modelo.Representante;

public class Controlador {

	private static I_GestorDatos primario;
	private static I_GestorDatos secundario;
	private static String FICHEROCONFIGURACIONBBDD,FICHEROCONFIGURACIONJSON,FICHEROCONFIGURACIONMONGO;
	
	public Controlador(){
		FICHEROCONFIGURACIONBBDD = "FicherosConfiguracion/ConfiguracionBBDD.ini";
		FICHEROCONFIGURACIONJSON = "FicherosConfiguracion/ConfiguracionJSON.ini";
		FICHEROCONFIGURACIONMONGO = "FicherosConfiguracion/ConfiguracionMONGO.ini";
	}

	public void elegirdato(int acceso) throws FileNotFoundException, IOException {
		if (acceso == 1) {
			primario = new Gestor_Fichero();
		} else if (acceso == 2) {
			primario = new Gestor_BBDD(FICHEROCONFIGURACIONBBDD);
		} else if (acceso == 3) {
			primario = new Gestor_Hibernate();
		} else if (acceso == 4) {
			primario = new Gestor_JSON(FICHEROCONFIGURACIONJSON);
		} else if (acceso == 5) {
			primario = new Gestor_Mongo(FICHEROCONFIGURACIONMONGO);
		}
	}

	public void leerActores() throws IOException {
		HashMap<String, Actor> ver = primario.leertodosActores();
		int cont = 1;
		if (ver.size() == 0) {
			System.out.println("No existen actores actuslmente.");
		} else {
			for (Entry<String, Actor> entry : ver.entrySet()) {
				System.out.println("<---- Actor " + cont + " ---->");
				System.out.println("Id: " + entry.getValue().getId());
				System.out.println("Nombre: " + entry.getValue().getNombre());
				System.out.println("Descripcion: " + entry.getValue().getDescripcion());
				System.out.println("Color de pelo: " + entry.getValue().getPelo());
				System.out.println("Color de ojos: " + entry.getValue().getOjos());
				if(entry.getValue().getRepresentante().getId().equals("null")){
					System.out.println("Id del representante: Sin representante.");
				}else{
					System.out.println("Id del representante: " + entry.getValue().getRepresentante().getId());
				}
				cont++;
			}
		}
	}

	public void leerRepresentantes() throws IOException {
		HashMap<String, Representante> ver = primario.leertodosRepresentante();
		int cont = 1;
		if (ver.size() == 0) {
			System.out.println("No existen representantes actuslmente.");
		} else {
			for (Entry<String, Representante> entry : ver.entrySet()) {
				System.out.println("<---- Representante " + cont + " ---->");
				System.out.println("Id: " + entry.getValue().getId());
				System.out.println("Nombre: " + entry.getValue().getNombre());
				System.out.println("Edad: " + entry.getValue().getEdad());
				cont++;
			}
		}
	}
	
	public HashMap<String, Representante> getRepresentantes() throws IOException {
		return primario.leertodosRepresentante();
	}
	

	public void leerActoresId() throws IOException {
		HashMap<String, Actor> ver = primario.leertodosActores();
		for (Entry<String, Actor> entry : ver.entrySet()) {
			System.out.print(entry.getValue().getId() + "." + entry.getValue().getNombre() + " ");
		}
		System.out.println();
	}

	public void leerRepresentantesId() throws IOException {
		HashMap<String, Representante> ver = primario.leertodosRepresentante();
		for (Entry<String, Representante> entry : ver.entrySet()) {
			System.out.print(entry.getValue().getId() + "." + entry.getValue().getNombre() + " ");
		}
		System.out.println();
	}
	
	public Representante escojerRepresentante(String id_repre) throws IOException {
		HashMap<String, Representante> ver = primario.leertodosRepresentante();
		Representante elegir_repre = null;
		String id = null, name = null, edad = null;
		for (Entry<String, Representante> entry : ver.entrySet()) {
			if (entry.getValue().getId().equals(id_repre)) {
				id = entry.getValue().getId();
				name = entry.getValue().getNombre();
				edad = entry.getValue().getEdad();
				elegir_repre = new Representante(id, name, edad);
			}
		}
		return elegir_repre;
	}

	public boolean pedirdatosagregarActor(Actor nuevo) throws IOException {
		if (primario.agregarActor(nuevo)) {
			return true;
		}
		return false;
	}

	public boolean pedirdatosagregarRepresentante(Representante repres) throws IOException {
		if (primario.agregarRepresentante(repres)) {
			return true;
		}
		return false;
	}

	public void importar(int importar) throws IOException {
		if (importar == 1) {
			secundario = new Gestor_Fichero();
			HashMap<String, Actor> leer_actores = secundario.leertodosActores();
			HashMap<String, Representante> leer_representante = secundario.leertodosRepresentante();
			primario.escribirtodosRepresentante(leer_representante);
			primario.escribirtodosActores(leer_actores);
		} else if (importar == 2) {
			secundario = new Gestor_BBDD(FICHEROCONFIGURACIONBBDD);
			HashMap<String, Actor> leer_actores = secundario.leertodosActores();
			HashMap<String, Representante> leer_representante = secundario.leertodosRepresentante();
			primario.escribirtodosRepresentante(leer_representante);
			primario.escribirtodosActores(leer_actores);
		} else if (importar == 3) {
			secundario = new Gestor_Hibernate();
			HashMap<String, Actor> leer_actores = secundario.leertodosActores();
			HashMap<String, Representante> leer_representante = secundario.leertodosRepresentante();
			primario.escribirtodosRepresentante(leer_representante);
			primario.escribirtodosActores(leer_actores);
		} else if (importar == 4) {
			secundario = new Gestor_JSON(FICHEROCONFIGURACIONJSON);
			HashMap<String, Actor> leer_actores = secundario.leertodosActores();
			HashMap<String, Representante> leer_representante = secundario.leertodosRepresentante();
			primario.escribirtodosRepresentante(leer_representante);
			primario.escribirtodosActores(leer_actores);
		} else if (importar == 5) {
			secundario = new Gestor_Mongo(FICHEROCONFIGURACIONMONGO);
			HashMap<String, Actor> leer_actores = secundario.leertodosActores();
			HashMap<String, Representante> leer_representante = secundario.leertodosRepresentante();
			primario.escribirtodosRepresentante(leer_representante);
			primario.escribirtodosActores(leer_actores);
		}
	}

	public void exportar(int exportar) throws IOException {
		if (exportar == 1) {
			secundario = new Gestor_Fichero();
			HashMap<String, Actor> leer_actores = primario.leertodosActores();
			HashMap<String, Representante> leer_representante = primario.leertodosRepresentante();
			secundario.escribirtodosRepresentante(leer_representante);
			secundario.escribirtodosActores(leer_actores);

		} else if (exportar == 2) {
			secundario = new Gestor_BBDD(FICHEROCONFIGURACIONBBDD);
			HashMap<String, Actor> leer_actores = primario.leertodosActores();
			HashMap<String, Representante> leer_representante = primario.leertodosRepresentante();
			secundario.escribirtodosRepresentante(leer_representante);
			secundario.escribirtodosActores(leer_actores);
		} else if (exportar == 3) {
			secundario = new Gestor_Hibernate();
			HashMap<String, Actor> leer_actores = primario.leertodosActores();
			HashMap<String, Representante> leer_representante = primario.leertodosRepresentante();
			secundario.escribirtodosRepresentante(leer_representante);
			secundario.escribirtodosActores(leer_actores);
		} else if (exportar == 4) {
			secundario = new Gestor_JSON(FICHEROCONFIGURACIONJSON);
			HashMap<String, Actor> leer_actores = primario.leertodosActores();
			HashMap<String, Representante> leer_representante = primario.leertodosRepresentante();
			secundario.escribirtodosRepresentante(leer_representante);
			secundario.escribirtodosActores(leer_actores);
		} else if (exportar == 5) {
			secundario = new Gestor_JSON(FICHEROCONFIGURACIONMONGO);
			HashMap<String, Actor> leer_actores = primario.leertodosActores();
			HashMap<String, Representante> leer_representante = primario.leertodosRepresentante();
			secundario.escribirtodosRepresentante(leer_representante);
			secundario.escribirtodosActores(leer_actores);
		}
	}

	public boolean borrarRepresentantes() throws IOException {
		if (primario.borrarTodoRepresentantes()) {
			return true;
		}
		return false;
	}

	public boolean borrarActores() throws IOException {
		if (primario.borrarTodoActores()) {
			return true;
		}
		return false;
	}

	public boolean modificarUnActor(String modificar, Actor actmodificar) throws IOException {
		if (primario.modificarUnActor(modificar, actmodificar)) {
			return true;
		}
		return false;

	}

	public boolean modificarUnRepresentante(String modificar, Representante reprmodificar) throws IOException {
		if (primario.modificarUnRepresentante(modificar, reprmodificar)) {
			return true;
		}
		return false;
	}

	public boolean borrarUnActores(String idborrar) throws IOException {
		if (primario.borrarUnActor(idborrar)) {
			return true;
		}
		return false;
	}

	public boolean borrarUnRepresentantes(String idborrar) throws IOException {
		if (primario.borrarUnRepresentante(idborrar)) {
			return true;
		}
		return false;
	}

}