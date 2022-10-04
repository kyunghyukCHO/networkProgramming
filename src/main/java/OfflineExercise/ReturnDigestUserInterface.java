package OfflineExercise;

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

        // Polling 을 이용한 방법 !!!

//        ReturnDigest[] digests = new ReturnDigest[args.length];
//        for (int i = 0; i < args.length; i++) {
//            digests[i] = new ReturnDigest(args[i]);
//            digests[i].start();
//        }
//        for (int i = 0; i < args.length; i++) {
//            StringBuffer result = new StringBuffer(args[i]);
//            result.append(" : ");
//            while (true) { // 무한 루프를 돌면서 스레드가 실행이 끝났는지 검사함
//                byte[] digest = digests[i].getDigest();
//                if (digest != null) {
//                    result.append(toHexString(digest));
//                    System.out.println(result);
//                    break;
//                }
//                else {
//                    System.out.print("");        // --> null인 상태에서 while문을 돌며 CPU 독점 상태.. 해결 불가능
//                }
//            }
//        }

        // Join 을 이용한 방법 !!!

        ReturnDigest[] digests = new ReturnDigest[args.length];
        for (int i = 0; i < args.length; i++) {
            digests[i] = new ReturnDigest(args[i]);
            digests[i].start();
        }

        for (int i = 0; i < args.length; i++) {
            StringBuffer result = new StringBuffer(args[i]);
            result.append(" : ");
            try {
                digests[i].join(); // run 메소드가 끝날 때까지 Blocking 시행
                byte[] digest = digests[i].getDigest();
                result.append(toHexString(digest));
                System.out.println(result);

                // 만약 thread 가 끝난 후 join 메소드를 호출한다면 blocking 없이 넘어갑니다.
                // blocking 을 시도하기 전 thread 의 상태를 확인합니다.

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }



    }
}
