/*
 * ModbusExtendedTest.java
 * JUnit based test
 *
 * Created on 17 novembre 2005, 17:27
 */

package schneider;

import junit.framework.*;

/**
 *
 * @author Merlin
 */
public class ModbusExtendedTest extends TestCase {
    
    ModbusExtended modbusExtended = null;
    
    public ModbusExtendedTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(ModbusExtendedTest.class);
        
        return suite;
    }

    /**
     * Test of ReadExceptionStatus method, of class schneider.ModbusExtended.
     */
    public void testReadExceptionStatus() {
        System.out.println("---------------------------------------------");
        System.out.println("test method ModbusExtended.readExceptionStatus");
        modbusExtended = new ModbusExtended();
        byte frame = 0x00;
        
        // TODO add your test code below by replacing the default call to fail.
        frame = modbusExtended.readExceptionStatus();
        Assert.assertEquals("Erreur sur la valeur du byte retourné",0x07,frame);
    }

    /**
     * Test of Diagnostics method, of class schneider.ModbusExtended.
     */
    public void testDiagnostics() {
        System.out.println("-------------------------------------");
        System.out.println("test method ModbusExtended.diagnostics");
        modbusExtended = new ModbusExtended();
        byte[] framePDU = null;
        char CAR = 0;
        short diagType = 0;
        
        // TODO add your test code below by replacing the default call to fail.
        
        /* Return Query Data */
        try{
            framePDU = modbusExtended.Diagnostics(diagType,CAR);
            Assert.assertEquals("Erreur sur la taille de la trame retournée",3,framePDU.length);
            Assert.assertEquals("Erreur sur framePDU[0]",0x08,framePDU[0]);
            Assert.assertEquals("Erreur sur framePDU[1]",0x00,framePDU[1]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x00,framePDU[2]);
        }
        catch(ModbusException e){
             System.out.println(e.getMessage());
        }
      
        
        /* Restart Communication Option : clear the log */
        diagType = 1;
        try{
            framePDU = modbusExtended.Diagnostics(diagType,CAR);
            Assert.assertEquals("Erreur sur la taille de la trame retournée",5,framePDU.length);
            Assert.assertEquals("Erreur sur framePDU[0]",0x08,framePDU[0]);
            Assert.assertEquals("Erreur sur framePDU[1]",0x00,framePDU[1]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x01,framePDU[2]);
            Assert.assertEquals("Erreur sur framePDU[2]",(byte)0xFF,framePDU[3]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x00,framePDU[4]);
        }
        catch(ModbusException e){
             System.out.println(e.getMessage());
        }
        
        
        /* Restart Communication Option : leave the log */
        diagType = 2;
        try{
            framePDU = modbusExtended.Diagnostics(diagType,CAR);
            Assert.assertEquals("Erreur sur la taille de la trame retournée",5,framePDU.length);
            Assert.assertEquals("Erreur sur framePDU[0]",0x08,framePDU[0]);
            Assert.assertEquals("Erreur sur framePDU[1]",0x00,framePDU[1]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x01,framePDU[2]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x00,framePDU[3]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x00,framePDU[4]);
        }
        catch(ModbusException e){
             System.out.println(e.getMessage());
        }
        
        
        /* Return Diagnostic Register */
        diagType = 3;
        try{
            framePDU = modbusExtended.Diagnostics(diagType,CAR);
            Assert.assertEquals("Erreur sur la taille de la trame retournée",5,framePDU.length);
            Assert.assertEquals("Erreur sur framePDU[0]",0x08,framePDU[0]);
            Assert.assertEquals("Erreur sur framePDU[1]",0x00,framePDU[1]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x02,framePDU[2]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x00,framePDU[3]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x00,framePDU[4]);
        }
        catch(ModbusException e){
             System.out.println(e.getMessage());
        }
        
        
        /* Change ASCII Input Delimiter */
        diagType = 4;
        CAR = 65;
        try{
            framePDU = modbusExtended.Diagnostics(diagType,CAR);
            Assert.assertEquals("Erreur sur la taille de la trame retournée",5,framePDU.length);
            Assert.assertEquals("Erreur sur framePDU[0]",0x08,framePDU[0]);
            Assert.assertEquals("Erreur sur framePDU[1]",0x00,framePDU[1]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x03,framePDU[2]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x41,framePDU[3]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x00,framePDU[4]);
        }
        catch(ModbusException e){
             System.out.println(e.getMessage());
        }
        
        
        /* Forced Listen Only Mode */
        diagType = 5;
        try{
            framePDU = modbusExtended.Diagnostics(diagType,CAR);
            Assert.assertEquals("Erreur sur la taille de la trame retournée",5,framePDU.length);
            Assert.assertEquals("Erreur sur framePDU[0]",0x08,framePDU[0]);
            Assert.assertEquals("Erreur sur framePDU[1]",0x00,framePDU[1]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x04,framePDU[2]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x00,framePDU[3]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x00,framePDU[4]);
        }
        catch(ModbusException e){
             System.out.println(e.getMessage());
        }
        
        
        /* Clear Counter and Diagnostic Registers */
        diagType = 6;
        try{
            framePDU = modbusExtended.Diagnostics(diagType,CAR);
            Assert.assertEquals("Erreur sur la taille de la trame retournée",5,framePDU.length);
            Assert.assertEquals("Erreur sur framePDU[0]",0x08,framePDU[0]);
            Assert.assertEquals("Erreur sur framePDU[1]",0x00,framePDU[1]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x0A,framePDU[2]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x00,framePDU[3]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x00,framePDU[4]);
        }
        catch(ModbusException e){
             System.out.println(e.getMessage());
        }
        
        
        /* Return Bus Message Count */
        diagType = 7;
        try{
            framePDU = modbusExtended.Diagnostics(diagType,CAR);
            Assert.assertEquals("Erreur sur la taille de la trame retournée",5,framePDU.length);
            Assert.assertEquals("Erreur sur framePDU[0]",0x08,framePDU[0]);
            Assert.assertEquals("Erreur sur framePDU[1]",0x00,framePDU[1]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x0B,framePDU[2]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x00,framePDU[3]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x00,framePDU[4]);
        }
        catch(ModbusException e){
             System.out.println(e.getMessage());
        }
        
        
        /* Return Bus Communication Error Count */
        diagType = 8;
        try{
            framePDU = modbusExtended.Diagnostics(diagType,CAR);
            Assert.assertEquals("Erreur sur la taille de la trame retournée",5,framePDU.length);
            Assert.assertEquals("Erreur sur framePDU[0]",0x08,framePDU[0]);
            Assert.assertEquals("Erreur sur framePDU[1]",0x00,framePDU[1]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x0C,framePDU[2]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x00,framePDU[3]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x00,framePDU[4]);
        }
        catch(ModbusException e){
             System.out.println(e.getMessage());
        }
        
        
        /* Return Bus Exception Error Count */
        diagType = 9;
        try{
            framePDU = modbusExtended.Diagnostics(diagType,CAR);
            Assert.assertEquals("Erreur sur la taille de la trame retournée",5,framePDU.length);
            Assert.assertEquals("Erreur sur framePDU[0]",0x08,framePDU[0]);
            Assert.assertEquals("Erreur sur framePDU[1]",0x00,framePDU[1]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x0D,framePDU[2]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x00,framePDU[3]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x00,framePDU[4]);
        }
        catch(ModbusException e){
             System.out.println(e.getMessage());
        }
        
        
        /* Return Slave Message Count */
        diagType = 10;
        try{
            framePDU = modbusExtended.Diagnostics(diagType,CAR);
            Assert.assertEquals("Erreur sur la taille de la trame retournée",5,framePDU.length);
            Assert.assertEquals("Erreur sur framePDU[0]",0x08,framePDU[0]);
            Assert.assertEquals("Erreur sur framePDU[1]",0x00,framePDU[1]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x0E,framePDU[2]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x00,framePDU[3]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x00,framePDU[4]);
        }
        catch(ModbusException e){
             System.out.println(e.getMessage());
        }
        
        
        /* Return Slave No Response Count */
        diagType = 11;
        try{
            framePDU = modbusExtended.Diagnostics(diagType,CAR);
            Assert.assertEquals("Erreur sur la taille de la trame retournée",5,framePDU.length);
            Assert.assertEquals("Erreur sur framePDU[0]",0x08,framePDU[0]);
            Assert.assertEquals("Erreur sur framePDU[1]",0x00,framePDU[1]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x0F,framePDU[2]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x00,framePDU[3]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x00,framePDU[4]);
        }
        catch(ModbusException e){
             System.out.println(e.getMessage());
        }
        
        
        /* Return Slave NAK Count */
        diagType = 12;
        try{
            framePDU = modbusExtended.Diagnostics(diagType,CAR);
            Assert.assertEquals("Erreur sur la taille de la trame retournée",5,framePDU.length);
            Assert.assertEquals("Erreur sur framePDU[0]",0x08,framePDU[0]);
            Assert.assertEquals("Erreur sur framePDU[1]",0x00,framePDU[1]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x10,framePDU[2]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x00,framePDU[3]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x00,framePDU[4]);
        }
        catch(ModbusException e){
             System.out.println(e.getMessage());
        }
        
        
        /* Return Slave Busy Count */
        diagType = 13;
        try{
            framePDU = modbusExtended.Diagnostics(diagType,CAR);
            Assert.assertEquals("Erreur sur la taille de la trame retournée",5,framePDU.length);
            Assert.assertEquals("Erreur sur framePDU[0]",0x08,framePDU[0]);
            Assert.assertEquals("Erreur sur framePDU[1]",0x00,framePDU[1]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x11,framePDU[2]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x00,framePDU[3]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x00,framePDU[4]);
        }
        catch(ModbusException e){
             System.out.println(e.getMessage());
        }
        
        
        /* Return Bus Character Overrun Count */
        diagType = 14;
        try{
            framePDU = modbusExtended.Diagnostics(diagType,CAR);
            Assert.assertEquals("Erreur sur la taille de la trame retournée",5,framePDU.length);
            Assert.assertEquals("Erreur sur framePDU[0]",0x08,framePDU[0]);
            Assert.assertEquals("Erreur sur framePDU[1]",0x00,framePDU[1]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x12,framePDU[2]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x00,framePDU[3]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x00,framePDU[4]);
        }
        catch(ModbusException e){
             System.out.println(e.getMessage());
        }
        
        
        /* Clear Overrun Counter and Flag */
        diagType = 15;
        try{
            framePDU = modbusExtended.Diagnostics(diagType,CAR);
            Assert.assertEquals("Erreur sur la taille de la trame retournée",5,framePDU.length);
            Assert.assertEquals("Erreur sur framePDU[0]",0x08,framePDU[0]);
            Assert.assertEquals("Erreur sur framePDU[1]",0x00,framePDU[1]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x14,framePDU[2]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x00,framePDU[3]);
            Assert.assertEquals("Erreur sur framePDU[2]",0x00,framePDU[4]);
        }
        catch(ModbusException e){
             System.out.println(e.getMessage());
        }
        
        
        /* Error on diagType */
        diagType = 20;
        try{
            framePDU = modbusExtended.Diagnostics(diagType,CAR);
        }
        catch(ModbusException e){
             System.out.println(e.getMessage());
        }
        
        
        /* Error on CAR */
        diagType = 4;
        CAR = 260;
        try{
            framePDU = modbusExtended.Diagnostics(diagType,CAR);
        }
        catch(ModbusException e){
             System.out.println(e.getMessage());
        }
                
        
    }

    /**
     * Test of getComEventCounter method, of class schneider.ModbusExtended.
     */
    public void testGetComEventCounter() {
        System.out.println("--------------------------------------------");
        System.out.println("test method ModbusExtended.getComEventCounter");
        modbusExtended = new ModbusExtended();
        byte frame = 0x00;
        
        // TODO add your test code below by replacing the default call to fail.
        frame = modbusExtended.getComEventCounter();
        Assert.assertEquals("Erreur sur la valeur du byte retourné",0x0B,frame);
    }

    /**
     * Test of getComEventLog method, of class schneider.ModbusExtended.
     */
    public void testGetComEventLog() {
        System.out.println("----------------------------------------");
        System.out.println("test method ModbusExtended.getComEventLog");
        modbusExtended = new ModbusExtended();
        byte frame = 0x00;
        
        // TODO add your test code below by replacing the default call to fail.
        frame = modbusExtended.getComEventLog();
        Assert.assertEquals("Erreur sur la valeur du byte retourné",0x0C,frame);
    }

    /**
     * Test of reportSlaveId method, of class schneider.ModbusExtended.
     */
    public void testReportSlaveId() {
        System.out.println("---------------------------------------");
        System.out.println("test method ModbusExtended.reportSlaveId");
        modbusExtended = new ModbusExtended();
        byte frame = 0x00;
        
        // TODO add your test code below by replacing the default call to fail.
        frame = modbusExtended.reportSlaveId();
        Assert.assertEquals("Erreur sur la valeur du byte retourné",0x11,frame);
    }

    /**
     * Test of extractData method, of class schneider.ModbusExtended.
     */
    public void testExtractData() {
        System.out.println("-------------------------------------");
        System.out.println("test method ModbusExtended.extractData");
        
        // TODO add your test code below by replacing the default call to fail.
        System.out.println("no tested yet !!!");
    }
    
}
