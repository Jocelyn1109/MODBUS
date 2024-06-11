/*
 * ModbusSerialResponse.java
 *
 * Created on 25 novembre 2005, 17:20
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
public class ModbusSerialResponse extends Crc {
    
    /** Creates a new instance of ModbusSerialResponse */
    public ModbusSerialResponse() {
    }
    
    
    /**
    *Check the CRC contained in the received Modbus Serial frame
    *@param receive_trame frame received 
    *@return boolean <b>TRUE</b> for CRC NOT OK and <b>FALSE</b> for CRC OK
    */
    public static boolean checkCrcTrameReceived(byte[] receive_trame){
        
        /* variables declaration */
        short crcCalculated, crcReceived, lengthTrameReceived, intermediateValue;
        int crcReceived_tmp;
        boolean resultCheck = true;
        
        /* save of received crc */
        lengthTrameReceived = (short)receive_trame.length;
        crcReceived = receive_trame[lengthTrameReceived - 2];
        crcReceived <<=8;
        intermediateValue = receive_trame[lengthTrameReceived - 1];
        crcReceived_tmp = crcReceived | intermediateValue;
        crcReceived = (short)crcReceived_tmp;
        
        /* calcul of the crc */
        short nb_byte = (short)(lengthTrameReceived - 2);// the length of the frame without two bytes of CRC
        crcCalculated = calculCRC(receive_trame,nb_byte);
        
        /* check correspondance between the two CRC */
        if (crcReceived != crcCalculated){
            resultCheck = false;
        }
        else{
            resultCheck = true;
        }
                
        return resultCheck;
        
    }
    
    /**
    *Check the error in received Modbus Serial frame
    *@param receive_trame frame received
    *@return string contening the error message
    */
    public static String checkIfModbusError(byte[] receive_trame){
       
        Modbus.checkIfError(receive_trame[1],receive_trame[2]);
        
        return Modbus.getErrorType();
    }
     
    /**
    *Extract data from the received Modbus Serial frame
    *@param receive_trame frame received
    *@return array of bytes contening the dates 
    */
    public static byte[] getData(byte[] receive_trame){
        
        /* variables declaration */
        short i;
        byte[] datas = null;
        int lengthTramePDU = (receive_trame.length)-3;
        byte[] tramePDU = new byte[lengthTramePDU];
        
        /* extract PDU frame in the Modbus Serial frame */
        for(i=0;i<lengthTramePDU;i++){
            tramePDU[i] = receive_trame[i+1];
        }
        
        /* extract datas */
        datas = ModbusExtended.extractData(tramePDU);
        
        return datas;
    }
    
    
}
