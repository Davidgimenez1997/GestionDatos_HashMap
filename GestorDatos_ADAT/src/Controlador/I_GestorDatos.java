package Controlador;

import java.io.IOException;
import java.util.HashMap;

import Modelo.Actor;
import Modelo.Representante;

public interface I_GestorDatos {

	public HashMap<String, Actor> leertodosActores() throws IOException;

	public HashMap<String, Representante> leertodosRepresentante() throws IOException;

	public boolean agregarActor(Actor nuevo) throws IOException;

	public boolean agregarRepresentante(Representante nuevo) throws IOException;

	public boolean comprobaridactor(Actor nuevo) throws IOException;

	public boolean comprobaridrepresentante(Representante nuevo) throws IOException;

	public void escribirtodosActores(HashMap<String, Actor> lista) throws IOException;

	public void escribirtodosRepresentante(HashMap<String, Representante> lista) throws IOException;

	public boolean borrarTodoActores() throws IOException;

	public boolean borrarTodoRepresentantes() throws IOException;

	public boolean modificarUnActor(String idmodificar, Actor modificar) throws IOException;

	public boolean modificarUnRepresentante(String idmodificar, Representante modificar) throws IOException;

	public boolean borrarUnActor(String Id) throws IOException;

	public boolean borrarUnRepresentante(String Id) throws IOException;
}
