
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;

public class WebFacade {

	public UsuarioVO accesoUsuario(UsuarioVO usuario, String accion)throws SQLException {    
		Connection connection = null;
		UsuarioVO usuarioVO = null;
	    try{
		   connection = GestorDeConexionesBD.getConnection();
	       UsuarioDAO usuarioDAO = new UsuarioDAO();
		   switch (accion){
				case "insertar":
					System.out.println("Insertar: " + usuario.getEmail());
					usuarioDAO.insertarUsuario(usuario,connection);
					break;
				case "modificar":
					System.out.println("Modificar: " + usuario.getEmail());
					usuarioDAO.modificarUsuario(usuario,connection);  
					break;
				case "buscar":
					System.out.println("Buscar: " + usuario.getEmail());
					usuarioVO = usuarioDAO.encontrarDatosUsuario(usuario.getEmail(),connection);  
					break;
				case "eliminar":
					System.out.println("Eliminar: " + usuario.getEmail());
					usuarioDAO.borrarUsuario(usuario.getEmail(),connection);
					break;
				default:
					System.out.println("Acción errónea");	
		   }
	       connection.close();  
	    } catch (Exception e) {
	       e.printStackTrace(System.err);
	    }
		return usuarioVO;               
	} 
	
	public LinkedList<BusquedaVO> accesoBusqueda(BusquedaVO busqueda, String accion)throws SQLException {    
		Connection connection = null;
		LinkedList<BusquedaVO> lista = null;
	    try{
		   connection = GestorDeConexionesBD.getConnection();
	       BusquedaDAO busquedaDAO = new BusquedaDAO();
		   switch (accion){
				case "insertar":
					System.out.println("Insertar: " + busqueda.getUsuario());
					busquedaDAO.insertarBusqueda(busqueda,connection);
					break;
				case "buscar":
					System.out.println("Buscar: " + busqueda.getUsuario());
					lista = busquedaDAO.encontrarDatosBusqueda(busqueda.getUsuario(),connection);  
					break;
				case "eliminar":
					System.out.println("Eliminar: " + busqueda.getUsuario());
					busquedaDAO.borrarBusqueda(busqueda.getUsuario(),connection);  
					break;
				default:
					System.out.println("Acción errónea");
		   }
	       connection.close();  
	    } catch (Exception e) {
	       e.printStackTrace(System.err);
	    } 
		return lista;
	} 
	
	
	public LinkedList<HotelVO> accesoHotel(HotelVO hotel, String accion)throws SQLException {    
		Connection connection = null;
		LinkedList<HotelVO> lista = new LinkedList<HotelVO>();
	    try{
		   connection = GestorDeConexionesBD.getConnection();
	       HotelDAO hotelDAO = new HotelDAO();
		   switch (accion){
				case "insertar":
					System.out.println("Insertar: " + hotel.getNombre());
					hotelDAO.insertarHotel(hotel,connection);
					break;
				case "modificar":
					System.out.println("Modificar: " + hotel.getNombre());
					hotelDAO.modificarHotel(hotel,connection);  
					break;
				case "eliminar":
					System.out.println("Eliminar: " + hotel.getNombre());
					hotelDAO.borrarHotel(hotel.getUrl(),connection);  
					break;
				case "buscar":
					System.out.println("Buscar: " + hotel.getNombre());
					lista = hotelDAO.encontrarTodosHotel(connection); 
					break;
				default:
					System.out.println("Acción errónea");
		   }
	       connection.close();  
	    } catch (Exception e) {
	       e.printStackTrace(System.err);
	    }
	    return lista;
	} 
	
	public static void main (String[] args) {    
	   	WebFacade fachada = new WebFacade();
		UsuarioVO usuario1 = new UsuarioVO("pepe@gmail.es","Pepe","Pérez","paca34","10/10/1940");
		System.out.println(usuario1.getEmail());
		System.out.println(usuario1.getNombre());
		System.out.println(usuario1.getApellidos());
		System.out.println(usuario1.getPass());
		System.out.println(usuario1.getFecha());
		HotelVO hotel1 = new HotelVO("www.kayak.es","Kayak");
		HotelVO hotel2 = new HotelVO("www.rastreator.es","Rastreator");
		
		BusquedaVO busqueda1 = new BusquedaVO("pepe@gmail.es", "Zaragoza", "10/10/2017", "11/10/2017", 1, 2);
		try{
			fachada.accesoUsuario(usuario1,"insertar");
			UsuarioVO usuario2 = fachada.accesoUsuario(usuario1,"buscar");
			System.out.println(usuario2.getNombre());
			
			fachada.accesoHotel(hotel1,"insertar");
			fachada.accesoHotel(hotel2,"insertar");
			LinkedList<HotelVO> hoteles = fachada.accesoHotel(hotel1,"buscar");
			for(HotelVO h:hoteles){
				System.out.println("Hotel: ");
				System.out.println(h.getNombre());
			}
			
			fachada.accesoBusqueda(busqueda1,"insertar");
			LinkedList<BusquedaVO> busquedas = fachada.accesoBusqueda(busqueda1,"buscar");
			for(BusquedaVO b:busquedas){
				System.out.println("Busqueda");
				System.out.println(b.getUsuario());
			}
	    }catch (Exception e){
			e.printStackTrace(System.err);
		}	
	}  
}
