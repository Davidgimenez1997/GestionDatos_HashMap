package Controlador;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import Modelo.Actor;
import Modelo.Representante;

public class Gestor_Mongo implements I_GestorDatos{
	
	private String IP,NAMEDATABASE,PORT;

	public Gestor_Mongo(String archivo) throws FileNotFoundException, IOException{
		Properties p = new Properties();
		p.load(new FileReader(archivo));
		IP = p.getProperty("IP");
		NAMEDATABASE = p.getProperty("NAMEDATABASE");
		PORT = p.getProperty("PORT");
		int port = Integer.parseInt(PORT);
		MongoClient mongoClient = new MongoClient(IP,port);
		MongoDatabase database = (MongoDatabase) mongoClient.getDatabase(NAMEDATABASE);
		System.out.println("ip-> "+IP+" NAME ->"+NAMEDATABASE+" PORT-> "+PORT);
		//MongoCollection<Document> collection = database.getCollection("dispensadores");
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
