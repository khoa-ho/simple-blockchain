import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Block {
    private int num;
    private int amount;
    private Hash prevHash;
    private long nonce;
    private Hash curHash;

    /**
     * Constructs a new block with given parameters
     * 
     * @param num
     *            the number of the block in the blockchain
     * @param amount
     *            the amount transferred from one account to another (from Anna to
     *            Bob)
     * @param prevHash
     *            the hash from the previous block in the chain
     * @throws NoSuchAlgorithmException
     */
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

    /**
     * Constructs a new block with given parameters
     * 
     * @param num
     *            the number of the block in the blockchain
     * @param amount
     *            the amount transferred from one account to another (from Anna to
     *            Bob)
     * @param prevHash
     *            the hash from the previous block in the chain
     * @param nonce
     *            the nonce of the block
     * @throws NoSuchAlgorithmException
     */
    public Block(int num, int amount, Hash prevHash, long nonce) throws NoSuchAlgorithmException {
        this.num = num;
        this.amount = amount;
        this.prevHash = prevHash;
        this.nonce = nonce;
        calculateHash();
    }

    /**
     * Gets the number of the block
     * 
     * @return the number of the block
     */
    public int getNum() {
        return num;
    }

    /**
     * Gets amount transferred in the block
     * 
     * @return the amount transferred in the block
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Calculates the hash value for the block
     * 
     * @throws NoSuchAlgorithmException
     */
    public void calculateHash() throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("sha-256");

        byte[] numByteBuffer = ByteBuffer.allocate(4).putInt(num).array();
        byte[] amountByteBuffer = ByteBuffer.allocate(4).putInt(amount).array();
        byte[] nonceByteBuffer = ByteBuffer.allocate(8).putLong(nonce).array();

        md.update(numByteBuffer);
        md.update(amountByteBuffer);
        if (num != 0) {
            md.update(prevHash.getData());
        }
        md.update(nonceByteBuffer);

        curHash = new Hash(md.digest());
    }

    /**
     * Gets the nonce in the block
     * 
     * @return the nonce of the block
     */
    public long getNonce() {
        return nonce;
    }

    /**
     * Gets the previous hash value of the previous block
     * 
     * @return the hash of the previous block
     */
    public Hash getPrevHash() {
        return prevHash;
    }

    /**
     * Gets the hash from the current block
     * 
     * @return the hash of this block
     */
    public Hash getHash() {
        return curHash;
    }

    /**
     * Formats block information into a string representation
     * 
     * @return a string representation of the block in the specified format
     */
    public String toString() {
        return "Block " + Integer.toString(num) + " (Amount: " + Integer.toString(amount) + ", "
                + "Nonce: " + Long.toString(nonce) + ", " + "prevHash: "
                + ((prevHash != null) ? prevHash.toString() : "null") + ", " + "hash: "
                + curHash.toString() + ")";
    }
}
