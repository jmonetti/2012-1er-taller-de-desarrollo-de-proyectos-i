<?php
include_once('DBConnection.php');

class EmergenciesDA {
	private $db;
	private $emergencies;

	public function EmergenciesDA() {
		$this->emergencies = DBConnection::emergencies();
	}
	
	/**
	 * Adds a new emergency
	 * @param unknown_type $emergency
	 */
	public function add($emergency) {
		$this->emergencies->insert($emergency);
	}
	
	/**
	 * Deletes all emergencies from the collection
	 */
	public function delete_all() {
		$this->emergencies->drop();
	}
	
	/**
	 * Returns all emergencies
	 */
	public function get_all() {
		return $this->emergencies->find();
	}
	
	public function find_by_id($id) {
		$criteria = array("identifier" => $id);
		$cursor = $this->emergencies->find($criteria);

		if($cursor->count())
			return $cursor->getNext();

		return null;
	}
}