import java.util.Arrays;

public class Hash {
    public byte[] data;

    public Hash(byte[] data) {
        this.data = data;
    }

    public byte[] getData() {
        return data;
    }

    public boolean isValid() {
        for (int i = 0; i < 3; i++) {
            if (data[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (byte b : data) {
            builder.append(String.format("%02x", Byte.toUnsignedInt(b)));
        }
        return builder.toString();
    }

    public boolean equals(Object other) {
        boolean ret = false;
        if (other instanceof Hash) {
            Hash o = (Hash) other;
            ret = Arrays.equals(this.data, o.data);
        }
        return ret;
    }
}
