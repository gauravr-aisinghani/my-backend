package com.example.util;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;


public class IdCardGenerator {


// returns byte[] of PNG image
public static byte[] generateFancyCardBytes(String name, String gdcNumber) throws IOException {
int w = 900;
int h = 550;


BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
Graphics2D g = img.createGraphics();


g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


// gradient background
GradientPaint gp = new GradientPaint(0, 0, new Color(30,136,229), w, h, new Color(2,119,189));
g.setPaint(gp);
g.fillRect(0,0,w,h);


// white rounded container
g.setColor(Color.WHITE);
g.fill(new RoundRectangle2D.Float(40,40,w-80,h-80,30,30));


g.setColor(Color.BLACK);
g.setFont(new Font("SansSerif", Font.BOLD, 34));
g.drawString("GDC DRIVER ID CARD", 260, 120);


g.setFont(new Font("SansSerif", Font.PLAIN, 26));
g.drawString("Name: " + (name == null ? "N/A" : name), 100, 250);


g.drawString("GDC No: " + (gdcNumber == null ? "N/A" : gdcNumber), 100, 310);


g.setFont(new Font("SansSerif", Font.ITALIC, 18));
g.drawString("Generated At: " + LocalDateTime.now().toString(), 100, 370);


g.dispose();


ByteArrayOutputStream baos = new ByteArrayOutputStream();
ImageIO.write(img, "png", baos);
baos.flush();
byte[] bytes = baos.toByteArray();
baos.close();
return bytes;
}


// helper: write bytes to temporary file and return path
public static File writeBytesToTempPng(byte[] bytes, Long driverRegistrationId) throws IOException {
String fileName = "gdc_" + driverRegistrationId + "_" + System.currentTimeMillis() + ".png";
File tmp = File.createTempFile("gdc_", ".png");
Files.write(tmp.toPath(), bytes);
return tmp;
}
}