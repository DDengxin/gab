package com.tengzhi.base.tools.captcha;

import com.tengzhi.base.tools.captcha.base.Captcha;
import com.tengzhi.base.tools.captcha.utils.GifEncoder;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.CubicCurve2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;


public class GifCaptcha
        extends Captcha {
    public GifCaptcha() {
    }

    public GifCaptcha(int width, int height) {
        setWidth(width);
        setHeight(height);
    }

    public GifCaptcha(int width, int height, int len) {
        this(width, height);
        setLen(len);
    }

    public GifCaptcha(int width, int height, int len, Font font) {
        this(width, height, len);
        setFont(font);
    }


    @Override
    public boolean out(OutputStream os) {
        try {
            char[] strs = textChar();

            Color[] fontColor = new Color[this.len];
            for (int i = 0; i < this.len; i++) {
                fontColor[i] = color();
            }

            int x1 = 5, y1 = num(5, this.height / 2);
            int x2 = this.width - 5, y2 = num(this.height / 2, this.height - 5);
            int ctrlx = num(this.width / 4, this.width / 4 * 3), ctrly = num(5, this.height - 5);
            if (num(2) == 0) {
                int ty = y1;
                y1 = y2;
                y2 = ty;
            }
            int ctrlx1 = num(this.width / 4, this.width / 4 * 3), ctrly1 = num(5, this.height - 5);
            int[][] besselXY = {{x1, y1}, {ctrlx, ctrly}, {ctrlx1, ctrly1}, {x2, y2}};

            GifEncoder gifEncoder = new GifEncoder();
            gifEncoder.setQuality(180);
            gifEncoder.setDelay(100);
            gifEncoder.setRepeat(0);
            gifEncoder.start(os);
            int j;
            for (j = 0; j < this.len; j++) {
                BufferedImage frame = graphicsImage(fontColor, strs, j, besselXY);
                gifEncoder.addFrame(frame);
                frame.flush();
            }
            gifEncoder.finish();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    @Override
    public String toBase64() {
        return toBase64("data:image/gif;base64,");
    }


    private BufferedImage graphicsImage(Color[] fontColor, char[] strs, int flag, int[][] besselXY) {
        BufferedImage image = new BufferedImage(this.width, this.height, 1);
        Graphics2D g2d = (Graphics2D) image.getGraphics();

        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, this.width, this.height);

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setComposite(AlphaComposite.getInstance(3, 0.1F * num(10)));
        drawOval(2, g2d);

        g2d.setComposite(AlphaComposite.getInstance(3, 0.7F));
        g2d.setStroke(new BasicStroke(1.2F, 0, 2));
        g2d.setColor(fontColor[0]);
        CubicCurve2D shape = new CubicCurve2D.Double(besselXY[0][0], besselXY[0][1], besselXY[1][0], besselXY[1][1], besselXY[2][0], besselXY[2][1], besselXY[3][0], besselXY[3][1]);
        g2d.draw(shape);

        g2d.setFont(getFont());
        FontMetrics fontMetrics = g2d.getFontMetrics();
        int fW = this.width / strs.length;
        int fSp = (fW - (int) fontMetrics.getStringBounds("W", g2d).getWidth()) / 2;
        for (int i = 0; i < strs.length; i++) {

            AlphaComposite ac3 = AlphaComposite.getInstance(3, getAlpha(flag, i));
            g2d.setComposite(ac3);
            g2d.setColor(fontColor[i]);
            int fY = this.height - (this.height - (int) fontMetrics.getStringBounds(String.valueOf(strs[i]), g2d).getHeight() >> 1);
            g2d.drawString(String.valueOf(strs[i]), i * fW + fSp + 3, fY - 3);
        }
        g2d.dispose();
        return image;
    }


    private float getAlpha(int i, int j) {
        int num = i + j;
        float r = 1.0F / (this.len - 1);
        float s = this.len * r;
        return (num >= this.len) ? (num * r - s) : (num * r);
    }
}


/* Location:              D:\Program Files\maven\.m2\repository\com\github\whvcse\easy-captcha\1.6.2\easy-captcha-1.6.2.jar!\com\wf\captcha\GifCaptcha.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */