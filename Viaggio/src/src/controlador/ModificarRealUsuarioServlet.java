package miprimeraaplicacionweb.controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import miprimeraaplicacionweb.modelo.BancoFacade;
import miprimeraaplicacionweb.modelo.datos.UsuarioVO;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;

/**
 * Servlet implementation class CrearUsuarioServlet
 */
public class ModificarRealUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ModificarRealUsuarioServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	 	
		boolean errores = false;
		HashMap tablaErrores = new HashMap();

		//Login
		String errorLogin = "";
		String login = request.getParameter("email");
		if (login != null){
			if (login.trim().equals(new String(""))){
				errorLogin ="El login es un campo obligario";
				tablaErrores.put("login", errorLogin);
				errores = true;
			}
		}else {
			errorLogin ="El login es un campo obligario";
			tablaErrores.put("login", errorLogin);
			errores = true;
		}
		//Nombre
		String errorNombre = "";
		String nombre = request.getParameter("nombre");
		if (nombre != null){
			if (nombre.trim().equals(new String(""))){
				errorNombre ="El nombre es un campo obligario";
				tablaErrores.put("nombre", errorNombre);
				errores = true;
			}
		}else {
			errorNombre ="El nombre es un campo obligario";
			tablaErrores.put("nombre", errorNombre);
			errores = true;
		}
		//Apellidos
		String errorApellidos = "";
		String apellidos = request.getParameter("apellidos");
		if (apellidos != null){
			if (apellidos.trim().equals(new String(""))){
				errorApellidos ="Apellidos es un campo obligario";
				tablaErrores.put("apellidos", errorApellidos);
				errores = true;
			}
		}else {
			errorApellidos ="Apellidos es un campo obligario";
			tablaErrores.put("apellidos", errorApellidos);
			errores = true;
		}
		
		//Contraseña
		String errorPassword = "";
		String password = request.getParameter("password");
		if (password != null){
			if (password.trim().equals(new String(""))){
				errorPassword ="Contraseña es un campo obligario";
				tablaErrores.put("password", errorPassword);
				errores = true;
			}
		}else {
			errorPassword ="Contraseña es un campo obligario";
			tablaErrores.put("password", errorPassword);
			errores = true;
		}
		
		//Contraseña 2
		String errorPasswordRe = "";
		String passwordRe = request.getParameter("passwordRe");
		if (passwordRe != null){
			if (passwordRe.trim().equals(new String(""))){
				errorPassword ="Repita la contraseña";
				tablaErrores.put("passwordRe", errorPasswordRe);
				errores = true;
			}
			if (password != passwordRe){
				errorPassword ="Las contraseñas deben coincidir";
				tablaErrores.put("passwordRe", errorPasswordRe);
				errores = true;
			}
		}else {
			errorPasswordRe ="Repita la contraseña";
			tablaErrores.put("passwordRe", errorPasswordRe);
			errores = true;
		}
		
		//Fecha de nacimiento
		String errorFecha = "";
		String fecha = request.getParameter("fecha");
		if (fecha != null){
			if (fecha.trim().equals(new String(""))){
				errorFecha ="Fecha es un campo obligario";
				tablaErrores.put("fecha", errorFecha);
				errores = true;
			}
		}else {
			errorFecha="Fecha es un campo obligario";
			tablaErrores.put("fecha", errorFecha);
			errores = true;
		}
		
		UsuarioVO usuario = new UsuarioVO(login, nombre, apellidos, password, fecha);
		if (login!=null){
			try{
				WebFacade fachada = new WebFacade();
				fachada.accesoUsuario(usuario,"modificar");
				Cookie emailC = new Cookie("usuario",usuario.getEmail());
					Cookie nombreC = new Cookie("nombre",usuario.getNombre());
					Cookie apellidosC = new Cookie("apellidos",usuario.getApellidos());
					Cookie fechaC = new Cookie("fecha",usuario.getFecha());
				
					response.addCookie(emailC);
					response.addCookie(nombreC);
					response.addCookie(apellidosC);
					response.addCookie(fechaC);
				response.sendRedirect("usuario.jsp");
			}catch (Exception e){
				e.printStackTrace(System.err);				
				response.sendRedirect("errorInterno.html");
			}
		}else{
			request.setAttribute("errores", tablaErrores);
			RequestDispatcher dispatcher = request.getRequestDispatcher("modificarUsuario.jsp");
			request.setAttribute("usuarioVO", usuario);	
			dispatcher.forward(request, response);
		}
	}

}