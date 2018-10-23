package Controlador;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		HashMap<String,Actor> actoresCreados = new HashMap<>();
		PreparedStatement pstm;
		Actor act;
		try {
			pstm = conexion.prepareStatement("SELECT * FROM actores");
			ResultSet rset = pstm.executeQuery();
			while (rset.next()) {
				act = new Actor();
				act.setId(rset.getString("Id"));
				act.setNombre(rset.getString("Nombre"));
				act.setDescripcion(rset.getString("Descripcion"));
				act.setPelo(rset.getString("Pelo"));
				act.setOjos(rset.getString("Ojos"));
				act.setRepresentante(crearRepresentante(rset.getString("Representante")));
				actoresCreados.put(rset.getString("Id"), act);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return actoresCreados;
	}
	
	 public Representante crearRepresentante(String id_repre) {
	        Representante repre = new Representante();
	        try (PreparedStatement stmt = conexion.prepareStatement("SELECT * FROM representantes WHERE Id = " + id_repre)) {
	            ResultSet rs = stmt.executeQuery();
	            while (rs.next()) {
	                repre = new Representante(rs.getString("Id"),rs.getString("Nombre"),rs.getString("Edad"));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return repre;
	    }

	@Override
	public HashMap<String, Representante> leertodosRepresentante() throws IOException {
		HashMap<String,Representante> representantesCreados = new HashMap<>();
		PreparedStatement pstm;
		Representante repre;
		try {
			pstm = conexion.prepareStatement("SELECT * FROM representantes");
			ResultSet rset = pstm.executeQuery();
			while (rset.next()) {
				repre = new Representante();
				repre.setId(rset.getString("Id"));
				repre.setNombre(rset.getString("Nombre"));
				repre.setEdad(rset.getString("Edad"));
				representantesCreados.put(rset.getString("Id"),repre);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return representantesCreados;
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
