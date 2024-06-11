/*
 * ModbusException.java
 *
 * Created on 22 novembre 2005, 14:30
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package schneider;

/**
 * @author Jocelyn GIROD
 * @author jocelyn.girod@wanadoo.fr
 * @version 1.0
 */
public class ModbusException extends Exception{
    
    /** Creates a new instance of ModbusException */
    public ModbusException() {
        super();
    }
    
    /** Creates a new instance of ModbusException with the given message*/
    public ModbusException(String msg){
        super(msg);
    }
    
}
