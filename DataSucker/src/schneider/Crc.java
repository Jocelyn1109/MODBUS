/*
 * Crc.java
 *
 * Created on 8 février 2006, 17:35
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package schneider;

/**
 * The class Crc provides methode for calculate <b>CRC</b> for Modbus serial.<br>
 * @author Jocelyn GIROD
 * @author jocelyn.girod@wanadoo.fr
 * @version 1.0
 */
public class Crc {
    
    /** Creates a new instance of Crc */
    public Crc() {
    }
    
    /** 
     * Calcul the CRC.<br>
     * @param trame the modbus frame
     * @param nb_byte the number of byte without the CRC
     * @return the CRC
     */
    public static short calculCRC(byte[] trame, short nb_byte){
        
        /* variables declaration */
        byte bit;
        short n;
        int reserve, crc_register = 0xFFFF;
        
        /* calculation */
        for(n=0;n<nb_byte;n++){
            crc_register = (crc_register ^ trame[n]);
            for(bit=0;bit<8;bit++){
                reserve = (crc_register & 0x1);
                crc_register = (crc_register >> 1);
                if(reserve == 1){
                    crc_register = (crc_register ^ 0xA001);
                }
            }
        }
        return (short)crc_register;
    }
    
}
