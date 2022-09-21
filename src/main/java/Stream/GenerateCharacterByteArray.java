package Stream;

import java.io.IOException;
import java.io.OutputStream;

public class GenerateCharacterByteArray {
    public static void main(String[] args) {
        try {
            generateCharacters(System.out);
        } catch (IOException ex){

        }

    }

    public static void generateCharacters(OutputStream out) throws IOException {
        int firstPrintableCharacter = 33;
        int numberOfPrintableCharacters = 94;
        int numberOfCharactersPerLine = 72;
        int start = firstPrintableCharacter;
        int count = 0;
        byte[] line = new byte[numberOfCharactersPerLine+2];

        while(count < 100) {
            for (int i=start; i<start + numberOfCharactersPerLine; i++) {
                line[i-start] = (byte) ((i-firstPrintableCharacter) % numberOfPrintableCharacters + firstPrintableCharacter);
            }
            line[numberOfCharactersPerLine] = ((byte) '\r');
            line[numberOfCharactersPerLine+1] = ((byte) '\n');

            out.write(line);
            start = ((start+1) - firstPrintableCharacter) % numberOfPrintableCharacters + firstPrintableCharacter;
            count++;
        }
    }
}
