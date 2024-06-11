/*
 * ModbusTest.java
 *
 * Created on 22 mars 2006, 17:45
 *
 * Programme permettant de tester le protocole modbus sur une ligne série
 * Equipement utilisé pour le test : TSX 3722 avec PCMCIA SCP 111 (Modbus/RS232).
 */

package ModbusSerial_test;
import schneider.ModbusSerial;
import schneider.ModbusSerialResponse;
import schneider.ModbusException;
import javax.comm.*;
import com.sun.comm.Win32Driver;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * @author Jocelyn GIROD
 * @author jocelyn.girod@wanadoo.fr
 */
public class ModbusTest {
    
    //variables declaration
    static InputStream inPort = null;
    static OutputStream outPort = null;
    static CommPortIdentifier portId = null;
    static SerialPort port = null;
    static ModbusSerial modbusSerial = new ModbusSerial();
    static ModbusSerialResponse modbusSerialResponse = new ModbusSerialResponse();
    static short add_slave = 11;
    
    /** Creates a new instance of ModbusTest */
    public ModbusTest() {
        
    }
    
    /**
     * Print data
     * @param no param 
     */
    private static void printData(byte[] datas){
        
        //variable declaration
        int lengthDatas = datas.length;
        
        //print decimal
        System.out.println("Affichage du résultat en Decimal :");
        for(short i=0;i<lengthDatas;i++){
            System.out.print(datas[i]+" ");
        }
        
        System.out.println(" ");
        
        //print hexadecimal
        System.out.println("Affichage du résultat en Hexadecimal :");
        for(short i=0;i<lengthDatas;i++){            
            System.out.print(Integer.toHexString(datas[i] & 0xff)+" ");
        }
        
        System.out.println(" ");
    }
    
