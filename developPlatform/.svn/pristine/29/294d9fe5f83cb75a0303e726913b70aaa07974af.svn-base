package com.tengzhi.base.tools.captcha.utils;

import com.tengzhi.base.tools.captcha.SpecCaptcha;
import com.tengzhi.base.tools.captcha.base.Captcha;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;


public class CaptchaUtil {
    private static final String SESSION_KEY = "captcha";
    private static final int DEFAULT_LEN = 4;
    private static final int DEFAULT_WIDTH = 130;
    private static final int DEFAULT_HEIGHT = 48;

    public static void out(HttpServletRequest request, HttpServletResponse response) throws IOException {
        out(DEFAULT_LEN, request, response);
    }


    public static void out(int len, HttpServletRequest request, HttpServletResponse response) throws IOException {
        out(DEFAULT_WIDTH, DEFAULT_HEIGHT, len, request, response);
    }


    public static void out(int width, int height, int len, HttpServletRequest request, HttpServletResponse response) throws IOException {
        out(width, height, len, null, request, response);
    }


    public static void out(Font font, HttpServletRequest request, HttpServletResponse response) throws IOException {
        out(DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_LEN, font, request, response);
    }


    public static void out(int len, Font font, HttpServletRequest request, HttpServletResponse response) throws IOException {
        out(DEFAULT_WIDTH, DEFAULT_HEIGHT, len, font, request, response);
    }


    public static void out(int width, int height, int len, Font font, HttpServletRequest request, HttpServletResponse response) throws IOException {
        SpecCaptcha specCaptcha = new SpecCaptcha(width, height, len);
        if (font != null) {
            specCaptcha.setFont(font);
        }
        out(specCaptcha, request, response);
    }


    public static void out(Captcha captcha, HttpServletRequest request, HttpServletResponse response) throws IOException {
        setHeader(response);
        request.getSession().setAttribute("captcha", captcha.text().toLowerCase());
        captcha.out(response.getOutputStream());
    }

    /**
     * 验证验证码(匹配大小写)
     * @param code
     * @param request
     * @return
     */
    public static boolean ver(String code, HttpServletRequest request) {
        if (code != null) {
            String captcha = getCurrentCaptch(request);
            return code.trim().toLowerCase().equals(captcha);
        }
        return false;
    }

    /**
     * 验证验证码(忽略大小写)
     * @param code
     * @param request
     * @return
     */
    public static boolean verIgnoreCase(String code, HttpServletRequest request) {
        if (code != null){
            String captcha = getCurrentCaptch(request);
            return code.trim().toLowerCase().equalsIgnoreCase(captcha);
        }
        return false;
    }

    /**
     * 获取当前验证码
     * @param request
     * @return
     */
    public static String getCurrentCaptch(HttpServletRequest request) {
        Object captcha = request.getSession().getAttribute(SESSION_KEY);
        return null == captcha ? "" : String.valueOf(captcha);
    }

    public static void clear(HttpServletRequest request) {
        request.getSession().removeAttribute(SESSION_KEY);
    }


    public static void setHeader(HttpServletResponse response) {
        response.setContentType("image/gif");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);
    }
}