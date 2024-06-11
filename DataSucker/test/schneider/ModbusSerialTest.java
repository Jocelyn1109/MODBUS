/*
 * ModbusSerialTest.java
 * JUnit based test
 *
 * Created on 23 novembre 2005, 13:33
 */

package schneider;

import junit.framework.*;

/**
 *
 * @author Merlin
 */
public class ModbusSerialTest extends TestCase {
    
    ModbusSerial modbusSerial = null;
    
    public ModbusSerialTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(ModbusSerialTest.class);
        
        return suite;
    }

    /**
     * Test of readCoilsOnSerial method, of class schneider.ModbusSerial.
     */
    public void testReadCoilsOnSerial() {
        System.out.println("------------------------------------------");
        System.out.println("test method ModbusSerial.readCoilsOnSerial");
        modbusSerial = new ModbusSerial();
        byte[] framePDU = null;
        short add_slave = 1;
        int st_add = 23;//0x17
        int nb_coils = 50;//0x32
        
        // TODO add your test code below by replacing the default call to fail.
        //No error
        try{
            framePDU = modbusSerial.readCoilsOnSerial(add_slave, st_add, nb_coils);
            Assert.assertEquals("Erreur sur la taille de la trame retournée",8,framePDU.length);
            Assert.assertEquals("Erreur sur framePDU[0]",0x01,framePDU[0]);
            Assert.assertEquals("Erreur sur framePDU[1]",0x01,framePDU[1]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x00,framePDU[2]);
            Assert.assertEquals("Erreur sur framePDU[3]",0x17,framePDU[3]);
            Assert.assertEquals("Erreur sur framePDU[4]",0x00,framePDU[4]);
            Assert.assertEquals("Erreur sur framePDU[5]",0x32,framePDU[5]);
            System.out.println("Method OK !");
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //Error on add_slave < 1
        add_slave = 0;
         try{
            framePDU = modbusSerial.readCoilsOnSerial(add_slave, st_add, nb_coils);
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //Error on add_slave > 247
        add_slave = 250;
         try{
            framePDU = modbusSerial.readCoilsOnSerial(add_slave, st_add, nb_coils);
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //Error on nb_coils
        add_slave = 2;
        nb_coils = 0;
        try{
            framePDU = modbusSerial.readCoilsOnSerial(add_slave, st_add, nb_coils);
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Test of readDiscretInputsOnSerial method, of class schneider.ModbusSerial.
     */
    public void testReadDiscretInputsOnSerial() {
        System.out.println("-------------------------------------------------");
        System.out.println("test method ModbusSerial.readDiscretInputsOnSerial");
        modbusSerial = new ModbusSerial();
        byte[] framePDU = null;
        short add_slave = 200;//0xC8
        int st_add = 111;//0x6F
        int nb_inputs = 136;//0x88
        
        // TODO add your test code below by replacing the default call to fail.
        //No error
        try{
            framePDU = modbusSerial.readDiscretInputsOnSerial(add_slave, st_add, nb_inputs);
            Assert.assertEquals("Erreur sur la taille de la trame retournée",8,framePDU.length);
            Assert.assertEquals("Erreur sur framePDU[0]",(byte)0xC8,framePDU[0]);
            Assert.assertEquals("Erreur sur framePDU[1]",0x02,framePDU[1]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x00,framePDU[2]);
            Assert.assertEquals("Erreur sur framePDU[3]",0x6F,framePDU[3]);
            Assert.assertEquals("Erreur sur framePDU[4]",0x00,framePDU[4]);
            Assert.assertEquals("Erreur sur framePDU[5]",(byte)0x88,framePDU[5]);
            System.out.println("Method OK !");
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //Error on add_slave < 1
        add_slave = -1;
         try{
            framePDU = modbusSerial.readDiscretInputsOnSerial(add_slave, st_add, nb_inputs);
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //Error on add_slave > 247
        add_slave = 255;
         try{
            framePDU = modbusSerial.readDiscretInputsOnSerial(add_slave, st_add, nb_inputs);
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //Error on nb_inputs
        add_slave = 11;
        nb_inputs = 2001;
        try{
            framePDU = modbusSerial.readDiscretInputsOnSerial(add_slave, st_add, nb_inputs);
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Test of readHoldingRegistersOnSerial method, of class schneider.ModbusSerial.
     */
    public void testReadHoldingRegistersOnSerial() {
        System.out.println("-----------------------------------------------");
        System.out.println("test method Modbus.readHoldingRegistersOnSerial");
        modbusSerial = new ModbusSerial();
        byte[] framePDU = null;
        short add_slave = 15;//0xF
        int st_add = 221;//0xDD
        int nb_hregisters = 125;//0x7D
        
        // TODO add your test code below by replacing the default call to fail.
        try{
            framePDU = modbusSerial.readHoldingRegistersOnSerial(add_slave,st_add, nb_hregisters);
            
            Assert.assertEquals("Erreur sur la taille de la trame retournée",8,framePDU.length);
            Assert.assertEquals("Erreur sur framePDU[0]",0xF,framePDU[0]);
            Assert.assertEquals("Erreur sur framePDU[1]",0x03,framePDU[1]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x00,framePDU[2]);
            Assert.assertEquals("Erreur sur framePDU[3]",(byte)0xDD,framePDU[3]);
            Assert.assertEquals("Erreur sur framePDU[4]",0x00,framePDU[4]);
            Assert.assertEquals("Erreur sur framePDU[5]",0x7D,framePDU[5]);
            System.out.println("Method OK !");
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //Error on add_slave < 1
        add_slave = -2;
         try{
            framePDU = modbusSerial.readHoldingRegistersOnSerial(add_slave, st_add, nb_hregisters);
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //Error on add_slave > 247
        add_slave = 248;
         try{
            framePDU = modbusSerial.readHoldingRegistersOnSerial(add_slave, st_add, nb_hregisters);
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //Error on nb_hregisters
        add_slave = 4;
        nb_hregisters = 126;
        try{
            framePDU = modbusSerial.readHoldingRegistersOnSerial(add_slave, st_add, nb_hregisters);
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Test of readInputRegistersOnSerial method, of class schneider.ModbusSerial.
     */
    public void testReadInputRegistersOnSerial() {
        System.out.println("---------------------------------------------------");
        System.out.println("test method ModbusSerial.readInputRegistersOnSerial");
        modbusSerial = new ModbusSerial();
        byte[] framePDU = null;
        short add_slave = 31;//0x1F
        int st_add = 65000;//0xFDE8
        int nb_iregisters = 55;//0x37
        
        // TODO add your test code below by replacing the default call to fail.
        //No error
         try{
            framePDU = modbusSerial.readInputRegistersOnSerial(add_slave,st_add, nb_iregisters);
            
            Assert.assertEquals("Erreur sur la taille de la trame retournée",8,framePDU.length);
            Assert.assertEquals("Erreur sur framePDU[0]",0x1F,framePDU[0]);
            Assert.assertEquals("Erreur sur framePDU[1]",0x04,framePDU[1]);
            Assert.assertEquals("Erreur sur framePDU[2]",(byte)0xFD,framePDU[2]);
            Assert.assertEquals("Erreur sur framePDU[3]",(byte)0xE8,framePDU[3]);
            Assert.assertEquals("Erreur sur framePDU[4]",0x00,framePDU[4]);
            Assert.assertEquals("Erreur sur framePDU[5]",0x37,framePDU[5]);
            System.out.println("Method OK !");
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //Error on add_slave < 1
        add_slave = 0;
         try{
            framePDU = modbusSerial.readInputRegistersOnSerial(add_slave, st_add, nb_iregisters);
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //Error on add_slave > 247
        add_slave = 255;
         try{
            framePDU = modbusSerial.readInputRegistersOnSerial(add_slave, st_add, nb_iregisters);
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //Error on nb_iregisters
        add_slave = 247;
        nb_iregisters = 0;
        try{
            framePDU = modbusSerial.readInputRegistersOnSerial(add_slave, st_add, nb_iregisters);
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Test of writeSingleCoilOnSerial method, of class schneider.ModbusSerial.
     */
    public void testWriteSingleCoilOnSerial() {
        System.out.println("------------------------------------------");
        System.out.println("test method Modbus.writeSingleCoilOnSerial");
        modbusSerial = new ModbusSerial();
        byte[] framePDU = null;
        short add_slave = 75;//0x4B
        int address = 65535;//0xFFFF
        boolean value = true;
        
        // TODO add your test code below by replacing the default call to fail.
        //No error 
        try{
            framePDU = modbusSerial.writeSingleCoilOnSerial(add_slave, address, value);
            
            Assert.assertEquals("Erreur sur la taille de la trame retournée",8,framePDU.length);
            Assert.assertEquals("Erreur sur framePDU[0]",0x4B,framePDU[0]);
            Assert.assertEquals("Erreur sur framePDU[1]",0x05,framePDU[1]);
            Assert.assertEquals("Erreur sur framePDU[2]",(byte)0xFF,framePDU[2]);
            Assert.assertEquals("Erreur sur framePDU[3]",(byte)0xFF,framePDU[3]);
            Assert.assertEquals("Erreur sur framePDU[4]",(byte)0xFF,framePDU[4]);
            Assert.assertEquals("Erreur sur framePDU[5]",0x00,framePDU[5]);
            System.out.println("Method OK !");
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //Error on add_slave < 1
        add_slave = -10;
         try{
            framePDU = modbusSerial.writeSingleCoilOnSerial(add_slave, address, value);
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //Error on add_slave > 247
        add_slave = 275;
         try{
            framePDU = modbusSerial.writeSingleCoilOnSerial(add_slave, address, value);
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //Error on address
        add_slave = 237;
        address = -1;
        try{
            framePDU = modbusSerial.writeSingleCoilOnSerial(add_slave, address, value);
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Test of writeSingleRegisterOnSerial method, of class schneider.ModbusSerial.
     */
    public void testWriteSingleRegisterOnSerial() {
        System.out.println("----------------------------------------------");
        System.out.println("test method Modbus.writeSingleRegisterOnSerial");
        modbusSerial = new ModbusSerial();
        byte[] framePDU = null;
        short add_slave = 75;//0x4B
        int address = 14551;//0x38D7
        int registerValue = 200;//0xC8
        
        // TODO add your test code below by replacing the default call to fail.
        //No error
        try{
            framePDU = modbusSerial.writeSingleRegisterOnSerial(add_slave, address, registerValue);
            
            Assert.assertEquals("Erreur sur la taille de la trame retournée",8,framePDU.length);
            Assert.assertEquals("Erreur sur framePDU[0]",0x4B,framePDU[0]);
            Assert.assertEquals("Erreur sur framePDU[1]",0x06,framePDU[1]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x38,framePDU[2]);
            Assert.assertEquals("Erreur sur framePDU[3]",(byte)0xD7,framePDU[3]);
            Assert.assertEquals("Erreur sur framePDU[4]",0x00,framePDU[4]);
            Assert.assertEquals("Erreur sur framePDU[5]",(byte)0xC8,framePDU[5]);
            System.out.println("Method OK !");
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //Error on add_slave < 1
        add_slave = -12;
         try{
            framePDU = modbusSerial.writeSingleRegisterOnSerial(add_slave, address, registerValue);
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //Error on add_slave > 247
        add_slave = 300;
         try{
            framePDU = modbusSerial.writeSingleRegisterOnSerial(add_slave, address, registerValue);
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //Error on registerValue
        add_slave = 67;
        registerValue = -11;
        try{
            framePDU = modbusSerial.writeSingleRegisterOnSerial(add_slave, address, registerValue);
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Test of writeMultipleCoilsOnSerial method, of class schneider.ModbusSerial.
     */
    public void testWriteMultipleCoilsOnSerial() {
        System.out.println("----------------------------------------------------");
        System.out.println("test methode ModbusSerial.writeMultipleCoilsOnSerial");
        modbusSerial = new ModbusSerial();
        byte[] framePDU = null;
        byte[] value = new byte[4];
        byte byteCount;
        short add_slave = 1;
        int st_add = 554;//0x22A
        int nb_coils = 25;//0x19
        int lengthFrame;
        
        // TODO add your test code below by replacing the default call to fail.
        // No error
        value[0] = 0x01;
        value[1] = 0x10;
        value[2] = 0x0A;
        value[3] = (byte)0xAA;
        try{
            framePDU = modbusSerial.writeMultipleCoilsOnSerial(add_slave, st_add, nb_coils, value);
            
            byteCount = framePDU[6];
            lengthFrame = 9 + (int)byteCount;
            Assert.assertEquals("Erreur sur la taille de la trame retournée",lengthFrame,framePDU.length);
            Assert.assertEquals("Erreur sur framePDU[0]",0x01,framePDU[0]);
            Assert.assertEquals("Erreur sur framePDU[1]",0x0F,framePDU[1]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x02,framePDU[2]);
            Assert.assertEquals("Erreur sur framePDU[3]",0x2A,framePDU[3]);
            Assert.assertEquals("Erreur sur framePDU[4]",0x00,framePDU[4]);
            Assert.assertEquals("Erreur sur framePDU[5]",0x19,framePDU[5]);
            Assert.assertEquals("Erreur sur framePDU[6], valeur du byteCount",4,framePDU[6]);
            Assert.assertEquals("Erreur sur framePDU[7]",0x01,framePDU[7]);
            Assert.assertEquals("Erreur sur framePDU[8]",0x10,framePDU[8]);
            Assert.assertEquals("Erreur sur framePDU[9]",0x0A,framePDU[9]);
            Assert.assertEquals("Erreur sur framePDU[10]",(byte)0xAA,framePDU[10]);            
            System.out.println("Method OK !");
            
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //Error on add_slave < 1
        add_slave = -120;
         try{
            framePDU = modbusSerial.writeMultipleCoilsOnSerial(add_slave, st_add, nb_coils, value);
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //Error on add_slave > 247
        add_slave = 310;
         try{
            framePDU = modbusSerial.writeMultipleCoilsOnSerial(add_slave, st_add, nb_coils, value);
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //Error on nb_coils
        add_slave = 32;
        nb_coils = 2000;
        try{
            framePDU = modbusSerial.writeMultipleCoilsOnSerial(add_slave, st_add, nb_coils, value);
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Test of writeMultipleRegistersOnSerial method, of class schneider.ModbusSerial.
     */
    public void testWriteMultipleRegistersOnSerial() {
        System.out.println("-------------------------------------------------");
        System.out.println("test method Modbus.writeMultipleRegistersOnSerial");
        modbusSerial = new ModbusSerial();
        byte[] framePDU = null;
        byte[] value = new byte[4];
        byte byteCount;
        short add_slave = 9;
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
        lengthFrame = 9 + (nb_registers * 2);
        try{
            framePDU = modbusSerial.writeMultipleRegistersOnSerial(add_slave, startAddress,nb_registers,value);
            
            Assert.assertEquals("Erreur sur la taille de la trame retournée",lengthFrame,framePDU.length);
            Assert.assertEquals("Erreur sur framePDU[0]",0x9,framePDU[0]);
            Assert.assertEquals("Erreur sur framePDU[1]",0x10,framePDU[1]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x00,framePDU[2]);
            Assert.assertEquals("Erreur sur framePDU[3]",0x02,framePDU[3]);
            Assert.assertEquals("Erreur sur framePDU[4]",0x00,framePDU[4]);
            Assert.assertEquals("Erreur sur framePDU[5]",0x02,framePDU[5]);
            Assert.assertEquals("Erreur sur framePDU[6], valeur du byteCount",byteCount,framePDU[6]);
            Assert.assertEquals("Erreur sur framePDU[7]",0x00,framePDU[7]);
            Assert.assertEquals("Erreur sur framePDU[8]",0x0A,framePDU[8]);
            Assert.assertEquals("Erreur sur framePDU[9]",0x01,framePDU[9]);
            Assert.assertEquals("Erreur sur framePDU[10]",0x02,framePDU[10]);
            System.out.println("Method OK !");
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //Error on add_slave < 1
        add_slave = 0;
         try{
            framePDU = modbusSerial.writeMultipleRegistersOnSerial(add_slave, startAddress,nb_registers,value);
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //Error on add_slave > 247
        add_slave = 260;
         try{
            framePDU = modbusSerial.writeMultipleRegistersOnSerial(add_slave, startAddress,nb_registers,value);
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //Error on nb_registers > 120
        add_slave = 26;
        nb_registers = 121;
        try{
            framePDU = modbusSerial.writeMultipleRegistersOnSerial(add_slave, startAddress,nb_registers,value);
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //Error on nb_registers > 120 and add_slave > 247
        add_slave = 261;
        nb_registers = 121;
        try{
            framePDU = modbusSerial.writeMultipleRegistersOnSerial(add_slave, startAddress,nb_registers,value);
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Test of readWriteMultipleRegistersOnSerial method, of class schneider.ModbusSerial.
     */
    public void testReadWriteMultipleRegistersOnSerial() {
        System.out.println("-----------------------------------------------------------");
        System.out.println("test method ModbusSerial.readWriteMultipleRegistersOnSerial");
        modbusSerial = new ModbusSerial();
        int startAddressRead = 4, startAddressWrite = 15, nb_registersRead = 6, nb_registersWrite = 3;
        int lengthFrame;
        short add_slave = 15;
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
        lengthFrame = 13 + (int)byteCount;
        try{
            framePDU = modbusSerial.readWriteMultipleRegistersOnSerial(add_slave, startAddressRead,nb_registersRead,startAddressWrite,nb_registersWrite,value);
            
            Assert.assertEquals("Erreur sur la taille de la trame retournée",lengthFrame,framePDU.length);
            Assert.assertEquals("Erreur sur framePDU[0]",0xF,framePDU[0]);
            Assert.assertEquals("Erreur sur framePDU[1]",0x17,framePDU[1]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x00,framePDU[2]);
            Assert.assertEquals("Erreur sur framePDU[3]",0x04,framePDU[3]);
            Assert.assertEquals("Erreur sur framePDU[4]",0x00,framePDU[4]);
            Assert.assertEquals("Erreur sur framePDU[5]",0x06,framePDU[5]);
            Assert.assertEquals("Erreur sur framePDU[6]",0x00,framePDU[6]);
            Assert.assertEquals("Erreur sur framePDU[7]",0x0F,framePDU[7]);
            Assert.assertEquals("Erreur sur framePDU[8]",0x00,framePDU[8]);
            Assert.assertEquals("Erreur sur framePDU[9]",0x03,framePDU[9]);
            Assert.assertEquals("Erreur sur framePDU[10], valeur du byteCount",byteCount,framePDU[10]);
            Assert.assertEquals("Erreur sur framePDU[11]",0x00,framePDU[11]);
            Assert.assertEquals("Erreur sur framePDU[12]",(byte)0xFF,framePDU[12]);
            Assert.assertEquals("Erreur sur framePDU[13]",0x00,framePDU[13]);
            Assert.assertEquals("Erreur sur framePDU[14]",(byte)0xFF,framePDU[14]);
            Assert.assertEquals("Erreur sur framePDU[15]",0x00,framePDU[15]);
            Assert.assertEquals("Erreur sur framePDU[16]",(byte)0xFF,framePDU[16]);
            System.out.println("Method OK !");
            
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //Error on add_slave < 1
        add_slave = 0;
         try{
            framePDU = modbusSerial.readWriteMultipleRegistersOnSerial(add_slave, startAddressRead,nb_registersRead,startAddressWrite,nb_registersWrite,value);
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //Error on add_slave > 247
        add_slave = 267;
         try{
            framePDU = modbusSerial.readWriteMultipleRegistersOnSerial(add_slave, startAddressRead,nb_registersRead,startAddressWrite,nb_registersWrite,value);
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //Error on nb_registersRead
        add_slave = 214;
        nb_registersRead = 132;
        try{
            framePDU = modbusSerial.readWriteMultipleRegistersOnSerial(add_slave, startAddressRead,nb_registersRead,startAddressWrite,nb_registersWrite,value);
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //Error on nb_registersWrite
        nb_registersRead = 110;
        nb_registersWrite = 0;
        try{
            framePDU = modbusSerial.readWriteMultipleRegistersOnSerial(add_slave, startAddressRead,nb_registersRead,startAddressWrite,nb_registersWrite,value);
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Test of readDeviceIdentificationOnSerial method, of class schneider.ModbusSerial.
     */
    public void testReadDeviceIdentificationOnSerial() {
        System.out.println("------------------------------------");
        System.out.println("test method ModbusSerial.readDeviceIdentificationOnSerial");
        modbusSerial = new ModbusSerial();
        byte[] framePDU = null;
        byte deviceIdCode = 0x01;
        char objectId = 130; //0x82
        short add_slave = 11; //0xB
        
        
        // TODO add your test code below by replacing the default call to fail.
        //No error
        try{
            framePDU = modbusSerial.readDeviceIdentificationOnSerial(add_slave, deviceIdCode, objectId);
            
            Assert.assertEquals("Erreur sur la taille de la trame retournée",7,framePDU.length);
            Assert.assertEquals("Erreur sur framePDU[0]",0xB,framePDU[0]);
            Assert.assertEquals("Erreur sur framePDU[1]",0x2B,framePDU[1]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x0E,framePDU[2]);
            Assert.assertEquals("Erreur sur framePDU[3]",0x01,framePDU[3]);
            Assert.assertEquals("Erreur sur framePDU[4]",(byte)0x82,framePDU[4]);
            System.out.println("Method OK !");
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //Error on add_slave < 1
        add_slave = 0;
         try{
            framePDU = modbusSerial.readDeviceIdentificationOnSerial(add_slave, deviceIdCode, objectId);
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //Error on add_slave > 247
        add_slave = 267;
         try{
            framePDU = modbusSerial.readDeviceIdentificationOnSerial(add_slave, deviceIdCode, objectId);
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //Error on deviceIdCode
        add_slave = 11;
        deviceIdCode = 0x05;
        try{
            framePDU = modbusSerial.readDeviceIdentificationOnSerial(add_slave, deviceIdCode, objectId);
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Test of ReadExceptionStatusOnSerial method, of class schneider.ModbusSerial.
     */
    public void testReadExceptionStatusOnSerial() {
        System.out.println("----------------------------------------------------");
        System.out.println("test method ModbusSerial.readExceptionStatusOnSerial");
        modbusSerial = new ModbusSerial();
        byte[] framePDU = null;
        short add_slave = 33; //0x21
        
        // TODO add your test code below by replacing the default call to fail.
        //No error
        try{
            framePDU = modbusSerial.readExceptionStatusOnSerial(add_slave);
            Assert.assertEquals("Erreur sur la taille de la trame retournée",4,framePDU.length);
            Assert.assertEquals("Erreur sur framePDU[0]",0x21,framePDU[0]);
            Assert.assertEquals("Erreur sur framePDU[1]",0x07,framePDU[1]);
            System.out.println("Methode OK !");
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //Error on add_slave < 1
        add_slave = 0;
         try{
            framePDU = modbusSerial.readExceptionStatusOnSerial(add_slave);
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //Error on add_slave > 247
        add_slave = 267;
         try{
            framePDU = modbusSerial.readExceptionStatusOnSerial(add_slave);
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Test of DiagnosticsOnSerial method, of class schneider.ModbusSerial.
     */
    public void testDiagnosticsOnSerial() {
        System.out.println("-----------------------");
        System.out.println("test methode ModbusSerial.DiagnosticsOnSerial");
        modbusSerial = new ModbusSerial();
        byte[] framePDU = null;
        short add_slave = 10; //0xA
        char CAR = 0;
        short diagType = 0;
        
        // TODO add your test code below by replacing the default call to fail.
        // TODO add your test code below by replacing the default call to fail.
        
        /* Return Query Data */
        try{
            framePDU = modbusSerial.DiagnosticsOnSerial(add_slave, diagType,CAR);
            Assert.assertEquals("Erreur sur la taille de la trame retournée",6,framePDU.length);
            Assert.assertEquals("Erreur sur framePDU[0]",0xA,framePDU[0]);
            Assert.assertEquals("Erreur sur framePDU[1]",0x08,framePDU[1]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x00,framePDU[2]);
            Assert.assertEquals("Erreur sur framePDU[3]",0x00,framePDU[3]);
            System.out.println("Return Query Data OK !");
        }
        catch(ModbusException e){
             System.out.println(e.getMessage());
        }
      
        
        /* Restart Communication Option : clear the log */
        diagType = 1;
        try{
            framePDU = modbusSerial.DiagnosticsOnSerial(add_slave, diagType,CAR);
            Assert.assertEquals("Erreur sur la taille de la trame retournée",8,framePDU.length);
            Assert.assertEquals("Erreur sur framePDU[0]",0xA,framePDU[0]);
            Assert.assertEquals("Erreur sur framePDU[1]",0x08,framePDU[1]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x00,framePDU[2]);
            Assert.assertEquals("Erreur sur framePDU[3]",0x01,framePDU[3]);
            Assert.assertEquals("Erreur sur framePDU[4]",(byte)0xFF,framePDU[4]);
            Assert.assertEquals("Erreur sur framePDU[5]",0x00,framePDU[5]);
            System.out.println("Restart Communication Option : clear the log OK !");
        }
        catch(ModbusException e){
             System.out.println(e.getMessage());
        }
        
        
        /* Restart Communication Option : leave the log */
        diagType = 2;
        try{
            framePDU = modbusSerial.DiagnosticsOnSerial(add_slave, diagType,CAR);
            Assert.assertEquals("Erreur sur la taille de la trame retournée",8,framePDU.length);
            Assert.assertEquals("Erreur sur framePDU[0]",0xA,framePDU[0]);
            Assert.assertEquals("Erreur sur framePDU[1]",0x08,framePDU[1]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x00,framePDU[2]);
            Assert.assertEquals("Erreur sur framePDU[3]",0x01,framePDU[3]);
            Assert.assertEquals("Erreur sur framePDU[4]",0x00,framePDU[4]);
            Assert.assertEquals("Erreur sur framePDU[5]",0x00,framePDU[5]);
            System.out.println("Restart Communication Option : leave the log OK !");
        }
        catch(ModbusException e){
             System.out.println(e.getMessage());
        }
        
        
        /* Return Diagnostic Register */
        diagType = 3;
        try{
            framePDU = modbusSerial.DiagnosticsOnSerial(add_slave, diagType,CAR);
            Assert.assertEquals("Erreur sur la taille de la trame retournée",8,framePDU.length);
            Assert.assertEquals("Erreur sur framePDU[0]",0xA,framePDU[0]);
            Assert.assertEquals("Erreur sur framePDU[1]",0x08,framePDU[1]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x00,framePDU[2]);
            Assert.assertEquals("Erreur sur framePDU[3]",0x02,framePDU[3]);
            Assert.assertEquals("Erreur sur framePDU[4]",0x00,framePDU[4]);
            Assert.assertEquals("Erreur sur framePDU[5]",0x00,framePDU[5]);
            System.out.println("Return Diagnostic Register OK !");
        }
        catch(ModbusException e){
             System.out.println(e.getMessage());
        }
        
        
        /* Change ASCII Input Delimiter */
        diagType = 4;
        CAR = 65;
        try{
            framePDU = modbusSerial.DiagnosticsOnSerial(add_slave, diagType,CAR);
            Assert.assertEquals("Erreur sur la taille de la trame retournée",8,framePDU.length);
            Assert.assertEquals("Erreur sur framePDU[0]",0xA,framePDU[0]);
            Assert.assertEquals("Erreur sur framePDU[1]",0x08,framePDU[1]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x00,framePDU[2]);
            Assert.assertEquals("Erreur sur framePDU[3]",0x03,framePDU[3]);
            Assert.assertEquals("Erreur sur framePDU[4]",0x41,framePDU[4]);
            Assert.assertEquals("Erreur sur framePDU[5]",0x00,framePDU[5]);
            System.out.println("Change ASCII Input Delimiter OK !");
        }
        catch(ModbusException e){
             System.out.println(e.getMessage());
        }
        
        
        /* Forced Listen Only Mode */
        diagType = 5;
        try{
            framePDU = modbusSerial.DiagnosticsOnSerial(add_slave, diagType,CAR);
            Assert.assertEquals("Erreur sur la taille de la trame retournée",8,framePDU.length);
            Assert.assertEquals("Erreur sur framePDU[0]",0xA,framePDU[0]);
            Assert.assertEquals("Erreur sur framePDU[1]",0x08,framePDU[1]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x00,framePDU[2]);
            Assert.assertEquals("Erreur sur framePDU[3]",0x04,framePDU[3]);
            Assert.assertEquals("Erreur sur framePDU[4]",0x00,framePDU[4]);
            Assert.assertEquals("Erreur sur framePDU[5]",0x00,framePDU[5]);
            System.out.println("Forced Listen Only Mode OK !");
        }
        catch(ModbusException e){
             System.out.println(e.getMessage());
        }
        
        
        /* Clear Counter and Diagnostic Registers */
        diagType = 6;
        try{
            framePDU = modbusSerial.DiagnosticsOnSerial(add_slave, diagType,CAR);
            Assert.assertEquals("Erreur sur la taille de la trame retournée",8,framePDU.length);
            Assert.assertEquals("Erreur sur framePDU[0]",0xA,framePDU[0]);
            Assert.assertEquals("Erreur sur framePDU[1]",0x08,framePDU[1]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x00,framePDU[2]);
            Assert.assertEquals("Erreur sur framePDU[3]",0x0A,framePDU[3]);
            Assert.assertEquals("Erreur sur framePDU[4]",0x00,framePDU[4]);
            Assert.assertEquals("Erreur sur framePDU[5]",0x00,framePDU[5]);
            System.out.println("Clear Counter and Diagnostic Registers OK !");
        }
        catch(ModbusException e){
             System.out.println(e.getMessage());
        }
        
        
        /* Return Bus Message Count */
        diagType = 7;
        try{
             framePDU = modbusSerial.DiagnosticsOnSerial(add_slave, diagType,CAR);
            Assert.assertEquals("Erreur sur la taille de la trame retournée",8,framePDU.length);
            Assert.assertEquals("Erreur sur framePDU[0]",0xA,framePDU[0]);
            Assert.assertEquals("Erreur sur framePDU[1]",0x08,framePDU[1]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x00,framePDU[2]);
            Assert.assertEquals("Erreur sur framePDU[3]",0x0B,framePDU[3]);
            Assert.assertEquals("Erreur sur framePDU[4]",0x00,framePDU[4]);
            Assert.assertEquals("Erreur sur framePDU[5]",0x00,framePDU[5]);
            System.out.println("Return Bus Message Count OK !");
        }
        catch(ModbusException e){
             System.out.println(e.getMessage());
        }
        
        
        /* Return Bus Communication Error Count */
        diagType = 8;
        try{
             framePDU = modbusSerial.DiagnosticsOnSerial(add_slave, diagType,CAR);
            Assert.assertEquals("Erreur sur la taille de la trame retournée",8,framePDU.length);
            Assert.assertEquals("Erreur sur framePDU[0]",0xA,framePDU[0]);
            Assert.assertEquals("Erreur sur framePDU[1]",0x08,framePDU[1]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x00,framePDU[2]);
            Assert.assertEquals("Erreur sur framePDU[3]",0x0C,framePDU[3]);
            Assert.assertEquals("Erreur sur framePDU[4]",0x00,framePDU[4]);
            Assert.assertEquals("Erreur sur framePDU[5]",0x00,framePDU[5]);
            System.out.println("Return Bus Communication Error Count OK !");
        }
        catch(ModbusException e){
             System.out.println(e.getMessage());
        }
        
        
        /* Return Bus Exception Error Count */
        diagType = 9;
        try{
            framePDU = modbusSerial.DiagnosticsOnSerial(add_slave, diagType,CAR);
            Assert.assertEquals("Erreur sur la taille de la trame retournée",8,framePDU.length);
            Assert.assertEquals("Erreur sur framePDU[0]",0xA,framePDU[0]);
            Assert.assertEquals("Erreur sur framePDU[1]",0x08,framePDU[1]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x00,framePDU[2]);
            Assert.assertEquals("Erreur sur framePDU[3]",0x0D,framePDU[3]);
            Assert.assertEquals("Erreur sur framePDU[4]",0x00,framePDU[4]);
            Assert.assertEquals("Erreur sur framePDU[5]",0x00,framePDU[5]);
            System.out.println("Return Bus Exception Error Count OK !");
        }
        catch(ModbusException e){
             System.out.println(e.getMessage());
        }
        
        
        /* Return Slave Message Count */
        diagType = 10;
        try{
            framePDU = modbusSerial.DiagnosticsOnSerial(add_slave, diagType,CAR);
            Assert.assertEquals("Erreur sur la taille de la trame retournée",8,framePDU.length);
            Assert.assertEquals("Erreur sur framePDU[0]",0xA,framePDU[0]);
            Assert.assertEquals("Erreur sur framePDU[1]",0x08,framePDU[1]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x00,framePDU[2]);
            Assert.assertEquals("Erreur sur framePDU[3]",0x0E,framePDU[3]);
            Assert.assertEquals("Erreur sur framePDU[4]",0x00,framePDU[4]);
            Assert.assertEquals("Erreur sur framePDU[5]",0x00,framePDU[5]);
            System.out.println("Return Slave Message Count OK !");
        }
        catch(ModbusException e){
             System.out.println(e.getMessage());
        }
        
        
        /* Return Slave No Response Count */
        diagType = 11;
        try{
            framePDU = modbusSerial.DiagnosticsOnSerial(add_slave, diagType,CAR);
            Assert.assertEquals("Erreur sur la taille de la trame retournée",8,framePDU.length);
            Assert.assertEquals("Erreur sur framePDU[0]",0xA,framePDU[0]);
            Assert.assertEquals("Erreur sur framePDU[1]",0x08,framePDU[1]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x00,framePDU[2]);
            Assert.assertEquals("Erreur sur framePDU[3]",0x0F,framePDU[3]);
            Assert.assertEquals("Erreur sur framePDU[4]",0x00,framePDU[4]);
            Assert.assertEquals("Erreur sur framePDU[5]",0x00,framePDU[5]);
            System.out.println("Return Slave No Response Count OK !");
        }
        catch(ModbusException e){
             System.out.println(e.getMessage());
        }
        
        
        /* Return Slave NAK Count */
        diagType = 12;
        try{
            framePDU = modbusSerial.DiagnosticsOnSerial(add_slave, diagType,CAR);
            Assert.assertEquals("Erreur sur la taille de la trame retournée",8,framePDU.length);
            Assert.assertEquals("Erreur sur framePDU[0]",0xA,framePDU[0]);
            Assert.assertEquals("Erreur sur framePDU[1]",0x08,framePDU[1]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x00,framePDU[2]);
            Assert.assertEquals("Erreur sur framePDU[3]",0x10,framePDU[3]);
            Assert.assertEquals("Erreur sur framePDU[4]",0x00,framePDU[4]);
            Assert.assertEquals("Erreur sur framePDU[5]",0x00,framePDU[5]);
            System.out.println("Return Slave NAK Count OK !");
        }
        catch(ModbusException e){
             System.out.println(e.getMessage());
        }
        
        
        /* Return Slave Busy Count */
        diagType = 13;
        try{
            framePDU = modbusSerial.DiagnosticsOnSerial(add_slave, diagType,CAR);
            Assert.assertEquals("Erreur sur la taille de la trame retournée",8,framePDU.length);
            Assert.assertEquals("Erreur sur framePDU[0]",0xA,framePDU[0]);
            Assert.assertEquals("Erreur sur framePDU[1]",0x08,framePDU[1]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x00,framePDU[2]);
            Assert.assertEquals("Erreur sur framePDU[3]",0x11,framePDU[3]);
            Assert.assertEquals("Erreur sur framePDU[4]",0x00,framePDU[4]);
            Assert.assertEquals("Erreur sur framePDU[5]",0x00,framePDU[5]);
            System.out.println("Return Slave Busy Count OK !");
        }
        catch(ModbusException e){
             System.out.println(e.getMessage());
        }
        
        
        /* Return Bus Character Overrun Count */
        diagType = 14;
        try{
            framePDU = modbusSerial.DiagnosticsOnSerial(add_slave, diagType,CAR);
            Assert.assertEquals("Erreur sur la taille de la trame retournée",8,framePDU.length);
            Assert.assertEquals("Erreur sur framePDU[0]",0xA,framePDU[0]);
            Assert.assertEquals("Erreur sur framePDU[1]",0x08,framePDU[1]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x00,framePDU[2]);
            Assert.assertEquals("Erreur sur framePDU[3]",0x12,framePDU[3]);
            Assert.assertEquals("Erreur sur framePDU[4]",0x00,framePDU[4]);
            Assert.assertEquals("Erreur sur framePDU[5]",0x00,framePDU[5]);
            System.out.println("Return Bus Character Ovverrun Count OK !");
        }
        catch(ModbusException e){
             System.out.println(e.getMessage());
        }
        
        
        /* Clear Overrun Counter and Flag */
        diagType = 15;
        try{
            framePDU = modbusSerial.DiagnosticsOnSerial(add_slave, diagType,CAR);
            Assert.assertEquals("Erreur sur la taille de la trame retournée",8,framePDU.length);
            Assert.assertEquals("Erreur sur framePDU[0]",0xA,framePDU[0]);
            Assert.assertEquals("Erreur sur framePDU[1]",0x08,framePDU[1]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x00,framePDU[2]);
            Assert.assertEquals("Erreur sur framePDU[3]",0x14,framePDU[3]);
            Assert.assertEquals("Erreur sur framePDU[4]",0x00,framePDU[4]);
            Assert.assertEquals("Erreur sur framePDU[5]",0x00,framePDU[5]);
            System.out.println("Clear Overrun Counter and Flag OK !");
        }
        catch(ModbusException e){
             System.out.println(e.getMessage());
        }
        
        
        //Error on add_slave < 1
        add_slave = 0;
         try{
            framePDU = modbusSerial.DiagnosticsOnSerial(add_slave, diagType,CAR);
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //Error on add_slave > 247
        add_slave = 267;
         try{
            framePDU = modbusSerial.DiagnosticsOnSerial(add_slave, diagType,CAR);
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        /* Error on diagType */
        diagType = 20;
        try{
            framePDU = modbusSerial.DiagnosticsOnSerial(add_slave, diagType,CAR);
        }
        catch(ModbusException e){
             System.out.println(e.getMessage());
        }
        
        
        /* Error on CAR */
        diagType = 4;
        CAR = 260;
        try{
            framePDU = modbusSerial.DiagnosticsOnSerial(add_slave, diagType,CAR);
        }
        catch(ModbusException e){
             System.out.println(e.getMessage());
        }
        
    }

    /**
     * Test of getComEventCounterOnSerial method, of class schneider.ModbusSerial.
     */
    public void testGetComEventCounterOnSerial() {
        System.out.println("---------------------------------------------------");
        System.out.println("test method ModbusSerial.getComEventCounterOnSerial");
        modbusSerial = new ModbusSerial();
        byte[] framePDU = null;
        short add_slave = 9;//0x9;
        
        // TODO add your test code below by replacing the default call to fail.
        //No error
        try{
            framePDU = modbusSerial.getComEventCounterOnSerial(add_slave);
            Assert.assertEquals("Erreur sur la taille de la trame retournée",4,framePDU.length);
            Assert.assertEquals("Erreur sur framePDU[0]",0x9,framePDU[0]);
            Assert.assertEquals("Erreur sur framePDU[1]",0x0B,framePDU[1]);
            System.out.println("Methode OK !");
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //Error on add_slave < 1
        add_slave = 0;
         try{
            framePDU = modbusSerial.getComEventCounterOnSerial(add_slave);
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //Error on add_slave > 247
        add_slave = 267;
         try{
            framePDU = modbusSerial.getComEventCounterOnSerial(add_slave);
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        
    }

    /**
     * Test of getComEventLogOnSerial method, of class schneider.ModbusSerial.
     */
    public void testGetComEventLogOnSerial() {
        System.out.println("-----------------------------------------------");
        System.out.println("test method ModbusSerial.getComEventLogOnSerial");
        modbusSerial = new ModbusSerial();
        byte[] framePDU = null;
        short add_slave = 20;//0x14
        
        // TODO add your test code below by replacing the default call to fail.
        //No error
        try{
            framePDU = modbusSerial.getComEventLogOnSerial(add_slave);
            Assert.assertEquals("Erreur sur la taille de la trame retournée",4,framePDU.length);
            Assert.assertEquals("Erreur sur framePDU[0]",0x14,framePDU[0]);
            Assert.assertEquals("Erreur sur framePDU[1]",0x0C,framePDU[1]);
            System.out.println("Methode OK !");
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //Error on add_slave < 1
        add_slave = 0;
         try{
           framePDU = modbusSerial.getComEventLogOnSerial(add_slave);
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //Error on add_slave > 247
        add_slave = 267;
         try{
            framePDU = modbusSerial.getComEventLogOnSerial(add_slave);
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Test of reportSlaveIdOnSerial method, of class schneider.ModbusSerial.
     */
    public void testReportSlaveIdOnSerial() {
        System.out.println("----------------------------------------------");
        System.out.println("test method ModbusSerial.reportSlaveIdOnSerial");
        modbusSerial = new ModbusSerial();
        byte[] framePDU = null;
        short add_slave = 48;//0x30
        
        // TODO add your test code below by replacing the default call to fail.
        //No error
        try{
            framePDU = modbusSerial.reportSlaveIdOnSerial(add_slave);
            Assert.assertEquals("Erreur sur la taille de la trame retournée",4,framePDU.length);
            Assert.assertEquals("Erreur sur framePDU[0]",0x30,framePDU[0]);
            Assert.assertEquals("Erreur sur framePDU[1]",0x11,framePDU[1]);
            System.out.println("Methode OK !");
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //Error on add_slave < 1
        add_slave = 0;
         try{
           framePDU = modbusSerial.reportSlaveIdOnSerial(add_slave);
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
        
        //Error on add_slave > 247
        add_slave = 267;
         try{
            framePDU = modbusSerial.reportSlaveIdOnSerial(add_slave);
        }
        catch(ModbusException e){
            System.out.println(e.getMessage());
        }
    }
    
}
