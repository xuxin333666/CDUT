package cn.kgc.Listener;

import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;



public class UserCountListener implements HttpSessionBindingListener {
	private static AtomicInteger userCount = new AtomicInteger(0);
	
	@Override
	public void valueBound(HttpSessionBindingEvent arg0) {
		userCount.incrementAndGet();
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent arg0) {
		userCount.decrementAndGet();
	}

	public static AtomicInteger getUserCount() {
		return userCount;
	}

}
