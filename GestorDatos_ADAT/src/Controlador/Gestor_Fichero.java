package Controlador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import Modelo.Actor;
import Modelo.Representante;

public class Gestor_Fichero implements Interfaz_Controlador {

	@Override
	public int agregarActor(Actor nuevo) throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int agregarRepresentante(Representante nuevo) throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public HashMap<String, Actor> leertodosActores() throws IOException {
		HashMap<String, Actor> actores = new HashMap<>();
		HashMap<String, Representante> representantes = leertodosRepresentante();
		String cadena;
		FileReader f = new FileReader("src/actores.txt");
		BufferedReader b = new BufferedReader(f);
		Actor act;
		while ((cadena = b.readLine()) != null) {
			if(!cadena.equals("*")){
				String [] partes = cadena.split(":");
				act = new Actor();
				act.setId(partes[0]);
				act.setNombre(partes[1]);
				act.setDescripcion(partes[2]);
				act.setPelo(partes[3]);
				act.setOjos(partes[4]);
				act.setRepresentante(representantes.get(partes[5]));
				actores.put(partes[0],act);
			}	
		}
		b.close();
		return actores;
	}

	@Override
	public HashMap<String, Representante> leertodosRepresentante() throws IOException {
		HashMap<String, Representante> representantes = new HashMap<>();
		FileReader f = new FileReader("src/representantes.txt");
		BufferedReader b = new BufferedReader(f);
		String cadena;
		Representante repre;
		while ((cadena = b.readLine()) != null) {
			if(!cadena.equals("*")){
				String [] partes = cadena.split(":");
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
	public void escribirtodosActores(HashMap<String, Actor> lista) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void escribirtodosRepresentante(HashMap<String, Representante> lista) throws IOException {
		// TODO Auto-generated method stub
		
	}

}
