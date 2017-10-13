package fraang.yovela.notification;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map.Entry;

import fraang.yovela.foundation.Identifier;

public final class Notifier {
	private final NotificationRegistry notificationRegistry;

	public Notifier(final NotificationRegistry notificationRegistry) {
		this.notificationRegistry = notificationRegistry;
	}

	public void notifySubscribers() {
		final Collection<Object> notifiedSubscribers = new ArrayList<>(notificationRegistry.getSubscribers().size());
		notificationRegistry.getSubscribers().entrySet().stream()
				.forEach(subscribersEntry -> notifySubscriber(notifiedSubscribers, subscribersEntry));
	}

	private void notifySubscriber(final Collection<Object> notifiedSubscribers,
			final Entry<Identifier, Runnable> subscribersEntry) {
		final Identifier subscriberIdentifier = subscribersEntry.getKey();
		final Runnable subscriber = subscribersEntry.getValue();
		if (!notifiedSubscribers.contains(subscriberIdentifier)) {
			subscriber.run();
			notifiedSubscribers.add(subscriberIdentifier);
		}
	}
}
