package Controlador;

import java.io.IOException;
import java.util.HashMap;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.hibernate.Query;
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
		HashMap<String, Actor> actores = new HashMap<String, Actor>();
		Query q = s.createQuery("select e from Actor e");
		List results = q.list();
		Iterator Iterator = results.iterator();
		while (Iterator.hasNext()) {
			Actor actor = (Actor) Iterator.next();
			actores.put(actor.getId(), actor);
		}
		return actores;
	}

	@Override
	public HashMap<String, Representante> leertodosRepresentante() throws IOException {
		HashMap<String, Representante> representantes = new HashMap<String, Representante>();
		Query q = s.createQuery("select e from Representante e");
		List results = q.list();
		Iterator Iterator = results.iterator();
		while (Iterator.hasNext()) {
			Representante repre = (Representante) Iterator.next();
			representantes.put(repre.getId(), repre);
		}
		return representantes;
	}

	@Override
	public int agregarActor(Actor nuevo) throws IOException {
		if (comprobaridactor(nuevo)) {
			s.beginTransaction();
			s.save(nuevo);
			s.getTransaction().commit();
		}
		return 0;
	}

	@Override
	public boolean comprobaridactor(Actor nuevo) throws IOException {
		Actor act = nuevo;
		try {
			act = (Actor) s.get(Actor.class, act.getId());
		} catch (Exception e) {

		}
		if (act == null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean comprobaridrepresentante(Representante nuevo) throws IOException {
		Representante repre = nuevo;
		try {
			repre = (Representante) s.get(Representante.class, repre.getId());
		} catch (Exception e) {

		}
		if (repre == null) {
			return true;
		}
		return false;
	}

	@Override
	public int agregarRepresentante(Representante nuevo) throws IOException {
		if (comprobaridrepresentante(nuevo)) {
			s.beginTransaction();
			s.save(nuevo);
			s.getTransaction().commit();
		}
		return 0;
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
	public int borrarTodoActores() throws IOException {
		s.beginTransaction();
		String stringQuery = "DELETE FROM Actor";
		Query query = s.createQuery(stringQuery);
		query.executeUpdate();
		s.getTransaction().commit();
		return 0;
	}

	@Override
	public int borrarTodoRepresentantes() throws IOException {
		borrarTodoActores();
		s.beginTransaction();
		String stringQuery = "DELETE FROM Representante";
		Query query = s.createQuery(stringQuery);
		query.executeUpdate();
		s.getTransaction().commit();
		return 0;
	}

}
