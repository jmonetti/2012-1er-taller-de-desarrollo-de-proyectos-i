<?php
class GeoCoding {

	public static function get_address($lat, $lng) {
		$latlong = $lat.','.$lng;
		$url = 'http://maps.googleapis.com/maps/api/geocode/json?latlng='.$latlong.'&sensor=true';
		$response = GeoCoding::get_url_contents($url);

		$json_response = json_decode($response, true);

		return GeoCoding::buildAddress($json_response);
	}
	
	private static function buildAddress($json_response) {
		$address = 'NOT FOUND';
		
		if($json_response['status'] == 'OK')
		{
			$address = '';
			// Tomar el primer resultado
			$result = $json_response['results'][0];
			foreach($result['address_components'] as $component)
			{
				$address = $component['long_name'] . ', ' . $address;
			}
		}		
		
		return $address;
	}

	private static function get_url_contents($url) {
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