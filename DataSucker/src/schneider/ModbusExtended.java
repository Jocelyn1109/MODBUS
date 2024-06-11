/*
 * ModbusExtended.java
 *
 * Created on 7 novembre 2005, 11:13
 *
 */

package schneider;

/**
 * The class ModbusExtended provided functions specific for <b>serial line</b> only.<br>
 * @author Jocelyn GIROD
 * @author jocelyn.girod@wanadoo.fr
 * @version 1.0
 */
public class ModbusExtended extends Modbus{
    
    /**
    * Defines ReadExceptionStatus function code
    */
    private static final short READ_EXCEPTION_STATUS = 0x07;
    
    /**
    * Defines Diagnostics function code
    */
    private static final short DIAGNOSTICS = 0x08;
    
    /**
    * Defines getComEventCounter function code
    */
    private static final short GET_COM_EVENT_COUNTER = 0x0B;
    
    /**
    * Defines getComEventLog function code
    */
    private static final short GET_COM_EVENT_LOG = 0x0C;
    
    /**
    * Defines reportSlaveId
    */
    private static final short REPORT_SLAVE_ID = 0x11;
    
    /** Creates a new instance of ModbusExtended */
    public ModbusExtended() {
        super();
    }
    
    /** 
     * Build the request PDU to read the contents of eight <b>Exception Status</b> outputs in a remote device.<br>
     * Modbus function code 0x07.
     * @return a byte which contains the request<br>
     */
    public static byte readExceptionStatus(){
        return READ_EXCEPTION_STATUS;
    }
    
    
    /** 
     * Build the request PDU to provide a serie of tests for checking the communiation system between a client (Master)
     * device and a server (Slave).<br>
     * Modbus function code 0x08.
     * @param diagType specify the type of test to be performed :<br>
     * <b>0</b> :  Return Query Data<br>
     * <b>1</b> :  Restart Communication Option : clear the log<br>
     * <b>2</b> :  Restart Communication Option : leave the log<br>
     * <b>3</b> :  Return Diagnostic Register<br>
     * <b>4</b> :  Change ASCII Input Delimiter<br>
     * <b>5</b> :  Forced Listen Only Mode<br>
     * <b>6</b> :  Clean Counters and Diagnostic Registers<br>
     * <b>7</b> :  Return Bus Message Count<br>
     * <b>8</b> :  Return Bus Communication Error Count<br>
     * <b>9</b> :  Return Bus Exception Error Count<br>
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
    public static byte[] Diagnostics(short diagType, char CHAR)throws ModbusException{
        
        /* variables declaration */
        byte[] tramePDU = null;
        
        
        /* Test of parameter CHAR */
        if(CHAR > 255){
            
            /* character error */
            throw new ModbusException("Character error !");
        }
        else{
            switch(diagType){
            
                case 0 :  /* Return Query Data */
                          tramePDU = returnQueryData();
                          break;
                      
                case 1 :  /* Restart Communication Option : clear the log */
                          tramePDU = restartComOptionClearLog();
                          break;
                      
                case 2 :  /* Restart Communication Option : leave the log */
                          tramePDU = restartComOptionLeaveLog();
                          break;
                      
                case 3 :  /* Return Diagnostic Register */
                          tramePDU = returnDiagRegister();
                          break;
                      
                case 4 :  /* Change ASCII Input Delimiter */
                          tramePDU = changeInputDelimiter(CHAR);
                          break;
                      
                case 5 :  /* Forced Listen Only Mode */
                          tramePDU = forcedListenOnlyMode();
                          break;
                      
                case 6 :  /* Clear Counter and Diagnostic Registers */
                          tramePDU = clearCountersAndDiagRegisters();
                          break;
                      
                case 7 :  /* Return Bus Message Count */
                          tramePDU = returnBusMessageCount();
                          break;
                      
                case 8 :  /* Return Bus Communication Error Count */
                          tramePDU = returnBusComErrCount();
                          break;
                      
                case 9 :  /* Return Bus Exception Error Count */
                          tramePDU = returnBusExceptionErrCount();
                          break;
                      
                case 10 : /* Return Slave Message Count */
                          tramePDU = returnSlaveMsgCount();
                          break;
                      
                case 11 : /* Return Slave No Response Count */
                          tramePDU = returnSlaveNoMsgCount();
                          break;
                      
                case 12 : /* Return Slave NAK Count */
                          tramePDU = returnSlaveNakCount();
                          break;
                      
                case 13 : /* Return Slave Busy Count */
                          tramePDU = returnSlaveBusyCount();
                          break;
                      
                case 14 : /* Return Bus Character Overrun Count */
                          tramePDU = returnBusCharOverrunCount();
                          break;
                      
                case 15 : /* Clear Overrun Counter and Flag */
                          tramePDU = clearOverrunCounterAndFlag();
                          break;
                      
                default : 
                          throw new ModbusException("diagType not define !");
          }
        }
        
