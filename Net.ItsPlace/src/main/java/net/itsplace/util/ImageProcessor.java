package net.itsplace.util;

import java.io.*;
import java.awt.*;
import java.awt.image.*;
import com.sun.image.codec.jpeg.*;

import javax.imageio.*;

public class ImageProcessor{

        public static void saveJpeg(File file, BufferedImage image) throws Exception{
                saveJpeg(file, image, 0.7f);
        }

        public static void saveJpeg(String file, BufferedImage image) throws Exception{
                saveJpeg(file, image, 0.7f);
        }

        public static void saveJpeg(String file, BufferedImage image, float quality) throws Exception{
                saveJpeg(new File(file), image, quality);
        }

        public static void saveJpeg(File file, BufferedImage image, float quality) throws Exception{

                FileOutputStream fos = new FileOutputStream(file);

                JPEGImageEncoder jpeg = JPEGCodec.createJPEGEncoder(fos);
                JPEGEncodeParam param = jpeg.getDefaultJPEGEncodeParam(image);

                param.setQuality(quality, true);
                jpeg.setJPEGEncodeParam(param);
                jpeg.encode(image);
                fos.close();
        }


        public static BufferedImage scaling(Image input, int new_width, int new_height) throws Exception{

                int org_width= input.getWidth(null);
                int org_height= input.getHeight(null);

                int[] org_pixels = new int[org_width * org_height];
                PixelGrabber pg = new PixelGrabber(input, 0, 0, org_width, org_height, org_pixels, 0, org_width);
                pg.grabPixels();

                int[] new_pixels = new int[new_width * new_height];

                scalingProcess(org_pixels, org_width, org_height, new_pixels, new_width, new_height);

                BufferedImage bi = new BufferedImage(new_width, new_height,BufferedImage.TYPE_INT_RGB);

                bi.setRGB(0, 0, new_width, new_height, new_pixels, 0, new_width);
                return bi;
        }

        private static void scalingProcess(int[] pixels, int width, int height, int[] new_pixels, int new_width, int new_height){
                int i,j;
                int value = 10000;
                int pos_h = value;
                int pos_w = value;

                int inc_h = (height*value)/new_height; //세로 증가량
                int inc_w = (width*value)/new_width; //가로 증가량

                int srcRow = 0; //소스의 세로줄의 위치
                int srcp = 0; //소스의 위치
                int dstp = 0; //타겟의 위치

                for(i=0; i<new_height; i++){ //세로로 한줄씩 

                        while(pos_h >= value){
                                srcp = srcRow*width;
                                srcRow++;
                                pos_h -= value;
                        }

                        dstp = i*new_width; //복사될 이미지의 줄 단위의 시작 점

                        //한줄 복사
                        int pixel = 0; //픽셀 값
                        pos_w = value;
                        for(j=0; j<new_width; j++){

                                while(pos_w >= value){
                                        pixel = pixels[srcp];
                                        srcp++;
                                        pos_w -= value;
                                }

                                new_pixels[dstp] = pixel;
                                dstp++;
                                pos_w += inc_w;
                        }

                        pos_h += inc_h;
                }
        }
}
