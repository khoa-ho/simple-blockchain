import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class BlockChainDriver {
    private static BlockChain blkChain;

    /**
     * Prompts users for input and receive input as a string
     * 
     * @param text
     *            the prompt
     * @return the user input
     */
    private static String prompt(String text) {
        System.out.print(text);
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Executes commands
     * 
     * @param cmd
     *            input command from user
     * @throws NoSuchAlgorithmException
     */
    private static void command(String cmd) throws NoSuchAlgorithmException {
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
            String am = prompt("Amount transferred? ");
            Block blk = blkChain.mine(Integer.parseInt(am));
            System.out.println("amount = " + am + ", nounce = " + blk.getNonce());
        } else if (cmd.equals("append")) {
            int am = Integer.parseInt(prompt("Amount transferred? "));
            long non = Long.parseLong(prompt("Nonce? "));
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
        } else {
            System.out.println("Command not found! Type help for the command list.");
        }
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        blkChain = new BlockChain(Integer.parseInt(args[0]));

        String cmd = prompt("Command? ");
        while (!cmd.equals("quit")) {
            command(cmd);
            System.out.println(blkChain.toString());
            cmd = prompt("Command? ");
        }
    }
}
