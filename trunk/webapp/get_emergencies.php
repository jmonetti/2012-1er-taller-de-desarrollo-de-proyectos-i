<?php
    include_once('includes/EmergenciesDA.php');
    $dataAccess = new EmergenciesDA();	
	$cursor = $dataAccess->get_all();
	// iterate through the results
	if($cursor) {
		$markers = '';
		foreach ($cursor as $obj) {
			$emergency = '('.$obj['lat'].', '.$obj['lng'].')';
			if(isset($obj['number']))
				$emergency .= $obj['number'];
            if(isset($obj['address']))
			    $emergency .= ': ' . $obj['address'];
			?>
			<li lat="<?php echo $obj['lat']; ?>" lng="<?php echo $obj['lng'] ?>">
				<?php echo $emergency; ?>
			</li>
			<?php
		}
	}
?>
