/*
 * ModbusSerial.java
 *
 * Created on 21 novembre 2005, 11:14
 *
 */

package schneider;

/**
 * The class ModbusSerial provided methods allowing construction of frames for requests <b>MODBUS SERIAL LINE PDU</b>. 
 * @author Jocelyn GIROD
 * @author jocelyn.girod@wanadoo.fr
 * @version 1.0
 */
public class ModbusSerial extends Crc {
    
   /**
   * Defines the minimum slave address
   */
   private static final int MIN_SLAVE_ADDRESS = 1;
   
   /**
   * Defines the maximum slave address
   */
   private static final int MAX_SLAVE_ADDRESS = 247;
    
   /**
   * Defines a mask
   */
   private static final short MASK = 0xFF;
   
    /** Creates a new instance of ModbusSerial */
    public ModbusSerial() {
    }
    
    
    /** 
     * Build the request PDU to read from <b>1</b> to <b>2000</b> contiguous status of coils in a remote device on serial line.<br>
     * Modbus function code 0x01.
     * @param add_slave slave address
     * @param st_add starting address of the first coil
     * @param nb_coils number of coils to read
     * @return array of 8 bytes which contains the request<br>
     * if error on parameters, generate ModbusException
     */
    public static byte[] readCoilsOnSerial(short add_slave, int st_add, int nb_coils)throws ModbusException{
        
        /* variables declaration */
        byte[] tramePDU = null; 
        byte[] tramePDU_Serial = new byte[8];
        
        /* test of add_slave parameter */
        if((add_slave < MIN_SLAVE_ADDRESS)||(add_slave > MAX_SLAVE_ADDRESS)){
            
            /* slave address error */
            throw new ModbusException("No valid slave address !");
        }
        else{
 
           tramePDU = ModbusExtended.readCoils(st_add, nb_coils);         
                 
           /* no error */
           tramePDU_Serial[0] = (byte)add_slave; //slave address
           tramePDU_Serial[1] = tramePDU[0]; //function code
           tramePDU_Serial[2] = tramePDU[1]; //start address Hi
           tramePDU_Serial[3] = tramePDU[2]; //start address Lo
           tramePDU_Serial[4] = tramePDU[3]; //coils number Hi
           tramePDU_Serial[5] = tramePDU[4]; //coils number Lo
           byte nb_byte = 6;
           short crc = calculCRC(tramePDU_Serial, nb_byte), intermediateValue;
           tramePDU_Serial[6] = (byte)(crc & MASK);
           intermediateValue = crc;
           intermediateValue >>=8;
           tramePDU_Serial[7] = (byte)(intermediateValue & MASK);
                
            
        }
        
        return tramePDU_Serial;
    }
    
    
    /** 
     * Build the request PDU to read from <b>1</b> to <b>2000</b> contiguous status of discrete inputs in a remote device on serial line.<br>
     * Modbus function code 0x02.
     * @param add_slave slave address
     * @param st_add starting address of the first input
     * @param nb_inputs number of inputs to read
     * @return array of 8 bytes which contains the request<br>
     * if error on parameters, generate ModbusException
     */
    public static byte[] readDiscretInputsOnSerial(short add_slave, int st_add, int nb_inputs)throws ModbusException{
        
        /* variables declaration */
        byte[] tramePDU = null; 
        byte[] tramePDU_Serial = new byte[8];
        
        /* test of add_slave parameter */
        if((add_slave < MIN_SLAVE_ADDRESS)||(add_slave > MAX_SLAVE_ADDRESS)){
            
            /* slave address error */
            throw new ModbusException("No valid slave address !");
        }
        else{
            
            tramePDU = ModbusExtended.readDiscretInputs(st_add, nb_inputs);
            
            /* no error */
           tramePDU_Serial[0] = (byte)add_slave; //slave address
           tramePDU_Serial[1] = tramePDU[0]; //function code
           tramePDU_Serial[2] = tramePDU[1]; //start address Hi
           tramePDU_Serial[3] = tramePDU[2]; //start address Lo
           tramePDU_Serial[4] = tramePDU[3]; //inputs number Hi
           tramePDU_Serial[5] = tramePDU[4]; //inputs number Lo
           byte nb_byte = 6;
           short crc = calculCRC(tramePDU_Serial, nb_byte), intermediateValue;
           tramePDU_Serial[6] = (byte)(crc & MASK);
           intermediateValue = crc;
           intermediateValue >>=8;
           tramePDU_Serial[7] = (byte)(intermediateValue & MASK);
        }
        
        return tramePDU_Serial;
    }
    
