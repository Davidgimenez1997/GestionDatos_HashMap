<?php

require 'bbdd.php'; // Incluimos fichero en la que está la coenxión con la BBDD

/*
 * Se mostrará siempre la información en formato json para que se pueda leer desde un html (via js)
 * o una aplicación móvil o de escritorio realizada en java o en otro lenguajes
 */

$arrMensaje = array();  // Este array es el codificaremos como JSON tanto si hay resultado como si hay error



$query = "Delete from actores";

$result = $conn->query ( $query );

if (isset ( $result ) && $result) { // Si pasa por este if, la query está está bien y se obtiene resultado


		$arrMensaje["estado"] = "ok";
		$arrMensaje["actores"] = "Borrado_Correctamente";

} else {

	$arrMensaje["estado"] = "error";
	$arrMensaje["mensaje"] = "SE HA PRODUCIDO UN ERROR AL ACCEDER A LA BASE DE DATOS";
	$arrMensaje["error"] = $conn->error;
	$arrMensaje["query"] = $query;

}

$mensajeJSON = json_encode($arrMensaje,JSON_PRETTY_PRINT);

//echo "<pre>";  // Descomentar si se quiere ver resultado "bonito" en navegador. Solo para pruebas
echo $mensajeJSON;
//echo "</pre>"; // Descomentar si se quiere ver resultado "bonito" en navegador

$conn->close ();

?>
