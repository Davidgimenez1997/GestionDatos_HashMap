package Controlador;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import org.bson.Document;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import Modelo.Actor;
import Modelo.Representante;

public class Gestor_Mongo implements I_GestorDatos {

	private String IP, NAMEDATABASE, PORT;
	private String ACTORES, REPRESENTANTES;
	private MongoClient mongoClient;
	private MongoDatabase database;

	public Gestor_Mongo(String archivo) throws FileNotFoundException, IOException {
		Properties p = new Properties();
		p.load(new FileReader(archivo));
		IP = p.getProperty("IP");
		NAMEDATABASE = p.getProperty("NAMEDATABASE");
		PORT = p.getProperty("PORT");
		ACTORES = p.getProperty("ACTORES");
		REPRESENTANTES = p.getProperty("REPRESENTANTES");
		int port = Integer.parseInt(PORT);
		mongoClient = new MongoClient(IP, port);
		database = (MongoDatabase) mongoClient.getDatabase(NAMEDATABASE);
	}

	@Override
	public HashMap<String, Actor> leertodosActores() throws IOException {
		HashMap<String, Actor> aux = new HashMap<String, Actor>();
		MongoCollection<Document> collection = database.getCollection(ACTORES);
		Actor actor = null;
		Representante representante = null;
		String idActor, nombreActor, descripcion, pelo, ojos = null;
		String idRepresentante, nombreRepresentantel, edad = null;
		JSONObject obj;
		JSONArray arr;
		for (Document document : collection.find()) {
			obj = (JSONObject) JSONValue.parse(document.toJson().toString());
			arr = (JSONArray) obj.get("representante");
			idActor = document.get("id").toString();
			nombreActor = document.get("nombre").toString();
			descripcion = document.get("descripcion").toString();
			pelo = document.get("pelo").toString();
			ojos = document.get("ojos").toString();
			actor = new Actor(idActor, nombreActor, descripcion, pelo, ojos);
			for (int i = 0; i < arr.size(); i++) {
				JSONObject row = (JSONObject) arr.get(i);
				if (row.get("id") != null) {
					idRepresentante = row.get("id").toString();
				} else {
					idRepresentante = "null";
				}
				if (row.get("nombre") != null) {
					nombreRepresentantel = row.get("nombre").toString();
				} else {
					nombreRepresentantel = "null";
				}
				if (row.get("edad") != null) {
					edad = row.get("edad").toString();
				} else {
					edad = "null";
				}
				representante = new Representante(idRepresentante, nombreRepresentantel, edad);
				actor.setRepresentante(representante);
				aux.put(idActor, actor);
			}
		}
		return aux;
	}

	@Override
	public HashMap<String, Representante> leertodosRepresentante() throws IOException {
		HashMap<String, Representante> aux = new HashMap<String, Representante>();
		MongoCollection<Document> collection = database.getCollection(REPRESENTANTES);
		Representante representante = null;
		String id, nombre, edad = null;
		for (Document document : collection.find()) {
			id = document.get("id").toString();
			nombre = document.getString("nombre").toString();
			edad = document.getString("edad").toString();
			representante = new Representante(id, nombre, edad);
			aux.put(id, representante);
		}
		return aux;
	}

	@Override
	public boolean agregarActor(Actor nuevo) throws IOException {
		if (!comprobaridactor(nuevo)) {
			MongoCollection<Document> collection = database.getCollection(ACTORES);
			Document document = new Document();
			document.put("id", nuevo.getId());
			document.put("nombre", nuevo.getNombre());
			document.put("descripcion", nuevo.getDescripcion());
			document.put("pelo", nuevo.getPelo());
			document.put("ojos", nuevo.getOjos());
			JSONObject obj = new JSONObject();
			obj.put("id", nuevo.getRepresentante().getId());
			obj.put("nombre", nuevo.getRepresentante().getNombre());
			obj.put("edad", nuevo.getRepresentante().getEdad());
			JSONArray arr = new JSONArray();
			arr.add(obj);
			document.put("representante", arr);
			collection.insertOne(document);
			return true;
		}
		return false;
	}

	@Override
	public boolean agregarRepresentante(Representante nuevo) throws IOException {
		if (!comprobaridrepresentante(nuevo)) {
			MongoCollection<Document> collection = database.getCollection(REPRESENTANTES);
			Document document = new Document();
			document.put("id", nuevo.getId());
			document.put("nombre", nuevo.getNombre());
			document.put("edad", nuevo.getEdad());
			collection.insertOne(document);
			return true;
		}
		return false;
	}

	@Override
	public boolean comprobaridactor(Actor nuevo) throws IOException {
		HashMap<String, Actor> aux = leertodosActores();
		if (aux.get(nuevo.getId()) != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean comprobaridrepresentante(Representante nuevo) throws IOException {
		HashMap<String, Representante> aux = leertodosRepresentante();
		if (aux.get(nuevo.getId()) != null) {
			return true;
		} else {
			return false;
		}
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
		MongoCollection<Document> collection = database.getCollection(ACTORES);
		for (HashMap.Entry<String, Actor> entry : leertodosActores().entrySet()) {
			collection.deleteMany(Filters.gte("id", entry.getKey()));
		}
		return true;
	}

	@Override
	public boolean borrarTodoRepresentantes() throws IOException {
		borrarTodoActores();
		MongoCollection<Document> collection = database.getCollection(REPRESENTANTES);
		for (HashMap.Entry<String, Representante> entry : leertodosRepresentante().entrySet()) {
			collection.deleteMany(Filters.gte("id", entry.getKey()));
		}
		return true;
	}

	@Override
	public boolean modificarUnActor(String idmodificar, Actor modificar) throws IOException {
		if(leertodosActores().get(idmodificar)!=null){
			borrarUnActor(idmodificar);
			modificar.setId(idmodificar);
			agregarActor(modificar);
			return true;
		}
		return false;
	}

	@Override
	public boolean modificarUnRepresentante(String idmodificar, Representante modificar) throws IOException {
		if(leertodosRepresentante().get(idmodificar)!=null){
			borrarUnRepresentante(idmodificar);
			modificar.setId(idmodificar);
			agregarRepresentante(modificar);
			return true;
		}
		return false;
	}

	@Override
	public boolean borrarUnActor(String Id) throws IOException {
		MongoCollection<Document> collection = database.getCollection(ACTORES);
		Actor act;
		if (leertodosActores().get(Id) != null) {
			act = leertodosActores().get(Id);
			collection.deleteOne(Filters.eq("id", Id));
			Document document = new Document();
			document.put("id", act.getId());
			document.put("nombre", act.getNombre());
			document.put("descripcion", act.getDescripcion());
			document.put("pelo", act.getPelo());
			document.put("ojos", act.getOjos());
			JSONObject obj = new JSONObject();
			obj.put("id", "null");
			obj.put("nombre", "null");
			obj.put("edad", "null");
			JSONArray arr = new JSONArray();
			arr.add(obj);
			document.put("representante", arr);
			collection.deleteOne(document);
			return true;
		}
		return false;
	}

	@Override
	public boolean borrarUnRepresentante(String Id) throws IOException {
		if (leertodosRepresentante().get(Id) != null) {
			MongoCollection<Document> collection = database.getCollection(REPRESENTANTES);
			collection.deleteOne(Filters.eq("id", Id));
			return true;
		}
		return false;
	}
}
