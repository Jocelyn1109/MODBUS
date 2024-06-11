/*
 * Modbus.java
 *
 * Created on 7 octobre 2005, 10:18
 * 
 */

package schneider;

/**
 * The Modbus application protocol defines a simple Protocol Data Unit (PDU) 
 * independente of the underlying communication layers.
 *
 * The class Modbus provided methods allowing construction of frames for requests <b>MODBUS PDU</b>, 
 * as well as methods to treat the data and the errors.<br>
 * @author Jocelyn GIROD
 * @author jocelyn.girod@wanadoo.fr
 * @version 1.0
 */
public class Modbus {
    
    /* class variables */
    private static String errorType;
    
   /**
   * Defines the readCoil function code
   */
   private static final short READ_COIL = 0x01;
   
   /**
   * Defines the readDiscretInputs function code
   */
   private static final short READ_DISCRET_INPUTS = 0x02;
   
   /**
   * Defines the readHoldingRegisters function code
   */
   private static final short READ_HOLDING_REGISTERS = 0x03;
   
   /**
   * Defines the readInputRegisters function code
   */
   private static final short READ_INPUT_REGISTERS = 0x04;
   
   /**
   * Defines the writeSingleCoil function code
   */
   private static final short WRITE_SINGLE_COIL = 0x05;
   
   /**
   * Defines the writeSingleRegister functio code
   */
   private static final short WRITE_SINGLE_REGISTER = 0x06;
   
   /**
   * Defines the writeMultipleCoils function code
   */
   private static final short WRITE_MULTIPLE_COILS = 0x0F;
   
   /**
   * Defines the writeMultipleRegisters function code
   */
   private static final short WRITE_MULTIPLE_REGISTERS = 0x10;
   
   /**
   * Defines the readWriteMultipleRegisters function code
   */
   private static final short READ_WRITE_MULTIPLE_REGISTERS = 0x17;
   
   /**
   * Defines the readDeviceIdentification function code
   */
   private static final short READ_DEVICE_IDENTIFICATION = 0x2B;
   
   /**
   * Defines the minimum address number
   */
   private static final int MIN_ADDRESS = 0;
   
   /**
   * Defines the maximum address number
   */
   private static final int MAX_ADDRESS = 65535;
   
   /**
   * Defines the minimum number of coils to read
   */
   private static final int MIN_NBCOILS = 1;
   
   /**
   * Defines the maximum number of coils to read
   */
   private static final int MAX_NBCOILS = 2000;
   
   /**
   * Defines the minimum number of inputs to read
   */
   private static final int MIN_INPUTS = 1;
   
   /**
   * Defines the maximum number of inputs to read
   */
   private static final int MAX_INPUTS = 2000;
   
   /**
   * Defines the minimum number of holding registers to read
   */
   private static final int MIN_HREGISTERS = 1;
   
   /**
   * Defines the maximum number of holding registers to read
   */
   private static final int MAX_HREGISTERS = 125;
   
   /**
   * Defines the minimum number of input registers to read
   */
   private static final int MIN_IREGISTERS = 1;
   
   /**
   * Defines the maximum number of input registers to read
   */
   private static final int MAX_IREGISTERS = 125;
   
   /**
   * Defines coil OFF
   */
   private static final int COIL_OFF = 0x0;
   
   /**
   * Defines coil ON
   */
   private static final int COIL_ON = 0xFF00;
   
   /**
   * Defines the minimum value to write in register
   */
   private static final int MIN_RVALUE = 0;
   
   /**
   * Defines the maximum value to write in register
   */
   private static final int MAX_RVALUE = 65535;
   
   /**
   * Defines the minimum number of coils to write
   */
   private static final int MIN_COIL = 1;
   
   /**
   * Defines the maximum number of coils to write
   */
   private static final int MAX_COIL = 1968;
   
   /**
   * Defines a maximum array size
   */
   private static final int MAX_SIZE_ARRAY_1 = 246;
   
   /**
   * Defines the minimum number of registers to write
   */
   private static final int MIN_REGISTER = 1;
   
