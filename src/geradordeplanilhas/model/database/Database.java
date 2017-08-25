
package geradordeplanilhas.model.database;

/**
 *
 * @author nanda
 */

import java.sql.Connection;


public interface Database {
    
    public Connection conectar();
    public void desconectar(Connection conn);
}

