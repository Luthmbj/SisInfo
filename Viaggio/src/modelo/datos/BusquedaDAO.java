
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class BusquedaDAO {
	
	public void insertarBusqueda (BusquedaVO busqueda, Connection connection) {               
        try{
            /* Create "preparedStatement". */
            String queryString = "INSERT INTO Busqueda " +
                "(usuario, finicio, ffin, lugar, nhabitaciones, npersonas) " +
            		"VALUES (?,?,?,?,?,?)";
            
            PreparedStatement preparedStatement = 
                connection.prepareStatement(queryString);
            
            /* Fill "preparedStatement". */    
            preparedStatement.setString(1, busqueda.getUsuario());
            preparedStatement.setString(2, busqueda.getFInicio());
            preparedStatement.setString(3, busqueda.getFFin());
            preparedStatement.setString(4, busqueda.getLugar());
			preparedStatement.setString(5, Integer.toString(busqueda.getNHabitaciones()));
			preparedStatement.setString(6, Integer.toString(busqueda.getNPersonas()));
            
            /* Execute query. */                    
            int insertedRows = preparedStatement.executeUpdate();
                
            if (insertedRows != 1) {
                throw new SQLException( "Error en la inserción de búsqueda");
            }              
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }                
    } 
	
	public LinkedList<BusquedaVO> encontrarDatosBusqueda (String usuario, Connection connection){     
		LinkedList<BusquedaVO> lista = new LinkedList<BusquedaVO>();
    	try{
            /* Create "preparedStatement". */
            String queryString = "SELECT finicio,	ffin,	lugar, " +
            		" nhabitaciones,	npersonas FROM Busqueda WHERE  usuario = ? ORDER BY id DESC LIMIT 10";    
            
             PreparedStatement preparedStatement = 
                connection.prepareStatement(queryString);
            
            /* Fill "preparedStatement". */    
            preparedStatement.setString(1, usuario);
                   
            /* Execute query. */                    
            ResultSet resultSet = preparedStatement.executeQuery();
               
            if (!resultSet.first()) {
                throw new SQLException( "Error: Búsqueda no encontrada");
            }
            
            
			do{
				String finicio = resultSet.getString(1);
				String ffin = resultSet.getString(2);
				String lugar = resultSet.getString(3);
				int nhabitaciones = resultSet.getInt(4);
				int npersonas = resultSet.getInt(5);
				
				lista.add(new BusquedaVO(usuario,lugar,finicio,ffin,nhabitaciones, npersonas));
			}while(resultSet.next());
			

                
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    	return lista;
    } 
	
    public void borrarBusqueda(String usuario, Connection connection) {
		try {
			 /* Create "preparedStatement". */
			String queryString = "DELETE FROM Busqueda WHERE usuario = ?";                    
            PreparedStatement preparedStatement = 
                connection.prepareStatement(queryString);
			
			/* Fill "preparedStatement". */  
			preparedStatement.setString(1, usuario);
			
			/* Execute query. */                    
            preparedStatement.executeUpdate();
			   
		} catch (Exception e) {
            e.printStackTrace(System.err);
		}
    }


}