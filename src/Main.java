import reader.SpecialLineReader;
import reader.SpecialReader;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        //specialReader(3);
        specialLineReader(3);
    }

    private static void specialReader(int line) throws IOException {
        SpecialReader specialReader = new SpecialReader(new FileReader("src/dosya.txt"));
        System.err.print(specialReader.readLineAt(line));
        //specialReader.readWords(); // throw atacaktır.
    }

    private static void specialLineReader(int line) throws IOException {
        SpecialLineReader specialLineReader = new SpecialLineReader(new FileReader("src/dosya.txt"));
        System.err.println(specialLineReader.readLineAt(line));
        // specialLineReader.readWords(); // Exception fırlatacaktır.
    }
}