    /** 
     * initialize serial port
     * @param nameOdPort the name of the serial port  
     */
    public static void initSerialPort(String nameOfPort){
        
        //variable declaration
        int rcvTimeOut = 100;
        
        //driver initialization
        Win32Driver w32Driver = new Win32Driver();
        w32Driver.initialize();
        
        //recovery of the port
        try{
            portId = CommPortIdentifier.getPortIdentifier(nameOfPort);
        }
        catch(NoSuchPortException ex){
            System.out.println("The port "+nameOfPort+" doesn't existe on that system !!!");
            System.exit(0);
        }
        
        //check is port available        
        try{
            port = (SerialPort)portId.open("SerialPortTest", 4000);
            
            //setting of serial port
            try{
                port.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
                port.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_EVEN);
                port.enableReceiveTimeout(rcvTimeOut);
            }
            catch(UnsupportedCommOperationException ex){
                System.out.println(" Parameter error !!!");
            }
        }
        catch(PortInUseException ex){
            System.out.println("Port "+nameOfPort+" in use !");
        }
    }
    
    /**
     * recovery of input stream and output stream
     */
    public static void streamRecovery(){
        
        try{
            outPort = port.getOutputStream();
            inPort = port.getInputStream();
        }
        catch(IOException e){}
    }
    
    /**
     * test the read of coils
     * @param no param
     */
    public static void testReadOfCoils(){
        
        //Modbus parameters
        int start_address = 0;
        int coils_number = 10;
        byte[] modbusFrame = null, datas = null;
        
        boolean resultCrcCheck;
        
        //Modbus frame
        try{
            modbusFrame = modbusSerial.readCoilsOnSerial(add_slave, start_address, coils_number);
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //send and receive on serial port
        byte[] receiveFrame = new byte[256];
        int nbBytesReaded;
        try{
            //write
            outPort.write(modbusFrame);
            //read
            nbBytesReaded = inPort.read(receiveFrame);
        }
        catch(IOException e){}
        
        //frame check
        resultCrcCheck = modbusSerialResponse.checkCrcTrameReceived(receiveFrame);
        if (resultCrcCheck){
            System.out.println("Erreur de CRC !");
            System.exit(0);
        }
        else{
            //check Modbus errors
            String errorMessage = modbusSerialResponse.checkIfModbusError(receiveFrame);
            System.out.println(errorMessage);
            
            //save datas
            datas = modbusSerialResponse.getData(receiveFrame);
            //print datas 
            printData(datas);
        }
    }
    
    /**
     * test the read of discret inputs
     * @param no param
     */
    public static void testReadDiscretInputs(){
        
        //Modbus parameters
        int start_address = 0;
        int inputs_number = 5;
        byte[] modbusFrame = null, datas = null;
        
        boolean resultCrcCheck;
        
        //Modbus frame
        try{
            modbusFrame = modbusSerial.readDiscretInputsOnSerial(add_slave, start_address, inputs_number);
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //send and receive on serial port
        byte[] receiveFrame = new byte[256];
        int nbBytesReaded;
        try{
            //write
            outPort.write(modbusFrame);
            //read
            nbBytesReaded = inPort.read(receiveFrame);
        }
        catch(IOException e){}
        
        //frame check
        resultCrcCheck = modbusSerialResponse.checkCrcTrameReceived(receiveFrame);
        if (resultCrcCheck){
            System.out.println("Erreur de CRC !");
            System.exit(0);
        }
        else{
            //check Modbus errors
            String errorMessage = modbusSerialResponse.checkIfModbusError(receiveFrame);
            System.out.println(errorMessage);
            
            //save datas
            datas = modbusSerialResponse.getData(receiveFrame);
            //print datas 
            printData(datas);
        }
    }
    
    /**
     * test read holding registers
     * @param no param
     */
    public static void testReadHoldingRegisters(){
        
        //Modbus parameters
        int start_address = 10;//%MW10
        int HRegisters_number = 20;//%MW10 -> %MW30
        byte[] modbusFrame = null, datas = null;
        
        boolean resultCrcCheck;
        
        //Modbus frame
        try{
            modbusFrame = modbusSerial.readHoldingRegistersOnSerial(add_slave, start_address, HRegisters_number);
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //send and receive on serial port
        byte[] receiveFrame = new byte[256];
        int nbBytesReaded;
        try{
            //write
            outPort.write(modbusFrame);
            //read
            nbBytesReaded = inPort.read(receiveFrame);
        }
        catch(IOException e){}
        
        //frame check
        resultCrcCheck = modbusSerialResponse.checkCrcTrameReceived(receiveFrame);
        if (resultCrcCheck){
            System.out.println("Erreur de CRC !");
            System.exit(0);
        }
        else{
            //check Modbus errors
            String errorMessage = modbusSerialResponse.checkIfModbusError(receiveFrame);
            System.out.println(errorMessage);
            
            //save datas
            datas = modbusSerialResponse.getData(receiveFrame);
            //print datas 
            printData(datas);
        }
    }
    
    /**
     * test read input registers
     * @param no param
     */
    public static void testReadInputRegisters(){
        
        //Modbus parameters
        int start_address = 0;//%IW0
        int IRegisters_number = 5;//%IW0 -> %IW5
        byte[] modbusFrame = null, datas = null;
        
        boolean resultCrcCheck;
        
        //Modbus frame
        try{
            modbusFrame = modbusSerial.readInputRegistersOnSerial(add_slave, start_address, IRegisters_number);
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //send and receive on serial port
        byte[] receiveFrame = new byte[256];
        int nbBytesReaded;
        try{
            //write
            outPort.write(modbusFrame);
            //read
            nbBytesReaded = inPort.read(receiveFrame);
        }
        catch(IOException e){}
        
        //frame check
        resultCrcCheck = modbusSerialResponse.checkCrcTrameReceived(receiveFrame);
        if (resultCrcCheck){
            System.out.println("Erreur de CRC !");
            System.exit(0);
        }
        else{
            //check Modbus errors
            String errorMessage = modbusSerialResponse.checkIfModbusError(receiveFrame);
            System.out.println(errorMessage);
            
            //save datas
            datas = modbusSerialResponse.getData(receiveFrame);
            //print datas 
            printData(datas);
        }
    }
    
    /**
     * test write single coil
     * @param no param
     */
    public static void testWriteSingleCoil(){
        
        //Modbus parameters
        int address = 100;//%M100
        boolean value = true;
        byte[] modbusFrame = null, datas = null;
        
        boolean resultCrcCheck;
        
        //Modbus frame
        try{
            modbusFrame = modbusSerial.writeSingleCoilOnSerial(add_slave, address, value);
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //send and receive on serial port
        byte[] receiveFrame = new byte[256];
        int nbBytesReaded;
        try{
            //write
            outPort.write(modbusFrame);
            //read
            nbBytesReaded = inPort.read(receiveFrame);
        }
        catch(IOException e){}
        
        //frame check
        resultCrcCheck = modbusSerialResponse.checkCrcTrameReceived(receiveFrame);
        if (resultCrcCheck){
            System.out.println("Erreur de CRC !");
            System.exit(0);
        }
        else{
            //check Modbus errors
            String errorMessage = modbusSerialResponse.checkIfModbusError(receiveFrame);
            System.out.println(errorMessage);
            
            //save datas
            datas = modbusSerialResponse.getData(receiveFrame);
            //print datas 
            printData(datas);
        }
    }
    
    /**
     * test write single register on serial
     * @param no param
     */
    public static void testWriteSingleRegister(){
        
        //Modbus parameters
        int address = 200;//%MW200
        int rvalue = 111;//%MW200 = 111
        byte[] modbusFrame = null, datas = null;
        
        boolean resultCrcCheck;
        
        //Modbus frame
        try{
            modbusFrame = modbusSerial.writeSingleRegisterOnSerial(add_slave, address, rvalue);
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //send and receive on serial port
        byte[] receiveFrame = new byte[256];
        int nbBytesReaded;
        try{
            //write
            outPort.write(modbusFrame);
            //read
            nbBytesReaded = inPort.read(receiveFrame);
        }
        catch(IOException e){}
        
        //frame check
        resultCrcCheck = modbusSerialResponse.checkCrcTrameReceived(receiveFrame);
        if (resultCrcCheck){
            System.out.println("Erreur de CRC !");
            System.exit(0);
        }
        else{
            //check Modbus errors
            String errorMessage = modbusSerialResponse.checkIfModbusError(receiveFrame);
            System.out.println(errorMessage);
            
            //save datas
            datas = modbusSerialResponse.getData(receiveFrame);
            //print datas 
            printData(datas);
        }
    }
    
    /**
     * test write multiple coils on serial 
     * @param no param
     */
    public static void testWriteMultipleCoils(){
        
        //Modbus parameters
        int start_address = 30;//%M30
        int nb_coils = 16;//%M30 -> %M46
        byte[] modbusFrame = null, datas = null, value = null;        
        boolean resultCrcCheck;
        
        //value initialisation
        value = new byte[2];
        value[0]= (byte)0xD7; //11010111
        value[1]= (byte)0xFF; //11111111
        
        //Modbus frame
        try{
            modbusFrame = modbusSerial.writeMultipleCoilsOnSerial(add_slave, start_address, nb_coils, value);
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //send and receive on serial port
        byte[] receiveFrame = new byte[256];
        int nbBytesReaded;
        try{
            //write
            outPort.write(modbusFrame);
            //read
            nbBytesReaded = inPort.read(receiveFrame);
        }
        catch(IOException e){}
        
        //frame check
        resultCrcCheck = modbusSerialResponse.checkCrcTrameReceived(receiveFrame);
        if (resultCrcCheck){
            System.out.println("Erreur de CRC !");
            System.exit(0);
        }
        else{
            //check Modbus errors
            String errorMessage = modbusSerialResponse.checkIfModbusError(receiveFrame);
            System.out.println(errorMessage);
            
            //save datas
            datas = modbusSerialResponse.getData(receiveFrame);
            //print datas 
            printData(datas);
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // TODO code application logic here
        System.out.println("------------------------");
        System.out.println("Test Modbus Serie RS 232");
        System.out.println("------------------------");
        System.out.println(" ");
                
        //init serial port
        initSerialPort("COM1");
        
        //Récupération du flux de lécture et d'écriture sur le port série
        streamRecovery();
        
        //1.test de la lecture de bobine dans l'automate TSX 37322
        testReadOfCoils();
        System.out.println("---------------------------------");
        
        //2.test de lecture d'entrées dans l'automate TSX 3722
        testReadDiscretInputs();
        System.out.println("---------------------------------");
        
        //3.test de lecture de %MW dans l'automate TSX3722
        testReadHoldingRegisters();
        System.out.println("---------------------------------");
        
        //4.test de la lecture de %IW dans l'automate TSX3722
        testReadInputRegisters();
        System.out.println("---------------------------------");
        
        //5.test de l'écriture de %M100 à true dans l'automate TSX3722
        testWriteSingleCoil();
        System.out.println("---------------------------------");
        
        //6.test de l'écriture de %MW200 à 111 dans l'automate TSX3722
        testWriteSingleRegister();
        System.out.println("---------------------------------");
        
        //7.test de l'écriture de 16 %M dans l'automate TSX3722
        testWriteMultipleCoils();
        System.out.println("---------------------------------");
        
  
    }
    
    
    
}