    /** 
     * Build the request PDU to read the content of a contiguous block of holding registers in a remote device on serial line.<br>
     * Modbus function code 0x03.
     * @param add_slave slave address
     * @param st_add starting address of the first register
     * @param nb_hregisters number of registers to read
     * @return array of 8 bytes which contains the request<br>
     * if error on parameters, generate ModbusException
     */
    public static byte[] readHoldingRegistersOnSerial(short add_slave, int st_add, int nb_hregisters)throws ModbusException{
        
        /* variables declaration */
        byte[] tramePDU = null; 
        byte[] tramePDU_Serial = new byte[8];
        
        /* test of add_slave parameter */
        if((add_slave < MIN_SLAVE_ADDRESS)||(add_slave > MAX_SLAVE_ADDRESS)){
            
            /* slave address error */
            throw new ModbusException("No valid slave address !");
        }
        else{
            
            tramePDU = ModbusExtended.readHoldingRegisters(st_add, nb_hregisters);
            
            /* no error */
           tramePDU_Serial[0] = (byte)add_slave; //slave address
           tramePDU_Serial[1] = tramePDU[0]; //function code
           tramePDU_Serial[2] = tramePDU[1]; //start address Hi
           tramePDU_Serial[3] = tramePDU[2]; //start address Lo
           tramePDU_Serial[4] = tramePDU[3]; //holding registers number Hi
           tramePDU_Serial[5] = tramePDU[4]; //holding registers number Lo
           byte nb_byte = 6;
           short crc = calculCRC(tramePDU_Serial, nb_byte), intermediateValue;
           tramePDU_Serial[6] = (byte)(crc & MASK);
           intermediateValue = crc;
           intermediateValue >>=8;
           tramePDU_Serial[7] = (byte)(intermediateValue & MASK);
        }
        
        return tramePDU_Serial;
    }
    
    /** 
     * Build the request PDU to read from approx. <b>125</b> contiguous input registers in a remote device on serial line.<br>
     * Modbus function code 0x04.
     * @param add_slave slave address
     * @param st_add starting address of the first register
     * @param nb_iregisters number of registers to read
     * @return array of 8 bytes which contains the request<br>
     * if error on parameters, generate ModbusException
     */
    public static byte[] readInputRegistersOnSerial(short add_slave, int st_add, int nb_iregisters)throws ModbusException{
        
        /* variables declaration */
        byte[] tramePDU = null; 
        byte[] tramePDU_Serial = new byte[8];
        
        /* test of add_slave parameter */
        if((add_slave < MIN_SLAVE_ADDRESS)||(add_slave > MAX_SLAVE_ADDRESS)){
            
            /* slave address error */
            throw new ModbusException("No valid slave address !");
        }
        else{
            
            tramePDU = ModbusExtended.readInputRegisters(st_add, nb_iregisters);
            
            /* no error */
           tramePDU_Serial[0] = (byte)add_slave; //slave address
           tramePDU_Serial[1] = tramePDU[0]; //function code
           tramePDU_Serial[2] = tramePDU[1]; //start address Hi
           tramePDU_Serial[3] = tramePDU[2]; //start address Lo
           tramePDU_Serial[4] = tramePDU[3]; //input registers number Hi
           tramePDU_Serial[5] = tramePDU[4]; //input registers number Lo
           byte nb_byte = 6;
           short crc = calculCRC(tramePDU_Serial, nb_byte), intermediateValue;
           tramePDU_Serial[6] = (byte)(crc & MASK);
           intermediateValue = crc;
           intermediateValue >>=8;
           tramePDU_Serial[7] = (byte)(intermediateValue & MASK);
        }
        
        return tramePDU_Serial;
    }
    
