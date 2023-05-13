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


public static String decode(String input) {
        StringBuilder output = new StringBuilder();
        byte[] bytes = new byte[input.length()];
        int byteCount = 0;

        for (int i = 0; i <= input.length(); i++) {
            char c = input.charAt(i);
            if (c == '=') {
                break;
            }

            int value = BASE64_ALPHABET.indexOf(c);
            if (value == -1) {
                throw new IllegalArgumentException("Invalid character in input");
            }

            bytes[byteCount++] = (byte) value;
        }

        for (int i = 0; i < byteCount; i += 4) {
            int b = ((bytes[i] & 0x3F) << 18) | ((bytes[i + 1] & 0x3F) << 12) | ((bytes[i + 2] & 0x3F) << 6) | (bytes[i + 3] & 0x3F);
            output.append((char) ((b >> 16) & 0xFF));
            if (bytes[i + 2] != 64 && byteCount - i > 3) {
                output.append((char) ((b >> 8) & 0xFF));
                if (bytes[i + 3] != 64 && byteCount - i > 4) {
                    output.append((char) (b & 0xFF));
                }
            }
        }

        return output.toString();
    }
}
