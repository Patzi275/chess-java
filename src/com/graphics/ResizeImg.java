package com.graphics;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;


public class ResizeImg
{
    public static BufferedImage changeSize(String inImg, int w, int h)
            throws IOException
    {
        // lit l'image d'entrée
        File f = new File(inImg);
        BufferedImage inputImage = ImageIO.read(f);

        // crée l'image de sortie
        BufferedImage img = new BufferedImage(w, h, inputImage.getType());

        // balancer l'image d'entrée à l'image de sortie
        Graphics2D g = img.createGraphics();
        g.drawImage(inputImage, 0, 0, w, h, null);
        g.dispose();

        return img;
    }

    public static BufferedImage changeSize(BufferedImage inputImage, float scale)
            throws IOException
    {
        float w = inputImage.getWidth() * scale,
            h = inputImage.getHeight() * scale;

        BufferedImage img = new BufferedImage((int) w, (int) h, inputImage.getType());

        // balancer l'image d'entrée à l'image de sortie
        Graphics2D g = img.createGraphics();
        g.drawImage(inputImage, 0, 0, (int) w, (int) h, null);
        g.dispose();

        return img;
    }
}