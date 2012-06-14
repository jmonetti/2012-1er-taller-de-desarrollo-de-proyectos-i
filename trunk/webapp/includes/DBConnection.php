<?php
include_once 'Configuration.php';

class DBConnection {
	private static $db;
	
	private static function connect() {
		if(Configuration::$ConnectionString != '')
			$mongo = new Mongo(Configuration::$ConnectionString);
		else
			$mongo = new Mongo();

		DBConnection::$db = $mongo->urban_security;
	}
	
	private static function db() {
		if(DBConnection::$db == null){
			DBConnection::connect();
		}
		return DBConnection::$db;
	}
	
	/**
	 * Returns the emergencies collection
	 */
	public static function emergencies() {
		return DBConnection::db()->emergencies;
	}
}

?>