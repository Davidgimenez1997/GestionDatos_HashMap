<?php

/*  Formato JSON esperado */

$arrEsperado = array();
$arrActorEsperado = array();

$arrActorEsperado["Id"] = "1";

$arrEsperado["peticion"] = "delete";


$arrEsperado["actorBorrar"] = $arrActorEsperado;


/* Funcion para comprobar si el recibido es igual al esperado */

function JSONCorrectoBorrarActor($recibido){

	$auxCorrecto = false;

	if(isset($recibido["peticion"]) && $recibido["peticion"] ="delete" && isset($recibido["actorBorrar"])){

		$auxActor = $recibido["actorBorrar"];
		if(isset($auxActor["Id"])){
			$auxCorrecto = true;
		}

	}


	return $auxCorrecto;

}

