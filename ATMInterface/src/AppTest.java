import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class AppTest {
    private ATM atm;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    @Before
    public void setUp() {
        atm = new ATM();
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void tearDown() {
        atm = null;
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    public void testValidAuthentication() {
        // Create a dummy user
        User user = new User("4", "0410", 30000);

        // Set the input to simulate user input
       String userId="4";
       String userPIN="0410";
       double balance=30000;
        

        // Call the method under test
        atm.authenticateUser(user);

      assertEquals(user.getUserID(),userId);
      assertEquals(user.getUserPIN(),userPIN);
      assertEquals(user.getAccountBalance(),balance,0.001);
    }

    @Test
    public void testInvalidAuthentication() {
        // Create a dummy user
        User user = new User("5", "0410", 30000);

        // Set the input to simulate invalid user input
        String userId="4";
        String userPIN="0410";
        double balance=30000;

        // Call the method under test
        atm.authenticateUser(user);

        // Verify that the output contains the failure message
        assertEquals(user.getUserID(),userId);
        assertEquals(user.getUserPIN(),userPIN);
        assertEquals(user.getAccountBalance(),balance,0.001);
    }

    @Test
    public void testWithdrawMoney() {
        // Create a dummy user with initial balance
        User user = new User("4", "0410", 30000);

        // Set the input to simulate user input for withdrawal
        String input = "500\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        // Call the method under test
        atm.withdrawMoney(user);

        // Verify that the output contains the success message
        assertEquals("Amount withdrawn successfully.\n", outContent.toString());
    }

    @Test
    public void testDepositMoney() {
        // Create a dummy user with initial balance
        User user = new User("4", "0410", 30000);

        // Set the input to simulate user input for deposit
        String input = "500\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        // Call the method under test
        atm.depositMoney(user);

        // Verify that the output contains the success message
        assertEquals("Amount deposited successfully.\n", outContent.toString());
    }
}
