import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Block {
    private int num;
    private int amount;
    private Hash prevHash;
    private long nonce;
    private Hash curHash;

    public Block(int num, int amount, Hash prevHash) throws NoSuchAlgorithmException {
        this.num = num;
        this.amount = amount;
        this.prevHash = prevHash;
        this.nonce = 0;
        calculateHash();
        while (!curHash.isValid()) {
            nonce++;
            calculateHash();
        }
    }

    public Block(int num, int amount, Hash prevHash, long nonce) {
        this.num = num;
        this.amount = amount;
        this.prevHash = prevHash;
        this.nonce = nonce;
    }

    public int getNum() {
        return num;
    }

    public int getAmount() {
        return amount;
    }

    public void calculateHash() throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("sha-256");

        ByteBuffer numByteBuffer = ByteBuffer.allocate(4).putInt(num);
        ByteBuffer amountByteBuffer = ByteBuffer.allocate(4).putInt(amount);
        ByteBuffer nonceByteBuffer = ByteBuffer.allocate(8).putLong(nonce);

        md.update(numByteBuffer);
        md.update(amountByteBuffer);
        if (num != 0) {
            md.update(prevHash.getData());
        }
        md.update(nonceByteBuffer);

        curHash = new Hash(md.digest());
    }

    public long getNonce() throws NoSuchAlgorithmException {
        return nonce;
    }

    public Hash getPrevHash() {
        return prevHash;
    }

    public Hash getHash() {
        return curHash;
    }

    public String toString() {
        return "Block" + Integer.toString(num) + "(Amount:" + Integer.toString(amount) + ", " + "Nounce: "
                + Long.toString(nonce) + ", " + "prevHash: " + prevHash.toString() + ", " + "hash: "
                + curHash.toString() + ")";
    }
}
