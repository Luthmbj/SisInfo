
import java.sql.Connection;
import java.sql.SQLException;

public class WebFacade {

	public UsuarioVO accesoUsuario(UsuarioVO usuario, String accion)throws SQLException {    
		Connection connection = null;
		UsuarioVO usuarioVO = null;
	    try{
		   connection = GestorDeConexionesBD.getConnection();
	       UsuarioDAO usuarioDAO = new UsuarioDAO();
		   switch (accion){
				case "insertar":
					usuarioDAO.insertarUsuario(usuario,connection);
				case "modificar":
					usuarioDAO.modificarUsuario(usuario,connection);  
				case "buscar":
					usuarioVO = usuarioDAO.encontrarDatosUsuario(usuario.getEmail(),connection);  
				case "eliminar":
					usuarioDAO.borrarUsuario(usuario.getEmail(),connection); 
				default:
					System.out.println("Acción errónea");	
		   }
	       connection.close();  
	    } catch (Exception e) {
	       e.printStackTrace(System.err);
	    } finally{
			connection.close();
			return usuarioVO;
		}                
	} 
	
	public LinkedList<BusquedaVO> accesoBusqueda(BusquedaVO busqueda, String accion)throws SQLException {    
		Connection connection = null;
		LinkedList<BusquedaVO> lista = null;
	    try{
		   connection = GestorDeConexionesBD.getConnection();
	       BusquedaDAO busquedaDAO = new BusquedaDAO();
		   switch (accion){
				case "insertar":
					busquedaDAO.insertarBusqueda(busqueda,connection);
				case "buscar":
					lista = busquedaDAO.encontrarDatosBusqueda(busqueda.getUsuario(),busqueda.getId(),connection);  
				case "eliminar":
					busquedaDAO.borrarBusqueda(busqueda.getUsuario(),busqueda.getId(),connection);  
				default:
					System.out.println("Acción errónea");
		   }
	       connection.close();  
	    } catch (Exception e) {
	       e.printStackTrace(System.err);
	    } finally{
			connection.close();
			return lista;
		}                
	} 
	
	
	public LinkedList<HotelVO> accesoHotel(HotelVO hotel, String accion)throws SQLException {    
		Connection connection = null;
		//HotelVO hotelVO = null;
		LinkedList<HotelVO> lista = new LinkedList<HotelVO>();
	    try{
		   connection = GestorDeConexionesBD.getConnection();
	       HotelDAO hotelDAO = new HotelDAO();
		   switch (accion){
				case "insertar":
					hotelDAO.insertarHotel(hotel,connection);
				case "modificar":
					hotelDAO.modificarHotel(hotel,connection);  
				//case "buscar":
					//hotelVO = hotelDAO.encontrarDatosHotel(hotel.getUrl(),connection);  
				case "eliminar":
					hotelDAO.borrarHotel(hotel.getUrl(),connection);  
				case "buscar":
					lista = hotelDAO.encontrarTodosHotel(connection); 
				default:
					System.out.println("Acción errónea");
		   }
	       connection.close();  
	    } catch (Exception e) {
	       e.printStackTrace(System.err);
	    } finally{
			connection.close();
			//if(lista.isEmpty()){
				//return hotelVO;
			//}else{
				return lista;
			//}
		}                
	} 
	
	public static void main (String[] args) {    
	   	WebFacade fachada = new WebFacade();
		UsuarioVO usuario1 = new UsuarioVO("pepe@gmail.es","Pepe","Pérez","paca34","10/10/1940");
		try{
			fachada.accesoUsurio(usuario1,"insertar");
	    }catch (Exception e){
			e.printStackTrace(System.err);
		}	
	}  
}
