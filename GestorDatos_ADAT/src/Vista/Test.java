package Vista;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import Controlador.Controlador;
import Modelo.Actor;
import Modelo.Representante;

public class Test {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		Controlador control = new Controlador();
		Scanner teclado = new Scanner(System.in);
		String tipo = "";
		System.out.println("1¿Que acceso a datos primario quieres utilizar?");
		System.out.println("1.Fichero");
		System.out.println("2.Base de datos");
		System.out.println("3.Hibernate");
		int acceso = teclado.nextInt();
		switch (acceso) {
		case 1:
			tipo = "fichero";
			control.elegirdato(acceso);
			break;
		case 2:
			tipo = "base de datos";
			control.elegirdato(acceso);
			break;
		case 3:
			tipo = "hibernate";
			control.elegirdato(acceso);
			break;
		default:
			System.out.println("Opcion de acceso de datos primario no valido");
			break;
		}
		boolean salir = true;
		int opcion;
		while (salir) {
			System.out.println("Estas trabajando con " + tipo + ".");
			System.out.println("¿Que quieres hacer?");
			System.out.println("1.Leer");
			System.out.println("2.Agregar uno a");
			System.out.println("3.Importar datos a");
			System.out.println("4.Exportar datos a");
			System.out.println("5.Borrar todos de");
			System.out.println("6.Salir");
			opcion = teclado.nextInt();
			switch (opcion) {
			case 1:
				System.out.println("¿Que deseas leer?");
				System.out.println("1.Actores");
				System.out.println("2.Representantes");
				int leer = teclado.nextInt();
				switch (leer) {
				case 1:
					control.leerActores();
					break;
				case 2:
					control.leerRepresentantes();
					break;
				default:
					System.out.println("Opcion no valida");
					break;
				}
				break;
			case 2:
				System.out.println("¿Donde deseas agregar?");
				System.out.println("1.Actores");
				System.out.println("2.Representantes");
				int anadir = teclado.nextInt();
				switch (anadir) {
				case 1:
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
						control.pedirdatosagregarActor(nuevo);
						break;
					} else {
						System.out.println("No se a podido agregar el actor, el representante no a sido encontrado en la tabla representantes");
					}
					break;
				case 2:
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
					control.pedirdatosagregarRepresentante(repres);
					break;
				default:
					System.out.println("Opcion no valida");
					break;
				}
				break;
			case 3:
				System.out.println("¿A que acceso a datos quieres pasar los datos?");
				System.out.println("1.Fichero");
				System.out.println("2.Base de datos");
				System.out.println("3.Hiberate");
				int importar = teclado.nextInt();
				switch (importar) {
				case 1:
					if (acceso == 1) {
						System.out.println("No puedes importar de fichero a fichero.");
					} else if (acceso == 2) {
						control.importar(importar);
					} else if(acceso == 3){
						control.importar(importar);
					}
					break;
				case 2:
					if (acceso == 2) {
						System.out.println("No puedes importar de base de datos a base de datos.");
					} else if (acceso == 1) {
						control.importar(importar);
					} else if(acceso == 3){
						control.importar(importar);
					}
					break;
				case 3:
					if (acceso == 3) {
						System.out.println("No puedes importar de hibernate a hibernate.");
					} else if (acceso == 1) {
						control.importar(importar);
					} else if(acceso==2){
						control.importar(importar);
					}
					break;
				default:
					System.out.println("Opcion no valida");
					break;
				}
				break;
			case 4:
				System.out.println("¿De que acceso a datos quieres pasar los datos?");
				System.out.println("1.Fichero");
				System.out.println("2.Base de datos");
				System.out.println("3.Hibernate");
				int exportar = teclado.nextInt();
				switch (exportar) {
				case 1:
					if (acceso == 1) {
						System.out.println("No puedes exportar de fichero a fichero.");
					} else if (acceso == 2) {
						control.exportar(exportar);
					} else if(acceso == 3){
						control.exportar(exportar);
					}
					break;
				case 2:
					if (acceso == 2) {
						System.out.println("No puedes exportar de base de datos a base de datos.");
					} else if (acceso == 1) {
						control.exportar(exportar);
					} else if(acceso == 3){
						control.exportar(exportar);
					}
					break;
				case 3:
					if (acceso == 3) {
						System.out.println("No puedes exportar de hibernate a hibernate.");
					} else if (acceso == 1) {
						control.exportar(exportar);
					} else if(acceso == 2){
						control.exportar(exportar);
					}
					break;
				default:
					System.out.println("Opcion no valida");
					break;
				}
				break;
			case 5:
				System.out.println("¿De donde deseas borrar todo?");
				System.out.println("1.Actores");
				System.out.println("2.Representantes (Si borras esta entidad se borra Actores,ya que esta relaccionada.");
				int borrartodo = teclado.nextInt();
				switch (borrartodo) {
				case 1:
					control.borrarActores();
					System.out.println("Datos borrados de " + tipo);
					break;
				case 2:
					control.borrarRepresentantes();
					System.out.println("Datos borrados de " + tipo);
					break;
				default:
					System.out.println("Opcion no valida");
					break;
				}	
				break;
			case 6:
				salir = false;
				break;
			default:
				System.out.println("Ingrese un numero del 1 al 5");
				break;
			}
		}
		teclado.close();
	}
}