    /** 
     * Build the request PDU to write a single output to either <b>ON</b> or <b>OFF</b> in a remote device on serial line.<br>
     * Modbus function code 0x05.
     * @param add_slave slave address
     * @param add address of the coil to be forced 
     * @param value value to be written (TRUE for ON and FALSE for OFF)
     * @return array of 8 bytes which contains the request<br>
     * if error on parameters, generate ModbusException
     */
    public static byte[] writeSingleCoilOnSerial(short add_slave, int add, boolean value)throws ModbusException{
        
        /* variables declaration */
        byte[] tramePDU = null; 
        byte[] tramePDU_Serial = new byte[8];
        
        /* test of add_slave parameter */
        if((add_slave < MIN_SLAVE_ADDRESS)||(add_slave > MAX_SLAVE_ADDRESS)){
            
            /* slave address error */
            throw new ModbusException("No valid slave address !");
        }
        else{
            
            tramePDU = ModbusExtended.writeSingleCoil(add, value);
            
            /* no error */
           tramePDU_Serial[0] = (byte)add_slave; //slave address
           tramePDU_Serial[1] = tramePDU[0]; //function code
           tramePDU_Serial[2] = tramePDU[1]; //start address Hi
           tramePDU_Serial[3] = tramePDU[2]; //start address Lo
           tramePDU_Serial[4] = tramePDU[3]; //coil value Hi
           tramePDU_Serial[5] = tramePDU[4]; //coil value Lo
           byte nb_byte = 6;
           short crc = calculCRC(tramePDU_Serial, nb_byte), intermediateValue;
           tramePDU_Serial[6] = (byte)(crc & MASK);
           intermediateValue = crc;
           intermediateValue >>=8;
           tramePDU_Serial[7] = (byte)(intermediateValue & MASK);
        }
        
        return tramePDU_Serial;
    }
    
    /** 
     * Build the request PDU to write a single register in a remote device on serial line.<br>
     * Modbus function code 0x06.
     * @param add_slave slave address
     * @param add address of the register to be written
     * @param rvalue value to be written
     * @return array of 8 bytes which contains the request<br>
     * if error on parameters, generate ModbusException
     */
    public static byte[] writeSingleRegisterOnSerial(short add_slave, int add, int rvalue)throws ModbusException{
        
        /* variables declaration */
        byte[] tramePDU = null; 
        byte[] tramePDU_Serial = new byte[8];
        
        /* test of add_slave parameter */
        if((add_slave < MIN_SLAVE_ADDRESS)||(add_slave > MAX_SLAVE_ADDRESS)){
            
            /* slave address error */
            throw new ModbusException("No valid slave address !");
        }
        else{
            
            tramePDU = ModbusExtended.writeSingleRegister(add, rvalue);
            
            /* no error */
           tramePDU_Serial[0] = (byte)add_slave; //slave address
           tramePDU_Serial[1] = tramePDU[0]; //function code
           tramePDU_Serial[2] = tramePDU[1]; //start address Hi
           tramePDU_Serial[3] = tramePDU[2]; //start address Lo
           tramePDU_Serial[4] = tramePDU[3]; //register value Hi
           tramePDU_Serial[5] = tramePDU[4]; //register value Lo
           byte nb_byte = 6;
           short crc = calculCRC(tramePDU_Serial, nb_byte), intermediateValue;
           tramePDU_Serial[6] = (byte)(crc & MASK);
           intermediateValue = crc;
           intermediateValue >>=8;
           tramePDU_Serial[7] = (byte)(intermediateValue & MASK);
        }
        
        return tramePDU_Serial;
    }
    
