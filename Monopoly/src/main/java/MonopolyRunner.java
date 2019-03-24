public class MonopolyRunner {

    public static void main(String[] args) {
        System.out.println("Starting Monopoly...");
        System.out.println("Press \"New Game\" to begin.");
        Controller controller;
        if (args.length > 0) {
            String arg = args[0];
            controller = new Controller(arg);
        } else controller = new Controller("");
    }
}
