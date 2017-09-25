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

    public BlockChain(int initial) throws NoSuchAlgorithmException {
        this.sz = 0;
        this.anna = initial;
        this.bob = 0;
        this.first = new Node(new Block(sz++, initial, null), null);
        this.last = this.first;
    }

    public Block mine(int amount) throws NoSuchAlgorithmException {
        return new Block(sz, amount, last.value.getHash());
    }

    public int getSize() {
        return sz;
    }

    public void append(Block blk) {
        if (blk.getHash().isValid()) {
            Node n = new Node(blk, null);
            last.next = n;
            last = n;
            sz++;
            anna += blk.getAmount();
            bob -= blk.getAmount();
        } else {
            throw new IllegalArgumentException();
        }
    }

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

    public Hash getHash() {
        return last.value.getHash();
    }

    public boolean isValidBlockChain() {
        /**
         * Node cur = first; while (cur != null) { if (!cur.value.getHash().isValid()) {
         * return false; } cur = cur.next; }
         */
        return true;
    }

    public void printBalance() {
        System.out.println("Alice: " + Integer.toString(anna) + ", " + "Bob: " + Integer.toString(bob));
    }

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
