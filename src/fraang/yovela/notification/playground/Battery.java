package fraang.yovela.notification.playground;

final class Battery implements NotificationRegistry.Has {
	private final NotificationRegistry notificationRegistry = new NotificationRegistry();
	private final Notifier notifier = new Notifier(notificationRegistry);

	@Override
	public NotificationRegistry getNotificationRegistry() {
		return notificationRegistry;
	}

	void triggerNotification() {
		notifier.notifySubscribers();
	}
}
