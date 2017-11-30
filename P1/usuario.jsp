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
<!-- PESTA�AS -->
<script>
function openCity(evt, cityName) {
    var i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    document.getElementById(cityName).style.display = "block";
    evt.currentTarget.className += " active";
}
</script>

</head>
<body>
	<%
	//el user esta en una cookie, o solo su email, o lo traigo de BBDD?
	UsuarioVO usuario = ;
	accesoUsuario(usuario,buscar);


	%>

<!-- Contenido centra -->
<div class="contUser">
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

<!-- Contenido central -->
<div class="contUser">

<div class="tab">
<button class="tablinks" onclick="openCity(event, 'Hoteles')">Hoteles</button>
<button class="tablinks" onclick="openCity(event, 'Vuelos')">Vuelos</button>
<button class="tablinks" onclick="openCity(event, 'Coches')">Coches</button>
<button class="tablinks" onclick="openCity(event, 'Datos')">Mis Datos</button>
</div>

<%
	LinkedList<BusquedaVO> lista;
	BusquedaVO busqueda;
	response.getAttribute("busqueda",busqueda);
	lista = accesoBusqueda(busqueda,buscar);

	String city;
	String ini;
	String fin;
	String per;
	String hab;
%>

<div id="Hoteles" class="tabcontent">
  <h3>Hoteles</h3>
  <% if (lista.size() == 0){ %>
	    <p>No tienes ninguna b�squeda guardada</p>
  <%}else{
	  for(int i = 0; i < lista.size(); i++) {

	  	city = lista.getLugar();
	  	ini = lista.getFInicio();
	  	fin = lista.getFFin();
	  	per = lista.getNPersonas();
	  	hab = lista.getNHabitaciones();
	%>

	  	<p>En <%=city%> del <%=ini%> a <%=fin%>, para <%=per%> persona/s en <%=hab%> habitaci�n/es</p>
	<br/><br/>

<% 		}
	}
	//hay que poner un enlace a resultados con la busqueda?
%>
</div>

<div id="Vuelos" class="tabcontent">
  <h3>Vuelos</h3>
  <p>No tienes ninguna b�squeda guardada</p>
</div>
<div id="Coches" class="tabcontent">
  <h3>Coches</h3>
  <p>No tienes ninguna b�squeda guardada</p>
</div>

<div id="Datos" class="tabcontent">
	<%
		//aqui ya deberia tener los datos de usuario
		String Nom = usuario.getNombre();
		String Ape = usuario.getApellidos();
		String Date = usuario.getFecha();
		String Mail = usuario.getEmail();
	%>
  <h3>Mis Datos</h3>
  <p>Nombre: <%=Nom%></p>
  <p>Apellidos: <%=Ape%></p>
  <p>Fecha de Nacimiento: <%=Date%></p>
  <p>Email: <%=Mail%></p>
  <a href="modificar.jsp">Modificar mis datos</a>
</div>


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

</body>
</html>
