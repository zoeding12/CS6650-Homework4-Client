package clientPart2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

public class CsvWriter implements Runnable{
    private BlockingQueue<String> buffer;
    private int maxStores;
    private FileWriter fw;

    public CsvWriter(BlockingQueue<String> buffer, int maxStores) {
        this.buffer = buffer;
        this.maxStores = maxStores;
    }

    @Override
    public void run() {
        FileWriter fw = null;
        try{
            fw = new FileWriter("test" + maxStores + ".csv");
            while(true){
                String record = buffer.take();
                if(record.equals("END_OF_FILE")){
                    break;
                }
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
