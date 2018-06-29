package net.engining.gateway.api;

/**
 * demo 同步mq分发器.
 */
public interface DemoSyncDispatcher {

	public String onMessage(String req);
}
