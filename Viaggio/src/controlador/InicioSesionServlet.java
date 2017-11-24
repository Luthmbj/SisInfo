import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Viaggio.src.modelo.WebFacade;
import Viaggio.src.modelo.datos.UsuarioVO;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;


public class InicioSesionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public InicioSesionServlet() {
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
	 	
		//login
		boolean errores = false;
		HashMap tablaErrores = new HashMap();	
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
		
		
		//-----------------------------
		UsuarioVO usuario = new UsuarioVO(login,"", "", "", "");
		if (!errores){
			try{	
				WebFacade fachada = new WebFacade();
				usuario = fachada.accesoUsuario(usuario,"buscar");
				if (password==usuario.getPass()){
					HttpSession session = request.getSession();
					session.setAttribute("usuario",login);
					
					Cookie emailC = new Cookie("usuario",usuario.getEmail());
					Cookie nombreC = new Cookie("nombre",usuario.getNombre());
				
					response.addCookie(emailC);
					response.addCookie(nombreC);
					response.sendRedirect("index.html");
				}
				else{
					errorPassword ="La cuenta o la contraseña es incorrecta";
					tablaErrores.put("password", errorPassword);
					request.setAttribute("errores", tablaErrores);
					RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");	
					request.setAttribute("usuario",usuario);
					
					Cookie emailC = new Cookie("usuario",usuario.getEmail());
					request.addCookie(emailC);
					
					dispatcher.forward(request, response);
				}
			}catch (Exception e){
				e.printStackTrace(System.err);
			}
		}else{
			request.setAttribute("errores", tablaErrores);
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");	
			request.setAttribute("usuario",usuario);	
			
			Cookie emailC = new Cookie("usuario",login);
			request.addCookie(emailC);
			
			dispatcher.forward(request, response);
		}
		
	}

}