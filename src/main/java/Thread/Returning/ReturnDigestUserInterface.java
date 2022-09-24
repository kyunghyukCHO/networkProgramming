package Thread.Returning;

public class ReturnDigestUserInterface {

    public static String toHexString(byte[] bytes) { // byte array 를 읽어서 String 변환
        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]); // bitwise operation
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }


    public static void main(String[] args) {

        // NullPointException Solution First Try --> But Still NullPointException occur
        // Start all the thread and get digest
        /*
        ReturnDigest[] digests = new ReturnDigest[args.length];
        for (int i = 0; i < args.length; i++) {
            digests[i] = new ReturnDigest(args[i]);
            digests[i].start();
        }
        for (int i = 0; i < args.length; i++) {
            StringBuffer result = new StringBuffer(args[i]);
            result.append(" : ");
            byte[] digest = digests[i].getDigest();
            result.append(toHexString(digest));
            System.out.println(result);
        }
        */


        // Polling 을 이용한 방법 !!!
        ReturnDigest[] digests = new ReturnDigest[args.length];
        for (int i = 0; i < args.length; i++) {
            digests[i] = new ReturnDigest(args[i]);
            digests[i].start();
        }
        for (int i = 0; i < args.length; i++) {
            StringBuffer result = new StringBuffer(args[i]);
            result.append(" : ");
            while (true) { // 무한 루프를 돌면서 스레드가 실행이 끝났는지 검사함
                byte[] digest = digests[i].getDigest();
                if (digest != null) {
                    result.append(toHexString(digest));
                    System.out.println(result);
                    break;
                } else {
                    System.out.println();
                }
            }
        }

//        for (String filename : args) {
//            ReturnDigest dr = new ReturnDigest(filename);
//            dr.start(); // run() 매소드 실행
//
//            StringBuilder result = new StringBuilder(filename);
//            result.append(" : ");
//            byte[] digest = dr.getDigest();
//            result.append(toHexString(digest)); // NULL POINT EXCEPTION 발생 !!!!!!!!!!
//            System.out.println(result);
//        }
    }
}