   /**
   * Defines the maximum number of registers to write
   */
   private static final int MAX_REGISTER = 120;
   
   /**
   * Defines a maximum array size
   */
   private static final int MAX_SIZE_ARRAY_2 = 240;
   
   /**
   * Defines minimum register to read in readWriteMultipleRegisters function
   */
   private static final int MIN_REGISTER_READ = 1;
   
   /** 
   * Defines maximum register to read in readWriteMultipleRegisters function
   */
   private static final int MAX_REGISTER_READ = 118;
   
   /**
   * Defines minimum register to write in readWriteMultipleRegisters function
   */
   private static final int MIN_REGISTER_WRITE = 1;
   
   /**
   * Defines maximum register to write in readWriteMultipleRegisters function
   */
   private static final int MAX_REGISTER_WRITE = 118;
   
   /**
   * Defines a maximum array size
   */
   private static final int MAX_SIZE_ARRAY_3 = 236;
   
   /**
   * Defines the minimum object ID
   */
   private static final int MIN_OBJECTID = 0;
   
   /**
   * Defines the maximum object ID
   */
   private static final int MAX_OBJECTID = 255;
   
   /**
   * Defines a mask
   */
   private static final short MASK = 0xFF;
   
 
   
    /** Creates a new instance of Modbus */
    public Modbus() {
        
        /* variables initialization */
        errorType = new String("NO ERROR");
        
    }
    
    /** 
     * Build the request PDU to read from <b>1</b> to <b>2000</b> contiguous status of coils in a remote device.<br>
     * Modbus function code 0x01.
     * @param st_add starting address of the first coil
     * @param nb_coils number of coils to read
     * @return array of 5 bytes which contains the request<br>
     * if error on parameters, generate ModbusException
     */
    public static byte[] readCoils(int st_add, int nb_coils) throws ModbusException{
        
        /* variables declaration */
        byte[] tramePDU = new byte[5];
        
        /* test of the parameters and filling of the array tramePDU */
        if ((st_add < MIN_ADDRESS)||(st_add > MAX_ADDRESS)){
            
            /* st_add parameter error */
            throw new ModbusException("No valid address !");
            
        }
        else if ((nb_coils < MIN_NBCOILS)||(nb_coils > MAX_NBCOILS)){
            
            /* nb_coils parameter error */
            throw new ModbusException("No valid number of coils !");

        }
        else{
            
            /* variables declaration */
            short intermediateValue;
            
            /* filling of the array tramePDU */
            tramePDU[0] = READ_COIL; //function code
            intermediateValue = (short)st_add;
            intermediateValue >>= 8;
            tramePDU[1] = (byte)(intermediateValue & MASK);
            tramePDU[2] = (byte)(st_add & MASK);
            intermediateValue = (short)nb_coils;
            intermediateValue >>= 8;
            tramePDU[3] = (byte)(intermediateValue & MASK);
            tramePDU[4] = (byte)(nb_coils & MASK);
            
        }
        
        return tramePDU;
        
    }
    
    
    /** 
     * Build the request PDU to read from <b>1</b> to <b>2000</b> contiguous status of discrete inputs in a remote device.<br>
     * Modbus function code 0x02.
     * @param st_add starting address of the first input
     * @param nb_inputs number of inputs to read
     * @return array of 5 bytes which contains the request<br>
     * if error on parameters, generate ModbusException
     */
    public static byte[] readDiscretInputs(int st_add, int nb_inputs)throws ModbusException{
        
        /* variables declaration */
        byte[] tramePDU = new byte[5];
        
        /* test of the parameters and filling of the array tramePDU */
        if ((st_add < MIN_ADDRESS)||(st_add > MAX_ADDRESS)){
            
            /* st_add parameter error */
            throw new ModbusException("No valid address !");
            
        }
        else if ((nb_inputs < MIN_INPUTS)||(nb_inputs > MAX_INPUTS)){
            
            /* nb_inputs parameter error */
            throw new ModbusException("No valid number of inputs !");
        }
        else{
            
            /* variables declaration */
            short intermediateValue;
            
            /* filling of the array tramePDU */
            tramePDU[0] = READ_DISCRET_INPUTS; //function code
            intermediateValue = (short)st_add;
            intermediateValue >>= 8;
            tramePDU[1] = (byte)(intermediateValue & MASK);
            tramePDU[2] = (byte)(st_add & MASK);
            intermediateValue = (short)nb_inputs;
            intermediateValue >>= 8;
            tramePDU[3] = (byte)(intermediateValue & MASK);
            tramePDU[4] = (byte)(nb_inputs & MASK);
            
        }
        
        return tramePDU;
    }
    
    
    /** 
     * Build the request PDU to read the content of a contiguous block of holding registers in a remote device.<br>
     * Modbus function code 0x03.
     * @param st_add starting address of the first register
     * @param nb_hregisters number of registers to read
     * @return array of 5 bytes which contains the request<br>
     * if error on parameters, generate ModbusException
     */
    public static byte[] readHoldingRegisters(int st_add, int nb_hregisters)throws ModbusException{
        
        /* variables declaration */
        byte[] tramePDU = new byte[5];
        
        /* test of the parameters and filling of the array tramePDU */
        if ((st_add < MIN_ADDRESS)||(st_add > MAX_ADDRESS)){
            
            /* st_add parameter error */
            throw new ModbusException("No valid address !");
            
        }
        else if ((nb_hregisters < MIN_HREGISTERS)||(nb_hregisters > MAX_HREGISTERS)){
            
            /* nb_hregisters parameter error */
            throw new ModbusException("No valid number of holding registers !");
        }
        else{
            
            /* variables declaration */
            short intermediateValue;
            
            /* filling of the array tramePDU */
            tramePDU[0] = READ_HOLDING_REGISTERS; //function code
            intermediateValue = (short)st_add;
            intermediateValue >>= 8;
            tramePDU[1] = (byte)(intermediateValue & MASK);
            tramePDU[2] = (byte)(st_add & MASK);
            intermediateValue = (short)nb_hregisters;
            intermediateValue >>= 8;
            tramePDU[3] = (byte)(intermediateValue & MASK);
            tramePDU[4] = (byte)(nb_hregisters & MASK);
            
        }
        
        return tramePDU;
    }
    
