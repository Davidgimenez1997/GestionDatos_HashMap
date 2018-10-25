package Controlador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import Modelo.Actor;
import Modelo.Representante;

public class Gestor_Fichero implements I_GestorDatos {

	@Override
	public HashMap<String, Actor> leertodosActores() throws IOException {
		HashMap<String, Actor> actores = new HashMap<>();
		HashMap<String, Representante> representantes = leertodosRepresentante();
		String cadena;
		FileReader f = new FileReader("Ficheros/actores.txt");
		BufferedReader b = new BufferedReader(f);
		Actor act;
		while ((cadena = b.readLine()) != null) {
			if (!cadena.equals("*")) {
				String[] partes = cadena.split(":");
				act = new Actor();
				act.setId(partes[0]);
				act.setNombre(partes[1]);
				act.setDescripcion(partes[2]);
				act.setPelo(partes[3]);
				act.setOjos(partes[4]);
				act.setRepresentante(representantes.get(partes[5]));
				actores.put(partes[0], act);
			}
		}
		b.close();
		return actores;
	}

	@Override
	public HashMap<String, Representante> leertodosRepresentante() throws IOException {
		HashMap<String, Representante> representantes = new HashMap<>();
		FileReader f = new FileReader("Ficheros/representantes.txt");
		BufferedReader b = new BufferedReader(f);
		String cadena;
		Representante repre;
		while ((cadena = b.readLine()) != null) {
			if (!cadena.equals("*")) {
				String[] partes = cadena.split(":");
				repre = new Representante();
				repre.setId(partes[0]);
				repre.setNombre(partes[1]);
				repre.setEdad(partes[2]);
				representantes.put(partes[0], repre);
			}
		}
		b.close();
		return representantes;
	}

	@Override
	public boolean comprobaridactor(Actor nuevo) throws IOException {
		HashMap<String, Actor> ver = leertodosActores();
		for (HashMap.Entry<String, Actor> entry : ver.entrySet()) {
			if (entry.getValue().getId().equals(nuevo.getId())) {
				System.out.println("Id del actor repetido");
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean agregarActor(Actor nuevo) throws IOException {
		if (!comprobaridactor(nuevo)) {
			FileWriter fw = new FileWriter("Ficheros/actores.txt", true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(nuevo.getId());
			bw.write(":");
			bw.write(nuevo.getNombre());
			bw.write(":");
			bw.write(nuevo.getDescripcion());
			bw.write(":");
			bw.write(nuevo.getPelo());
			bw.write(":");
			bw.write(nuevo.getOjos());
			bw.write(":");
			bw.write(nuevo.getRepresentante().getId());
			bw.write("\n");
			bw.write("*");
			bw.write("\n");
			bw.close();
			return true;
		}
		return false;
	}

	@Override
	public boolean comprobaridrepresentante(Representante nuevo) throws IOException {
		HashMap<String, Representante> ver = leertodosRepresentante();
		for (HashMap.Entry<String, Representante> entry : ver.entrySet()) {
			if (entry.getValue().getId().equals(nuevo.getId())) {
				System.out.println("Id del representante repetido");
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean agregarRepresentante(Representante nuevo) throws IOException {
		if (!comprobaridrepresentante(nuevo)) {
			FileWriter fw = new FileWriter("Ficheros/representantes.txt", true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(nuevo.getId());
			bw.write(":");
			bw.write(nuevo.getNombre());
			bw.write(":");
			bw.write(nuevo.getEdad());
			bw.write("\n");
			bw.write("*");
			bw.write("\n");
			bw.close();
			return true;
		}
		return false;
	}

	@Override
	public void escribirtodosActores(HashMap<String, Actor> lista) throws IOException {
		borrarTodoActores();
		for (HashMap.Entry<String, Actor> entry : lista.entrySet()) {
			agregarActor(lista.get(entry.getKey()));
		}
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
		BufferedWriter bw = new BufferedWriter(new FileWriter("Ficheros/actores.txt"));
		bw.write("");
		bw.close();
		return true;
	}

	@Override
	public boolean borrarTodoRepresentantes() throws IOException {
		borrarTodoActores();
		BufferedWriter bw = new BufferedWriter(new FileWriter("Ficheros/representantes.txt"));
		bw.write("");
		bw.close();
		return true;
	}

	@Override
	public boolean modificarUnActor(String idmodificar, Actor modificar) throws IOException {
		HashMap<String, Actor> ver = leertodosActores();
		boolean fin = false;
		for (HashMap.Entry<String, Actor> entry : ver.entrySet()) {
			if (entry.getKey().contains(idmodificar)) {
				entry.setValue(modificar);
				fin = true;
			}
		}
		if (fin) {
			escribirtodosActores(ver);
		}
		return fin;
	}

	@Override
	public boolean modificarUnRepresentante(String idmodificar, Representante modificar) throws IOException {
		HashMap<String, Representante> ver_repre = leertodosRepresentante();
		HashMap<String, Actor> ver_actor = leertodosActores();
		boolean fin = false;
		for (HashMap.Entry<String, Representante> entry : ver_repre.entrySet()) {
			if (entry.getKey().contains(idmodificar)) {
				entry.setValue(modificar);
				fin = true;
			}
		}
		if (fin) {
			escribirtodosRepresentante(ver_repre);
			escribirtodosActores(ver_actor);
		}
		return fin;
	}

	@Override
	public boolean borrarUnActor(String Id) throws IOException {
		HashMap<String, Actor> ver = leertodosActores();
		boolean fin = false;
		if (ver.containsKey(Id)) {
			ver.remove(Id);
			escribirtodosActores(ver);
			fin = true;
		} else {
			fin = false;
		}
		return fin;
	}

	@Override
	public boolean borrarUnRepresentante(String Id) throws IOException {
		HashMap<String, Representante> ver_representantes = leertodosRepresentante();
		HashMap<String, Actor> ver_actores = leertodosActores();
		boolean fin = false;
		Actor act = new Actor();
		Representante nuevo = new Representante("NULL");
		for (HashMap.Entry<String, Actor> entry : ver_actores.entrySet()) {
			if (entry.getValue().getRepresentante().getId().contains(Id)) {
				act.setId(entry.getValue().getId());
				act.setNombre(entry.getValue().getNombre());
				act.setDescripcion(entry.getValue().getDescripcion());
				act.setOjos(entry.getValue().getOjos());
				act.setPelo(entry.getValue().getPelo());
				act.setRepresentante(nuevo);
				entry.setValue(act);
				fin = true;
			}
		}
		escribirtodosActores(ver_actores);
		if (ver_representantes.containsKey(Id)) {
			ver_representantes.remove(Id);
			escribirtodosRepresentante(ver_representantes);
			escribirtodosActores(ver_actores);
			fin = true;
		} else {
			fin = false;
		}

		return fin;
	}

}
