package fraang.yovela.notification.playground;

import static java.util.Collections.unmodifiableMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fraang.yovela.foundation.DependencyRegistry;
import fraang.yovela.foundation.Identifier;

public final class NotificationRegistry implements DependencyRegistry.Has {
	public interface Has {
		NotificationRegistry getNotificationRegistry();
	}

	private final Map<Identifier, Runnable> subscribers = new HashMap<>();
	private final DependencyRegistry dependencyRegistry = new DependencyRegistry();

	public void subscribe(final Identifier identifier, final Runnable subscriber, final List<Identifier> dependencies) {
		this.dependencyRegistry.register(identifier, dependencies);
		subscribers.put(identifier, subscriber);
	}

	public void unsubscribe(final Identifier identifier) {
		subscribers.remove(identifier);
		dependencyRegistry.deregister(identifier);
	}

	public Map<Identifier, Runnable> getSubscribers() {
		return unmodifiableMap(subscribers);
	}

	@Override
	public DependencyRegistry getDependencyRegistry() {
		return dependencyRegistry;
	}
}
