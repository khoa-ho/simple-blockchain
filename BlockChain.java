import java.security.NoSuchAlgorithmException;

public class BlockChain {

    private class Node {
        private Block value;
        private Node next;

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
        this.first.value = new Block(sz++, initial, null);
        this.first.next = null;
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
            anna += blk.getAmount();
            bob -= blk.getAmount();
            sz++;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public boolean removeLast() {
        if (sz == 0) {
            return false;
        } else {
            Node cur = first;
            while (cur.next != null) {
                cur = cur.next;
            }
            sz--;
            cur.next = cur.next.next;
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
        builder.append("\n");
        while (cur != null) {
            builder.append(cur.value.toString());
            builder.append("\n");
        }
        return builder.toString();
    }

}
