<?php
class GeoCoding {

	public function get_address($lat, $long) {
		$latlong = $lat.','.$long;
		$url = 'http://maps.googleapis.com/maps/api/geocode/json?latlng='.$latlong.'&sensor=true';
		$response = $this->get_url_contents($url);
		return json_decode($response);
	}
	
	private function get_url_contents($url) {
		$crl = curl_init();
		$timeout = 5;
		curl_setopt ($crl, CURLOPT_URL,$url);
		curl_setopt ($crl, CURLOPT_RETURNTRANSFER, 1);
		curl_setopt ($crl, CURLOPT_CONNECTTIMEOUT, $timeout);
		$ret = curl_exec($crl);
		curl_close($crl);
		return $ret;
	}
}
?>