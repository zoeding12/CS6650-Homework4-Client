package clientPart2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

public class CsvWriter implements Runnable{
    private BlockingQueue<String> buffer;
    private int maxStores;

    @Override
    public void run() {
        FileWriter fw = null;
        try{
            while(true){ // use a atomicboolean??
                fw = new FileWriter(maxStores + ".csv");
                String record = buffer.take();
                if(record.equals("END_OF_FILE")){
                    break;
                }
                fw.append(record);
                fw.append("\n");
            }

        }catch (IOException | InterruptedException e){
            e.printStackTrace();
        }finally {
            System.out.println("CSV file saved");
        }
    }
}
