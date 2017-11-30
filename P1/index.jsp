<!DOCTYPE html>
<html lang="es">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
	<meta name="title" content="Viaggio" />
	<meta name="description" content="Comparador" />
	<meta name="author" content="Blah" />

    <meta charset="utf-8" />
	<title>Viaggio, comparador de viajes</title>
	<link rel="stylesheet" href="css/style.css">

<!-- RESPONSIVE -->
<script src="css/jquery.min.js"></script>
<script>
$(function() {
    var pull = $('#pull');
    menu = $('nav ul');
    menuHeight = menu.height();

    $(pull).on('click', function(e) {
        e.preventDefault();
        menu.slideToggle();
    });
});

$(window).resize(function(){
    var w = $(window).width();
    if(w > 320 && menu.is(':hidden')) {
        menu.removeAttr('style');
    }
});
</script>

<!-- C�DIGO PARA INCREMENTAR/DECREMENTAR HABITACIONES/PERSONAS -->
<script>
	var contadorP=1;
	var contadorH=1;
	//Contador Personas
	function incrementarP() {
		if (contadorP<12){
			//if(contadorP%4==0){
			if((contadorP/4<=contadorH) && contadorP%4==0){
				contadorH++;
				document.getElementById("resultadoH").value=contadorH;
			}
			contadorP++;
			document.getElementById("resultadoP").value=contadorP;
		}
		else{
			contadorP=12;
			alert("Max. personas 12");
		}
	}

	function decrementarP() {
		if(contadorP!=1){
			if(contadorP==contadorH){
				contadorP--;
				document.getElementById("resultadoP").value=contadorP;
				contadorH--;
				document.getElementById("resultadoH").value=contadorH;
			}
			else{
				contadorP--;
				document.getElementById("resultadoP").value=contadorP;
				if(contadorP%4==0){
					contadorH--;
					document.getElementById("resultadoH").value=contadorH;
				}
			}
		}
	}
	//Contador Habitaciones
	function incrementarH() {
		if (contadorH<12){
			if(contadorH<contadorP){
				contadorH++;
				document.getElementById("resultadoH").value=contadorH;
			}
			else{
				contadorH++;
				document.getElementById("resultadoH").value=contadorH;
				if(contadorP<=12){
					contadorP++;
					document.getElementById("resultadoP").value=contadorP;
				}
			}
		}
		else{
			contadorH=12;
			if(contadorP==13) contadorP--;
			alert("Max. habitaciones 12");
		}

	}

	function decrementarH() {
		if(contadorH!=1){
			if(contadorH==contadorP){
				contadorH--;
				document.getElementById("resultadoH").value=contadorH;
				contadorP--;
				document.getElementById("resultadoP").value=contadorP;
			}
			else {
				contadorH--;
				document.getElementById("resultadoH").value=contadorH;
				if (contadorP/4<contadorH){
					contadorP--;
					document.getElementById("resultadoP").value=contadorP;
				}
			}
		}


	}
</script>

<!-- C�DIGO PARA ELEGIR FECHA EN EL FORMULARIO-->
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
    <script>
    $(function() {
        $("#fechal").datepicker({
				minDate: 0,
				onSelect: function(dateStr) {
           				var min = $(this).datepicker('getDate'); // Selected date or today if none
						var max = new Date(min.getTime());
						max.setMonth(max.getMonth() + 3); // a�adir 3 meses
            			$('#fechas').datepicker('option', {minDate: min, maxDate: max});
        		},
                dateFormat: "dd/mm/yy",
                dayNames: [ "Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado" ],
                dayNamesMin: [ "Do", "Lu", "Ma", "Mi", "Ju", "Vi", "Sa" ],
                firstDay: 1,
                gotoCurrent: true,
                monthNames: [ "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Deciembre" ]
            }
        );

		 $("#fechas").datepicker({
				//if(min == null){minDate: 1,}
				//else {minDate: new Date(fechal.getYear(),fechal.getMonth(),fechal.getDay()),}
				minDate: 1,
				maxDate: new Date(),
      		    onSelect: function(dateStr) {
          		  var max = $(this).datepicker('getDate'); // Selected date or null if none
          		   $('#fechal').datepicker('option', {maxDate: max});
       			},
                dateFormat: "dd/mm/yy",
                dayNames: [ "Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado" ],
                dayNamesMin: [ "Do", "Lu", "Ma", "Mi", "Ju", "Vi", "Sa" ],
                firstDay: 1,
                gotoCurrent: true,
                monthNames: [ "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Deciembre" ]
            }
        );

    });
	</script>