        return tramePDU;
    
    }
    
    /* Return Query Data - frame construction */
    private static byte[] returnQueryData(){
        
        /* variable declaration */
        byte[] frame = new byte[3];
        
        /* set frame */
        frame[0] = DIAGNOSTICS; //function code
        frame[1] = 0x00; //sub-function Hi
        frame[2] = 0x00; //sub-function Lo
        
        return frame;
    }
    
    /* Restart Communication Option : clear the log  - frame construction */
    private static byte[] restartComOptionClearLog(){
        
        /* variable declaration */
        byte[] frame = new byte[5];
        
        /* set frame */
        frame[0] = DIAGNOSTICS; //function code
        frame[1] = 0x00; //sub-function Hi
        frame[2] = 0x01; //sub-function Lo
        frame[3] = (byte)0xFF; //data
        frame[4] = 0x00; //data
        
        return frame;
    }
    
    /* Restart Communication Option : leave the log  - frame construction */
    private static byte[] restartComOptionLeaveLog(){
        
        /* variable declaration */
        byte[] frame = new byte[5];
        
        /* set frame */
        frame[0] = DIAGNOSTICS; //function code
        frame[1] = 0x00; //sub-function Hi
        frame[2] = 0x01; //sub-function Lo
        frame[3] = 0x00; //data
        frame[4] = 0x00; //data
        
        return frame;
        
    }
    
    /* Return Diagnostic Register - frame construction */
    private static byte[] returnDiagRegister(){
        
        /* variable declaration */
        byte[] frame = new byte[5];
        
        /* set frame */
        frame[0] = DIAGNOSTICS; //function code
        frame[1] = 0x00; //sub-function Hi
        frame[2] = 0x02; //sub-function Lo
        frame[3] = 0x00; //data
        frame[4] = 0x00; //data
        
        return frame;
        
    }
    
    /* Change ASCII Input Delimiter - frame construction */
    private static byte[] changeInputDelimiter(char charDelimiter){
        
        /* variable declaration */
        byte[] frame = new byte[5];
        
        /* set frame */
        frame[0] = DIAGNOSTICS; //function code
        frame[1] = 0x00; //sub-function Hi
        frame[2] = 0x03; //sub-function Lo
        frame[3] = (byte)charDelimiter; //character delimiter
        frame[4] = 0x00; //data
        
        return frame;
    }
    
    /* Forced Listen Only Mode */
    private static byte[] forcedListenOnlyMode(){
        
        /* variable declaration */
        byte[] frame = new byte[5];
        
        /* set frame */
        frame[0] = DIAGNOSTICS; //function code
        frame[1] = 0x00; //sub-function Hi
        frame[2] = 0x04; //sub-function Lo
        frame[3] = 0x00; //data
        frame[4] = 0x00; //data
        
        return frame;
    }
    
    /* Clear Counters and Diagnostic Registers - frame construction */
    private static byte[] clearCountersAndDiagRegisters(){
        
        /* variable declaration */
        byte[] frame = new byte[5];
        
        /* set frame */
        frame[0] = DIAGNOSTICS; //function code
        frame[1] = 0x00; //sub-function Hi
        frame[2] = 0x0A; //sub-function Lo
        frame[3] = 0x00; //data
        frame[4] = 0x00; //data
        
        return frame;
    }
    
    /* Return Bus message Count */
    private static byte[] returnBusMessageCount(){
        
        /* variable declaration */
        byte[] frame = new byte[5];
        
        /* set frame */
        frame[0] = DIAGNOSTICS; //function code
        frame[1] = 0x00; //sub-function Hi
        frame[2] = 0x0B; //sub-function Lo
        frame[3] = 0x00; //data
        frame[4] = 0x00; //data
        
        return frame;
    }
    
    /* Return Bus Communication Error Count - frame construction */
    private static byte[] returnBusComErrCount(){
        
        /* variable declaration */
        byte[] frame = new byte[5];
        
        /* set frame */
        frame[0] = DIAGNOSTICS; //function code
        frame[1] = 0x00; //sub-function Hi
        frame[2] = 0x0C; //sub-function Lo
        frame[3] = 0x00; //data
        frame[4] = 0x00; //data
        
        return frame;
    }
    
    /* Return Bus Exception Error Count - frame construction */
    private static byte[] returnBusExceptionErrCount(){
        
        /* variable declaration */
        byte[] frame = new byte[5];
        
        /* set frame */
        frame[0] = DIAGNOSTICS; //function code
        frame[1] = 0x00; //sub-function Hi
        frame[2] = 0x0D; //sub-function Lo
        frame[3] = 0x00; //data
        frame[4] = 0x00; //data
        
        return frame;
        
    }
    
    /* Return Slave Message Count - frame construction */
    private static byte[] returnSlaveMsgCount(){
        
        /* variable declaration */
        byte[] frame = new byte[5];
        
        /* set frame */
        frame[0] = DIAGNOSTICS; //function code
        frame[1] = 0x00; //sub-function Hi
        frame[2] = 0x0E; //sub-function Lo
        frame[3] = 0x00; //data
        frame[4] = 0x00; //data
        
        return frame;
        
    }
    
    /* Return Slave No Response Count - frame construction */
    private static byte[] returnSlaveNoMsgCount(){
        
        /* variable declaration */
         byte[] frame = new byte[5];
        
        /* set frame */
        frame[0] = DIAGNOSTICS; //function code
        frame[1] = 0x00; //sub-function Hi
        frame[2] = 0x0F; //sub-function Lo
        frame[3] = 0x00; //data
        frame[4] = 0x00; //data
        
        return frame;
        
    }
    
    /* Return Slave NAK Count - frame construction */
    private static byte[] returnSlaveNakCount(){
        
        /* variable declaration */
         byte[] frame = new byte[5];
        
        /* set frame */
        frame[0] = DIAGNOSTICS; //function code
        frame[1] = 0x00; //sub-function Hi
        frame[2] = 0x10; //sub-function Lo
        frame[3] = 0x00; //data
        frame[4] = 0x00; //data
        
        return frame;
        
    }
    
    /* Return Slave Busy Count - frame construction */
    private static byte[] returnSlaveBusyCount(){
        
        /* variable dclaration */
        byte[] frame = new byte[5];
        
        /* set frame */
        frame[0] = DIAGNOSTICS; //function code
        frame[1] = 0x00; //sub-function Hi
        frame[2] = 0x11; //sub-function Lo
        frame[3] = 0x00; //data
        frame[4] = 0x00; //data
        
        return frame;
        
    }
    
    /* Return Bus Character Overrun Count - frame construction */
    private static byte[] returnBusCharOverrunCount(){
        
        /* variable dclaration */
        byte[] frame = new byte[5];
        
        /* set frame */
        frame[0] = DIAGNOSTICS; //function code
        frame[1] = 0x00; //sub-function Hi
        frame[2] = 0x12; //sub-function Lo
        frame[3] = 0x00; //data
        frame[4] = 0x00; //data
        
        return frame;
        
    }
    
    /* Clear Overrun Counter and Flag */
    private static byte[] clearOverrunCounterAndFlag(){
        
       /* variable dclaration */
        byte[] frame = new byte[5];
        
        /* set frame */
        frame[0] = DIAGNOSTICS; //function code
        frame[1] = 0x00; //sub-function Hi
        frame[2] = 0x14; //sub-function Lo
        frame[3] = 0x00; //data
        frame[4] = 0x00; //data
        
        return frame;
         
    }
    
    /** 
     * Build the request PDU to get a status word and an event count from the remote device's<br>
     * communication event count.<br>
     * Modbus function code 0x0B.
     * @return a byte which contains the request<br>
     */
    public static byte getComEventCounter(){
        return GET_COM_EVENT_COUNTER ;
    }
    
    /** 
     * Build the request PDU to get a status word, event count, message count, and a field<br>
     * of event bytes from the remote device.<br>
     * Modbus function code 0x0C.
     * @return a byte which contains the request<br>
     */
    public static byte getComEventLog(){
        return GET_COM_EVENT_LOG;
    }
    
    /** 
     * Build the request PDU to read the description of the type, the current status, and other<br>
     * information specific to a remote device.<br>
     * Modbus function code 0x11.
     * @return a byte which contains the request<br>
     */
    public static byte reportSlaveId(){
        return REPORT_SLAVE_ID;
    }
   
    /** 
     * Extract data from frame PDU of Modbus response.<br>
     * Add the functions specific to the serial line.<br>
     * @param receive_tramePDU frame PDU of Modbus response 
     * @return array which contains the data
     */
    public static byte[] extractData(byte[] receive_tramePDU){
        
        /* variables declaration */
        byte[] dataArray = null;
        byte byteCount, functionCode;
        short i = 0;
        
        /* variable initialization */
        functionCode = receive_tramePDU[0];
        
        if((functionCode == READ_EXCEPTION_STATUS)||(functionCode == GET_COM_EVENT_COUNTER)||(functionCode == GET_COM_EVENT_LOG)
            ||(functionCode == 0x08)||(functionCode == REPORT_SLAVE_ID)){
        
                //function code READ_EXCEPTION_STATUS
                if(functionCode == READ_EXCEPTION_STATUS){
            
                    /* variable initialization */
                    dataArray = new byte[1];
            
                    /* filling of dataArray */
                    dataArray[0] = receive_tramePDU[1];  
                }
        
                //function code 0x08
                /*if (functionCode == 0x08){
                (to do)
                }*/
        
                //function code GET_COM_EVENT_COUNTER
                //in the case of that function the frame contains an echo of what one requested from the server
                if(functionCode == GET_COM_EVENT_COUNTER){
            
                    /* variable initialization */
                    dataArray = new byte[4];
            
                    /* filling of dataArray */
                    for(i=0;i<4;i++){
                        dataArray[i] = receive_tramePDU[1+i];
                    }
                }
        
                //function code GET_COM_EVENT_LOG
                if(functionCode == GET_COM_EVENT_LOG){
            
                    /* variables initialization */
                    byteCount = receive_tramePDU[1];
                    dataArray = new byte[byteCount];
            
                    /* filling of dataArray */
                    for(i=0;i<6+(byteCount-6);i++){
                        dataArray[i] = receive_tramePDU[2+i];
                    }
                }
        
                //function code REPORT_SLAVE_ID
                /*if (functionCode == REPORT_SLAVE_ID){
                (to do)
                }*/
        }
        else{
            dataArray = Modbus.extractData(receive_tramePDU);  
        }
        
        
        return dataArray;
    }
}
