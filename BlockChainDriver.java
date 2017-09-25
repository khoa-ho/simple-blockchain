import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class BlockChainDriver {
    public static BlockChain blkChain;

    public static String prompt() {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public static void command(String cmd) throws NoSuchAlgorithmException {
        if (cmd.equals("help")) {
            System.out.println("Valid commands:");
            System.out.println("\tmine: discovers the nonce for a given transaction");
            System.out.println("\tappend: appends a new block onto the end of the chain");
            System.out.println("\tremove: removes the last block from the end of the chain");
            System.out.println("\tcheck: checks that the block chain is valid");
            System.out.println("\treport: reports the balances of Alice and Bob");
            System.out.println("\thelp: prints this list of commands");
            System.out.println("\tquit: quits the program");
        } else if (cmd.equals("mine")) {
            System.out.print("Amount transferred? ");
            String am = prompt();
            Block blck = blkChain.mine(Integer.parseInt(am));
            System.out.println("amount = " + am + ", nounce = " + blck.getNonce());
        } else if (cmd.equals("append")) {
            System.out.print("Amount transferred? ");
            int am = Integer.parseInt(prompt());
            System.out.print("Nonce? ");
            long non = Long.parseLong(prompt());
            Block n = new Block(blkChain.getSize(), am, blkChain.getHash(), non);
            blkChain.append(n);
        } else if (cmd.equals("remove")) {
            blkChain.removeLast();
        } else if (cmd.equals("check")) {
            if (blkChain.isValidBlockChain()) {
                System.out.println("Chain is valid");
            } else {
                System.out.println("Chain is invalid");
            }
        } else if (cmd.equals("report")) {
            blkChain.printBalance();
        }
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        blkChain = new BlockChain(Integer.parseInt(args[0]));

        System.out.print("Command? ");
        String cmd = prompt();
        while (!cmd.equals("quit")) {
            command(cmd);
            System.out.println(blkChain.toString());
            System.out.print("Command? ");
            cmd = prompt();
        }
    }
}
