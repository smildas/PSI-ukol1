public class Main {

    public static void main(String[] args) {
        if(args.length != 2){
            System.out.println("java -jar <path to jar file> <ST parametr> <discovery verbose (true/false)> ");
            return;
        }

        String st = args[0];
        boolean verbose = Boolean.parseBoolean(args[1]);


        Thread threadDiscovery = new Thread(new SSDPDiscavery(st, verbose));
        threadDiscovery.start();

        Thread threadSsdpReciever = new Thread(new SSDPMessageReciever());
        threadSsdpReciever.start();

    }
}