    /** 
     * Build the request PDU to read from approx. <b>125</b> contiguous input registers in a remote device.<br>
     * Modbus function code 0x04.
     * @param st_add starting address of the first register
     * @param nb_iregisters number of registers to read
     * @return array of 5 bytes which contains the request<br>
     * if error on parameters, generate ModbusException
     */
    public static byte[] readInputRegisters(int st_add, int nb_iregisters)throws ModbusException{
        
        /* variables declaration */
        byte[] tramePDU = new byte[5];
        
        /* test of the parameters and filling of the array tramePDU */
        if ((st_add < MIN_ADDRESS)||(st_add > MAX_ADDRESS)){
            
            /* st_add parameter error */
            throw new ModbusException("No valid address !");
            
        }
        else if ((nb_iregisters < MIN_IREGISTERS)||(nb_iregisters > MAX_IREGISTERS)){
            
            /* nb_iregisters parameter error */
            throw new ModbusException("No valid number of input registers !");
        }
        else{
            
            /* variables declaration */
            short intermediateValue;
            
            /* filling of the array tramePDU */
            tramePDU[0] = READ_INPUT_REGISTERS; //function code
            intermediateValue = (short)st_add;
            intermediateValue >>= 8;
            tramePDU[1] = (byte)(intermediateValue & MASK);
            tramePDU[2] = (byte)(st_add & MASK);
            intermediateValue = (short)nb_iregisters;
            intermediateValue >>= 8;
            tramePDU[3] = (byte)(intermediateValue & MASK);
            tramePDU[4] = (byte)(nb_iregisters & MASK);
            
        }
        
        return tramePDU;
        
    }
        
