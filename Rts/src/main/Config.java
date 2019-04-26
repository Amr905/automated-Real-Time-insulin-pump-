package main;

import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;

import event.*;

public class Config {

	private static EPServiceProvider engine = EPServiceProviderManager.getDefaultProvider();

	public static void registerEvents() {
		engine.getEPAdministrator().getConfiguration().addEventType(DisplayClockEvent.class);
		engine.getEPAdministrator().getConfiguration().addEventType(SugarMeasurementEvent.class);
		engine.getEPAdministrator().getConfiguration().addEventType(SystemTesterEvent.class);
		engine.getEPAdministrator().getConfiguration().addEventType(DisplayMsgEvent.class);
		engine.getEPAdministrator().getConfiguration().addEventType(ReservoirEvent.class);
		engine.getEPAdministrator().getConfiguration().addEventType(DisplayLastDose.class);
		System.out.println("Events Successfully Registered.");
	}

	public static EPStatement createStatement(String s) {
		EPStatement result = engine.getEPAdministrator().createEPL(s);
		System.out.println("EPL Statement Successfully Created.");
		return result;
	}

	public static void sendEvent(Object o) {
		engine.getEPRuntime().sendEvent(o);
	}

}
