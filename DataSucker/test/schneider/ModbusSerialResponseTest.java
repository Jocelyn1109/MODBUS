/*
 * ModbusSerialResponseTest.java
 * JUnit based test
 *
 * Created on 9 février 2006, 20:50
 */

package schneider;

import junit.framework.*;

/**
 *
 * @author JGirod
 */
public class ModbusSerialResponseTest extends TestCase {
    
    public ModbusSerialResponseTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(ModbusSerialResponseTest.class);
        
        return suite;
    }

    /**
     * Test of checkCrcTrameReceived method, of class schneider.ModbusSerialResponse.
     */
    public void testCheckCrcTrameReceived() {
        System.out.println("------------------------------------------------------");
        System.out.println("test method ModbusSerialResponse.checkCrcTrameReceived");
        
        byte[] receive_trame = null;
        
        boolean expResult = true;
        //boolean result = ModbusSerialResponse.checkCrcTrameReceived(receive_trame);
        //assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        System.out.println("Test not implemented yet !!!");
    }

    /**
     * Test of checkIfModbusError method, of class schneider.ModbusSerialResponse.
     */
    public void testCheckIfModbusError() {
        System.out.println("--------------------------------------------------");
        System.out.println("test method ModbusSerialReponse.checkIfModbusError");
        
        /* variables declaration */
        byte[] receive_trame = new byte[5];
        byte[] receive_trame_1 = new byte[11];
        boolean resultCheck;
        String expResult = "";
        String expResult_1 = "";
        
        //ERROR
        /* variable initialization - receive_trame[] for error */
        receive_trame[0] = 0x1;
        receive_trame[1] = (byte)0x83; //error code
        receive_trame[2] = 0x01; //exception code
        receive_trame[3] = 0x11; //CRC
        receive_trame[4] = 0x3; //CRC
        
        
        String result = ModbusSerialResponse.checkIfModbusError(receive_trame);
        expResult = "ERROR:ILLEGAL FUNCTION";
        
        // TODO review the generated test code and remove the default call to fail.
         Assert.assertEquals("Erreur sur result",expResult,result);
         
        //NO ERROR
        /* variable initialization - receive_trame[] for no error*/
        receive_trame_1[0] = 0x1;
        receive_trame_1[1] = 0x3;
        receive_trame_1[2] = 0x6;
        receive_trame_1[3] = 0x2;
        receive_trame_1[4] = 0x2B;
        receive_trame_1[5] = 0x0;
        receive_trame_1[6] = 0x0;
        receive_trame_1[7] = 0x0;
        receive_trame_1[8] = 0x64;
        receive_trame_1[9] = 0x11;
        receive_trame_1[10] = 0x3;
        
        String result_1 = ModbusSerialResponse.checkIfModbusError(receive_trame_1);
        expResult_1 = "NO ERROR";
        
        // TODO review the generated test code and remove the default call to fail.
         Assert.assertEquals("Erreur sur result_1",expResult_1,result_1);
    }

    /**
     * Test of getData method, of class schneider.ModbusSerialResponse.
     */
    public void testGetData() {
        System.out.println("----------------------------------------");
        System.out.println("test method ModbusSerialResponse.getData");
        
        byte[] receive_trame = new byte[11];
        byte[] receive_trame_1 = new byte[6];
        byte[] receive_trame_2 = new byte[8];
        byte[] expResult = new byte[6];
        byte[] expResult_1 = new byte[4];
        
        /* variable initialization - receive_trame[] for no error*/
        receive_trame[0] = 0x1;
        receive_trame[1] = 0x3;
        receive_trame[2] = 0x6;
        receive_trame[3] = 0x2;
        receive_trame[4] = 0x2B;
        receive_trame[5] = 0x0;
        receive_trame[6] = 0x0;
        receive_trame[7] = 0x0;
        receive_trame[8] = 0x64;
        receive_trame[9] = 0x11;
        receive_trame[10] = 0x3; 
        
        /* variable initialization - expResult[] for no error*/
        expResult[0] = 0x2;
        expResult[1] = 0x2B;
        expResult[2] = 0x0;
        expResult[3] = 0x0;
        expResult[4] = 0x0;
        expResult[5] = 0x64;
        
        
        // TODO review the generated test code and remove the default call to fail.
        // with no Modbus excpetion
        byte[] result = ModbusSerialResponse.getData(receive_trame);
        Assert.assertEquals("Erreur sur result[0]",expResult[0],result[0]);
        Assert.assertEquals("Erreur sur result[1]",expResult[1],result[1]);
        Assert.assertEquals("Erreur sur result[2]",expResult[2],result[2]);
        Assert.assertEquals("Erreur sur result[3]",expResult[3],result[3]);
        Assert.assertEquals("Erreur sur result[4]",expResult[4],result[4]);
        Assert.assertEquals("Erreur sur result[5]",expResult[5],result[5]);
        
        
        /* variable initialization - receive_trame_1[] for error*/
        receive_trame_1[0] = 0x1;
        receive_trame_1[1] = (byte)0x83; //error code
        receive_trame_1[2] = 0x01; //exception code
        receive_trame_1[3] = 0x11; //CRC
        receive_trame_1[4] = 0x3; //CRC
        
        result = ModbusSerialResponse.getData(receive_trame_1);
        Assert.assertEquals("Erreur sur result[0]",127,result[0]);
        
        /* variable initialization - receive_trame_1[] for no error - function code = Get Comm Event Counter */
        receive_trame_2[0] = 0x1;
        receive_trame_2[1] = 0x0B;
        receive_trame_2[2] = (byte)0xFF;
        receive_trame_2[3] = (byte)0xFF;
        receive_trame_2[4] = 0x01;
        receive_trame_2[5] = 0x08;
        receive_trame_2[6] = 0x11; //CRC
        receive_trame_2[7] = 0x3; //CRC
        
        /* variable initialization - expResult_1[] for no error*/
        expResult_1[0] = (byte)0xFF;
        expResult_1[1] = (byte)0xFF;
        expResult_1[2] = 0x01;
        expResult_1[3] = 0x08;
        
        byte[] result_1 = ModbusSerialResponse.getData(receive_trame_2);
        Assert.assertEquals("Erreur sur result_1[0]",expResult_1[0],result_1[0]);
        Assert.assertEquals("Erreur sur result_1[1]",expResult_1[1],result_1[1]);
        Assert.assertEquals("Erreur sur result_1[2]",expResult_1[2],result_1[2]);
        Assert.assertEquals("Erreur sur result_1[3]",expResult_1[3],result_1[3]);    
        
        
    }
    
}
