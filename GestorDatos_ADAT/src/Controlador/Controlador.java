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
			//primario = new GestorBBDD("src/Configuracion.ini");
		}
		else if(acceso==3){
			//primario = new GestorHibernate();
		}
	}

	public void leerActores() throws IOException {
		HashMap<String, Actor> ver = primario.leertodosActores();
		int cont = 1;
		for (HashMap.Entry<String, Actor> entry : ver.entrySet()) {
			System.out.println("<----Actor "+cont+" ---->");
			System.out.println("Id: "+entry.getValue().getId());
			System.out.println("Nombre: "+entry.getValue().getNombre());
			System.out.println("Descripcion: "+entry.getValue().getDescripcion());
			System.out.println("Color de pelo: "+entry.getValue().getPelo());
			System.out.println("Color de ojos: "+entry.getValue().getOjos());
			System.out.println("Id del representante: "+entry.getValue().getRepresentante().getId());
			cont++;
		}
	}

	public void leerRepresentantes() throws IOException {
		HashMap<String, Representante> ver = primario.leertodosRepresentante();
		int cont = 1;
		for (HashMap.Entry<String, Representante> entry : ver.entrySet()) {
			System.out.println("<----Representante "+cont+" ---->");
			System.out.println("Id: "+entry.getValue().getId());
			System.out.println("Nombre: "+entry.getValue().getNombre());
			System.out.println("Edad: "+entry.getValue().getEdad());
			cont++;
		}
		
	}

	public void leerActoresId() {
		// TODO Auto-generated method stub
		
	}

	public void leerRepresentantesId() {
		// TODO Auto-generated method stub
		
	}

	public Representante escojerRepresentante(String id_repre) {
		// TODO Auto-generated method stub
		return null;
	}

	public void pedirdatosagregarActor(Actor nuevo) {
		// TODO Auto-generated method stub
		
	}

	public void pedirdatosagregarRepresentante(Representante repres) {
		// TODO Auto-generated method stub
		
	}

	public void importar(int importar) {
		// TODO Auto-generated method stub
		
	}

	public void exportar(int exportar) {
		// TODO Auto-generated method stub
		
	}


}
