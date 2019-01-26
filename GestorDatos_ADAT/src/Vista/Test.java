package Vista;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import Controlador.Controlador;
import Modelo.Actor;
import Modelo.Representante;

public class Test {

	private Controlador control;
	private Scanner teclado;
	private String primario,modificar,idborrar;
	private boolean optCorrecta,salir;
	private int opcion,acceso,leer,anadir,importar,exportar,borrartodo,op_modificar,borraruno;

	public Test() {
		control = new Controlador();
		teclado = new Scanner(System.in);
	}

	public static void main(String[] args) {
		Test test = new Test();
		test.runApp();
	}

	private void runApp() {
			try {
				initMenuOpciones();
				initMainMenu();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	private void initMenuOpciones() throws FileNotFoundException, IOException {
		primario = "";
		optCorrecta = true;
		while (optCorrecta) {
			System.out.println("1¿Que acceso a datos primario quieres utilizar?");
			System.out.println("1.Fichero");
			System.out.println("2.Base de datos");
			System.out.println("3.Hibernate");
			System.out.println("4.JSON");
			System.out.println("5.Mongo");
			try {
				acceso = teclado.nextInt();
				switch (acceso) {
				case 1:
					primario = "fichero";
					control.elegirdato(acceso);
					optCorrecta = false;
					break;
				case 2:
					primario = "base de datos";
					control.elegirdato(acceso);
					optCorrecta = false;
					break;
				case 3:
					primario = "hibernate";
					control.elegirdato(acceso);
					optCorrecta = false;
					break;
				case 4:
					primario = "json";
					control.elegirdato(acceso);
					optCorrecta = false;
					break;
				case 5:
					primario = "mongo";
					control.elegirdato(acceso);
					optCorrecta = false;
					break;
				default:
					System.out.println("Opcion de acceso de datos primario no valido");
					break;
				}
			} catch (NumberFormatException e) {
				System.err.println("NO HAS INTRODUCIDO UN NUMERO!!");
				System.err.println("Intentelo de nuevo...");
			}
		}
	}

	private void initMainMenu() throws IOException {
		salir = true;
		while (salir) {
			System.out.println("Estas trabajando con " + primario + ".");
			System.out.println("¿Que quieres hacer?");
			System.out.println("1.Leer");
			System.out.println("2.Agregar uno a");
			System.out.println("3.Importar datos a");
			System.out.println("4.Exportar datos a");
			System.out.println("5.Borrar todos de");
			System.out.println("6.Modificar uno de");
			System.out.println("7.Borrar uno de");
			System.out.println("8.Salir");
			try {
				opcion = teclado.nextInt();
				switch (opcion) {
				case 1:
					leer();
					break;
				case 2:
					agregar();
					break;
				case 3:
					importar(acceso);
					break;
				case 4:
					exporta(acceso);
					break;
				case 5:
					borrarTodo();
					break;
				case 6:
					modificarUno();
					break;
				case 7:
					borrarUno();
					break;
				case 8:
					salir = false;
					break;
				default:
					System.out.println("Ingrese un numero del 1 al 8");
					break;
				}
			} catch (NumberFormatException e) {
				System.err.println("NO HAS INTRODUCIDO UN NUMERO!!");
				System.err.println("Intentelo de nuevo...");
			}
		}
		teclado.close();
	}

	private void borrarUno() throws IOException {
		teclado.nextLine();
		System.out.println("¿De donde quieres borrar uno?");
		System.out.println("1.Actores");
		System.out.println("2.Representantes");
		borraruno = teclado.nextInt();
		try {
			switch (borraruno) {
			case 1:
				mostrarIdBorrarActor();
				break;
			case 2:
				mostrarIdBorrarRepresentante();
				break;
			default:
				System.out.println("Opcion no valida");
				break;
			}
		} catch (NumberFormatException e) {
			System.err.println("NO HAS INTRODUCIDO UN NUMERO!!");
			System.err.println("Intentelo de nuevo...");
		}

		teclado.nextLine();
		System.out.println("Escribe el id del dato que quieres eliminar");
		try {
			idborrar = teclado.nextLine();
			switch (borraruno) {
			case 1:
				borrarUnActor(idborrar);
				break;
			case 2:
				borrarUnRepresentante(idborrar);
				break;
			default:
				System.out.println("Opcion no valida");
				break;
			}
		} catch (NumberFormatException e) {
			System.err.println("NO HAS INTRODUCIDO UN NUMERO!!");
			System.err.println("Intentelo de nuevo...");
		}
	}

	private void mostrarIdBorrarActor() throws IOException {
		System.out.println("Id de los actores escoja uno:");
		control.leerActoresId();
	}

	private void mostrarIdBorrarRepresentante() throws IOException {
		System.out.println("Id de los representantes escoja uno:");
		control.leerRepresentantesId();
	}

	private void borrarUnActor(String idBorrar) throws IOException {
		if (control.borrarUnActores(idborrar)) {
			System.out.println("Datos borrados de " + primario);
		} else {
			System.out.println("Datos no borrados de " + primario);
		}
	}

	private void borrarUnRepresentante(String idBorrar) throws IOException {
		if (control.borrarUnRepresentantes(idborrar)) {
			System.out.println("Datos borrados de " + primario);
		} else {
			System.out.println("Datos no borrados de " + primario);
		}
	}

	private void modificarUno() throws IOException {
		teclado.nextLine();
		modificar = null;
		System.out.println("¿De donde deseas modificar?");
		System.out.println("1.Actores");
		System.out.println("2.Representantes");
		try {
			op_modificar = teclado.nextInt();
			switch (op_modificar) {
			case 1:
				modificar = tecladomodificarUnActor();
				break;
			case 2:
				modificar = tecladomodificarUnRepresentante();
				break;
			default:
				System.out.println("Opcion no valida");
				break;
			}
		} catch (NumberFormatException e) {
			System.err.println("NO HAS INTRODUCIDO UN NUMERO!!");
			System.err.println("Intentelo de nuevo...");
		}
		try {
			switch (op_modificar) {
			case 1:
				modificarActor();
				break;
			case 2:
				modificarRepresentante();
				break;
			}
		} catch (NumberFormatException e) {
			System.err.println("NO HAS INTRODUCIDO UN NUMERO!!");
			System.err.println("Intentelo de nuevo...");
		}
	}

	private String tecladomodificarUnActor() throws IOException {
		System.out.println("Id de los actores escoja uno:");
		control.leerActoresId();
		teclado.nextLine();
		System.out.println("Escribe el id del dato que quieres modificar");
		String modificar = null;
		try {
			modificar = teclado.nextLine();
		} catch (NumberFormatException e) {
			System.err.println("NO HAS INTRODUCIDO UN NUMERO!!");
		}
		return modificar;
	}

	private String tecladomodificarUnRepresentante() throws IOException {
		System.out.println("Id de los representantes escoja uno:");
		control.leerRepresentantesId();
		teclado.nextLine();
		System.out.println("Escribe el id del dato que quieres modificar");
		String modificar = null;
		try {
			modificar = teclado.nextLine();
		} catch (NumberFormatException e) {
			System.err.println("NO HAS INTRODUCIDO UN NUMERO!!");
		}
		return modificar;
	}

	private void modificarActor() throws IOException {
		System.out.println("Escriba el nuevo nombre");
		String nuevonombre = teclado.nextLine();
		System.out.println("Escriba la nueva descripcion");
		String nuevadescr = teclado.nextLine();
		System.out.println("Escriba el nuevo color de pelo");
		String nuevopelo = teclado.nextLine();
		System.out.println("Escriba el nuevo color de ojos");
		String nuevoojos = teclado.nextLine();
		System.out.println("Id de los representantes escoja uno:");
		control.leerRepresentantesId();
		System.out.println("Escriba el nuevo id del representante");
		String nuevorepre = teclado.nextLine();
		Representante obj = new Representante(nuevorepre);
		Actor actmodificar = new Actor(modificar, nuevonombre, nuevadescr, nuevopelo, nuevoojos, obj);
		if (control.modificarUnActor(modificar, actmodificar)) {
			System.out.println("Actor modificado correctamente de " + primario);
		} else {
			System.out.println("No se pudo modificar el actor de " + primario);
		}
	}

	private void modificarRepresentante() throws IOException {
		System.out.println("Escriba el nuevo nombre");
		String nuevonombrerepre = teclado.nextLine();
		System.out.println("Escriba la nueva edad");
		String nuevaedad = teclado.nextLine();
		Representante reprmodificar = new Representante(modificar, nuevonombrerepre, nuevaedad);
		if (control.modificarUnRepresentante(modificar, reprmodificar)) {
			System.out.println("Representante modificado correctamente de " + primario);
		} else {
			System.out.println("No se pudo modificar el representante de " + primario);
		}
	}

	private void borrarTodo() throws IOException {
		System.out.println("¿De donde deseas borrar todo?");
		System.out.println("1.Actores");
		System.out.println("2.Representantes (Si borras esta entidad se borra Actores,ya que esta relaccionada.");
		try {
			borrartodo = teclado.nextInt();
			switch (borrartodo) {
			case 1:
				borrarTodosActores();
				break;
			case 2:
				borrarTodosRepresentantes();
				break;
			default:
				System.err.println("Opcion no valida");
				break;
			}
		} catch (NumberFormatException e) {
			System.err.println("NO HAS INTRODUCIDO UN NUMERO!!");
			System.err.println("Intentelo de nuevo...");
		}
	}

	private void borrarTodosActores() throws IOException {
		if (control.borrarActores()) {
			System.out.println("Actores borrados de " + primario);
		} else {
			System.err.println("Actores no borrados de " + primario);
		}
	}

	private void borrarTodosRepresentantes() throws IOException {
		teclado.nextLine();
		System.out.println("¿Estas seguro de borrar?S/N");
		String seguro = teclado.nextLine();
		switch (seguro.toLowerCase()) {
		case "s":
			if (control.borrarRepresentantes()) {
				System.out.println("Actores y representantes borrados de " + primario);
			} else {
				System.err.println("Actores y representantes no borrados de " + primario);
			}
			break;
		case "n":
			System.err.println("Cancelado el borrado de Actores y representantes");
			break;
		default:
			System.err.println("Opcion no valida");
			break;
		}
	}

	private void exporta(int aux) throws IOException {
		System.out.println("¿De que acceso a datos quieres pasar los datos?");
		System.out.println("1.Fichero");
		System.out.println("2.Base de datos");
		System.out.println("3.Hibernate");
		System.out.println("4.JSON");
		System.out.println("5.Mongo");
		try {
			exportar = teclado.nextInt();
			switch (exportar) {
			case 1:
				exportarFicheros(exportar);
				break;
			case 2:
				exportarBBDD(exportar);
				break;
			case 3:
				exportarHibernate(exportar);
				break;
			case 4:
				exportarJSON(exportar);
				break;
			case 5:
				exportarMongo(exportar);
				break;
			default:
				System.out.println("Opcion no valida");
				break;
			}
		} catch (NumberFormatException e) {
			System.err.println("NO HAS INTRODUCIDO UN NUMERO!!");
			System.err.println("Intentelo de nuevo...");
		}
	}

	private void exportarFicheros(int exportar) throws IOException {
		if (acceso == 1) {
			System.err.println("No puedes exportar de fichero a fichero.");
		} else if (acceso == 2) {
			control.exportar(exportar);
		} else if (acceso == 3) {
			control.exportar(exportar);
		} else if (acceso == 4) {
			control.exportar(exportar);
		} else if (acceso == 5) {
			control.exportar(exportar);
		}
	}

	private void exportarBBDD(int exportar) throws IOException {
		if (acceso == 2) {
			System.err.println("No puedes exportar de base de datos a base de datos.");
		} else if (acceso == 1) {
			control.exportar(exportar);
		} else if (acceso == 3) {
			control.exportar(exportar);
		} else if (acceso == 4) {
			control.exportar(exportar);
		} else if (acceso == 5) {
			control.exportar(exportar);
		}
	}

	private void exportarHibernate(int exportar) throws IOException {
		if (acceso == 3) {
			System.err.println("No puedes exportar de hibernate a hibernate.");
		} else if (acceso == 1) {
			control.exportar(exportar);
		} else if (acceso == 2) {
			control.exportar(exportar);
		} else if (acceso == 4) {
			control.exportar(exportar);
		} else if (acceso == 5) {
			control.exportar(exportar);
		}
	}

	private void exportarJSON(int exportar) throws IOException {
		if (acceso == 4) {
			System.err.println("No puedes exportsr de JSON a JSON");
		} else if (acceso == 1) {
			control.exportar(exportar);
		} else if (acceso == 2) {
			control.exportar(exportar);
		} else if (acceso == 3) {
			control.exportar(exportar);
		} else if (acceso == 5) {
			control.exportar(exportar);
		}
	}

	private void exportarMongo(int exportar) throws IOException {
		if (acceso == 4) {
			control.exportar(exportar);
		} else if (acceso == 1) {
			control.exportar(exportar);
		} else if (acceso == 2) {
			control.exportar(exportar);
		} else if (acceso == 3) {
			control.exportar(exportar);
		} else if (acceso == 5) {
			System.err.println("No puedes exportsr de Mongo a Mongo");
		}
	}

	private void importar(int aux) throws IOException {
		System.out.println("¿A que acceso a datos quieres pasar los datos?");
		System.out.println("1.Fichero");
		System.out.println("2.Base de datos");
		System.out.println("3.Hiberate");
		System.out.println("4.JSON");
		System.out.println("5.Mongo");
		try {
			importar = teclado.nextInt();
			switch (importar) {
			case 1:
				importarFicheros(importar);
				break;
			case 2:
				importarBBDD(importar);
				break;
			case 3:
				importarHibernate(importar);
				break;
			case 4:
				importarJSON(importar);
				break;
			case 5:
				importarMongo(importar);
				break;
			default:
				System.err.println("Opcion no valida");
				break;
			}
		} catch (NumberFormatException e) {
			System.err.println("NO HAS INTRODUCIDO UN NUMERO!!");
			System.err.println("Intentelo de nuevo...");
		}
	}

	private void importarFicheros(int importar) throws IOException {
		if (acceso == 1) {
			System.err.println("No puedes importar de fichero a fichero.");
		} else if (acceso == 2) {
			control.importar(importar);
		} else if (acceso == 3) {
			control.importar(importar);
		} else if (acceso == 4) {
			control.importar(importar);
		} else if (acceso == 5) {
			control.importar(importar);
		}
	}

	private void importarBBDD(int importar) throws IOException {
		if (acceso == 2) {
			System.err.println("No puedes importar de base de datos a base de datos.");
		} else if (acceso == 1) {
			control.importar(importar);
		} else if (acceso == 3) {
			control.importar(importar);
		} else if (acceso == 4) {
			control.importar(importar);
		} else if (acceso == 5) {
			control.importar(importar);
		}
	}

	private void importarHibernate(int importar) throws IOException {
		if (acceso == 3) {
			System.err.println("No puedes importar de hibernate a hibernate.");
		} else if (acceso == 1) {
			control.importar(importar);
		} else if (acceso == 2) {
			control.importar(importar);
		} else if (acceso == 4) {
			control.importar(importar);
		} else if (acceso == 5) {
			control.importar(importar);
		}
	}

	private void importarJSON(int importar) throws IOException {
		if (acceso == 4) {
			System.err.println("No puedes importar de JSON a JSON");
		} else if (acceso == 1) {
			control.importar(importar);
		} else if (acceso == 2) {
			control.importar(importar);
		} else if (acceso == 3) {
			control.importar(importar);
		} else if (acceso == 5) {
			control.importar(importar);
		}
	}

	private void importarMongo(int importar) throws IOException {
		if (acceso == 4) {
			control.importar(importar);
		} else if (acceso == 1) {
			control.importar(importar);
		} else if (acceso == 2) {
			control.importar(importar);
		} else if (acceso == 3) {
			control.importar(importar);
		} else if (acceso == 5) {
			System.err.println("No puedes importar de Mongo a Mongo");
		}
	}

	private void agregar() throws IOException {
		System.out.println("¿Donde deseas agregar?");
		System.out.println("1.Actores");
		System.out.println("2.Representantes");
		try {
			anadir = teclado.nextInt();
			switch (anadir) {
			case 1:
				agregarActor();
				break;
			case 2:
				agregarRepresentante();
				break;
			default:
				System.err.println("Opcion no valida");
				break;
			}
		} catch (NumberFormatException e) {
			System.err.println("NO HAS INTRODUCIDO UN NUMERO!!");
			System.err.println("Intentelo de nuevo...");
		}
	}

	private void agregarActor() throws IOException {
		System.out.println("Introduzca un id diferente de los que ves en la pantalla");
		control.leerActoresId();
		teclado.nextLine();
		System.out.println("Introduzca id del actor");
		String id = teclado.nextLine();
		System.out.println("Introduzca un nombre del actor");
		String nombre = teclado.nextLine();
		System.out.println("Introduzca descripcion del actor");
		String desc = teclado.nextLine();
		System.out.println("Introduzca color de pelo del actor");
		String pelo = teclado.nextLine();
		System.out.println("Introduzca color de ojos del actor");
		String ojos = teclado.nextLine();
		System.out.println("Id de los representantes escoja uno:");
		control.leerRepresentantesId();
		System.out.println("Introduzca el id del representante del actor");
		String id_repre = teclado.nextLine();
		Representante obj_repre = control.escojerRepresentante(id_repre);
		if (obj_repre != null) {
			Actor nuevo = new Actor(id, nombre, desc, pelo, ojos, obj_repre);
			if (control.pedirdatosagregarActor(nuevo)) {
				System.out.println("Actor añadido correctamente a " + primario);
			} else {
				System.err.println("No se a podido añadir el actor a " + primario);
			}
		} else {
			System.err.println("No se a podido agregar el actor, el representante no a sido encontrado");
		}
	}

	private void agregarRepresentante() throws IOException {
		System.out.println("Introduzca un id diferente de los que ves en la pantalla");
		control.leerRepresentantesId();
		teclado.nextLine();
		System.out.println("Introduzca id del representante");
		String idrepresentante = teclado.nextLine();
		System.out.println("Introduzca un nombre del representante");
		String nombrerepresentante = teclado.nextLine();
		System.out.println("Introduzca la edad del representante");
		String edadrepresentante = teclado.nextLine();
		Representante repres = new Representante(idrepresentante, nombrerepresentante, edadrepresentante);
		if (control.pedirdatosagregarRepresentante(repres)) {
			System.out.println("Representante añadido correctamente a " + primario);
		} else {
			System.err.println("No se a podido añadir el representante a " + primario);
		}
	}

	private void leer() throws IOException {
		System.out.println("¿Que deseas leer?");
		System.out.println("1.Actores");
		System.out.println("2.Representantes");
		try {
			leer = teclado.nextInt();
			switch (leer) {
			case 1:
				control.leerActores();
				break;
			case 2:
				control.leerRepresentantes();
				break;
			default:
				System.err.println("Opcion no valida");
				break;
			}
		} catch (NumberFormatException e) {
			System.err.println("NO HAS INTRODUCIDO UN NUMERO!!");
			System.err.println("Intentelo de nuevo...");
		}
	}
}
