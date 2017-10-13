package fraang.yovela.notification.playground;

import static java.util.Arrays.asList;

import java.util.List;

import fraang.yovela.foundation.Identifier;

final class OperatingSystem {
	void batteryIsLow() {
		System.out.println("The battery is low. Show user a warning.");
	}

	static enum Notification {
		BATTERY_IS_LOW(new Identifier("BATTERY_IS_LOW"),
				asList(BatteryLed.Notification.BATTERY_IS_LOW.getIdentifier()));
		private final Identifier identifier;
		private final List<Identifier> dependencies;

		private Notification(final Identifier identifier, final List<Identifier> dependencies) {
			this.identifier = identifier;
			this.dependencies = dependencies;
		}

		Identifier getIdentifier() {
			return identifier;
		}

		List<Identifier> getDependencies() {
			return dependencies;
		}
	}
}
