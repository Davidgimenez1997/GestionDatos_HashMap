<?php

/*  Formato JSON esperado */

$arrEsperado = array();
$arrRepresentanteEsperado = array();

$arrRepresentanteEsperado["Id"] = "1";

$arrEsperado["peticion"] = "delete";


$arrEsperado["representanteBorrar"] = $arrRepresentanteEsperado;


/* Funcion para comprobar si el recibido es igual al esperado */

function JSONCorrectoBorrarRepresentante($recibido){

	$auxCorrecto = false;

	if(isset($recibido["peticion"]) && $recibido["peticion"] ="delete" && isset($recibido["representanteBorrar"])){

		$auxRepresentante = $recibido["representanteBorrar"];
		if(isset($auxRepresentante["Id"])){
			$auxCorrecto = true;
		}

	}


	return $auxCorrecto;

}