    /** 
     * Build the request PDU to write each coil in a sequence of coil to either <b>ON</b> or <b>OFF</b> in a remote device on serial line.<br>
     * Modbus function code 0x0F.
     * @param add_slave slave address
     * @param st_add address of the first coil to be forced 
     * @param nb_coils number of coils to write
     * @param value array containing the sequences of value to be written
     * @return array of bytes which contains the request<br>
     * if error on parameters, generate ModbusException
     */
     public static byte[] writeMultipleCoilsOnSerial(short add_slave, int st_add, int nb_coils, byte[] value)throws ModbusException{
         
         /* variables declaration */
        byte[] tramePDU = null; 
        //byte[] tramePDU_Serial = new byte[8];
        byte[] tramePDU_Serial = null;
        
        /* test of add_slave parameter */
        if((add_slave < MIN_SLAVE_ADDRESS)||(add_slave > MAX_SLAVE_ADDRESS)){
            
            /* slave address error */
            throw new ModbusException("No valid slave address !");
        }
        else{
            
            tramePDU = ModbusExtended.writeMultipleCoils(st_add, nb_coils, value);
            int lengthFrame = tramePDU.length;
            int lengthData = lengthFrame - 6;
            short i;
            
            tramePDU_Serial = new byte[lengthFrame+3];
            
            /* no error */
           tramePDU_Serial[0] = (byte)add_slave; //slave address
           tramePDU_Serial[1] = tramePDU[0]; //function code
           tramePDU_Serial[2] = tramePDU[1]; //start address Hi
           tramePDU_Serial[3] = tramePDU[2]; //start address Lo
           tramePDU_Serial[4] = tramePDU[3]; //coils number Hi
           tramePDU_Serial[5] = tramePDU[4]; //coils number Lo
           tramePDU_Serial[6] = tramePDU[5]; //byteCount
           
           for(i=0;i<lengthData;i++){
               tramePDU_Serial[7+i] = tramePDU[6+i];
           }
           
           byte nb_byte = (byte)(lengthFrame + 1);
           short crc = calculCRC(tramePDU_Serial, nb_byte), intermediateValue;
           tramePDU_Serial[7+i] = (byte)(crc & MASK);
           intermediateValue = crc;
           intermediateValue >>=8;
           tramePDU_Serial[7+i+1] = (byte)(intermediateValue & MASK);
        }
        
        return tramePDU_Serial;
     }
     
     /** 
     * Build the request PDU to write a block of contiguous registers (<b>1</b> to approx. <b>120</b> registers) in a remote device on serial line.<br>
     * Modbus function code 0x10.
     * @param add_slave slave address
     * @param st_add address of the first register to be written
     * @param nb_registers number of registers to write
     * @param value array containing the value to be written in registers
     * @return array of bytes which contains the request<br>
     * if error on parameters, generate ModbusException
     */
     public static byte[] writeMultipleRegistersOnSerial(short add_slave, int st_add, int nb_registers, byte[] value)throws ModbusException{
         
          /* variables declaration */
        byte[] tramePDU = null; 
        byte[] tramePDU_Serial = null;
        
        /* test of add_slave parameter */
        if((add_slave < MIN_SLAVE_ADDRESS)||(add_slave > MAX_SLAVE_ADDRESS)){
            
            /* slave address error */
            throw new ModbusException("No valid slave address !");
        }
        else{
            
            tramePDU =ModbusExtended.writeMultipleRegisters(st_add, nb_registers, value);
            int lengthFrame = tramePDU.length;
            int lengthData = lengthFrame - 6;
            short i;
            
            //tramePDU_Serial = new byte[7+lengthData+2];
            tramePDU_Serial = new byte[lengthFrame+3];
            /* no error */
            tramePDU_Serial[0] = (byte)add_slave; //slave address
            tramePDU_Serial[1] = tramePDU[0]; //function code
            tramePDU_Serial[2] = tramePDU[1]; //start address Hi
            tramePDU_Serial[3] = tramePDU[2]; //start address Lo
            tramePDU_Serial[4] = tramePDU[3]; //registers number Hi
            tramePDU_Serial[5] = tramePDU[4]; //registers number Lo
            tramePDU_Serial[6] = tramePDU[5]; //byteCount
           
            for(i=0;i<lengthData;i++){
               tramePDU_Serial[7+i] = tramePDU[6+i];
            }
           
            byte nb_byte = (byte)(lengthFrame + 1);
            short crc = calculCRC(tramePDU_Serial, nb_byte), intermediateValue;
            tramePDU_Serial[7+i] = (byte)(crc & MASK);
            intermediateValue = crc;
            intermediateValue >>=8;
            tramePDU_Serial[7+i+1] = (byte)(intermediateValue & MASK);
        }
        
        return tramePDU_Serial;
     }
     
