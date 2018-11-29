<?php

/*  Formato JSON esperado */

$arrEsperado = array();
$arrRepresentanteEsperado = array();

$arrRepresentanteEsperado["Id"] = "1";
$arrRepresentanteEsperado["Nombre"] = "nombre";
$arrRepresentanteEsperado["Descripcion"]= "Descripcion";
$arrRepresentanteEsperado["Pelo"]= "Pelo";
$arrRepresentanteEsperado["Ojos"]= "Ojos";
$arrRepresentanteEsperado["Representante"]= " id Representante";

$arrEsperado["peticion"] = "update";


$arrEsperado["actorModificar"] = $arrRepresentanteEsperado;


/* Funcion para comprobar si el recibido es igual al esperado */

function JSONCorrectoModificarActor($recibido){

	$auxCorrecto = false;

	if(isset($recibido["peticion"]) && $recibido["peticion"] ="update" && isset($recibido["actorModificar"])){

		$auxRepresentante = $recibido["actorModificar"];
		if(isset($auxRepresentante["Id"]) && isset($auxRepresentante["Nombre"]) && isset($auxRepresentante["Descripcion"])&& isset ($auxRepresentante["Pelo"]) && isset ($auxRepresentante["Ojos"]) && isset ($auxRepresentante["Representante"])){
			$auxCorrecto = true;
		}

	}


	return $auxCorrecto;

}

