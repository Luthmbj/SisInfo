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


public class ModificarUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ModificarUsuarioServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	 	
		boolean errores = false;
		String login = request.getParameter("email");
		if (login != null){
			if (login.trim().equals(new String(""))){
				errores = true;
			}
		}else {
			errores = true;
		}
		//-----------------------------
		
		if (!errores){
			try{
				WebFacade fachada = new WebFacade();
				UsuarioVO usuario = fachada.accesoUsuario(new UsuarioVO(login,"","","",""),"buscar");
				RequestDispatcher dispatcher = request.getRequestDispatcher("modificarUsuario.jsp");
				request.setAttribute("usuarioVO", usuario);
				dispatcher.forward(request, response);		
			}catch (Exception e){
				e.printStackTrace(System.err);
				response.sendRedirect("errorInterno.html");
			}
		}else{
			response.sendRedirect("errorInterno.html");
		}		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}