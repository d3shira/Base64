public class Main {
    public static void main(String[] args) {

    }
}
class Base64 {

    private static final String BASE64_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

    public static String encode(String input) {
        byte[] bytes = input.getBytes();
        StringBuilder output = new StringBuilder();
        int paddingCount = (3 - (bytes.length % 3)) % 3;
        byte[] paddedBytes = new byte[bytes.length + paddingCount];
        System.arraycopy(bytes, 0, paddedBytes, 0, bytes.length);

        for (int i = 0; i < paddedBytes.length; i += 3) {
            int b = ((paddedBytes[i] & 0xFF) << 16) | ((paddedBytes[i + 1] & 0xFF) << 8) | (paddedBytes[i + 2] & 0xFF);
            output.append(BASE64_ALPHABET.charAt((b >> 18) & 0x3F));
            output.append(BASE64_ALPHABET.charAt((b >> 12) & 0x3F));
            output.append(BASE64_ALPHABET.charAt((b >> 6) & 0x3F));
            output.append(BASE64_ALPHABET.charAt(b & 0x3F));
        }

        for (int i = 0; i < paddingCount; i++) {
            output.setCharAt(output.length() - 1 - i, '=');
        }

        return output.toString();
    }









}
