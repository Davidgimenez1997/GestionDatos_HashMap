package Modelo;

public class Representante {
	private String id;
	private String nombre;
	private String edad;

	public Representante() {

	}

	public Representante(String id) {
		this.id = id;
	}

	public Representante(String id, String nombre, String edad) {
		this.id = id;
		this.nombre = nombre;
		this.edad = edad;
	}

	public Representante(String nombre, String edad) {
		this.nombre = nombre;
		this.edad = edad;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEdad() {
		return edad;
	}

	public void setEdad(String edad) {
		this.edad = edad;
	}

}
