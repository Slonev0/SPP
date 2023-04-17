package spp.tp3_4;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
/*
public class MultiThreadedImageFilteringEngine implements IImageFilteringEngine {

    private final int numThreads;
    private BufferedImage inputImage;
    private BufferedImage outputImage;
    private List<FilterWorker> workers;

    public MultiThreadedImageFilteringEngine(int numThreads) {
        this.numThreads = numThreads;
        workers = new ArrayList<>(numThreads);
        for (int i = 0; i < numThreads; i++) {
            workers.add(new FilterWorker());
        }
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
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();
        int numPixels = width * height;
        int pixelsPerWorker = numPixels / numThreads;
        int extraPixels = numPixels % numThreads;

        CountDownLatch latch = new CountDownLatch(numThreads);

        int startIndex = 0;
        for (int i = 0; i < numThreads; i++) {
            int endIndex = startIndex + pixelsPerWorker;
            if (i < extraPixels) {
                endIndex++;
            }
            workers.get(i).setBounds(startIndex, endIndex);
            workers.get(i).setFilter(someFilter);
            workers.get(i).setLatch(latch);
            startIndex = endIndex;
        }

        for (FilterWorker worker : workers) {
            worker.start();
        }

        latch.await();
    }
}*/
