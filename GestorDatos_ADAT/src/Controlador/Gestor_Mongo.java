package Controlador;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Properties;

import org.bson.Document;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import Modelo.Actor;
import Modelo.Representante;

public class Gestor_Mongo implements I_GestorDatos{
	
	private String IP,NAMEDATABASE,PORT;
	private MongoClient mongoClient;
	private MongoDatabase database;

	public Gestor_Mongo(String archivo) throws FileNotFoundException, IOException{
		Properties p = new Properties();
		p.load(new FileReader(archivo));
		IP = p.getProperty("IP");
		NAMEDATABASE = p.getProperty("NAMEDATABASE");
		PORT = p.getProperty("PORT");
		int port = Integer.parseInt(PORT);
		mongoClient = new MongoClient(IP,port);
		database = (MongoDatabase) mongoClient.getDatabase(NAMEDATABASE);
	}

	@Override
	public HashMap<String, Actor> leertodosActores() throws IOException {
		HashMap<String, Actor> aux = new HashMap<String, Actor>();
		HashMap<String,Representante> auxRepre = new HashMap<>();
		MongoCollection<Document> collection = database.getCollection("actores");
		//MongoCollection<Document> documentAll = collection.find();
		//String jsonDocument = documentAll.toJson();
		Actor actor = null;
		Representante representante = null;
		String idActor,nombreActor,descripcion,pelo,ojos = null;
		String idRepresentante,nombreRepresentantel,edad = null;
		JSONObject obj;
		JSONArray arr;
		for(Document document:collection.find()){
			obj =(JSONObject) JSONValue.parse(document.toJson().toString());
			arr = (JSONArray) obj.get("representante");
			idActor = document.get("id").toString();
			nombreActor = document.get("nombre").toString();
			descripcion = document.get("descripcion").toString();
			pelo = document.get("pelo").toString();
			ojos = document.get("ojos").toString();
			actor = new Actor(idActor, nombreActor, descripcion, pelo, ojos);
			for (int i = 0; i < arr.size(); i++) {
				JSONObject row = (JSONObject) arr.get(i);
				idRepresentante = row.get("id").toString();
				nombreRepresentantel = row.get("nombre").toString();
				edad = row.get("edad").toString();
				representante = new Representante(idRepresentante,nombreRepresentantel,edad);
				actor.setRepresentante(representante);
				aux.put(idActor, actor);
			}
		}
		return aux;
	}

	@Override
	public HashMap<String, Representante> leertodosRepresentante() throws IOException {
		HashMap<String, Representante> aux = new HashMap<String, Representante>();
		MongoCollection<Document> collection = database.getCollection("representantes");
		Representante representante = null;
		String id,nombre,edad = null;
		for(Document document:collection.find()){
			id = document.get("id").toString();
			nombre = document.getString("nombre").toString();
			edad = document.getString("edad").toString();
			representante = new Representante(id,nombre,edad);
			aux.put(id, representante);			
		}
		return aux;
	}

	@Override
	public boolean agregarActor(Actor nuevo) throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean agregarRepresentante(Representante nuevo) throws IOException {
		// TODO Auto-generated method stub
		return false;
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
	public void escribirtodosActores(HashMap<String, Actor> lista) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void escribirtodosRepresentante(HashMap<String, Representante> lista) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean borrarTodoActores() throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean borrarTodoRepresentantes() throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modificarUnActor(String idmodificar, Actor modificar) throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modificarUnRepresentante(String idmodificar, Representante modificar) throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean borrarUnActor(String Id) throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean borrarUnRepresentante(String Id) throws IOException {
		// TODO Auto-generated method stub
		return false;
	}
}
