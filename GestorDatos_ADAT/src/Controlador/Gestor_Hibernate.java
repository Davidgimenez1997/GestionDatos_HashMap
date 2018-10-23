package Controlador;

import java.io.IOException;
import java.util.HashMap;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import Modelo.Actor;
import Modelo.Representante;

public class Gestor_Hibernate implements Interfaz_Controlador {

	private SessionFactory sessionFactory;
	private Session s;

	public Gestor_Hibernate() {
		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();
			s = sessionFactory.openSession();
			System.out.println("Conexion establecida con Hibernate");

		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			ex.printStackTrace();
			throw new ExceptionInInitializerError(ex);
		}
	}

	@Override
	public HashMap<String, Actor> leertodosActores() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, Representante> leertodosRepresentante() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int agregarActor(Actor nuevo) throws IOException {
		// TODO Auto-generated method stub
		return 0;
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
	public int agregarRepresentante(Representante nuevo) throws IOException {
		// TODO Auto-generated method stub
		return 0;
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
	public int borrarTodoActores() throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int borrarTodoRepresentantes() throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

}
