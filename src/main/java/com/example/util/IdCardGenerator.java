package com.example.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class IdCardGenerator {

    // Generate a fancy, premium-style ID card (returns PNG bytes)
    public static byte[] generateFancyCardBytes(String name, String gdcNumber) throws IOException {

        int w = 900;
        int h = 550;

        BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();

        // High quality rendering
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        // Background gradient
        GradientPaint gradient = new GradientPaint(
                0, 0, new Color(25, 118, 210),
                w, h, new Color(21, 101, 192)
        );
        g.setPaint(gradient);
        g.fillRect(0, 0, w, h);

        // White rounded card container
        g.setColor(Color.WHITE);
        g.fill(new RoundRectangle2D.Float(40, 40, w - 80, h - 80, 35, 35));

        // Title
        g.setColor(Color.BLACK);
        g.setFont(new Font("SansSerif", Font.BOLD, 38));
        String title = "GDC DRIVER ID CARD";

        int titleWidth = g.getFontMetrics().stringWidth(title);
        g.drawString(title, (w - titleWidth) / 2, 130);

        // Main text
        g.setFont(new Font("SansSerif", Font.PLAIN, 26));
        g.drawString("Name: " + (name != null ? name : "N/A"), 100, 250);

        g.drawString("GDC No: " + (gdcNumber != null ? gdcNumber : "N/A"), 100, 310);

        // Timestamp
        g.setFont(new Font("SansSerif", Font.ITALIC, 18));
        String dateStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        g.drawString("Generated At: " + dateStr, 100, 380);

        g.dispose();

        // Convert to byte[]
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(img, "png", baos);
        byte[] bytes = baos.toByteArray();
        baos.close();

        return bytes;
    }

    // Save PNG bytes to temp file
    public static File writeBytesToTempPng(byte[] bytes, Long driverRegistrationId) throws IOException {
        String fileName = "gdc_" + driverRegistrationId + "_" + System.currentTimeMillis() + ".png";

        File tempFile = File.createTempFile("gdc_", ".png");  // system temp directory
        Files.write(tempFile.toPath(), bytes);

        return tempFile;
    }
}
