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

    public static byte[] generateFancyCardBytes(
            BufferedImage selfieImg,
            String fullName,
            String mobile,
            String address,
            String gdcNumber
    ) throws IOException {

        final int w = 1100;
        final int h = 650;

        BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();

        // HQ rendering
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // Background
        GradientPaint bg = new GradientPaint(0, 0, new Color(240, 244, 248), 0, h, new Color(224, 232, 240));
        g.setPaint(bg);
        g.fillRect(0, 0, w, h);

        // Card container
        int margin = 45;
        int cardW = w - margin * 2;
        int cardH = h - margin * 2;
        int cardX = margin;
        int cardY = margin;

        // Soft shadow
        g.setColor(new Color(0, 0, 0, 55));
        g.fillRoundRect(cardX + 8, cardY + 10, cardW, cardH, 40, 40);

        // White card
        g.setColor(Color.WHITE);
        g.fillRoundRect(cardX, cardY, cardW, cardH, 36, 36);

        // Gold header bar
        g.setColor(new Color(212, 175, 55));
        g.fillRoundRect(cardX, cardY, cardW, 85, 36, 36);

        g.setColor(Color.WHITE);
        g.setFont(new Font("SansSerif", Font.BOLD, 32));
        String title = "GDC DRIVER IDENTIFICATION CARD";
        int titleW = g.getFontMetrics().stringWidth(title);
        g.drawString(title, cardX + (cardW - titleW) / 2, cardY + 55);

        // LEFT PANEL
        int leftX = cardX + 35;
        int leftY = cardY + 115;
        int leftW = 380;
        int leftH = cardH - 150;

        GradientPaint leftBg = new GradientPaint(leftX, leftY, new Color(7, 80, 140), leftX + leftW, leftY + leftH, new Color(3, 50, 110));
        g.setPaint(leftBg);
        g.fillRoundRect(leftX, leftY, leftW, leftH, 22, 22);

        // Selfie area (larger)
        int photoPadding = 25;
        int photoX = leftX + photoPadding;
        int photoY = leftY + photoPadding;
        int photoW = leftW - photoPadding * 2;
        int photoH = photoW;

        g.setColor(new Color(255, 255, 255, 230));
        g.fillRoundRect(photoX - 6, photoY - 6, photoW + 12, photoH + 12, 20, 20);

        if (selfieImg != null) {
            BufferedImage scaled = resizeToFit(selfieImg, photoW, photoH);

            Shape oldClip = g.getClip();
            RoundRectangle2D.Float rr = new RoundRectangle2D.Float(photoX, photoY, photoW, photoH, 20, 20);
            g.setClip(rr);
            g.drawImage(scaled, photoX, photoY, null);
            g.setClip(oldClip);

            g.setColor(new Color(0, 0, 0, 50));
            g.setStroke(new BasicStroke(3f));
            g.draw(rr);
        } else {
            g.setColor(new Color(200, 200, 200));
            g.fillRoundRect(photoX, photoY, photoW, photoH, 20, 20);
            g.setColor(Color.DARK_GRAY);
            g.setFont(new Font("SansSerif", Font.BOLD, 30));
            String msg = "PHOTO";
            int pw = g.getFontMetrics().stringWidth(msg);
            g.drawString(msg, photoX + (photoW - pw) / 2, photoY + photoH / 2);
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font("SansSerif", Font.PLAIN, 14));
        g.drawString("Issued: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                leftX + 30, leftY + leftH - 20);

        // RIGHT PANEL
        int rightX = leftX + leftW + 50;
        int rightY = leftY + 10;

        g.setColor(new Color(18, 28, 50));
        g.setFont(new Font("SansSerif", Font.BOLD, 30));
        g.drawString("Driver Details", rightX, rightY);

        int ty = rightY + 55;
        int gap = 50;

        g.setColor(new Color(20, 20, 20));
        g.setFont(new Font("SansSerif", Font.BOLD, 32));
        g.drawString("Name: " + safe(fullName), rightX, ty);
        ty += gap;

        g.setFont(new Font("SansSerif", Font.PLAIN, 28));
        g.drawString("Mobile: " + safe(mobile), rightX, ty);
        ty += gap;

        g.setFont(new Font("SansSerif", Font.PLAIN, 24));
        g.drawString("Address:", rightX, ty);
        ty += 32;

        g.setFont(new Font("SansSerif", Font.PLAIN, 22));
        drawWrapped(g, safe(address), rightX, ty, 500);
        ty += 90;

        g.setFont(new Font("SansSerif", Font.BOLD, 30));
        g.setColor(new Color(3, 99, 148));
        g.drawString("GDC Number: " + safe(gdcNumber), rightX, ty);

        // footer note
        g.setFont(new Font("SansSerif", Font.ITALIC, 14));
        g.setColor(new Color(80, 80, 80));
        String note = "This card is computer generated and valid only after verification.";
        int nw = g.getFontMetrics().stringWidth(note);
        g.drawString(note, cardX + cardW - nw - 20, cardY + cardH - 20);

        g.dispose();

        // Byte output
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(img, "png", baos);
        return baos.toByteArray();
    }

    // helper for safe text
    private static String safe(String s) {
        return (s == null || s.isBlank()) ? "N/A" : s;
    }

    // address wrapping
    private static void drawWrapped(Graphics2D g, String text, int x, int y, int maxWidth) {
        int index = 0;
        while (index < text.length()) {
            int end = Math.min(index + 40, text.length());
            int space = text.lastIndexOf(" ", end);
            if (space > index) end = space;

            String line = text.substring(index, end).trim();
            g.drawString(line, x, y);
            y += 28;
            index = end;
        }
    }

    // resize helper
    private static BufferedImage resizeToFit(BufferedImage src, int targetW, int targetH) {
        int sw = src.getWidth();
        int sh = src.getHeight();

        float scale = Math.max((float) targetW / sw, (float) targetH / sh);
        int newW = Math.round(sw * scale);
        int newH = Math.round(sh * scale);

        BufferedImage scaled = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = scaled.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g.drawImage(src, 0, 0, newW, newH, null);
        g.dispose();

        int x = (newW - targetW) / 2;
        int y = (newH - targetH) / 2;

        return scaled.getSubimage(x, y, targetW, targetH);
    }

    public static File writeBytesToTempPng(byte[] bytes, Long driverRegistrationId) throws IOException {
        File temp = File.createTempFile("gdc_", ".png");
        Files.write(temp.toPath(), bytes);
        return temp;
    }
}
