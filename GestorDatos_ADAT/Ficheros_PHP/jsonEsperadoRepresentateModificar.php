<?php

/*  Formato JSON esperado */

$arrEsperado = array();
$arrRepresentanteEsperado = array();


$arrRepresentanteEsperado["id"] = "1 (Un string)";
$arrRepresentanteEsperado["nombre"] = "David (Un string)";
$arrRepresentanteEsperado["edad"] = "25 (Un string)";

$arrEsperado["peticion"] = "update";


$arrEsperado["representanteModificar"] = $arrRepresentanteEsperado;


/* Funcion para comprobar si el recibido es igual al esperado */

function JSONCorrectoModificarRepresentante($recibido){

	$auxCorrecto = false;

	if(isset($recibido["peticion"]) && $recibido["peticion"] ="update" && isset($recibido["representanteModificar"])){

		$auxRepresentante = $recibido["representanteModificar"];
		if(isset($auxRepresentante["id"]) && isset($auxRepresentante["nombre"]) && isset($auxRepresentante["edad"])){
			$auxCorrecto = true;
		}

	}


	return $auxCorrecto;

}