     /** 
     * Build the request PDU to performs a combination of one read operation and one write operation 
     * in a single MODBUS transaction on serial line.<br>
     * Modbus function code 0x17.
     * @param add_slave slave address
     * @param st_addRead address of the first register to reading
     * @param nb_registersRead number of registers to reading
     * @param st_addWrite address of the first register to be written
     * @param nb_registersWrite number of registers to be written
     * @param value array containing the value to be written in registers
     * @return array of bytes which contains the request<br>
     * if error on parameters, generate ModbusException
     */
     public static byte[] readWriteMultipleRegistersOnSerial(short add_slave,
                                                      int st_addRead, 
                                                      int nb_registersRead, 
                                                      int st_addWrite,
                                                      int nb_registersWrite,
                                                      byte[] value)throws ModbusException{
      
          /* variables declaration */
          byte[] tramePDU = null; 
          byte[] tramePDU_Serial = null;
        
          /* test of add_slave parameter */
          if((add_slave < MIN_SLAVE_ADDRESS)||(add_slave > MAX_SLAVE_ADDRESS)){
            
             /* slave address error */
             throw new ModbusException("No valid slave address !");
         }
         else{
            
            tramePDU =ModbusExtended.readWriteMultipleRegisters(st_addRead, nb_registersRead, st_addWrite, nb_registersWrite, value);
            int lengthFrame = tramePDU.length;
            int lengthData = lengthFrame - 10;
            short i;
            
            //tramePDU_Serial = new byte[11+lengthData+2];
            tramePDU_Serial = new byte[lengthFrame + 3];
            
            /* no error */
            tramePDU_Serial[0] = (byte)add_slave; //slave address
            tramePDU_Serial[1] = tramePDU[0]; //function code;
            tramePDU_Serial[2] = tramePDU[1]; //st_addRead Hi
            tramePDU_Serial[3] = tramePDU[2]; //st_addRead Lo
            tramePDU_Serial[4] = tramePDU[3]; //number of registers to read Hi
            tramePDU_Serial[5] = tramePDU[4]; //number of registers to read Lo
            tramePDU_Serial[6] = tramePDU[5]; //st_addWrite Hi
            tramePDU_Serial[7] = tramePDU[6]; //st_addWrite Lo
            tramePDU_Serial[8] = tramePDU[7]; //number of registers to write Hi
            tramePDU_Serial[9] = tramePDU[8]; //number of registers to write Lo
            tramePDU_Serial[10] = tramePDU[9]; //byteCount
            
            for(i=0;i<lengthData;i++){
               tramePDU_Serial[11+i] = tramePDU[10+i];
            }
            
            byte nb_byte = (byte)(lengthFrame + 1);
            short crc = calculCRC(tramePDU_Serial, nb_byte), intermediateValue;
            tramePDU_Serial[11+i] = (byte)(crc & MASK);
            intermediateValue = crc;
            intermediateValue >>=8;
            tramePDU_Serial[11+i+1] = (byte)(intermediateValue & MASK);
            
         }
          
         return tramePDU_Serial;
     }
     
