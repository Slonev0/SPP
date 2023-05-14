package spp.tp3_4;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class MultiThreadedImageFilteringEngine implements IImageFilteringEngine {

    private final int numThreads;
    private BufferedImage inputImage;
    private BufferedImage outputImage;
    private List<Worker> workers;
    public CyclicBarrier startBarrier;
    public CyclicBarrier endBarrier;
    Runnable barrierAction = () -> {
        System.out.println("All threads at the barrier. release the barrier");
    };

    public MultiThreadedImageFilteringEngine(int numThreads) {
        super();
        this.numThreads = numThreads;
        startBarrier = new CyclicBarrier(numThreads + 1);
        endBarrier = new CyclicBarrier(numThreads + 1);
    }

    @Override
    public void loadImage(String inputImage) throws Exception {
        this.inputImage = ImageIO.read(new File(inputImage));
        outputImage = this.inputImage;
    }

    @Override
    public void writeOutPngImage(String outFile) throws Exception {
        File f = new File(outFile);
        ImageIO.write(outputImage, "png", f);
    }

    @Override
    public void setImg(BufferedImage newImg) {
        inputImage = newImg;
        outputImage = new BufferedImage(inputImage.getWidth(), inputImage.getHeight(), BufferedImage.TYPE_INT_RGB);
    }

    @Override
    public BufferedImage getImg() {
        return inputImage;
    }


    private void setUpWorkers(IFilter someFilter) throws InterruptedException {
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();
        int pixelsPerWorker = width / numThreads;
        int extraPixels = width % numThreads;

        int startX = 0;
        int startY = 0;

        for (Worker worker : workers){
            worker.setFilter(someFilter);
            worker.setMyImg(inputImage);
            worker.setOutputImg(outputImage);

            worker.setStartX(startX);
            worker.setStartY(startY);


            int endX = startX + pixelsPerWorker;
            if (extraPixels > 0) {
                worker.setEndX(Math.min(endX + 1, width));
            }
            else {
                worker.setEndX(Math.min(endX, width));
            }

            int endY = startY + height;
            worker.setEndY(endY);
            //System.out.println("Worker" + worker.getName() + " set endY to " + endY);

            extraPixels--;
            startX = endX;
            if (startX >= width) {
                startX = 0;
                startY += height;
            }
        }
        workers.get(workers.size()-1).setEndX(width);
        workers.get(workers.size()-1).setEndY(height);
    }



    @Override
    public void applyFilter(IFilter someFilter) throws InterruptedException {
        setOutImg(someFilter.getMargin());

        initWorkers();
        setUpWorkers(someFilter);

        for (Thread worker : workers) {
            //System.out.println("Starting worker " + worker.getName());
            worker.start();
        }
        try {
            startBarrier.await(); // Let all threads start processing
            endBarrier.await();   // Wait for all threads to finish
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    private void initWorkers(){
        workers = new ArrayList<>(numThreads);
        for (int i = 0; i < numThreads; i++) {
            workers.add(new Worker(startBarrier, endBarrier));
        }
    }
    private void setOutImg(int margin) {
        outputImage = new BufferedImage(inputImage.getWidth() - (margin * 2),
                inputImage.getHeight() - (margin * 2),
                BufferedImage.TYPE_INT_RGB);

    }
}
