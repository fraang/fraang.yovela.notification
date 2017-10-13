package fraang.yovela.notification.playground;

public final class Playground {
	public static void main(String[] args) {
		runNotificationExampleComputer();
	}

	private static void runNotificationExampleComputer() {
		Battery battery = new Battery();
		BatteryLed batteryLed = new BatteryLed();
		OperatingSystem operatingSystem = new OperatingSystem();

		battery.getNotificationRegistry().subscribe(OperatingSystem.Notification.BATTERY_IS_LOW.getIdentifier(),
				operatingSystem::batteryIsLow, OperatingSystem.Notification.BATTERY_IS_LOW.getDependencies());

		battery.getNotificationRegistry().subscribe(BatteryLed.Notification.BATTERY_IS_LOW.getIdentifier(),
				batteryLed::batteryIsLow, BatteryLed.Notification.BATTERY_IS_LOW.getDependencies());

		battery.triggerNotification();

		battery.getNotificationRegistry().unsubscribe(OperatingSystem.Notification.BATTERY_IS_LOW.getIdentifier());
		battery.getNotificationRegistry().unsubscribe(BatteryLed.Notification.BATTERY_IS_LOW.getIdentifier());

		battery.triggerNotification();
	}
}
