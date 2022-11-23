package Stream.OutputStream;

public class GenerateCharacterSingleByte01 {
    public static void main(String[] args) {
        for (int i=0; i<300; i++) {
            System.out.println("=========");
            System.out.write(i); // wirte 는 정수형을 읽습니다 그 중 1byte 를 읽습니다. -> 그 값의 ASCII 출력
            System.out.println();
            System.out.println(i); //
            System.out.println("=========");
        }
    }
}