    /** 
     * Build the request PDU to write a single output to either <b>ON</b> or <b>OFF</b> in a remote device.<br>
     * Modbus function code 0x05.
     * @param add address of the coil to be forced 
     * @param value value to be written (TRUE for ON and FALSE for OFF)
     * @return array of 5 bytes which contains the request<br>
     * if error on parameters, generate ModbusException
     */
    public static byte[] writeSingleCoil(int add, boolean value)throws ModbusException{
        
        /* variables declaration */
        byte[] tramePDU = new byte[5];
        
        /* test of the parameters and filling of the array tramePDU */
        if ((add < MIN_ADDRESS)||(add > MAX_ADDRESS)){
            
            /* add parameter error */
            throw new ModbusException("No valid address !");
            
        }
        else{
            
            /* variables declaration */
            int coilValue;
            short intermediateValue;
            
            /* value to be written */
            if(value == false){
                coilValue = COIL_OFF;
            }
            else{
                coilValue = COIL_ON;
            }
            /* filling of the array tramePDU */
            tramePDU[0] = WRITE_SINGLE_COIL; //function code
            intermediateValue = (short)add;
            intermediateValue >>= 8;
            tramePDU[1] = (byte)(intermediateValue & MASK);
            tramePDU[2] = (byte)(add & MASK);
            intermediateValue = (short)coilValue;
            intermediateValue >>= 8;
            tramePDU[3] = (byte)(intermediateValue & MASK);
            tramePDU[4] = (byte)(coilValue & MASK);
            
        }
        
        return tramePDU;
        
    }
    
    /** 
     * Build the request PDU to write a single register in a remote device.<br>
     * Modbus function code 0x06.
     * @param add address of the register to be written
     * @param rvalue value to be written
     * @return array of 5 bytes which contains the request<br>
     * if error on parameters, generate ModbusException
     */
    public static byte[] writeSingleRegister(int add, int rvalue)throws ModbusException{
        
        /* variables declaration */
        byte[] tramePDU = new byte[5];
        
        /* test of the parameters and filling of the array tramePDU */
        if ((add < MIN_ADDRESS)||(add > MAX_ADDRESS)){
            
            /* add parameter error */
            throw new ModbusException("No valid address !");
            
        }
        else if ((rvalue < MIN_RVALUE)||(rvalue > MAX_RVALUE)){
            
            /* rvalue parameter error */
            throw new ModbusException("No valid value !");
        }
        else{
            
            /* variables declaration */
            short intermediateValue;
            
            /* filling of the array tramePDU */
            tramePDU[0] = WRITE_SINGLE_REGISTER; //function code
            intermediateValue = (short)add;
            intermediateValue >>= 8;
            tramePDU[1] = (byte)(intermediateValue & MASK);
            tramePDU[2] = (byte)(add & MASK);
            intermediateValue = (short)rvalue;
            intermediateValue >>= 8;
            tramePDU[3] = (byte)(intermediateValue & MASK);
            tramePDU[4] = (byte)(rvalue & MASK);
            
        }
        
        return tramePDU;
    }
    
