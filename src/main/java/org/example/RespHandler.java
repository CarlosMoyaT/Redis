package org.example;

import java.io.*;
import java.util.*;

//Resp is a binary protocol that uses control sequences encoded in standard ASCII
public class RespHandler {
    public static void main(String[] args) {

        String input = "$5\r\nAhmed\r\n"; //RESP input example
        StringReader stringReader = new StringReader(input);
        BufferedReader reader = new BufferedReader(stringReader);

    /*Read the data type, which is the first byte in the buffer.
     Then, read the number to determine the number of characters we need to read,
     which is 5, plus an additional 2 bytes, ‘\r\n’.
    */

        try {
            char type = (char) reader.read();
            if (type != '$') {
                System.out.println("Invalid type, expecting bulk strings only");
                System.exit(1);
            }

            // read the number for size String
            StringBuilder sizeStr = new StringBuilder();
            char ch;
            while ((ch = (char) reader.read()) != '\r') {
                sizeStr.append(ch);
            }

            long strSize = Long.parseLong(sizeStr.toString());

            reader.read();

            char[] stringChars = new char[(int) strSize];
            reader.read(stringChars);

            String result = new String(stringChars);
            System.out.println("Received string: " + result);

            if ((char) reader.read() != '\r' || (char) reader.read() != '\n') {
                System.out.println("Expected end of line \\r\\n not found.");
                System.exit(1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
