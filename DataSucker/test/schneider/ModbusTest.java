/*
 * ModbusTest.java
 * JUnit based test
 *
 * Created on 12 octobre 2005, 10:17
 */

package schneider;

import junit.framework.*;

/**
 *
 * @author Merlin
 */
public class ModbusTest extends TestCase {
    
    Modbus modbus = null;
    
    public ModbusTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
        //modbus = new Modbus();
        //Assert.assertEquals("NO ERROR",modbus.getErrorType());
    }

    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(ModbusTest.class);
        
        return suite;
    }

    /**
     * Test of readCoils method, of class schneider.Modbus.
     */
    public void testReadCoils() {
        System.out.println("----------------------------");
        System.out.println("test method Modbus.readCoils");
        modbus = new Modbus();
        byte[] framePDU = null;
        int startAddress = 23; //0x17
        int nb_coils = 50; //0x32 
        
        // TODO add your test code below by replacing the default call to fail.
        //No error
        try{
            framePDU = modbus.readCoils(startAddress, nb_coils);
            
            Assert.assertEquals("Erreur sur la taille de la trame retournée",5,framePDU.length);
            Assert.assertEquals("Erreur sur framePDU[0]",0x01,framePDU[0]);
            Assert.assertEquals("Erreur sur framePDU[1]",0x00,framePDU[1]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x17,framePDU[2]);
            Assert.assertEquals("Erreur sur framePDU[3]",0x00,framePDU[3]);
            Assert.assertEquals("Erreur sur framePDU[4]",0x32,framePDU[4]);
            System.out.println("Method OK !");
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());  
        }
        
        //Error on startAddress
        startAddress = 66100; //0x10234
        nb_coils = 50; //0x32
        try{
            framePDU = modbus.readCoils(startAddress, nb_coils);
            //Assert.assertEquals("Erreur sur la taille de la trame retournée",5,framePDU.length);
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());  
        }
        
        //Error on nb_coils
        startAddress = 23; //0x17
        nb_coils = 2075; //0x81B
        try{
            framePDU = modbus.readCoils(startAddress, nb_coils);
            //Assert.assertEquals("Erreur sur la taille de la trame retournée",5,framePDU.length); 
        
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());  
        }
        
    }

    /**
     * Test of readDiscretInputs method, of class schneider.Modbus.
     */
    public void testReadDiscretInputs() {
        System.out.println("------------------------------------");
        System.out.println("test method Modbus.readDiscretInputs");
        modbus = new Modbus();
        byte[] framePDU = null;
        int startAddress = 111;//0x6F
        int nb_inputs = 138;//0x88
        
        // TODO add your test code below by replacing the default call to fail.
        //No error
        try{
            framePDU = modbus.readDiscretInputs(startAddress, nb_inputs);
            
            Assert.assertEquals("Erreur sur la taille de la trame retournée",5,framePDU.length);
            Assert.assertEquals("Erreur sur framePDU[0]",0x02,framePDU[0]);
            Assert.assertEquals("Erreur sur framePDU[1]",0x00,framePDU[1]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x6F,framePDU[2]);
            Assert.assertEquals("Erreur sur framePDU[3]",0x00,framePDU[3]);
            Assert.assertEquals("Erreur sur framePDU[4]",(byte)0x88,framePDU[4]);
            System.out.println("Method OK !");
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //Error on startAddress
        startAddress = 70000; //0x11170
        nb_inputs = 136; //0x88
        try{
            framePDU = modbus.readDiscretInputs(startAddress, nb_inputs);
            //Assert.assertEquals("Erreur sur la taille de la trame retournée",5,framePDU.length);
           
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //Error on nb_inputs
        startAddress = 111; //0x6F
        nb_inputs = 4602; //0x11FA
        try{
            framePDU = modbus.readDiscretInputs(startAddress, nb_inputs);
            //Assert.assertEquals("Erreur sur la taille de la trame retournée",5,framePDU.length);
           
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
    }

    /**
     * Test of readHoldingRegisters method, of class schneider.Modbus.
     */
    public void testReadHoldingRegisters() {
        System.out.println("---------------------------------------");
        System.out.println("test method Modbus.readHoldingRegisters");
        modbus = new Modbus();
        byte[] framePDU = null;
        int startAddress = 125; //0x7D
        int nb_hregisters = 107; //0x6B
        
        // TODO add your test code below by replacing the default call to fail.
        //No error
        try{
            framePDU = modbus.readHoldingRegisters(startAddress, nb_hregisters);
            
            Assert.assertEquals("Erreur sur la taille de la trame retournée",5,framePDU.length);
            Assert.assertEquals("Erreur sur framePDU[0]",0x03,framePDU[0]);
            Assert.assertEquals("Erreur sur framePDU[1]",0x00,framePDU[1]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x7D,framePDU[2]);
            Assert.assertEquals("Erreur sur framePDU[3]",0x00,framePDU[3]);
            Assert.assertEquals("Erreur sur framePDU[4]",0x6B,framePDU[4]);
            System.out.println("Method OK !");
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //Error on startAddress
        startAddress = -1; //0xFFFFFFFF
        nb_hregisters = 107; //0x6B
        try{
            framePDU = modbus.readHoldingRegisters(startAddress, nb_hregisters);
            //Assert.assertEquals("Erreur sur la taille de la trame retournée",5,framePDU.length);
           
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //Error on nb_hregisters
        startAddress = 125; //0x7D
        nb_hregisters = 126; //0x7E
        try{
            framePDU = modbus.readHoldingRegisters(startAddress, nb_hregisters);
            Assert.assertEquals("Erreur sur la taille de la trame retournée",5,framePDU.length);
           
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        
        }
    }

    /**
     * Test of readInputRegisters method, of class schneider.Modbus.
     */
    public void testReadInputRegisters() {
        System.out.println("-------------------------------------");
        System.out.println("test method Modbus.readInputRegisters");
        modbus = new Modbus();
        byte[] framePDU = null;
        int startAddress = 2; //0x02
        int nb_iregisters = 20; //0x14
        
        // TODO add your test code below by replacing the default call to fail.
        //No error
        try{
            framePDU = modbus.readInputRegisters(startAddress, nb_iregisters);
            
            Assert.assertEquals("Erreur sur la taille de la trame retournée",5,framePDU.length);
            Assert.assertEquals("Erreur sur framePDU[0]",0x04,framePDU[0]);
            Assert.assertEquals("Erreur sur framePDU[1]",0x00,framePDU[1]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x02,framePDU[2]);
            Assert.assertEquals("Erreur sur framePDU[3]",0x00,framePDU[3]);
            Assert.assertEquals("Erreur sur framePDU[4]",0x14,framePDU[4]);
            System.out.println("Method OK !");
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //Error on startAddress
        startAddress = 65538; //0x10002
        nb_iregisters = 20; //0x14
        try{
            framePDU = modbus.readInputRegisters(startAddress, nb_iregisters);            
            //Assert.assertEquals("Erreur sur la taille de la trame retournée",5,framePDU.length);
        
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //Error on nb_iregisters
        startAddress = 2; //0x02
        nb_iregisters = 0; //0x0
        try{
            framePDU = modbus.readInputRegisters(startAddress, nb_iregisters);            
            //Assert.assertEquals("Erreur sur la taille de la trame retournée",5,framePDU.length);
        
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
    }

    /**
     * Test of writeSingleCoil method, of class schneider.Modbus.
     */
    public void testWriteSingleCoil() {
        System.out.println("----------------------------------");
        System.out.println("test method Modbus.writeSingleCoil");
        modbus = new Modbus();
        byte[] framePDU = null;
        int address = 1;
        boolean value = false;
        
        // TODO add your test code below by replacing the default call to fail.
        //No error and value = false
        try{
            framePDU = modbus.writeSingleCoil(address, value);
            
            Assert.assertEquals("Erreur sur la taille de la trame retournée",5,framePDU.length);
            Assert.assertEquals("Erreur sur framePDU[0]",0x05,framePDU[0]);
            Assert.assertEquals("Erreur sur framePDU[1]",0x00,framePDU[1]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x01,framePDU[2]);
            Assert.assertEquals("Erreur sur framePDU[3]",0x00,framePDU[3]);
            Assert.assertEquals("Erreur sur framePDU[4]",0x00,framePDU[4]);
            System.out.println("Method OK !");
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //No error and value = true
        value = true;
        try{
            framePDU = modbus.writeSingleCoil(address, value);
            
            Assert.assertEquals("Erreur sur la taille de la trame retournée",5,framePDU.length);
            Assert.assertEquals("Erreur sur framePDU[0]",0x05,framePDU[0]);
            Assert.assertEquals("Erreur sur framePDU[1]",0x00,framePDU[1]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x01,framePDU[2]);
            Assert.assertEquals("Erreur sur framePDU[3]",(byte)0xFF,framePDU[3]);
            Assert.assertEquals("Erreur sur framePDU[4]",0x00,framePDU[4]);
            System.out.println("Method OK !");
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //Error on address
        address = -2;//0xFFFFFFFE
        try{
            framePDU = modbus.writeSingleCoil(address,value);
            //Assert.assertEquals("Erreur sur la taille de la trame retournée",5,framePDU.length);
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
    }

    /**
     * Test of writeSingleRegister method, of class schneider.Modbus.
     */
    public void testWriteSingleRegister() {
        System.out.println("--------------------------------------");
        System.out.println("test method Modbus.writeSingleRegister");
        modbus = new Modbus();
        byte[] framePDU = null;
        int address = 14551;//0x38D7
        int registerValue = 200;//0xC8
        
        // TODO add your test code below by replacing the default call to fail.
        //No error
        try{
            framePDU = modbus.writeSingleRegister(address, registerValue);
            
            Assert.assertEquals("Erreur sur la taille de la trame retournée",5,framePDU.length);
            Assert.assertEquals("Erreur sur framePDU[0]",0x06,framePDU[0]);
            Assert.assertEquals("Erreur sur framePDU[1]",0x38,framePDU[1]);
            Assert.assertEquals("Erreur sur framePDU[2]",(byte)0xD7,framePDU[2]);
            Assert.assertEquals("Erreur sur framePDU[3]",0x00,framePDU[3]);
            Assert.assertEquals("Erreur sur framePDU[4]",(byte)0xC8,framePDU[4]);
            System.out.println("Method OK !");
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //Error on address
        address = 65536;//0x10000
        try{
            framePDU = modbus.writeSingleRegister(address, registerValue);
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //Error on registerValue
        address = 100;//0x64
        registerValue = -15;//0xFFFFFFF1
        try{
            framePDU = modbus.writeSingleRegister(address, registerValue);
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
    }


    /**
     * Test of writeMultipleCoils method, of class schneider.Modbus.
     */
    public void testWriteMultipleCoils() {
        System.out.println("--------------------------------------");
        System.out.println("test method Modbus.writeMultipleCoils");
        modbus = new Modbus();
        byte[] framePDU = null;
        byte[] value = new byte[4];
        byte byteCount;
        int startAddress = 554;
        int nb_coils = 25;
        int lengthFrame;
        
        // TODO add your test code below by replacing the default call to fail.
        // No error
        value[0] = 0x01;
        value[1] = 0x10;
        value[2] = 0x0A;
        value[3] = (byte)0xAA;
        try{
            framePDU = modbus.writeMultipleCoils(startAddress, nb_coils, value);
            
            byteCount = framePDU[5];
            lengthFrame = 6 + (int)byteCount;
            Assert.assertEquals("Erreur sur la taille de la trame retournée",lengthFrame,framePDU.length);
            Assert.assertEquals("Erreur sur framePDU[0]",0x0F,framePDU[0]);
            Assert.assertEquals("Erreur sur framePDU[1]",0x02,framePDU[1]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x2A,framePDU[2]);
            Assert.assertEquals("Erreur sur framePDU[3]",0x00,framePDU[3]);
            Assert.assertEquals("Erreur sur framePDU[4]",0x19,framePDU[4]);
            Assert.assertEquals("Erreur sur framePDU[5], valeur du byteCount",4,framePDU[5]);
            Assert.assertEquals("Erreur sur framePDU[6]",0x01,framePDU[6]);
            Assert.assertEquals("Erreur sur framePDU[7]",0x10,framePDU[7]);
            Assert.assertEquals("Erreur sur framePDU[8]",0x0A,framePDU[8]);
            Assert.assertEquals("Erreur sur framePDU[9]",(byte)0xAA,framePDU[9]);            
            System.out.println("Method OK !");
            
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
       //Error on startAddress
       startAddress = 70000;//0x11170
       try{
            framePDU = modbus.writeMultipleCoils(startAddress, nb_coils, value);            
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
       
       //Error on nb_coils < 1
       startAddress = 12;//0xC
       nb_coils = 0;
       try{
           framePDU = modbus.writeMultipleCoils(startAddress, nb_coils, value);
       }
       catch(ModbusException e){
           System.out.println(e.getMessage());
       }
       
       //Error on nb_coils > 1968
       nb_coils = 1970;//0x7B2
       try{
           framePDU = modbus.writeMultipleCoils(startAddress, nb_coils, value);
       }
       catch(ModbusException e){
           System.out.println(e.getMessage());
       }
       
       //Error on value
       value = new byte[250];
       nb_coils = 15;//0xF;
       try{
           framePDU = modbus.writeMultipleCoils(startAddress, nb_coils, value);
       }
       catch(ModbusException e){
           System.out.println(e.getMessage());
       }
       
       //Erro on value
       value = new byte[60];
       nb_coils = 500;//0x1F4
       try{
           framePDU = modbus.writeMultipleCoils(startAddress, nb_coils, value);
       }
       catch(ModbusException e){
           System.out.println(e.getMessage());
       }
       
       
        
       
    }

    /**
     * Test of writeMultipleRegisters method, of class schneider.Modbus.
     */
    public void testWriteMultipleRegisters() {
        System.out.println("------------------------------------");
        System.out.println("test method Modbus.multipleRegisters");
        modbus = new Modbus();
        byte[] framePDU = null;
        byte[] value = new byte[4];
        byte byteCount;
        int startAddress = 2;
        int nb_registers = 2;
        int lengthFrame;
        
        
        // TODO add your test code below by replacing the default call to fail.
        //No error
        value[0] = 0x00;
        value[1] = 0x0A;
        value[2] = 0x01;
        value[3] = 0x02;
        byteCount = (byte)(nb_registers * 2);
        lengthFrame = 6 + (nb_registers * 2);
        try{
            framePDU = modbus.writeMultipleRegisters(startAddress,nb_registers,value);
            
            Assert.assertEquals("Erreur sur la taille de la trame retournée",lengthFrame,framePDU.length);
            Assert.assertEquals("Erreur sur framePDU[0]",0x10,framePDU[0]);
            Assert.assertEquals("Erreur sur framePDU[1]",0x00,framePDU[1]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x02,framePDU[2]);
            Assert.assertEquals("Erreur sur framePDU[3]",0x00,framePDU[3]);
            Assert.assertEquals("Erreur sur framePDU[4]",0x02,framePDU[4]);
            Assert.assertEquals("Erreur sur framePDU[5], valeur du byteCount",byteCount,framePDU[5]);
            Assert.assertEquals("Erreur sur framePDU[6]",0x00,framePDU[6]);
            Assert.assertEquals("Erreur sur framePDU[7]",0x0A,framePDU[7]);
            Assert.assertEquals("Erreur sur framePDU[8]",0x01,framePDU[8]);
            Assert.assertEquals("Erreur sur framePDU[9]",0x02,framePDU[9]);
            System.out.println("Method OK !");
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //Error on startAddress
        startAddress = -2;//0xFFFFFFFE
        try{
            framePDU = modbus.writeMultipleRegisters(startAddress,nb_registers,value);
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //Error on nb_registers < 1
        startAddress = 10;//0xA
        nb_registers = 0;//0X0
        try{
            framePDU = modbus.writeMultipleRegisters(startAddress,nb_registers,value);
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //Error on nb_registers > 120
        nb_registers = 124;//0x7C
        try{
            framePDU = modbus.writeMultipleRegisters(startAddress,nb_registers,value);
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //Error on value
        nb_registers = 110;//0x6E
        value = new byte[242];
        try{
            framePDU = modbus.writeMultipleRegisters(startAddress,nb_registers,value);
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //Error on value
        nb_registers = 4;//0x4
        value = new byte[4];
        try{
            framePDU = modbus.writeMultipleRegisters(startAddress,nb_registers,value);
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Test of readWriteMultipleRegisters method, of class schneider.Modbus.
     */
    public void testReadWriteMultipleRegisters() {
        System.out.println("---------------------------------------------");
        System.out.println("test method Modbus.readWriteMultipleRegisters");
        modbus = new Modbus();
        int startAddressRead = 4, startAddressWrite = 15, nb_registersRead = 6, nb_registersWrite = 3;
        int lengthFrame;
        byte[] value = new byte[6];
        byte[] framePDU = null; 
        byte byteCount;
        
        // TODO add your test code below by replacing the default call to fail.
        // No error
        value[0] = 0x00;
        value[1] = (byte)0xFF;
        value[2] = 0x00;
        value[3] = (byte)0xFF;
        value[4] = 0x00;
        value[5] = (byte)0xFF;
        byteCount = (byte)(nb_registersWrite * 2);
        lengthFrame = 10 + (int)byteCount;
        try{
            framePDU = modbus.readWriteMultipleRegisters(startAddressRead,nb_registersRead,startAddressWrite,nb_registersWrite,value);
            
            Assert.assertEquals("Erreur sur la taille de la trame retournée",lengthFrame,framePDU.length);
            Assert.assertEquals("Erreur sur framePDU[0]",0x17,framePDU[0]);
            Assert.assertEquals("Erreur sur framePDU[1]",0x00,framePDU[1]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x04,framePDU[2]);
            Assert.assertEquals("Erreur sur framePDU[3]",0x00,framePDU[3]);
            Assert.assertEquals("Erreur sur framePDU[4]",0x06,framePDU[4]);
            Assert.assertEquals("Erreur sur framePDU[5]",0x00,framePDU[5]);
            Assert.assertEquals("Erreur sur framePDU[6]",0x0F,framePDU[6]);
            Assert.assertEquals("Erreur sur framePDU[7]",0x00,framePDU[7]);
            Assert.assertEquals("Erreur sur framePDU[8]",0x03,framePDU[8]);
            Assert.assertEquals("Erreur sur framePDU[9], valeur du byteCount",byteCount,framePDU[9]);
            Assert.assertEquals("Erreur sur framePDU[10]",0x00,framePDU[10]);
            Assert.assertEquals("Erreur sur framePDU[11]",(byte)0xFF,framePDU[11]);
            Assert.assertEquals("Erreur sur framePDU[12]",0x00,framePDU[12]);
            Assert.assertEquals("Erreur sur framePDU[13]",(byte)0xFF,framePDU[13]);
            Assert.assertEquals("Erreur sur framePDU[14]",0x00,framePDU[14]);
            Assert.assertEquals("Erreur sur framePDU[15]",(byte)0xFF,framePDU[15]);
            System.out.println("Method OK !");
            
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //Error on startAddressRead
        startAddressRead = 66000;//0x101D0
        try{
            framePDU = modbus.readWriteMultipleRegisters(startAddressRead,nb_registersRead,startAddressWrite,nb_registersWrite,value);            
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //Error on nb_registersRead < 1
        startAddressRead = 458;//0x1CA
        nb_registersRead = 0;
        try{
            framePDU = modbus.readWriteMultipleRegisters(startAddressRead,nb_registersRead,startAddressWrite,nb_registersWrite,value);            
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //Error on nb_registersRead > 118
        nb_registersRead = 120;//0x78
        try{
            framePDU = modbus.readWriteMultipleRegisters(startAddressRead,nb_registersRead,startAddressWrite,nb_registersWrite,value);            
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //Error on startAddressWrite
        startAddressWrite = -1;//0xFFFFFFFF
        nb_registersRead = 55;//0x37
        try{
            framePDU = modbus.readWriteMultipleRegisters(startAddressRead,nb_registersRead,startAddressWrite,nb_registersWrite,value);            
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //Error on nb_registersWrite < 1
        startAddressWrite = 1;//0x1
        nb_registersWrite = 0;//0x0
        try{
            framePDU = modbus.readWriteMultipleRegisters(startAddressRead,nb_registersRead,startAddressWrite,nb_registersWrite,value);            
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //Error on nb_registersWrite > 118
        nb_registersWrite = 130;//0x82
        try{
            framePDU = modbus.readWriteMultipleRegisters(startAddressRead,nb_registersRead,startAddressWrite,nb_registersWrite,value);            
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //Error on value
        nb_registersWrite = 3;//0x3
        value = new byte[240];
        try{
            framePDU = modbus.readWriteMultipleRegisters(startAddressRead,nb_registersRead,startAddressWrite,nb_registersWrite,value);            
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //Error on value
        nb_registersWrite = 6;//0x6
        value = new byte[6];
        try{
            framePDU = modbus.readWriteMultipleRegisters(startAddressRead,nb_registersRead,startAddressWrite,nb_registersWrite,value);            
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
    }

    /**
     * Test of readDeviceIdentification method, of class schneider.Modbus.
     */
    public void testReadDeviceIdentification() {
        System.out.println("-------------------------------------------");
        System.out.println("test method Modbus.readDeviceIdentification");
        modbus = new Modbus();
        byte deviceIdCode = 0x01;
        char objectId = 130;
        byte[] framePDU = null;
        
        // TODO add your test code below by replacing the default call to fail.
        //No error
        try{
            framePDU = modbus.readDeviceIdentification(deviceIdCode, objectId);
            
            Assert.assertEquals("Erreur sur la taille de la trame retournée",4,framePDU.length);
            Assert.assertEquals("Erreur sur framePDU[0]",0x2B,framePDU[0]);
            Assert.assertEquals("Erreur sur framePDU[1]",0x0E,framePDU[1]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x01,framePDU[2]);
            Assert.assertEquals("Erreur sur framePDU[3]",(byte)0x82,framePDU[3]);
            System.out.println("Method OK !");
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //Error on deviceIdCode
        deviceIdCode = 0x05;
        try{
            framePDU = modbus.readDeviceIdentification(deviceIdCode, objectId);
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //Error on objectIdCode < 0
        deviceIdCode = 0x02;
        objectId = (char)-1;//0xFFFFFFFF
        try{
            framePDU = modbus.readDeviceIdentification(deviceIdCode, objectId);
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //Error on objectIdCode > 255
        objectId = 256;//0x100
        try{
            framePDU = modbus.readDeviceIdentification(deviceIdCode, objectId);
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
    }

    /**
     * Test of checkIfError method, of class schneider.Modbus.
     */
    public void testCheckIfError() {
        System.out.println("-------------------------------");
        System.out.println("test method Modbus.checkIfError");
        modbus = new Modbus();
        byte functionCode, exceptionCode;
        boolean resultCheckError;
        
        // TODO add your test code below by replacing the default call to fail.
        // No error
        functionCode = 0x01;
        exceptionCode = 0x00;
        resultCheckError = modbus.checkIfError(functionCode, exceptionCode);
        Assert.assertEquals("Erreur sur la valeur retournée par la fonction",false,resultCheckError);
        if (resultCheckError == false){
            System.out.println(modbus.getErrorType());
        }
        
        //Error on Write File Record
        functionCode = (byte)0x95; 
        exceptionCode = 0x08;
        resultCheckError = modbus.checkIfError(functionCode, exceptionCode);
        Assert.assertEquals("Erreur sur la valeur retournée par la fonction",true,resultCheckError);
        if (resultCheckError == true){
            System.out.println(modbus.getErrorType());
        }
        
        //Error on Write Single Coil
        functionCode = (byte)0x85;
        exceptionCode = 0x03;
        resultCheckError = modbus.checkIfError(functionCode, exceptionCode);
        Assert.assertEquals("Erreur sur la valeur retournée par la fonction",true,resultCheckError);
        if (resultCheckError == true){
            System.out.println(modbus.getErrorType());
        }
        
        //Error on Get Comm Event Counter
        functionCode = (byte)0x8B;
        exceptionCode = 0x04;
        resultCheckError = modbus.checkIfError(functionCode, exceptionCode);
        Assert.assertEquals("Erreur sur la valeur retournée par la fonction",true,resultCheckError);
        if (resultCheckError == true){
            System.out.println(modbus.getErrorType());
        }
        
       
    
    }

    /**
     * Test of getErrorType method, of class schneider.Modbus.
     */
    public void testGetErrorType() {
        System.out.println("-------------------------------");
        System.out.println("test method Modbus.getErrorType");
        modbus = new Modbus();
        
        // TODO add your test code below by replacing the default call to fail.
        System.out.println(modbus.getErrorType());
    }

    /**
     * Test of extractData method, of class schneider.Modbus.
     */
    public void testExtractData() {
        System.out.println("------------------------------");
        System.out.println("test method Modbus.extractData");
        
        /* variables declaration */
        modbus = new Modbus();
        byte[] dataReceived = null;
        byte[] trameReceive_PDU = new byte[9];
        trameReceive_PDU[0] = 0x18;
        trameReceive_PDU[1] = 0x00;
        trameReceive_PDU[2] = 0x06;
        trameReceive_PDU[3] = 0x00;
        trameReceive_PDU[4] = 0x02;
        trameReceive_PDU[5] = 0x01;
        trameReceive_PDU[6] = (byte)0xB8;
        trameReceive_PDU[7] = 0x12;
        trameReceive_PDU[8] = (byte)0x84;
       
        
        // TODO add your test code below by replacing the default call to fail.
        dataReceived = modbus.extractData(trameReceive_PDU);
        Assert.assertEquals("Erreur sur la taille de la trame retournée",4,dataReceived.length);
        Assert.assertEquals("Erreur sur dataReceived[0]",0x01,dataReceived[0]);
        Assert.assertEquals("Erreur sur dataReceived[1]",(byte)0xB8,dataReceived[1]);
        Assert.assertEquals("Erreur sur dataReceived[2]",0x12,dataReceived[2]);
        Assert.assertEquals("Erreur sur dataReceived[3]",(byte)0x84,dataReceived[3]);
        System.out.println("No error on method extractData !");
        
    }
    
}