    /** 
     * Build the request PDU to write each coil in a sequence of coil to either <b>ON</b> or <b>OFF</b> in a remote device.<br>
     * Modbus function code 0x0F.
     * @param st_add address of the first coil to be forced 
     * @param nb_coils number of coils to write
     * @param value array containing the sequences of value to be written
     * @return array of bytes which contains the request<br>
     * if error on parameters, generate ModbusException
     */
    public static byte[] writeMultipleCoils(int st_add, int nb_coils, byte[] value)throws ModbusException{
        
        /* variables declaration */
        byte[] tramePDU;
        int byteCount, rs;
        
        /* byteCount calcul */
        rs = nb_coils % 8;
        if (rs == 0){
            byteCount = nb_coils / 8;
        }
        else {
            //One adds 1 to the whole part of division.
            //To calculate this whole part one applies the principle of Euclidienne division.
            byteCount = ((nb_coils - rs)/8) + 1;
        }
        
        
        
        /* test of the parameters and filling of the array tramePDU */
        if ((st_add < MIN_ADDRESS)||(st_add > MAX_ADDRESS)){
            
            /* st_add parameter error */
            throw new ModbusException("No valid address !");
            
        }
        else if ((nb_coils < MIN_COIL)||(nb_coils > MAX_COIL)){
            
            /* nb_iregisters parameter error */
            throw new ModbusException("No valid number of coils !");
        }
        else if (value.length > MAX_SIZE_ARRAY_1){ //the size of the array must be lower than MAX_SIZE_ARRAY_1 = 246
            
            /* value parameter error */
            throw new ModbusException("The size of the values array must be lower than 246 bytes!");
        }
        else if (value.length != byteCount){
            
            /* correspondence between nb_coils and value error */
            throw new ModbusException("Correspondence between number of coils and values array error !");
        }
        else{
            
            /* variables declaration */
            int lengthTrame;
            short i, intermediateValue;
            
            /* calcul of the length of tramePDU */
            lengthTrame = byteCount + 6;
        
            /* creation of tramePDU */
            tramePDU = new byte[lengthTrame];
            
            /* filling of the array tramePDU */
            tramePDU[0] = WRITE_MULTIPLE_COILS; //function code
            intermediateValue = (short)st_add;
            intermediateValue >>= 8;
            tramePDU[1] = (byte)(intermediateValue & MASK);
            tramePDU[2] = (byte)(st_add & MASK);
            intermediateValue = (short)nb_coils;
            intermediateValue >>= 8;
            tramePDU[3] = (byte)(intermediateValue & MASK);
            tramePDU[4] = (byte)(nb_coils & MASK);
            tramePDU[5] = (byte)byteCount;
            
            for(i=0;i<byteCount;i++){
                tramePDU[6+i] = value[i];
            }             
        }
        
        return tramePDU;
        
    }
    
    /** 
     * Build the request PDU to write a block of contiguous registers (<b>1</b> to approx. <b>120</b> registers) in a remote device.<br>
     * Modbus function code 0x10.
     * @param st_add address of the first register to be written
     * @param nb_registers number of registers to write
     * @param value array containing the value to be written in registers
     * @return array of bytes which contains the request<br>
     * if error on parameters, generate ModbusException
     */
    public static byte[] writeMultipleRegisters(int st_add, int nb_registers, byte[] value)throws ModbusException{
        
        /* variables declaration */
        byte[] tramePDU;
        int byteCount;
        
        /* byteCount calcul */
        byteCount = nb_registers * 2;
        
        /* test of the parameters and filling of the array tramePDU */
        if ((st_add < MIN_ADDRESS)||(st_add > MAX_ADDRESS)){
            
            /* st_add parameter error */
            throw new ModbusException("No valid address !");
            
        }
        else if ((nb_registers < MIN_REGISTER)||(nb_registers > MAX_REGISTER)){
            
            /* nb_iregisters parameter error */
            throw new ModbusException("No valid number of registers !");
        }
        else if (value.length > MAX_SIZE_ARRAY_2){ //the size of the array must be lower than MAX_SIZE_ARRAY_2 = 240
            
            /* value parameter error */
            throw new ModbusException("The size of the values array must be lower than 240 bytes!");
        }
        else if (value.length != byteCount){
            
            /* correspondence between nb_coils and value error */
            throw new ModbusException("Correspondence between number of coils and values array error !");            
        }
        else{
            
            /* variables declaration */
            int lengthTrame, i;
            short intermediateValue;
            /* calcul of the length of tramePDU */
            lengthTrame = byteCount + 6;
        
             /* creation of tramePDU */
            tramePDU = new byte[lengthTrame];
            
            /* filling of the array tramePDU */
            tramePDU[0] = WRITE_MULTIPLE_REGISTERS; //function code
            intermediateValue = (short)st_add;
            intermediateValue >>= 8;
            tramePDU[1] = (byte)(intermediateValue & MASK);
            tramePDU[2] = (byte)(st_add & MASK);
            intermediateValue = (short)nb_registers;
            intermediateValue >>= 8;
            tramePDU[3] = (byte)(intermediateValue & MASK);
            tramePDU[4] = (byte)(nb_registers & MASK);
            tramePDU[5] = (byte)byteCount;
            
            for(i=0;i<byteCount;i++){
                tramePDU[6+i] = value[i];
            }     
            
        }
        
        return tramePDU;
                
    }
    
