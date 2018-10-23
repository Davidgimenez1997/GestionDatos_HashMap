package Controlador;

import java.io.IOException;
import java.util.HashMap;

import Modelo.Actor;
import Modelo.Representante;

public interface Interfaz_Controlador {
	
	public int agregarActor(Actor nuevo) throws IOException;

	public int agregarRepresentante(Representante nuevo) throws IOException;

	public HashMap<String,Actor> leertodosActores() throws IOException;

	public HashMap<String,Representante> leertodosRepresentante() throws IOException;

	public void escribirtodosActores(HashMap<String, Actor> lista) throws IOException;

	public void escribirtodosRepresentante(HashMap<String, Representante> lista) throws IOException;
}
