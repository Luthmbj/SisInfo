<!DOCTYPE html>
<html lang="es">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
	<meta name="title" content="Viaggio" />
	<meta name="description" content="Comparador" />
	<meta name="author" content="Blah" />
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

<!-- Comprobar si las claves son iguales -->
<script>
function comprobarClave(){
    clave1 = document.f1.clave1.value
    clave2 = document.f1.clave2.value

	if (clave1==""){
		alert("Debe rellenar los dos campos contraseña...\n")
	   return false;
	}
    else if ((clave1 == clave2)) {
		alert("Todo correcto. Falta mandar el formulario al servidor...\n")
		return true;
	}
	//Aquí en vez del alert sería mandar los datos al servidor
    else{
       alert("Las dos claves son distintas...\n")
	   return false;
	}
}
</script>


<!-- comrobar si el email del f1 tiene formato correcto -->
<script>
function comprobarEmail(){
	e = document.f1.emailreg.value
	var emailRegex = /^[-\w.%+]{1,64}@(?:[A-Z0-9-]{1,63}\.){1,125}[A-Z]{2,63}$/i;
	if (emailRegex.test(e)) {
      comprobarClave();
    } else {
      alert("Email incorrecto...\n")
	   return false;
    }
}
</script>

<!-- comrobar si el email del f2 tiene formato correcto -->
<script>
function comprobarEmail2(){
	e2 =document.f2.emailLog.value
	var emailRegex = /^[-\w.%+]{1,64}@(?:[A-Z0-9-]{1,63}\.){1,125}[A-Z]{2,63}$/i;
	if (!emailRegex.test(e2)) {
       alert("Email con formato incorrecto...\n")
	   return false;
    }
	return true;
}
</script>



