
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface BusquedaDAO {
	
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
			preparedStatement.setString(5, busqueda.getNHabitaciones());
			preparedStatement.setString(6, busqueda.getNPersonas());
            
            /* Execute query. */                    
            int insertedRows = preparedStatement.executeUpdate();
                
            if (insertedRows != 1) {
                throw new SQLException( "Error en la inserción de búsqueda");
            }              
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }                
    } 
	
	public BusquedaVO encontrarDatosBusqueda (String usuario, int id, Connection connection){     
    	BusquedaVO BusquedaVO = null;
    	try{
            /* Create "preparedStatement". */
            String queryString = "SELECT finicio,	ffin,	lugar " +
            		" nhabitacion,	npersonas FROM Busqueda WHERE  usuario = ? AND id = ?";                    
            PreparedStatement preparedStatement = 
                connection.prepareStatement(queryString);
            
            /* Fill "preparedStatement". */    
            preparedStatement.setString(1, usuario);
			preparedStatement.setString(2, id);
                   
            /* Execute query. */                    
            ResultSet resultSet = preparedStatement.executeQuery();
               
            if (!resultSet.first()) {
                throw new SQLException( "Error: Búsqueda no encontrada");
            }
            
            /* Execute query. */                    
            String finicio = resultSet.getString(1);
            String ffin = resultSet.getString(2);
            String lugar = resultSet.getString(3);
            String nhabitaciones = resultSet.getString(4);
			String npersonas = resultSet.getString(5);
              
            BusquedaVO = new BusquedaVO (finicio,ffin,lugar,
									nhabitaciones, npersonas);
                
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    	return BusquedaVO;
    } 
	
    public void borrarBusqueda(String usuario, int id, Connection connection) {
		try {
			 /* Create "preparedStatement". */
			String queryString = "DELETE FROM Busqueda WHERE usuario = ? AND id = ?";                    
            PreparedStatement preparedStatement = 
                connection.prepareStatement(queryString);
			
			/* Fill "preparedStatement". */  
			preparedStatement.setString(1, usuario);
			preparedStatement.setString(2, id);
			
			/* Execute query. */                    
            int deletedRows = preparedStatement.executeQuery();
			if (deletedRows != 1) {
                throw new SQLException( "Error al eliminar búsqueda");
            }       
		} catch (Exception e) {
            e.printStackTrace(System.err);
		}
    }


}