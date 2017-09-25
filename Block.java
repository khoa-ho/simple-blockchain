import java.security.NoSuchAlgorithmException;

public class BlockChain {

    private class Node {
        public Block value;
        public Node next;

        public Node(Block value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    private Node first;
    private Node last;
    private int sz;
    private int anna;
    private int bob;

    /**
     * Initializes a blockchain that possess a single block the starts with the
     * given initial amount
     * 
     * @param initial
     *            the initial amount that Alice has
     * @throws NoSuchAlgorithmException
     */
    public BlockChain(int initial) throws NoSuchAlgorithmException {
        this.sz = 0;
        this.anna = initial;
        this.bob = 0;
        this.first = new Node(new Block(sz++, initial, null), null);
        this.last = this.first;
    }

    /**
     * Mines a new candidate block to be added to the list
     * 
     * @param amount
     *            the amount transferred
     * @return the candidate block
     * @throws NoSuchAlgorithmException
     */
    public Block mine(int amount) throws NoSuchAlgorithmException {
        return new Block(sz, amount, last.value.getHash());
    }

    /**
     * Gets the size of the blockchain
     * 
     * @return the size of the blockchain
     */
    public int getSize() {
        return sz;
    }

    /**
     * Appends this block to the end of the chain
     * 
     * @param blk
     *            the block to be added
     * @throws IllegalArgumentException
     *             if this block cannot be added to the list (because it is invalid
     *             wrt the rest of the blocks)
     */
    public void append(Block blk) {
        if (blk.getHash().isValid()) {
            Node n = new Node(blk, null);
            last.next = n;
            last = n;
            sz++;
            anna += blk.getAmount();
            bob -= blk.getAmount();
        } else {
            System.out.println("The given block has invalid hash!");
            throw new IllegalArgumentException();
        }
    }

    /**
     * Removes the last block from the chain
     * 
     * @return true if a block is removed
     */
    public boolean removeLast() {
        if (sz == 0) {
            return false;
        } else {
            Block lastBlk = last.value;
            anna -= lastBlk.getAmount();
            bob += lastBlk.getAmount();

            Node cur = first;
            while (cur.next.next != null) {
                cur = cur.next;
            }
            cur.next = null;
            last = cur;
            sz--;
            return true;
        }
    }

    /**
     * Gets the hash of the last block in the chain
     * 
     * @return the hash
     */
    public Hash getHash() {
        return last.value.getHash();
    }

    /**
     * Walks the blockchain and ensures that its blocks are consistent and valid
     * 
     * @return true if the balance of each person at the end of the chain is
     *         non-negative
     */
    public boolean isValidBlockChain() {
        return bob >= 0 && anna >= 0;
    }

    /**
     * Prints the balance of each person at the end of the chain
     */
    public void printBalance() {
        System.out.println(
                "Alice: " + Integer.toString(anna) + ", " + "Bob: " + Integer.toString(bob));
    }

    /**
     * Represents the blockchain as a string representation
     * 
     * @return the string representation of each of its blocks, earliest to latest,
     *         one per line
     */
    public String toString() {
        Node cur = first;
        StringBuilder builder = new StringBuilder();
        while (cur != null) {
            builder.append("\n");
            builder.append(cur.value.toString());
            cur = cur.next;
        }
        return builder.toString();
    }
}
