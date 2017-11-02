
import java.sql.Connection;
import java.sql.SQLException;

public class WebFacade {

	public void accesoUsuario(UsuarioVO usuario, String accion)throws SQLException {    
		Connection connection = null;
	    try{
		   connection = GestorDeConexionesBD.getConnection();
	       UsuarioDAO usuarioDAO = new UsuarioDAO();
		   switch (accion){
				case "crear":
					usuarioDAO.insertarUsuario(usuario,connection);
				case "modificar":
					usuarioDAO.modificarUsuario(usuario,connection);  
				case "buscar":
					usuarioDAO.encontrarDatosUsuario(usuario.getEmail(),connection);  
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
		}                
	} 
	
	public void accesoBusqueda(BusquedaVO busqueda, String accion)throws SQLException {    
		Connection connection = null;
	    try{
		   connection = GestorDeConexionesBD.getConnection();
	       BusquedaDAO busquedaDAO = new BusquedaDAO();
		   switch (accion){
				case "crear":
					busquedaDAO.insertarBusqueda(usuario,connection);
				case "buscar":
					busquedaDAO.encontrarDatosBusqueda(busqueda.getUsuario(),busqueda.getId(),connection);  
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
		}                
	} 
	
	
	public void accesoHotel(HotelVO hotel, String accion)throws SQLException {    
		Connection connection = null;
	    try{
		   connection = GestorDeConexionesBD.getConnection();
	       HotelDAO hotelDAO = new HotelDAO();
		   switch (accion){
				case "crear":
					hotelDAO.insertarHotel(hotel,connection);
				case "modificar":
					hotelDAO.modificarHotel(hotel,connection);  
				case "buscar":
					hotelDAO.encontrarDatosHotel(hotel.getUrl(),connection);  
				case "eliminar":
					hotelDAO.borrarHotel(hotel.getUrl(),connection);  
				default:
					System.out.println("Acción errónea");
		   }
	       connection.close();  
	    } catch (Exception e) {
	       e.printStackTrace(System.err);
	    } finally{
			connection.close();
		}                
	} 
	
	public static void main (String[] args) {    
	   	WebFacade fachada = new WebFacade();
		UsuarioVO usuario1 = new UsuarioVO("pepe@gmail.es","Pepe","Pérez","paca34","10/10/1940");
		try{
			fachada.accesoUsurio(usuario1,"crear");
	    }catch (Exception e){
			e.printStackTrace(System.err);
		}	
	}  
}
