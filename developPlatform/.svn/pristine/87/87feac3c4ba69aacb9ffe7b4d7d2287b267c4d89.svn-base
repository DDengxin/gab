package com.tengzhi.base.security.browser.extension.session;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.session.InvalidSessionStrategy;

import com.tengzhi.base.security.browser.extension.session.communal.CommunalSession;

/**
 * @author lqy
 *session失效策略
 */
public class BrowserFailureSession extends CommunalSession implements InvalidSessionStrategy {

    public BrowserFailureSession(String invalidSessionUrl) {
		super(invalidSessionUrl);
	}

    @Override
    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        super.onSessionInvalid(request, response);
    }
}