     /** 
     * Build the request PDU to allows the identification and additional information relative to the 
     * physical and functional description of a remote device on serial line.<br>
     * Modbus function code 0x2B.<br>
     * Modbus Encapsulated Interface 0x0E.<br>
     * @param add_slave slave address
     * @param deviceIdCode specify the type of access :<br>
     * 01 : request to get the basic device identification (stream access),<br>
     * 02 : request to get the regular device identification (stream access),<br>
     * 03 : request to get the extended device identification (stream access),<br>
     * 04 : request to get one specific identification object (individual access).<br><br> 
     * @param objectId identify the first object to be obtained
     * @return array of 7 bytes which contains the request<br>
     * if error on parameters, generate ModbusException
     */
     public static byte[] readDeviceIdentificationOnSerial(short add_slave, byte deviceIdCode, char objectId)throws ModbusException{
         
        /* variables declaration */
        byte[] tramePDU = null; 
        byte[] tramePDU_Serial = new byte[7];
        
        /* test of add_slave parameter */
        if((add_slave < MIN_SLAVE_ADDRESS)||(add_slave > MAX_SLAVE_ADDRESS)){
            
            /* slave address error */
            throw new ModbusException("No valid slave address !");
        }
        else{
            
            tramePDU = ModbusExtended.readDeviceIdentification(deviceIdCode, objectId);
            
            /* no error */
           tramePDU_Serial[0] = (byte)add_slave; //slave address
           tramePDU_Serial[1] = tramePDU[0]; //function code
           tramePDU_Serial[2] = tramePDU[1]; //Modbus Encapsulated Interface
           tramePDU_Serial[3] = tramePDU[2]; //deviceIdCode
           tramePDU_Serial[4] = tramePDU[3]; //objectId
           byte nb_byte = 5;
           short crc = calculCRC(tramePDU_Serial, nb_byte), intermediateValue;
           tramePDU_Serial[5] = (byte)(crc & MASK);
           intermediateValue = crc;
           intermediateValue >>=8;
           tramePDU_Serial[6] = (byte)(intermediateValue & MASK);
        }
        
        return tramePDU_Serial;
     }
    
     /** 
     * Build the request PDU to read the contents of eight <b>Exception Status</b> outputs in a remote device on serial line.<br>
     * Modbus function code 0x07.
     * @param add_slave slave address
     * @return array of 4 bytes which contains the request<br>
     */
     public static byte[] readExceptionStatusOnSerial(short add_slave)throws ModbusException{
         
        /* variables declaration */
        byte tramePDU = 0; 
        byte[] tramePDU_Serial = new byte[4];
        
        /* test of add_slave parameter */
        if((add_slave < MIN_SLAVE_ADDRESS)||(add_slave > MAX_SLAVE_ADDRESS)){
            
            /* slave address error */
            throw new ModbusException("No valid slave address !");
        }
        else{
            
            tramePDU = ModbusExtended.readExceptionStatus();
            
            /* no error */
            tramePDU_Serial[0] = (byte)add_slave; //slave address
            tramePDU_Serial[1] = tramePDU; //function code
            byte nb_byte = 2;
            short crc = calculCRC(tramePDU_Serial, nb_byte), intermediateValue;
            tramePDU_Serial[2] = (byte)(crc & MASK);
            intermediateValue = crc;
            intermediateValue >>=8;
            tramePDU_Serial[3] = (byte)(intermediateValue & MASK);
                       
        }
        
        return tramePDU_Serial;
         
     }
     
