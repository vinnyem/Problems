
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class PublisherSubscriber {
    public static class Publisher implements Runnable {
        private BlockingQueue<String> queue;
        List<String> dictionary;

        public Publisher(BlockingQueue <String> queue, List<String> dictionary) {
            this.queue = queue;
            this.dictionary = dictionary;
        }

        public void run () {
            try {
                int i = 0;
                while (i<dictionary.size()) {
                    Thread.sleep(10);
                    //Enqueuing the items in the Queue
                    queue.put(dictionary.get(i++));
                }
                Thread.sleep(10);
                queue.put ("EOF");
            } catch (InterruptedException e) {
                System.out.println("Caught an Exception. Throw something meaningful");
            }
        }
    }

    public static class Subscriber implements Runnable {
        private BlockingQueue <String> queue;

        public Subscriber(BlockingQueue <String> queue) {
            this.queue = queue;
        }

        public void run () {
            try {
                String text;
                //Dequeuing the items
                while (!(text = queue.take().toString()).equals("EOF")) {
                    Thread.sleep(10);
                    System.out.println("Subscriber: " + text);
                }
            } catch (InterruptedException e) {
                System.out.println("Caught an Exception. Throw something meaningful");
            }
        }
    }

    public static void main (String[] args) throws java.lang.Exception

    {
        //Populate list items
        List<String> dictionaryWords = new ArrayList<String>();
        for (char alphabets = 'a'; alphabets <= 'z'; alphabets++) {
            dictionaryWords.add(alphabets+"");
        }
        //Initializing publisher and subscriber
        BlockingQueue <String> blockingQueue = new ArrayBlockingQueue<String>(26);
        Publisher publisher = new Publisher(blockingQueue, dictionaryWords);
        Subscriber subscriber = new Subscriber(blockingQueue);

        System.out.println("Task Starting");
        Thread publisherThread = new Thread(publisher);
        Thread subscriberThread = new Thread(subscriber);
        long startTime = System.nanoTime();

        //Starting the threads
        publisherThread.start();
        subscriberThread.start();
        System.out.println("Task Started");
        publisherThread.join();
        subscriberThread.join();

        long totalTime = System.nanoTime() - startTime;
        System.out.println("TotalTime elapsed: " + String.valueOf(totalTime/1000000) + " ms");
        System.out.println("Task Complete");

    }

}