<?php

/*  Formato JSON esperado */

$arrEsperado = array();
$arrRepresentanteEsperado = array();


$arrRepresentanteEsperado["id"] = "1 (Un string)";
$arrRepresentanteEsperado["nombre"] = "David (Un string)";
$arrRepresentanteEsperado["edad"] = "25 (Un string)";

$arrEsperado["peticion"] = "add";


$arrEsperado["representanteAnnadir"] = $arrRepresentanteEsperado;


/* Funcion para comprobar si el recibido es igual al esperado */

function JSONCorrectoAnnadirRepresentante($recibido){

	$auxCorrecto = false;

	if(isset($recibido["peticion"]) && $recibido["peticion"] ="add" && isset($recibido["representanteAnnadir"])){

		$auxRepresentante = $recibido["representanteAnnadir"];
		if(isset($auxRepresentante["id"]) && isset($auxRepresentante["nombre"]) && isset($auxRepresentante["edad"])){
			$auxCorrecto = true;
		}

	}


	return $auxCorrecto;

}

