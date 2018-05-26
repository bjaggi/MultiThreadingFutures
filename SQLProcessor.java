
import java.util.Random;
import java.util.concurrent.Callable;

public class SQLProcessor implements Callable<String> {

    private int id;

    public SQLProcessor(int id) {
        this.id = id;
    }


    @Override
    public String toString() {

            System.out.println("Thread "+id+"will sleep for 5 secs");

        return  Integer.toString(id);
    }


    @Override
    public String call() throws Exception {
        try {
            Random random = new Random();
            int sleepInterval = random.nextInt(50000);
            System.out.println("Thread ...call method(), Thread Id :  "+id+", will sleep for " + sleepInterval +" milli secs");
            Thread.sleep(sleepInterval);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return  Integer.toString(id);
    }
}



