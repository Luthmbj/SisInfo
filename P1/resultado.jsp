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

<div class="contUser">

<div class="cabRes">
<h2>Resultados </h2>

<%
	String city = (String)request.getParameter("Lugar");
	String ini = (String)request.getParameter("fechal");
	String fin = (String)request.getParameter("fechas");
	String per = (String)request.getParameter("resultadoP");
	String hab = (String)request.getParameter("resultadoH");
	//siempre se muestra aunque no exista la ciudad
	%>

	<p>En <%=city%> del <%=ini%> a <%=fin%>, para <%=per%> persona/s en <%=hab%> habitaci�n/es</p>

</div>
<%
	LinkedList<ResultadoVO> resultados;
	response.getAttribute("resultados",resultados);
%>
<% for(int i = 0; i < resultados.size(); i++) { %>
	//ATENCION
	//getFoto devuelve un objeto tipo Image, pero aqui necesitamos la ruta
	<div class="contRes">
	<div class="cImg"> <img src="<%=resultados.get(i).getFoto()%>"> </div>
		<div class="cDatos">
			<%=resultados.get(i).getNombre()%>
			<br />
			<%=resultados.get(i).getPrecio()%>
			<br />
			<a href="#">Ver caracter�sticas</a>
		</div>
	</div>

<% } %>

<br /><br />
<h2>No hay mas resultados en esta búsqueda</h2>

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
