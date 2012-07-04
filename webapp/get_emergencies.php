<?php
    include_once('includes/EmergenciesDA.php');

    $fields = array(
    	'date' => 'Fecha',
        'address' => 'Ubicación',
    	'info' => 'Información'
    );

    $dataAccess = new EmergenciesDA();	
	$cursor = $dataAccess->get_all();
    $row_nb = 0;
	if($cursor->count()) { ?>
        <tr>
            <?php foreach ($fields as $key => $value) {
                echo '<th>' . $value . '</th>';
            }?>
        </tr>
		<?php foreach ($cursor as $obj) {?>
			<tr <?php if ($row_nb++ % 2) echo 'class=even'?>>
                <?php foreach ($fields as $key => $value) {
                    if (!isset($obj[$key]))
                        $obj[$key] = '';
                    echo '<td>' . $obj[$key] . '</td>';
                }?>
			</tr>
		<?php 
        }
    } else {
        echo '<tr><td>No hay emergencias en este momento</td></tr>';
    }
?>

<script type="text/javascript">
    mapHandler.clearMarkers();
    <?php foreach ($cursor as $obj) {?>
	    mapHandler.addMarker({ "lat": <?php echo $obj['lat']?>, "lng": <?php echo $obj['lng']?> });
    <?php } ?>
</script>
