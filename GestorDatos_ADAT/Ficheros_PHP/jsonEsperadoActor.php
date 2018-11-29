<?php

/*  Formato JSON esperado */

$arrEsperado = array();
$arrActorEsperado = array();


$arrActorEsperado["id"] = "1 (Un string)";
$arrActorEsperado["nombre"] = "David (Un string)";
$arrActorEsperado["descripcion"] = "Buen actor (Un string)";
$arrActorEsperado["pelo"] = "azul (Un string)";
$arrActorEsperado["ojos"] = "moreno (Un string)";
$arrActorEsperado["representante"] = "2 (Un string)";


$arrEsperado["peticion"] = "add";


$arrEsperado["actorAnnadir"] = $arrActorEsperado;


/* Funcion para comprobar si el recibido es igual al esperado */

function JSONCorrectoAnnadirActor($recibido){

	$auxCorrecto = false;

	if(isset($recibido["peticion"]) && $recibido["peticion"] ="add" && isset($recibido["actorAnnadir"])){

		$auxRepresentante = $recibido["actorAnnadir"];
		if(isset($auxRepresentante["id"]) && isset($auxRepresentante["nombre"]) && isset($auxRepresentante["descripcion"])&& isset ($auxRepresentante["pelo"]) && isset ($auxRepresentante["ojos"]) && isset ($auxRepresentante["representante"])){
			$auxCorrecto = true;
		}

	}


	return $auxCorrecto;

}