    /** 
     * Build the request PDU to performs a combination of one read operation and one write operation 
     * in a single MODBUS transaction.<br>
     * Modbus function code 0x17.
     * @param st_addRead address of the first register to reading
     * @param nb_registersRead number of registers to reading
     * @param st_addWrite address of the first register to be written
     * @param nb_registersWrite number of registers to be written
     * @param value array containing the value to be written in registers
     * @return array of bytes which contains the request<br>
     * if error on parameters, generate ModbusException
     */
    public static byte[] readWriteMultipleRegisters(int st_addRead, 
                                             int nb_registersRead, 
                                             int st_addWrite,
                                             int nb_registersWrite,
                                             byte[] value)throws ModbusException{
        
        /* variables declaration */
        byte[] tramePDU;
        int byteCount;
        
        /* byteCount calcul */
        byteCount = nb_registersWrite * 2;
        
        /* test of the parameters and filling of the array tramePDU */
        if ((st_addRead < MIN_ADDRESS)||(st_addRead > MAX_ADDRESS)){
            
            /* st_addRead parameter error */
            throw new ModbusException("No valid address of reading !");
            
        }
        else if((st_addWrite < MIN_ADDRESS)||(st_addWrite > MAX_ADDRESS)){
            
            /* st_addWrite parameter error */
            throw new ModbusException("No valid address of writting !");
            
        }
        else if ((nb_registersRead < MIN_REGISTER_READ)||(nb_registersRead > MAX_REGISTER_READ)){
            
            /* nb_registersRead parameter error */
            throw new ModbusException("No valid number of registers to read !");
        }
        else if ((nb_registersWrite < MIN_REGISTER_WRITE)||(nb_registersWrite > MAX_REGISTER_WRITE)){
            
            /* nb_registersWrite parameter error */
            throw new ModbusException("No valid number of registers to write !");
        }
        else if (value.length > MAX_SIZE_ARRAY_3){ //the size of the array must be lower than MAX_SIZE_ARRAY_3 = 236
            
            /* value parameter error */
            throw new ModbusException("The size of the values array must be lower than 236 bytes!");
        }
        else if (value.length != byteCount){
            
             /* correspondence between nb_coils and value error */
            throw new ModbusException("Correspondence between number of coils and values array error !");
        }
        else{
            
            /* variables declaration */
            int lengthTrame, i;
            short intermediateValue;
            
            /* calcul of the length of tramePDU */
            lengthTrame = byteCount + 10;
        
            /* creation of tramePDU */
            tramePDU = new byte[lengthTrame];
            
            /* filling of the array tramePDU */
            tramePDU[0] = READ_WRITE_MULTIPLE_REGISTERS; //function code
            intermediateValue = (short)st_addRead;
            intermediateValue >>= 8;
            tramePDU[1] = (byte)(intermediateValue & MASK);
            tramePDU[2] = (byte)(st_addRead & MASK);
            intermediateValue = (short)nb_registersRead;
            intermediateValue >>= 8;
            tramePDU[3] = (byte)(intermediateValue & MASK);
            tramePDU[4] = (byte)(nb_registersRead & MASK);
            intermediateValue = (short)st_addWrite;
            intermediateValue >>= 8;
            tramePDU[5] = (byte)(intermediateValue & MASK);
            tramePDU[6] = (byte)(st_addWrite & MASK);
            intermediateValue = (short)nb_registersWrite;
            intermediateValue >>= 8;
            tramePDU[7] = (byte)(intermediateValue & MASK);
            tramePDU[8] = (byte)(nb_registersWrite & MASK);
            tramePDU[9] = (byte)byteCount;
            
            for(i=0;i<byteCount;i++){
                tramePDU[10+i] = value[i];
            }
            
        }
        
        return tramePDU;
            
     }
    
