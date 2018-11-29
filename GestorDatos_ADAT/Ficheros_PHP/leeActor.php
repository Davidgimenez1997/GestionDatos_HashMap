<?php

require 'bbdd.php'; // Incluimos fichero en la que está la coenxión con la BBDD

/*
 * Se mostrará siempre la información en formato json para que se pueda leer desde un html (via js)
 * o una aplicación móvil o de escritorio realizada en java o en otro lenguajes
 */

$arrMensaje = array();  // Este array es el codificaremos como JSON tanto si hay resultado como si hay error


$query = "SELECT * FROM actores";

$result = $conn->query ( $query );

if (isset ( $result ) && $result) { // Si pasa por este if, la query está está bien y se obtiene resultado

	if ($result->num_rows > 0) { // Aunque la query esté bien puede no obtenerse resultado (tabla vacía). Comprobamos antes de recorrer

		$arrActores = array(); //Array numérico

		while ( $row = $result->fetch_assoc () ) {

			// Por cada vuelta del bucle creamos un jugador. Como es un objeto hacemos un array asociativo
			$arrActor = array(); //Array asociativo
			// Por cada columna de la tabla creamos una propiedad para el objeto
			$arrActor["id"] = $row["Id"];
			$arrActor["nombre"] = $row["Nombre"];
			$arrActor["descripcion"] = $row["Descripcion"];
			$arrActor["pelo"] = $row["Pelo"];
			$arrActor["ojos"] = $row["Ojos"];
			$arrActor["representante"] = $row["Representante"];

			// Por último, añadimos el nuevo jugador al array de jugadores
			$arrActores[] = $arrActor;

		}

		// Añadimos al $arrMensaje el array de jugadores y añadimos un campo para indicar que todo ha ido OK
		$arrMensaje["estado"] = "ok";
		$arrMensaje["actores"] = $arrActores;


	} else {

		$arrMensaje["estado"] = "ok";
		$arrMensaje["actores"] = []; // Array vacío si no hay resultados
	}

} else {

	$arrMensaje["estado"] = "error";
	$arrMensaje["mensaje"] = "SE HA PRODUCIDO UN ERROR AL ACCEDER A LA BASE DE DATOS";
	$arrMensaje["error"] = $conn->error;
	$arrMensaje["query"] = $query;

}

//var_dump($arrMensaje);

$mensajeJSON = json_encode($arrMensaje,JSON_PRETTY_PRINT);

//echo "<pre>";  // Descomentar si se quiere ver resultado "bonito" en navegador. Solo para pruebas
echo $mensajeJSON;
//echo "</pre>"; // Descomentar si se quiere ver resultado "bonito" en navegador

$conn->close ();


?>
