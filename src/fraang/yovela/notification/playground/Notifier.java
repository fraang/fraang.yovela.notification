package fraang.yovela.notification.playground;

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
		final Collection<Identifier> notifiedSubscribers = new ArrayList<>(
				notificationRegistry.getSubscribers().size());
		notificationRegistry.getSubscribers().entrySet().stream()
				.forEach(subscribersEntry -> notifySubscriber(notifiedSubscribers, subscribersEntry));
	}

	private void notifySubscriber(final Collection<Identifier> notifiedSubscribers,
			final Entry<Identifier, Runnable> subscribersEntry) {
		final Identifier subscriberIdentifier = subscribersEntry.getKey();
		final Runnable subscriber = subscribersEntry.getValue();
		notifySubscriber(notifiedSubscribers, subscriberIdentifier, subscriber);
	}

	private void notifySubscriber(final Collection<Identifier> notifiedSubscribers,
			final Identifier subscriberIdentifier, final Runnable subscriber) {
		if (!notifiedSubscribers.contains(subscriberIdentifier)) {
			notificationRegistry.getDependencyRegistry().getDependencies(subscriberIdentifier).stream()
					.forEach(dependencyIdentifier -> notifySubscriber(notifiedSubscribers, dependencyIdentifier,
							notificationRegistry.getSubscribers().get(dependencyIdentifier)));
			subscriber.run();
			notifiedSubscribers.add(subscriberIdentifier);
		}
	}
}
