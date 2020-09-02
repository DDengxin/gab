package com.tengzhi.base.tools.captcha;

import com.tengzhi.base.tools.captcha.base.ChineseCaptchaAbstract;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import javax.imageio.ImageIO;

public class ChineseCaptcha
        extends ChineseCaptchaAbstract {
    public ChineseCaptcha(int width, int height) {
        this();
        setWidth(width);
        setHeight(height);
    }

    public ChineseCaptcha() {
    }

    public ChineseCaptcha(int width, int height, int len) {
        this(width, height);
        setLen(len);
    }

    public ChineseCaptcha(int width, int height, int len, Font font) {
        this(width, height, len);
        setFont(font);
    }


    @Override
    public boolean out(OutputStream out) {
        return graphicsImage(textChar(), out);
    }


    @Override
    public String toBase64() {
        return toBase64("data:image/png;base64,");
    }


    private boolean graphicsImage(char[] strs, OutputStream out) {
        try {
            BufferedImage bi = new BufferedImage(this.width, this.height, 1);
            Graphics2D g2d = (Graphics2D) bi.getGraphics();

            g2d.setColor(Color.WHITE);
            g2d.fillRect(0, 0, this.width, this.height);

            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            drawOval(3, g2d);

            g2d.setStroke(new BasicStroke(1.2F, 0, 2));
            drawBesselLine(1, g2d);

            g2d.setFont(getFont());
            FontMetrics fontMetrics = g2d.getFontMetrics();
            int fW = this.width / strs.length;
            int fSp = (fW - (int) fontMetrics.getStringBounds("王", g2d).getWidth()) / 2;
            int i;
            for (i = 0; i < strs.length; i++) {
                g2d.setColor(color());
                int fY = this.height - (this.height - (int) fontMetrics.getStringBounds(String.valueOf(strs[i]), g2d).getHeight() >> 1);
                g2d.drawString(String.valueOf(strs[i]), i * fW + fSp + 3, fY - 3);
            }
            g2d.dispose();
            ImageIO.write(bi, "png", out);
            out.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
