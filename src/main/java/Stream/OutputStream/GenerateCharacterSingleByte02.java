package Stream.OutputStream;

import java.io.IOException;
import java.io.OutputStream;

public class GenerateCharacterSingleByte02 {
    public static void main(String[] args) {
        try {
            generateCharacters(System.out);
        } catch (IOException ex){

        }
    }

    public static void generateCharacters(OutputStream out) throws IOException {
        int firstPrintableCharacter = 33; // printable ASCII characters start from 33
        int numberOfPrintableCharacters = 94;
        int numberOfCharactersPerLine = 72;
        int start = firstPrintableCharacter;
        int count = 0;
        byte[] line = new byte[numberOfCharactersPerLine+2];

        while(count < 1000) {
            for (int i=start; i<start + numberOfCharactersPerLine; i++) {
                out.write((byte) ((i-firstPrintableCharacter) % numberOfPrintableCharacters + firstPrintableCharacter));
                // i 가 128 일 경우 순환됩니다.
            }
            out.write((byte) '\r'); // carriage return
            out.write((byte) '\n'); // line feed
            start = ((start+1) - firstPrintableCharacter) % numberOfPrintableCharacters + firstPrintableCharacter;
            count++;
        }
    }
}
