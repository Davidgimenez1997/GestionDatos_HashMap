package Controlador;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import Modelo.Actor;
import Modelo.Representante;

public class Controlador {
	private static Interfaz_Controlador primario;
	private static Interfaz_Controlador secundario;

	public void elegirdato(int acceso) throws FileNotFoundException, IOException {
		if (acceso == 1) {
			primario = new Gestor_Fichero();
		} else if (acceso == 2) {
			 primario = new Gestor_BBDD("src/Configuracion.ini");
		} else if (acceso == 3) {
			 primario = new Gestor_Hibernate();
		}
	}

	public void leerActores() throws IOException {
		HashMap<String, Actor> ver = primario.leertodosActores();
		int cont = 1;
		for (HashMap.Entry<String, Actor> entry : ver.entrySet()) {
			System.out.println("<----Actor " + cont + " ---->");
			System.out.println("Id: " + entry.getValue().getId());
			System.out.println("Nombre: " + entry.getValue().getNombre());
			System.out.println("Descripcion: " + entry.getValue().getDescripcion());
			System.out.println("Color de pelo: " + entry.getValue().getPelo());
			System.out.println("Color de ojos: " + entry.getValue().getOjos());
			System.out.println("Id del representante: " + entry.getValue().getRepresentante().getId());
			cont++;
		}
	}

	public void leerRepresentantes() throws IOException {
		HashMap<String, Representante> ver = primario.leertodosRepresentante();
		int cont = 1;
		for (HashMap.Entry<String, Representante> entry : ver.entrySet()) {
			System.out.println("<----Representante " + cont + " ---->");
			System.out.println("Id: " + entry.getValue().getId());
			System.out.println("Nombre: " + entry.getValue().getNombre());
			System.out.println("Edad: " + entry.getValue().getEdad());
			cont++;
		}
	}

	public void leerActoresId() throws IOException {
		HashMap<String, Actor> ver = primario.leertodosActores();
		for (HashMap.Entry<String, Actor> entry : ver.entrySet()) {
			System.out.println("Id: " + entry.getValue().getId());
		}
	}

	public void leerRepresentantesId() throws IOException {
		HashMap<String, Representante> ver = primario.leertodosRepresentante();
		for (HashMap.Entry<String, Representante> entry : ver.entrySet()) {
			System.out.println("Id: " + entry.getValue().getId());
		}
	}

	public Representante escojerRepresentante(String id_repre) throws IOException {
		HashMap<String, Representante> ver = primario.leertodosRepresentante();
		Representante elegir_repre = null;
		String id = null, name = null, edad = null;
		for (HashMap.Entry<String, Representante> entry : ver.entrySet()) {
			if (entry.getValue().getId().equals(id_repre)) {
				id = entry.getValue().getId();
				name = entry.getValue().getNombre();
				edad = entry.getValue().getEdad();
				elegir_repre = new Representante(id, name, edad);
			}
		}
		return elegir_repre;
	}

	public void pedirdatosagregarActor(Actor nuevo) throws IOException {
		primario.agregarActor(nuevo);
	}

	public void pedirdatosagregarRepresentante(Representante repres) throws IOException {
		primario.agregarRepresentante(repres);
	}

	public void importar(int importar) throws IOException {
		if (importar == 1) {
			secundario = new Gestor_Fichero();
			HashMap<String, Actor> leer_actores = secundario.leertodosActores();
			HashMap<String, Representante> leer_representante = secundario.leertodosRepresentante();
			primario.escribirtodosRepresentante(leer_representante);
			primario.escribirtodosActores(leer_actores);
		} else if (importar == 2) {
			secundario = new Gestor_BBDD("src/Configuracion.ini");
			HashMap<String, Actor> leer_actores = secundario.leertodosActores();
			HashMap<String, Representante> leer_representante = secundario.leertodosRepresentante();
			primario.escribirtodosRepresentante(leer_representante);
			primario.escribirtodosActores(leer_actores);
		} 
		/*
		 * else if (importar == 3){
		
			secundario = new GestorHibernate();
			HashMap<String, Actor> leer_actores = secundario.leertodosActores();
			HashMap<String, Representante> leer_representante = secundario.leertodosRepresentante();
			primario.escribirtodosRepresentante(leer_representante);
			primario.escribirtodosActores(leer_actores);
			
		}
		 */
	}

	public void exportar(int exportar) throws IOException {
		if (exportar == 1) {
			secundario = new Gestor_Fichero();
			HashMap<String, Actor> leer_actores = primario.leertodosActores();
			HashMap<String, Representante> leer_representante = primario.leertodosRepresentante();
			secundario.escribirtodosRepresentante(leer_representante);
			secundario.escribirtodosActores(leer_actores);

		} else if (exportar == 2) {
			secundario = new Gestor_BBDD("src/Configuracion.ini");
			HashMap<String, Actor> leer_actores = primario.leertodosActores();
			HashMap<String, Representante> leer_representante = primario.leertodosRepresentante();
			secundario.escribirtodosRepresentante(leer_representante);
			secundario.escribirtodosActores(leer_actores);
		} 
		/*else if(exportar == 3){
			secundario = new GestorHibernate();
			ArrayList<Actores> leer_actores = primario.leertodosActores();
			ArrayList<Representante> leer_representante = primario.leertodosRepresentante();
			secundario.escribirtodosRepresentante(leer_representante);
			secundario.escribirtodosActores(leer_actores);
		}
*/
	}

	public void borrarRepresentantes() throws IOException {
		primario.borrarTodoRepresentantes();
		
	}

	public void borrarActores() throws IOException {
		primario.borrarTodoActores();
	}

}