    /** 
     * Build the request PDU to allows the identification and additional information relative to the 
     * physical and functional description of a remote device.<br>
     * Modbus function code 0x2B.<br>
     * Modbus Encapsulated Interface 0x0E.<br>
     * @param deviceIdCode specify the type of access :<br>
     * 01 : request to get the basic device identification (stream access),<br>
     * 02 : request to get the regular device identification (stream access),<br>
     * 03 : request to get the extended device identification (stream access),<br>
     * 04 : request to get one specific identification object (individual access).<br><br> 
     * @param objectId identify the first object to be obtained
     * @return array of 4 bytes which contains the request<br>
     * if error on parameters, generate ModbusException
     */
    public static byte[] readDeviceIdentification(byte deviceIdCode, char objectId)throws ModbusException{
        
        /* variable delaration */
        byte[] tramePDU = new byte[4];
        
        /* test of the parameters and filling of the array tramePDU */
        if ((deviceIdCode != 01)&&(deviceIdCode != 02)&&(deviceIdCode != 03)&&(deviceIdCode != 04)){
            
            /* deviceIdCode parameter error */
            throw new ModbusException("No valid device ID code !");
            
        }
        else if((objectId < MIN_OBJECTID)||(objectId > MAX_OBJECTID)){
            
            /* objectId parameter error */
            throw new ModbusException("No valid object ID !");
            
        }
        else{
            
            /* filling of the array tramePDU */
            tramePDU[0] = READ_DEVICE_IDENTIFICATION; //function code
            tramePDU[1] = 0x0E; //Modbus Encapsulated Interface
            tramePDU[2] = deviceIdCode;
            tramePDU[3] = (byte)objectId;
        }
        
        return tramePDU;
    }
    
    /** 
     * Check if the response frame returned by the Modbus server contains error codes.<br>
     * @param functionCode function code requested (first byte of the received frame)
     * @param exceptionCode exception code (seconde byte of received frame)
     * @return boolean<br>
     * true = error<br>
     * false = no error
     */
    public static boolean checkIfError(byte functionCode, byte exceptionCode){
        
        /* variable declaration */
        boolean resultCheck = false;
        
        /* test of the parameters and assignment of resultCheck according to the result */
        if((functionCode == (byte)0x81)||(functionCode == (byte)0x82)||(functionCode == (byte)0x83)||(functionCode == (byte)0x84)
        ||(functionCode == (byte)0x85)||(functionCode == (byte)0x86)||(functionCode == (byte)0x87)||(functionCode == (byte)0x8B)
        ||(functionCode == (byte)0x8C)||(functionCode == (byte)0x8F)||(functionCode == (byte)0x90)||(functionCode == (byte)0x91)
        ||(functionCode == (byte)0x94)||(functionCode == (byte)0x95)||(functionCode == (byte)0x96)||(functionCode == (byte)0x97)
        ||(functionCode == (byte)0x98)||(functionCode == (byte)0xAB)){
            
            if(exceptionCode == 0x01){
                errorType = "ERROR:ILLEGAL FUNCTION";
                resultCheck = true;
            }
            else if(exceptionCode == 0x02){
                errorType = "ERROR:ILLEGAL DATA ADDRESS";
                resultCheck = true;
            }
            else if(exceptionCode == 0x03){
                errorType = "ERROR:ILLEGAL DATA VALUE";
                resultCheck = true;
            }
            else if(exceptionCode == 0x04){
                errorType = "ERROR:SLAVE DEVICE FAILURE";
                resultCheck = true;
            }
            else if(exceptionCode == 0x05){
                errorType = "ACKNOWLEDGE";
                resultCheck = true;
            }
            else if(exceptionCode == 0x06){
                errorType = "ERROR:SLAVE DEVICE BUSY";
                resultCheck = true;
            }
            else if(exceptionCode == 0x08){
                errorType = "ERROR:MEMORY PARITY ERROR";
                resultCheck = true;
            }
            else if(exceptionCode == 0x0A){
                errorType = "ERROR:GATEWAY PATH UNAVAILABLE";
                resultCheck = true;
            }
            else if(exceptionCode == 0x0B){
                errorType = "ERROR:GATEWAY TARGET DEVICE FAILED TO RESPOND";
                resultCheck = true;
            }
        }
        else{
           errorType = "NO ERROR";
           resultCheck = false;
        }
        
        return resultCheck;
    }
    
    
    /** 
     * Return the error type.<br>
     * @return string which contains the error type
     */
    public static String getErrorType(){
        return errorType;
    }
    