</head>



<body>
<!-- Men� cabecera -->
<header id="main-header">

<a href="index.jsp"><img alt="Home" id="logo-header" src="img/logo.png"></a>
<nav class="clearfix">
	<ul class="clearfix">
		<li><a href="index.jsp">Hotel</a></li>
		<li><a href="#">Vuelo</a></li>
		<li><a href="#">Coche</a></li>
        <li><a href="login.jsp">  Mi cuenta</a></li>
	</ul>
<a href="#" id="pull"></a>
 </nav>

</header>


<section id="main-content">
<div class="banform">
</br>


<h1>Todos los hoteles</h1>
<form>
<div class="reaj1">
<center><input type="text" name="Lugar" placeholder="Lugar" id="Lugar" size="20" value=""  required />
<input type="text" name="fechal" placeholder="Fecha de llegada" id="fechal" size="20" value="" required/>
<input type="text" name="fechas" placeholder="Fecha de salida" id="fechas" size="20" value="" required />
</center>
<!-- <br/><br/> -->
</div>

<center>
<div class="reaj3">
<div class="colum2 r1">
<label title="nper" style="color:#FFFFFF; font-size:15px"><!--&nbsp;&nbsp;&nbsp;-->N� de personas</label>
<input style="color:#ffffff; background-color:#00a5d3; border-radius:0px;border: 2px solid #007096;" type="button" onClick="incrementarP()" value="+"size="1">
<label>
<input  name="resultadoP" id="resultadoP" type="text" value="1" onClick="contadorP = this.value"
onchange="contador = this.value" size="1">
</label>
<input style="color:#ffffff; background-color:#00a5d3; border-radius:0px;border: 2px solid #007096;" type="button" onClick="decrementarP()" value="-" size="1">
</div>

<div class="colum2 r1">
<label title="nhab" style="color:#FFFFFF; font-size:15px">&nbsp;&nbsp;&nbsp;&nbsp;N� de habitaciones</label>
<input style="color:#ffffff; background-color:#00a5d3; border-radius:0px;border: 2px solid #007096;" type="button" onClick="incrementarH()" value="+"size="1">
<label>
<input name="resultadoH" id="resultadoH" type="text" value="1" onClick="contadorH = this.value"
onchange="contadorH = this.value" size="1">
</label>
<input style="color:#ffffff; background-color:#00a5d3; border-radius:0px;border: 2px solid #007096;" type="button" id="menos" onClick="decrementarH()" value="-" size="1">
</div>
</div>
</center>

<center>
<div class="reaj2">
<button class="boton bIn" name="Buscar">Buscar</button>
</div>
</center>
</form>
</center>
</div>
<article>
<header>
<h2>Destinos m�s populares</h2>
</header>

<div class='destinos'>

<!--<div style=' float: left;text-align: left;'>-->
<div class=" colum column-left">
<a href="resultado.jsp">
<img alt="Barcelona" src="img/bcn.png"></a>
</br>
<p>Barcelona</p>
</div>

<!--<div style=' float: left;  text-align: left;'>-->
<div class="colum column-center">
<a href="resultado.jsp">
<img alt="Madrid" src="img/madrid.png"></a>
</br>
<p>Madrid</p>
</div>

<!--<div style='float: right;text-align: left;'>-->
<div class="colum column-right">
<a href="resultado.jsp">
<img alt="Londres" src="img/londres.png"></a>
</br>
<p>Londres</p>
</div>

</div>

</article> <!-- /article -->

	</section> <!-- / #main-content -->

 <!-- PIE DE P�GINA -->
<footer id="main-footer">

<div id='footer2'  class="colum2 column-left">
  <p align="left"><strong>Viaggio</strong>
  </br>Blog
  </br>Mi cuenta
  </br>Trabaja con nosotros
  </p>
  </br>
</div>

<div id='footer3'  class="colum2 column-center">
  <p align="left"><strong>Contacto</strong>
  </br>FAQS
  </br>Propietarios de Hoteles
  </br>Prensa
  </p>
  </br>
</div>

<div id='footer4'  class="colum2 column-right">
  <p align="left"><strong>Seguridad</strong>
  </br>Privacidad
  </br>T�rminos y condiciones
  </br>Aviso Legal
  <p align="left">&copy; 2017 Viaggio</p>
  </br>
  </p>
</div>

</div>

</body>
</html>
