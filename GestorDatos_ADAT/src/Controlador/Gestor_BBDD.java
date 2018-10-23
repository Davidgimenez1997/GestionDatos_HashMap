package Controlador;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Properties;

import Modelo.Actor;
import Modelo.Representante;

public class Gestor_BBDD implements Interfaz_Controlador{
	
	private Connection conexion;

	public Gestor_BBDD(String archivo) throws FileNotFoundException, IOException {
		Properties p = new Properties();
		p.load(new FileReader(archivo));
		String login = p.getProperty("login");
		String pwd = p.getProperty("pwd");
		String firsturl = p.getProperty("firsturl");
		String secondurl = p.getProperty("secondurl");
		String driver = p.getProperty("driver");
		String bdname = p.getProperty("bdname");
		String allurl = firsturl + bdname + secondurl;
		try {
			Class.forName(driver);
			conexion = DriverManager.getConnection(allurl, login, pwd);
			if (conexion != null) {
				System.out.println("Conexion establecida con Base de Datos");
			}
		} catch (Exception e) {
			System.out.println(" – Error de Conexión con MySQL -");
			e.printStackTrace();
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
}
