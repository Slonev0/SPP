package spp.tp3_4;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class MultiThreadedImageFilteringEngine implements IImageFilteringEngine {

    private final int numThreads;
    private BufferedImage inputImage;
    private BufferedImage outputImage;
    private List<Worker> workers;

    private CyclicBarrier barrier;

    public MultiThreadedImageFilteringEngine(int numThreads) {
        this.numThreads = numThreads;
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

    @Override
    public void applyFilter(IFilter someFilter) throws InterruptedException {
        initWorkers();
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();
        int numPixels = width * height;
        int pixelsPerWorker = numPixels / numThreads;
        int extraPixels = numPixels % numThreads;
        //CountDownLatch latch = new CountDownLatch(numThreads);
        int startIndex = 0;
        for (Worker worker : workers){
            int endIndex = startIndex + pixelsPerWorker;
            worker.setFilter(someFilter);
            worker.setMyImg(inputImage);
            worker.setOutputImg(outputImage);
            worker.setStartX(startIndex/width);
            worker.setStartY(startIndex%width);
            worker.setEndX(endIndex/width);
            worker.setEndY(endIndex%width);
            startIndex = endIndex;
        }
        workers.get(numThreads - 1).setEndX(workers.get(numThreads - 1).getEndX() + (startIndex+extraPixels)/width);
        workers.get(numThreads - 1).setEndY(workers.get(numThreads - 1).getEndY() + (startIndex+extraPixels)%width);

        for (Thread worker : workers) {
            System.out.println("Starting worker " + worker.getName());
            worker.start();
        }
    }

    private void initWorkers(){
        this.barrier = new CyclicBarrier(numThreads);
        workers = new ArrayList<>(numThreads);
        for (int i = 0; i < numThreads; i++) {
            workers.add(new Worker(numThreads, barrier));
        }
    }
}
