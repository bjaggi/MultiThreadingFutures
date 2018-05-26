
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class MultiThreading {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool(   );

        Future<SQLProcessor> future = executorService.submit(new Callable<SQLProcessor>() {
            int id = 1;
            @Override
            public SQLProcessor call() throws Exception {
                Random random = new Random();
                int duration = random.nextInt(4000);

          /*      if(duration > 2000)
                    throw new IOException("Sleeping for too long !");
*/

                System.out.println("Starting ..."+id);

                try {
                    Thread.sleep(duration);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("FINISHED ..."+id);
                return new SQLProcessor(id);
            }
        });




        List<Future<String>> list = new ArrayList<Future<String>>();
        for (int i = 0; i < 20; i++) {
            Callable<String> worker = new SQLProcessor(i);
            Future<String> submit = executorService.submit(worker);
            list.add(submit);
        }



        String sumStr = "";
        System.out.println(list.size());
        // now retrieve the result
        for (Future<String> future2 : list) {
            try {
                sumStr += future2.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Custom Objects : "+sumStr);



        try {
            for (Future<String> future2 : list)
            System.out.println(future2.get() + " , isDone " + future2.isDone());


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }



        try {
            executorService.awaitTermination(1000, TimeUnit.MILLISECONDS);
            executorService.shutdown();
            System.out.println(" THE END !!! ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}


/*
    Random random = new Random();
    int duration  = random.nextInt(4000);
                System.out.println("Starting ...");

                try {
        Thread.sleep(duration);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }

                System.out.println("FINISHED ...");*/