     /** 
     * Build the request PDU to provide a serie of tests for checking the communiation system between a client (Master)
     * device and a server (Slave) on serial line.<br>
     * Modbus function code 0x08.
     * @param add_slave slave address
     * @param diagType specify the type of test to be performed :<br>
     * <b>0</b> : Return Query Data<br>
     * <b>1</b> : Restart Communication Option : clear the log<br>
     * <b>2</b> : Restart Communication Option : leave the log<br>
     * <b>3</b> : Return Diagnostic Register<br>
     * <b>4</b> : Change ASCII Input Delimiter<br>
     * <b>5</b> : Forced Listen Only Mode<br>
     * <b>6</b> : Clean Counters and Diagnostic Registers<br>
     * <b>7</b> : Return Bus Message Count<br>
     * <b>8</b> : Return Bus Communication Error Count<br>
     * <b>9</b> : Return Bus Exception Error Count<br>
     * <b>10</b> : Return Slave Message Count<br>
     * <b>11</b> : Return Slave No Response Count<br>
     * <b>12</b> : Return Slave NAK Count<br>
     * <b>13</b> : Return Slave Busy Count<br>
     * <b>14</b> : Return Bus Character Overrun Count<br>
     * <b>15</b> : Clear Overrun Counter and Flag<br>
     *<br>
     * @param CHAR in case of <b>Change ASCII Input Delimiter (4)</b>, specify the end of message delimiter
     * @return array of bytes which contains the request<br>
     * if error on parameters, generate ModbusException
     */
     public static byte[] DiagnosticsOnSerial(short add_slave, short diagType, char CHAR)throws ModbusException{
         
        /* variables declaration */
        byte[] tramePDU = null; 
        byte[] tramePDU_Serial = null;
        
        /* test of add_slave parameter */
        if((add_slave < MIN_SLAVE_ADDRESS)||(add_slave > MAX_SLAVE_ADDRESS)){
            
            /* slave address error */
            throw new ModbusException("No valid slave address !");
        }
        else{
            
            byte nb_byte;
            short crc, intermediateValue;
            tramePDU = ModbusExtended.Diagnostics(diagType, CHAR);
            
                if(tramePDU.length==3){
                    
                    /* tramePDU_Serial initialization */
                    tramePDU_Serial = new byte[6];
                    
                    /* no error */
                    tramePDU_Serial[0] = (byte)add_slave; //slave address
                    tramePDU_Serial[1] = tramePDU[0]; //function code
                    tramePDU_Serial[2] = tramePDU[1]; //sub-function Hi
                    tramePDU_Serial[3] = tramePDU[2]; //sub-function Lo
                    nb_byte = 4;
                    crc = calculCRC(tramePDU_Serial, nb_byte);
                    tramePDU_Serial[4] = (byte)(crc & MASK);
                    intermediateValue = crc;
                    intermediateValue >>=8;
                    tramePDU_Serial[5] = (byte)(intermediateValue & MASK);
                    
                }
                else{
                    
                    /* tramePDU_Serial initialization */
                    tramePDU_Serial = new byte[8];
                    
                    /* no error */
                    tramePDU_Serial[0] = (byte)add_slave; //slave address
                    tramePDU_Serial[1] = tramePDU[0]; //function code
                    tramePDU_Serial[2] = tramePDU[1]; //sub-function Hi
                    tramePDU_Serial[3] = tramePDU[2]; //sub-function Lo
                    tramePDU_Serial[4] = tramePDU[3]; //data or character delimiter
                    tramePDU_Serial[5] = tramePDU[4]; //data
                    nb_byte = 6;
                    crc = calculCRC(tramePDU_Serial, nb_byte);
                    tramePDU_Serial[6] = (byte)(crc & MASK);
                    intermediateValue = crc;
                    intermediateValue >>=8;
                    tramePDU_Serial[7] = (byte)(intermediateValue & MASK);
                
                }
        }
        
        return tramePDU_Serial;
     }
     
