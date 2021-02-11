package clientPart2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

public class CsvWriter implements Runnable{
    private BlockingQueue<String> buffer;
    private int maxStores;
    private AtomicBoolean closeCsvWriter;
    private FileWriter fw;

    public CsvWriter(BlockingQueue<String> buffer, int maxStores, AtomicBoolean closeCsvWriter) {
        this.buffer = buffer;
        this.maxStores = maxStores;
        this.closeCsvWriter = closeCsvWriter;
    }

    @Override
    public void run() {
        FileWriter fw = null;
        try{
            fw = new FileWriter("test" + maxStores + ".csv");
            while(!closeCsvWriter.get()){
                String record = buffer.take();
                fw.append(record);
            }
            fw.close();

        }catch (IOException | InterruptedException e){
            e.printStackTrace();
        }finally {
            System.out.println("CSV file saved");
        }
    }
}
