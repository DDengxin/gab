package com.tengzhi.base.security.browser.extension.session;

import java.io.IOException;

import javax.servlet.ServletException;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import com.tengzhi.base.security.browser.extension.session.communal.CommunalSession;


/**
 * @author lqy
 *被挤下线的
 */
public class BrowserKickSession extends CommunalSession implements SessionInformationExpiredStrategy {

    public BrowserKickSession(String invalidSessionUrl) {
		super(invalidSessionUrl);
	}

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        super.onSessionInvalid(event.getRequest(), event.getResponse());
    }

    @Override
    protected boolean isConcurrency() {
        return true;
    }
}
