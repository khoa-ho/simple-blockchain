import java.util.Arrays;

public class Hash {
    public byte[] data;

    /**
     * Constructs a new Hash object that contains the given hash (as an array of
     * bytes)
     * 
     * @param data
     *            hash as an array of bytes
     */
    public Hash(byte[] data) {
        this.data = data;
    }

    /**
     * Gets hash
     * 
     * @return the hash contained in this object
     */
    public byte[] getData() {
        return data;
    }

    /**
     * Checks if this hash meets the criteria for validity
     * 
     * @return true if its first three indices contain zeroes
     */
    public boolean isValid() {
        for (int i = 0; i < 3; i++) {
            if (data[i] != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Converts hash to String format
     * 
     * @return the hash as a string of hexadecimal digits, 2 digits per byte
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (byte b : data) {
            builder.append(String.format("%02x", Byte.toUnsignedInt(b)));
        }
        return builder.toString();
    }

    /**
     * Compares an object with the Hash object
     * 
     * @param other
     *            an object to compare this hash to
     * @return true if hash is structurally to other
     */
    public boolean equals(Object other) {
        boolean ret = false;
        if (other instanceof Hash) {
            Hash o = (Hash) other;
            ret = Arrays.equals(this.data, o.data);
        }
        return ret;
    }
}