<!-- Función que comprueba si una fecha está en formato dd/mm/aaaa y es anterior a la actual -->
<script>
function validaFechaDDMMAAAA(){
	var fecha = document.f1.fN.value;
	var dtCh= "/";
	var minYear=1900;
	var hoy = new Date();
	var maxDay=hoy.getDate();
	var maxMonth=hoy.getMonth()+1;
	var maxYear=hoy.getFullYear();

	function isInteger(s){
		var i;
		for (i = 0; i < s.length; i++){
			var c = s.charAt(i);
			if (((c < "0") || (c > "9"))) return false;
		}
		return true;
	}
	function stripCharsInBag(s, bag){
		var i;
		var returnString = "";
		for (i = 0; i < s.length; i++){
			var c = s.charAt(i);
			if (bag.indexOf(c) == -1) returnString += c;
		}
		return returnString;
	}
	function daysInFebruary (year){
		return (((year % 4 == 0) && ( (!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28 );
	}
	function DaysArray(n) {
		for (var i = 1; i <= n; i++) {
			this[i] = 31
			if (i==4 || i==6 || i==9 || i==11) {this[i] = 30}
			if (i==2) {this[i] = 29}
		}
		return this
	}
	function isDate(dtStr){
		var daysInMonth = DaysArray(12)
		var pos1=dtStr.indexOf(dtCh)
		var pos2=dtStr.indexOf(dtCh,pos1+1)
		var strDay=dtStr.substring(0,pos1)
		var strMonth=dtStr.substring(pos1+1,pos2)
		var strYear=dtStr.substring(pos2+1)
		strYr=strYear
		if (strDay.charAt(0)=="0" && strDay.length>1) strDay=strDay.substring(1)
		if (strMonth.charAt(0)=="0" && strMonth.length>1) strMonth=strMonth.substring(1)
		for (var i = 1; i <= 3; i++) {
			if (strYr.charAt(0)=="0" && strYr.length>1) strYr=strYr.substring(1)
		}
		month=parseInt(strMonth)
		day=parseInt(strDay)
		year=parseInt(strYr)
		if (pos1==-1 || pos2==-1){
			return false
		}
		if (strMonth.length<1 || month<1 || month>12){
			return false
		}
		if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
			return false
		}
		if (strYear.length != 4 || year==0 || year<minYear || year>maxYear){
			return false
		}
		if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
			return false
		}
		if(((year==maxYear)&& (month>maxMonth)) ||((year==maxYear)&& (month==maxMonth) && (day>=maxDay))){
			return false;
		}

		return true;
	}

	if(isDate(fecha)){
		comprobarEmail();//comprobarClave();
		/*return true;*/
	}
	else{
		alert("La fecha debe ir en formato dd/mm/aaaa y ser anterior a la actual...\n")
		return false;
	}
}
</script>


</head>


<body>
<!-- Menú cabecera -->
<header id="main-header">

<a href="index.html"><img alt="Home" id="logo-header" src="img/logo.png"></a>
<nav class="clearfix">
	<ul class="clearfix">
		<li><a href="index.html">Hotel</a></li>
		<li><a href="#">Vuelo</a></li>
		<li><a href="#">Coche</a></li>
        <li><a href="login.html">  Mi cuenta</a></li>
	</ul>
<a href="#" id="pull"></a>
 </nav>

</header>

<!-- Formularios -->
<div class="contLog" id="centro">

<div class="formReg" id="registro">
<h3> ¿Eres nuevo?¡Regístrate! </h3>
<form class="formLog" name="f1">
<!-- Campos a 2 columnas -->

<div class="colsFormL">
				<label for="nombre">Nombre:</label>
				<br>
				<input id="nombre" name="Nombre" type="text" placeholder="Nombre" required />
                <br>
                <label for="date">Fecha de nacimiento:</label>
        		<br>
				<input name="fN" id="date" type="text" placeholder="dd/mm/aaaa" required>
      			<br>
                <label for="contra">Contraseña: </label>
				<br>
				<input id="clave" type="password" name="clave1" required>
				<br><br>
</div>
<div class="colsFormR">
     			<label for="apel">Apellidos: </label>
      			<br>
				<input id="apel" type="text" placeholder="Apellidos"  required>
                <br>
                <label for="email">Correo electrónico: </label>
				<br>
				<input name="emailreg" id="email" type="text" placeholder="Email" required>
      			<br>
                <label for="repcon">Repetir contraseña: </label>
				<br>
				<input id="repcon" type="password" name="clave2" required>
                <br><br>
</div>

<div>
<button class="boton bLog" name="Registrarse" onClick="validaFechaDDMMAAAA()">Registrarse</button>
<br>

</div>
			</form>
		</div>

		<%
			String errorCorreo="";
			String errorPass="";
			Map<String, String> errors =(Map<String, String>) request.getAttribute("errores");
			if (errors != null) {
				String errorHeader = "<font color=\"red\" font size=1><b>";
				String errorFooter = "</b></font>";
				if (errors.containsKey("login")) {
					errorCorreo= errorHeader + errors.get("login") + errorFooter;
				}
				if (errors.containsKey("password")) {
					errorPass= errorHeader + errors.get("password") + errorFooter;
				}
			}
			Cookie[] cookies = request.getCookies();

			String userId = null;
			for(Cookie cookie : cookies){
				if("usuario".equals(cookie.getName())){
					userId = cookie.getValue();
				}
			}
		%>


<div id="inicio" class="formReg"> <!-- antes tb  style='float:left;width:185px;height:230px;padding:10px;margin:0 0 0 80px;background-color:lightblue;' -->
	<h3>  Acceder a mi cuenta </h3>
	<form action="InicioSesionServlet" method="post">
		<div class="colsFormL">
			<%if(userId==null){
				%>
				<label for="user">Usuario:</label><input id="user" type="text" name="email"/> <%=errorCorreo%>
				<%
			}else{
				%>
				<label for="user">Usuario:</label><input id="user" type="text" name="email" value="<%=userId %>"/>
				<%
			}
			%>
			<br>
			<input name="emailLog" id="e" type="text" placeholder="email" required>
				<br><br>
		</div>

		<div class="colsFormR">
			<%if(userId==null){
				%>
				<label for="pass">Contraseña:</label><input id="pass" type="password" /> <%=errorPass%>
				<%
			}else{
				%>
				<label for="pass">Contraseña:</label><input id="pass" type="password" value="<%=""%>" />
				<%
			}
			%>
			<br>
			<input name="pass" type="password" required>
			<br><br>
		</div>
			<%=errorUsuario %>
		<div>
			<button class="boton bLog" name="submit" onClick="comprobarEmail2()">Iniciar sesión</button>
		</div>
	</form>
</div>
</div>

 <!-- PIE DE PÁGINA -->
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
  </br>Términos y condiciones
  </br>Aviso Legal
  <p align="left">&copy; 2017 Viaggio</p>
  </br>
  </p>
</div>

</div>
</body>
</html>
