<!DOCTYPE html>
<html lang="es">
	<head>
		<link href='http://fonts.googleapis.com/css?family=Droid+Sans' rel='stylesheet' type='text/css'>
		<meta charset="utf-8">
		<title>Pure CSS3 Menu</title>
		<link href="style.css" media="screen" rel="stylesheet" type="text/css" />
		
	</head>

<body>
	<div id='menuWrapper'>
<div class='menu'>
<ul>
<li><a href="URL del enlace">Home</a></li>
<li><a href="#">Curso</a>
<ul>
<li><a href="URL del enlace">1º</a></li>
<li><a href="URL del enlace">2º</a></li>
<li><a href="URL del enlace">3º</a></li>
<li><a href="URL del enlace">4º</a></li>
	</ul>

<li><a href="#">Asignaturas</a>
<ul>
<li><a href="URL del enlace">MMI</a></li>
<li><a href="URL del enlace">IS</a></li>
<li><a href="URL del enlace">ASOR</a></li>

</ul>
</li>



</ul>


<form action='/search' id='search' method='get' name='searchForm' style='display:inline;'> 
<input id='search-box' name='q' onblur='if (this.value == &quot;&quot;) this.value = &quot;Buscar...&quot;;' onfocus='if (this.value == &quot;Buscar...&quot;) this.value = &quot;&quot;;' size='28' type='text' value='Buscar...'/></form>

</div></div>
<div>
 <table border="1">
    <tr>
      <td>ASIGNATURA</td>
      <td>CURSO</td>
      <td>ITINERARIO</td>
    </tr>
    <tr>
      <td>ASOR</td>
      <td>4º</td>
      <td>TI</td>
    </tr>
    <tr>
      <td>IS</td>
      <td>2º</td>
      <td>TI</td>
    </tr>
</table>

</div>
</body>

</html>
