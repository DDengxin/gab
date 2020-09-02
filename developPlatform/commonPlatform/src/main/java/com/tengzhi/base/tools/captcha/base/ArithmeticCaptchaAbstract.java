package com.tengzhi.base.tools.captcha.base;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


public abstract class ArithmeticCaptchaAbstract
        extends Captcha {
    private String arithmeticString;

    public ArithmeticCaptchaAbstract() {
        setLen(2);
    }


    @Override
    protected char[] alphas() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.len; i++) {
            sb.append(num(10));
            if (i < this.len - 1) {
                int type = num(1, 4);
                if (type == 1) {
                    sb.append("+");
                } else if (type == 2) {
                    sb.append("-");
                } else if (type == 3) {
                    sb.append("x");
                }
            }
        }
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("javascript");
        try {
            this.chars = String.valueOf(engine.eval(sb.toString().replaceAll("x", "*")));
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        sb.append("=?");
        this.arithmeticString = sb.toString();
        return this.chars.toCharArray();
    }

    public String getArithmeticString() {
        checkAlpha();
        return this.arithmeticString;
    }

    public void setArithmeticString(String arithmeticString) {
        this.arithmeticString = arithmeticString;
    }
}


/* Location:              D:\Program Files\maven\.m2\repository\com\github\whvcse\easy-captcha\1.6.2\easy-captcha-1.6.2.jar!\com\wf\captcha\base\ArithmeticCaptchaAbstract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */