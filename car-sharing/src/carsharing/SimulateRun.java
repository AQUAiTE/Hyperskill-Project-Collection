/*package carsharing;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class SimulateRun {
    public static void main(String[] args) {
        PrintStream originalOut = System.out;
        PrintStream originalErr = System.err;

        // Simulate input
        String input = String.join("\n",
                "1" ,
                        "2" ,
                        "Tesla" ,
                        "2" ,
                        "Jeep" ,
                        "2" ,
                        "Hyundai" ,
                        "1" ,
                        "1" ,
                        "2" ,
                        "Model S" ,
                        "2" ,
                        "Model X" ,
                        "2" ,
                        "Model Y" ,
                        "2" ,
                        "Cybertruck" ,
                        "0" ,
                        "1" ,
                        "2" ,
                        "2" ,
                        "Wrangler" ,
                        "2" ,
                        "Cherokee" ,
                        "2" ,
                        "Trailhawk" ,
                        "0" ,
                        "1" ,
                        "3" ,
                        "2" ,
                        "Elantra" ,
                        "0" ,
                        "0" ,
                        "3" ,
                        "James Bond" ,
                        "3" ,
                        "Austin Powers" ,
                        "2" ,
                        "1" ,
                        "1" ,
                        "1" ,
                        "1" ,
                        "0" ,
                        "2", // Log in as customer
                        "2",
                        "2", // Return
                        "1", // Rent Car
                        "1", // Pick Company
                        "2", // Pick Car
                        "3", // See Info
                        "2", // Return
                        "0",
                        "0"
        );

        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(inputStream);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        System.setOut(printStream);
        System.setErr(printStream);

        Main.main(new String[0]);

        printStream.flush();
        System.setOut(originalOut);
        System.setErr(originalErr);

        String output = outputStream.toString(StandardCharsets.UTF_8);
        System.out.println("Captured Output:");
        System.out.println(output);
    }
}

*/