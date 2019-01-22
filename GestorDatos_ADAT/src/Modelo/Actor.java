package Modelo;

public class Actor {

	private String id;
	private String nombre;
	private String descripcion;
	private String pelo;
	private String ojos;
	private Representante representante;

	public Actor() {
	}

	public Actor(String id, String nombre, String descripcion, String pelo, String ojos, Representante representante) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.pelo = pelo;
		this.ojos = ojos;
		this.representante = representante;
	}

	public Actor(String nombre, String descripcion, String pelo, String ojos, Representante representante) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.pelo = pelo;
		this.ojos = ojos;
		this.representante = representante;
	}
	
	

	public Actor(String id, String nombre, String descripcion, String pelo, String ojos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.pelo = pelo;
		this.ojos = ojos;
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getPelo() {
		return pelo;
	}

	public void setPelo(String pelo) {
		this.pelo = pelo;
	}

	public String getOjos() {
		return ojos;
	}

	public void setOjos(String ojos) {
		this.ojos = ojos;
	}

	public Representante getRepresentante() {
		return representante;
	}

	public void setRepresentante(Representante representante) {
		this.representante = representante;
	}
}
