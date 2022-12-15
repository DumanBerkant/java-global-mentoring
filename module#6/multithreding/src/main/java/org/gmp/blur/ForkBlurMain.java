package org.gmp.blur;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ForkJoinPool;

public class ForkBlurMain {
    public static void main(String[] arg) throws IOException {

        BufferedImage image = ImageIO.read(ForkBlur.class.getClassLoader().getResource("image.jpeg"));

        int w = image.getWidth();
        int h = image.getHeight();

        int[] src = image.getRGB(0, 0, w, h, null, 0, w);
        int[] dst = new int[src.length];


        ForkBlur fb = new ForkBlur(src, 0, src.length, dst);
        ForkJoinPool pool = new ForkJoinPool();
        long startTime = System.currentTimeMillis();
        pool.invoke(fb);
        long endTime = System.currentTimeMillis();

        System.out.println("Image blur took " + (endTime - startTime) +
                " milliseconds.");

        BufferedImage dstImage =
                new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        dstImage.setRGB(0, 0, w, h, dst, 0, w);


        String dstName = "test";
        File dstFile = new File(dstName);
        ImageIO.write(dstImage, "jpeg", dstFile);

        System.out.println("Output image: " + dstName);

    }
}