     /** 
     * Build the request PDU to get a status word and an event count from the remote device's<br>
     * communication event count on serial line.<br>
     * Modbus function code 0x0B.
     * @param add_slave slave address
     * @return array of 4 bytes which contains the request<br>
     */
     public static byte[] getComEventCounterOnSerial(short add_slave)throws ModbusException{
         
        /* variables declaration */
        byte tramePDU = 0; 
        byte[] tramePDU_Serial = new byte[4];
        
        /* test of add_slave parameter */
        if((add_slave < MIN_SLAVE_ADDRESS)||(add_slave > MAX_SLAVE_ADDRESS)){
            
            /* slave address error */
            throw new ModbusException("No valid slave address !");
        }
        else{
            
            tramePDU = ModbusExtended.getComEventCounter();
            
            /* no error */
            tramePDU_Serial[0] = (byte)add_slave; //slave address
            tramePDU_Serial[1] = tramePDU; //function code
            byte nb_byte = 2;
            short crc = calculCRC(tramePDU_Serial, nb_byte), intermediateValue;
            tramePDU_Serial[2] = (byte)(crc & MASK);
            intermediateValue = crc;
            intermediateValue >>=8;
            tramePDU_Serial[3] = (byte)(intermediateValue & MASK);
                       
        }
        
        return tramePDU_Serial;
     }
     
     /** 
     * Build the request PDU to get a status word, event count, message count, and a field<br>
     * of event bytes from the remote device on serial line.<br>
     * Modbus function code 0x0C.
     * @param add_slave slave address
     * @return array of 4 bytes which contains the request<br>
     */
     public static byte[] getComEventLogOnSerial(short add_slave)throws ModbusException{
         
        /* variables declaration */
        byte tramePDU = 0; 
        byte[] tramePDU_Serial = new byte[4];
        
        /* test of add_slave parameter */
        if((add_slave < MIN_SLAVE_ADDRESS)||(add_slave > MAX_SLAVE_ADDRESS)){
            
            /* slave address error */
            throw new ModbusException("No valid slave address !");
        }
        else{
            
            tramePDU = ModbusExtended.getComEventLog();
            
            /* no error */
            tramePDU_Serial[0] = (byte)add_slave; //slave address
            tramePDU_Serial[1] = tramePDU; //function code
            byte nb_byte = 2;
            short crc = calculCRC(tramePDU_Serial, nb_byte), intermediateValue;
            tramePDU_Serial[2] = (byte)(crc & MASK);
            intermediateValue = crc;
            intermediateValue >>=8;
            tramePDU_Serial[3] = (byte)(intermediateValue & MASK);
                       
        }
        
        return tramePDU_Serial;
     }
     
     /** 
     * Build the request PDU to read the description of the type, the current status, and other<br>
     * information specific to a remote device on serial.<br>
     * Modbus function code 0x11.slave address
     * @param add_slave slave address 
     * @return array of 4 bytes which contains the request<br>
     */
     public static byte[] reportSlaveIdOnSerial(short add_slave)throws ModbusException{
         
        /* variables declaration */
        byte tramePDU = 0; 
        byte[] tramePDU_Serial = new byte[4];
        
        /* test of add_slave parameter */
        if((add_slave < MIN_SLAVE_ADDRESS)||(add_slave > MAX_SLAVE_ADDRESS)){
            
            /* slave address error */
            throw new ModbusException("No valid slave address !");
        }
        else{
            
            tramePDU = ModbusExtended.reportSlaveId();
            
            /* no error */
            tramePDU_Serial[0] = (byte)add_slave; //slave address
            tramePDU_Serial[1] = tramePDU; //function code
            byte nb_byte = 2;
            short crc = calculCRC(tramePDU_Serial, nb_byte), intermediateValue;
            tramePDU_Serial[2] = (byte)(crc & MASK);
            intermediateValue = crc;
            intermediateValue >>=8;
            tramePDU_Serial[3] = (byte)(intermediateValue & MASK);
                       
        }
        
        return tramePDU_Serial;
     }
    
    
}
