<?php
include_once('includes/EmergenciesDA.php');
include_once('includes/GeoCoding.php');

if(array_key_exists("data", $_REQUEST)) {
	$EmergenciesDA = new EmergenciesDA();
	
	$json = $_REQUEST["data"];
	$emergency = json_decode ($json, true);
    if ( is_numeric($emergency['lat']) && is_numeric($emergency['lng'])) {
	    // Obtener dirección
	    $locationAddress = GeoCoding::get_address($emergency['lat'], $emergency['lng']);
	    $emergency['address'] = $locationAddress; 

	    $EmergenciesDA->add($emergency);

	    echo "OK";
    }
    else
    {
    	echo "Error";    	
    }
}
?>
