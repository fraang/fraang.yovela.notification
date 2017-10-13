package fraang.yovela.notification.playground;

import java.util.ArrayList;
import java.util.List;

import fraang.yovela.foundation.Identifier;

final class BatteryLed {
	void batteryIsLow() {
		System.out.println("The battery is low. Color the LED red.");
	}

	static enum Notification {
		BATTERY_IS_LOW(new Identifier("BATTERY_IS_LOW"), new ArrayList<>());
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
