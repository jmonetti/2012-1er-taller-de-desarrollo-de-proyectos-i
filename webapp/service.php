<?php
include_once('includes/EmergenciesDA.php');

if(array_key_exists("data", $_REQUEST)) {
	$EmergenciesDA = new EmergenciesDA();
	
	$json = $_REQUEST["data"];
	
	$emergency = json_decode ($json, true);
	$EmergenciesDA->add($emergency);
}
?>