    /** 
     * Extract data from frame PDU of Modbus response.<br>
     * @param receive_tramePDU frame PDU of Modbus response 
     * @return array which contains the data or return <b>127</b> if the function code doesn't existe<br>
     * or in case of modbus error code.
     */
    public static byte[] extractData(byte[] receive_tramePDU){
        
        /* variables declaration */
        byte byteCount, functionCode;
        byte[] dataArray = null;
        int fifoCount;
        short i;
        /* variable initialization */
        functionCode = receive_tramePDU[0];
        
        /* the data processing is done compared to function code returned by the Modbus server */
        
        //function code READ_COIL, READ_DISCRET_INPUTS, READ_HOLDING_REGISTERS, READ_INPUT_REGISTERS and READ_WRITE_MULTIPLE_REGISTERS
        if((functionCode == READ_COIL)||(functionCode == READ_DISCRET_INPUTS)||(functionCode == READ_HOLDING_REGISTERS)
        ||(functionCode == READ_INPUT_REGISTERS)||(functionCode == READ_WRITE_MULTIPLE_REGISTERS)){
            
            /* variables initialization */
            byteCount = receive_tramePDU[1];
            dataArray = new byte[byteCount];
            
            /* filling of dataArray */
            for(i=0;i<byteCount;i++){
                dataArray[i] = receive_tramePDU[2+i];
            }
        }
        
        //function code WRITE_SINGLE_COIL, WRITE_SINGLE_REGISTER, WRITE_MULTIPLE_COILS and WRITE_MULTIPLE_REGISTERS
        //in the case of these functions the frame contains an echo of what one requested from the server
        else if((functionCode == WRITE_SINGLE_COIL)||(functionCode == WRITE_SINGLE_REGISTER)||(functionCode == WRITE_MULTIPLE_COILS)
        ||(functionCode == WRITE_MULTIPLE_REGISTERS)){
            
            /* variable initialization */
            dataArray = new byte[4];
            
            /* filling of dataArray */
            for(i=0;i<4;i++){
                dataArray[i] = receive_tramePDU[1+i];
            }
        }
        
        //function code 0x14
        /*else if (functionCode == 0x14){
            (to do)
         }*/
        
        //function code 0x15
        /*else if (functionCode == 0x15){
            (to do)
         }*/
        
        //function code 0x16
        else if(functionCode == 0x16){
            
            /* variable initialization */
            dataArray = new byte[6];
            
            /* filling of dataArray */
            for(i=0;i<6;i++){
                dataArray[i] = receive_tramePDU[1+i];
            }
        }
        
        //function code 0x18
        else if(functionCode == 0x18){
            
            /* variable declaration */
            int intermediateValue;
            
            fifoCount = (int)receive_tramePDU[3];
            fifoCount <<=8;
            intermediateValue = (int)receive_tramePDU[4];
            fifoCount = fifoCount | intermediateValue;
            
            /* variable initialization */
            dataArray = new byte[(2*fifoCount)];
            
            /* filling of dataArray */
            for(i=0;i<(fifoCount*2);i++){
                dataArray[i] = receive_tramePDU[5+i];
            }
        }
        
        //function code 0x2B
        /*else if (functionCode == 0x2B){
            (to do)
         }*/
        
        //no function code or modbus error code
        else{
            
            /* variable initialization */
            dataArray = new byte[1];
            
            /* filling of dataArray */
            dataArray[0] = 127;
        }
        
        
        return dataArray;
        
    }
    
        
}
