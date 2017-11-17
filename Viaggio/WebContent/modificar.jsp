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
    clave1 = document.datos.clave1.value
    clave2 = document.datos.clave2.value

	if (clave1==""){
		alert("Debe rellenar los dos campos contrase�a...\n")
	   return false;
	}
    else if ((clave1 == clave2)) {
		alert("Todo correcto. Falta mandar el formulario al servidor...\n")
		return true;
	}
	//Aqu� en vez del alert ser�a mandar los datos al servidor
    else{
       alert("Las dos claves son distintas...\n")
	   return false;
	}
}
</script>


<!-- comrobar si el email del f1 tiene formato correcto -->
<script>
function comprobarEmail(){
	e = document.datos.emailreg.value
	var emailRegex = /^[-\w.%+]{1,64}@(?:[A-Z0-9-]{1,63}\.){1,125}[A-Z]{2,63}$/i;
	if (emailRegex.test(e)) {
      comprobarClave();
    } else {
      alert("Email incorrecto...\n")
	   return false;
    }
}
</script>

<!-- Funci�n que comprueba si una fecha est� en formato dd/mm/aaaa y es anterior a la actual -->
<script>
function validaFechaDDMMAAAA(){
	var fecha = document.datos.fN.value;
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
<!-- Men� cabecera -->
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

<div class="contUser">

<div class="cabRes">
<h2>Mis Datos </h2>
<p>Puede editar todos o alguno de los datos</p>
</div>

<%
	String errorNombre="";
	String errorApel="";
	String errorFecha="";
	String errorMail="";
	String errorPass1="";
	String errorPass2="";
	Map<String, String> errors =(Map<String, String>) request.getAttribute("errors");
	if (errors != null) {
		String errorHeader = "<font color=\"red\" font size=1><b>";
		String errorFooter = "</b></font>";
		if (errors.containsKey("errorNombre")) {
			errorNombre= errorHeader + errors.get("errorNombre") + errorFooter;
		}
		if (errors.containsKey("errorApel")) {
			errorApel= errorHeader + errors.get("errorApel") + errorFooter;
		}
		if (errors.containsKey("errorFecha")) {
			errorFecha= errorHeader + errors.get("errorFecha") + errorFooter;
		}
		if (errors.containsKey("errorMail")) {
			errorMail= errorHeader + errors.get("errorMail") + errorFooter;
		}
		if (errors.containsKey("errorPass1")) {
			errorPass1= errorHeader + errors.get("errorPass1") + errorFooter;
		}
		if (errors.containsKey("errorPass2")) {
			errorPass2= errorHeader + errors.get("errorPass2") + errorFooter;
		}
	}
	Cookie[] cookies = request.getCookies();

	String userId = null;
	for(Cookie cookie : cookies){
		if("email".equals(cookie.getName())){	//como se llaman las cosas que he de coger de la cookie?
			userId = cookie.getValue();
		}
	}
%>

<form style="text-align:left; padding-left:15%;" name="datos" action="">
	<%if(userId==null){
		%>
		<label for="nombre">Nombre: </label><input id="nombre" name="Nombre" type="text" placeholder="Nombre" required><%=errorNombre%>
		<br><br>
		<label for="apel">Apellidos: </label><input id="apel" type="text" placeholder="Apellidos"  required> <%=errorApel%>
		<br><br>
		<label for="date">Fecha de nacimiento: </label> <input name="fN" id="date" type="text" placeholder="dd/mm/aaaa" required> <%=errorFecha%>
		<br><br>
		<label for="email">Correo electr�nico: </label><input name="emailreg" id="email" type="text" placeholder="Email" required> <%=errorMail%>
		<br><br>
		<label for="contra">Contrase�a: </label><input id="clave" type="password" name="clave1" required> <%=errorPass1%>
		<br><br>
		<label for="repcon">Repetir contrase�a: </label><input id="repcon" type="password" name="clave2" required> <%=errorPass2%>
		<%
	}else{
		%>
		<label for="nombre">Nombre: </label><input id="nombre" name="Nombre" type="text" placeholder="Nombre" required value="<%=userId %>">
		<br><br>
		<label for="apel">Apellidos: </label><input id="apel" type="text" placeholder="Apellidos"  required value="<%=userId%>">
		<br><br>
		<label for="date">Fecha de nacimiento: </label> <input name="fN" id="date" type="text" placeholder="dd/mm/aaaa" required value="<%=userId%>">
		<br><br>
		<label for="email">Correo electr�nico: </label><input name="emailreg" id="email" type="text" placeholder="Email" required value="<%=userId%>">
		<br><br>
		<label for="contra">Contrase�a: </label><input id="clave" type="password" name="clave1" required value="<%=userId%>">
		<br><br>
		<label for="repcon">Repetir contrase�a: </label><input id="repcon" type="password" name="clave2" required value="<%=userId%>">
		<%
	}
	%>
	<br><br>



<button class="boton bLog" style=" margin-right:5%; width:auto; background-color:#FB751C; color:#333" name="Guardar">Cancelar</button>
<button class="boton bLog" style=" width:auto; background-color:#9FEA15; color:#333" name="Guardar" onClick="validaFechaDDMMAAAA()">Guardar datos</button>


</form>






</div>


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


</div>
</body>
</html>
