

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface HotelDAO {
	
	public void insertarHotel (HotelVO hotel, Connection connection) {               
        try{
            /* Create "preparedStatement". */
            String queryString = "INSERT INTO Hotel " +
                "(url,nombre) " + " VALUES (?,?)";
            
            PreparedStatement preparedStatement = 
                connection.prepareStatement(queryString);
            
            /* Fill "preparedStatement". */    
            preparedStatement.setString(1, hotel.getUrl());
            preparedStatement.setString(2, hotel.getNombre());
            
            /* Execute query. */                    
            int insertedRows = preparedStatement.executeUpdate();
                
            if (insertedRows != 1) {
                throw new SQLException( "Error en la inserción de hotel");
            }              
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }                
    } 
	
	public void modificarHotel (HotelVO hotel, Connection connection) {     
        try{
            /* Create "preparedStatement". */
            String queryString = "UPDATE Hotel " +
                "SET nombre = ? WHERE  email = ?)";                    
            PreparedStatement preparedStatement = 
                connection.prepareStatement(queryString);
            
            /* Fill "preparedStatement". */    
            preparedStatement.setString(1, hotel.getNombre());
            preparedStatement.setString(2, hotel.getUrl());
              
            /* Execute query. */                    
            int insertedRows = preparedStatement.executeUpdate();
                
            if (insertedRows != 1) {
                throw new SQLException( "Error en la actualización de hotel");
            }                
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }                
    } 
	
	public hotelVO encontrarDatosHotel (String url, Connection connection){     
    	hotelVO hotelVO = null;
    	try{
            /* Create "preparedStatement". */
            String queryString = "SELECT nombre FROM Hotel WHERE  url = ?";                    
            PreparedStatement preparedStatement = 
                connection.prepareStatement(queryString);
            
            /* Fill "preparedStatement". */    
            preparedStatement.setString(1, url);
                   
            /* Execute query. */                    
            ResultSet resultSet = preparedStatement.executeQuery();
               
            if (!resultSet.first()) {
                throw new SQLException( "Error: Hotel no encontrado");
            }
            
            /* Execute query. */                    
            String nombre = resultSet.getString(1);
              
            hotelVO = new HotelVO (url, nombre);
                
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    	return hotelVO;
    } 
	
    public void borrarHotel(String url, Connection connection) {
		try {
			 /* Create "preparedStatement". */
			String queryString = "DELETE FROM Hotel WHERE url = ?";                    
            PreparedStatement preparedStatement = 
                connection.prepareStatement(queryString);
			
			/* Fill "preparedStatement". */  
			preparedStatement.setString(1, url);
			
			/* Execute query. */                    
            int deletedRows = preparedStatement.executeQuery();
			if (deletedRows != 1) {
                throw new SQLException( "Error al eliminar hotel");
            }       
		} catch (Exception e) {
            e.printStackTrace(System.err);
		}
    }


}