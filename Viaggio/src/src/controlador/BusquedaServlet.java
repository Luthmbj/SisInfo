import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import miprimeraaplicacionweb.modelo.BancoFacade;
import miprimeraaplicacionweb.modelo.datos.UsuarioVO;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;


public class BusquedaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

    /**
     * Default constructor. 
     */
    public BusquedaServlet() {
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
		
		
		String login = request.getParameter("usuario");
		String lugar = request.getParameter("lugar");
		String fInicio = request.getParameter("fInicio");
		String fFin = request.getParameter("fFin");
		int nHabitaciones = request.getParameter("nHabitaciones");
		int nPersonas = request.getParameter("nPersonas");

		//-----------------------------
		try{	
			WebFacade fachada = new WebFacade();
			fachada.accesoBusqueda(new BusquedaVO(login,lugar,fInicio,fFin,nPersonas),"insertar");
			LinkedList<ResultadoVO> resultados = new LinkedList<ResultadoVO>();
			resultados.add(new ResultadoVO("Hotel Maravilla",75.30,foto));
			resultados.add(new ResultadoVO("Hotel Aragón",70.00,foto));
			resultados.add(new ResultadoVO("Hotel España",50.46,foto));
			resultados.add(new ResultadoVO("Hotel aASDDSF",66.66,foto));
			response.setAttribute("resultados",resultados);
			response.sendRedirect("resultado.jsp");
		}catch (Exception e){
			e.printStackTrace(System.err);
		}
		
	}

}