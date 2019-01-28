package Controlador;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import Modelo.Actor;
import Modelo.Representante;

public class Gestor_Hibernate implements I_GestorDatos {

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
		Representante repre;
		while (Iterator.hasNext()) {
			Actor actor = (Actor) Iterator.next();
			if(actor.getRepresentante()==null){
				repre = new Representante("null");
				actor.setRepresentante(repre);
				actores.put(actor.getId(), actor);
			}else{
				actores.put(actor.getId(), actor);
			}
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
	public boolean agregarActor(Actor nuevo) throws IOException {
		if (comprobaridactor(nuevo)) {
			s.beginTransaction();
			s.save(nuevo);
			s.getTransaction().commit();
			return true;
		}
		return false;
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
		System.out.println("Id del actor repetido");
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
		System.out.println("Id del representante repetido");
		return false;
	}

	@Override
	public boolean agregarRepresentante(Representante nuevo) throws IOException {
		if (comprobaridrepresentante(nuevo)) {
			s.beginTransaction();
			s.save(nuevo);
			s.getTransaction().commit();
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
		s.beginTransaction();
		String stringQuery = "DELETE FROM Actor";
		Query query = s.createQuery(stringQuery);
		query.executeUpdate();
		s.getTransaction().commit();
		return true;
	}

	@Override
	public boolean borrarTodoRepresentantes() throws IOException {
		borrarTodoActores();
		s.beginTransaction();
		String stringQuery = "DELETE FROM Representante";
		Query query = s.createQuery(stringQuery);
		query.executeUpdate();
		s.getTransaction().commit();
		return true;
	}

	@Override
	public boolean modificarUnActor(String idmodificar, Actor modificar) throws IOException {
		boolean fin = false;
		s.beginTransaction();
		Actor obj = (Actor) s.get(Actor.class, idmodificar);
		obj.setId(idmodificar);
		obj.setNombre(modificar.getNombre());
		obj.setDescripcion(modificar.getDescripcion());
		obj.setPelo(modificar.getPelo());
		obj.setOjos(modificar.getOjos());
		obj.setRepresentante(modificar.getRepresentante());
		s.update(obj);
		fin = true;
		s.getTransaction().commit();
		return fin;
	}

	@Override
	public boolean modificarUnRepresentante(String idmodificar, Representante modificar) throws IOException {
		boolean fin = false;
		s.beginTransaction();
		Representante obj = (Representante) s.get(Representante.class, idmodificar);
		obj.setId(idmodificar);
		obj.setNombre(modificar.getNombre());
		obj.setEdad(modificar.getEdad());
		s.update(obj);
		fin = true;
		s.getTransaction().commit();
		return fin;
	}

	@Override
	public boolean borrarUnActor(String Id) throws IOException {
		boolean fin = false;
		Actor obj = new Actor();
		HashMap<String, Actor> ver = leertodosActores();
		for (HashMap.Entry<String, Actor> entry : ver.entrySet()) {
			if(entry.getValue().getId().equals(Id)){
				System.out.println("ENTRO");
				obj = entry.getValue();
				s.beginTransaction();
				s.delete(obj);
				s.getTransaction().commit();
				fin = true;
			}
		}
		return fin;
	}

	@Override
	public boolean borrarUnRepresentante(String Id) throws IOException {
		boolean fin = false;
		Actor obj = new Actor();
		HashMap<String, Actor> ver = leertodosActores();
		for (HashMap.Entry<String, Actor> entry : ver.entrySet()) {
			if(entry.getValue().getRepresentante().getId().equals(Id)){
				System.out.println("ENTRO");
				obj = entry.getValue();
				obj.setRepresentante(null);
				s.beginTransaction();
				s.update(obj);
			}
		}
		Representante obj2 = new Representante();
		HashMap<String, Representante> ver2 = leertodosRepresentante();
		for (HashMap.Entry<String, Representante> entry : ver2.entrySet()) {
			if(entry.getValue().getId().equals(Id)){
				System.out.println("ENTRO");
				obj2 = entry.getValue();
				s.delete(obj2);
				s.getTransaction().commit();
				fin = true;
			}
		}
		return fin;
	}

}
