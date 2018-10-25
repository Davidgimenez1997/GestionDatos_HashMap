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

public class Gestor_BBDD implements I_GestorDatos {

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
		HashMap<String, Actor> actoresCreados = new HashMap<>();
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
		try (PreparedStatement stmt = conexion
				.prepareStatement("SELECT * FROM representantes WHERE Id = " + id_repre)) {
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				repre = new Representante(rs.getString("Id"), rs.getString("Nombre"), rs.getString("Edad"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return repre;
	}

	@Override
	public HashMap<String, Representante> leertodosRepresentante() throws IOException {
		HashMap<String, Representante> representantesCreados = new HashMap<>();
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
				representantesCreados.put(rset.getString("Id"), repre);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return representantesCreados;
	}

	@Override
	public boolean comprobaridactor(Actor nuevo) throws IOException {
		PreparedStatement pstm;
		HashMap<String, Actor> ver = leertodosActores();
		try {
			pstm = conexion.prepareStatement("select id FROM actores where id = " + nuevo.getId());
			ResultSet rset = pstm.executeQuery();
			while (rset.next()) {
				for (HashMap.Entry<String, Actor> entry : ver.entrySet()) {
					if (entry.getValue().getId().equals(nuevo.getId())) {
						System.out.println("Id del actor repetido");
						return true;
					}
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean agregarActor(Actor nuevo) throws IOException {
		try {
			if (!comprobaridactor(nuevo)) {
				try {
					PreparedStatement sql = conexion.prepareStatement(
							"INSERT INTO actores (Id,Nombre,Descripcion,Pelo,Ojos,Representante)VALUES (?,?,?,?,?,?)");
					sql.setString(1, nuevo.getId());
					sql.setString(2, nuevo.getNombre());
					sql.setString(3, nuevo.getDescripcion());
					sql.setString(4, nuevo.getPelo());
					sql.setString(5, nuevo.getOjos());
					sql.setString(6, nuevo.getRepresentante().getId());
					sql.executeUpdate();
					return true;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean comprobaridrepresentante(Representante nuevo) throws IOException {
		PreparedStatement pstm;
		HashMap<String, Representante> ver = leertodosRepresentante();
		try {
			pstm = conexion.prepareStatement("SELECT Id FROM representantes where id = " + nuevo.getId());
			ResultSet rset = pstm.executeQuery();
			while (rset.next()) {
				for (int i = 0; i < leertodosActores().size(); i++) {
					for (HashMap.Entry<String, Representante> entry : ver.entrySet()) {
						if (entry.getValue().getId().equals(nuevo.getId())) {
							System.out.println("Id del representante repetido");
							return true;
						}
					}
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean agregarRepresentante(Representante nuevo) throws IOException {
		try {
			if (!comprobaridrepresentante(nuevo)) {
				try {
					PreparedStatement sql = conexion
							.prepareStatement("INSERT INTO representantes (Id,Nombre,Edad)VALUES (?,?,?)");
					sql.setString(1, nuevo.getId());
					sql.setString(2, nuevo.getNombre());
					sql.setString(3, nuevo.getEdad());
					sql.executeUpdate();
					return true;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
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
		PreparedStatement pstm;
		try {
			pstm = conexion.prepareStatement("Delete from actores");
			pstm.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean borrarTodoRepresentantes() throws IOException {
		borrarTodoActores();
		PreparedStatement pstm;
		// int r = 0;
		try {
			pstm = conexion.prepareStatement("Delete from representantes");
			pstm.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean modificarUnActor(String idmodificar, Actor modificar) throws IOException {
		HashMap<String, Actor> ver = leertodosActores();
		PreparedStatement pstm;
		boolean fin = false;
		for (HashMap.Entry<String, Actor> entry : ver.entrySet()) {
			if (entry.getKey().contains(idmodificar)) {
				try {
					pstm = conexion.prepareStatement("UPDATE actores SET " + "Nombre = ?" + ",Descripcion = ?"
							+ ",Pelo = ?" + ",Ojos = ? " + ",Representante = ? " + "WHERE Id=?");
					pstm.setString(1, modificar.getNombre());
					pstm.setString(2, modificar.getDescripcion());
					pstm.setString(3, modificar.getPelo());
					pstm.setString(4, modificar.getOjos());
					pstm.setString(5, modificar.getRepresentante().getId());
					pstm.setString(6, idmodificar);
					pstm.executeUpdate();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
				fin = true;
			}
		}
		return fin;
	}

	@Override
	public boolean modificarUnRepresentante(String idmodificar, Representante modificar) throws IOException {
		PreparedStatement pstm;
		HashMap<String, Representante> ver_repre = leertodosRepresentante();
		boolean fin = false;
		for (HashMap.Entry<String, Representante> entry : ver_repre.entrySet()) {
			if (entry.getKey().contains(idmodificar)) {
				try {
					pstm = conexion.prepareStatement("UPDATE representantes SET " + "Nombre = ?" + ",Edad = ?" + "WHERE Id=?");
					pstm.setString(1, modificar.getNombre());
					pstm.setString(2, modificar.getEdad());
					pstm.setString(3, idmodificar);
					pstm.executeUpdate();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
				fin = true;
			}
		}
		return fin;
	}

	@Override
	public boolean borrarUnActor(String Id) throws IOException {
		boolean fin = false;
		PreparedStatement pstm;
		try {
			String query = "Delete from actores Where Id = " + Id;
			pstm = conexion.prepareStatement(query);
			pstm.executeUpdate();
			fin = true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return fin;

	}

	@Override
	public boolean borrarUnRepresentante(String Id) throws IOException {
		boolean fin = false;
		PreparedStatement pstm;
		try {
			String query = "UPDATE actores SET " + "Representante = NULL " + "WHERE Representante = " + Id;
			String query2 = "Delete from representantes Where Id = " + Id;
			pstm = conexion.prepareStatement(query);
			pstm.executeUpdate();
			pstm = conexion.prepareStatement(query2);
			pstm.executeUpdate();
			fin = true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return fin;
	}

}
