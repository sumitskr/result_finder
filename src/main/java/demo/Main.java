package demo;

import java.util.HashMap;
import java.util.Map;

public class Main {
	public static void main(String[] args) {
		Map<String, String> arguments = new HashMap<>();
		
        // Parse command-line arguments
        for (int i = 0; i < args.length; i += 2) {
            if (i + 1 < args.length) {
                String key = args[i];
                String value = args[i + 1];
                arguments.put(key, value);
            }
        }
        if(args.length > 0) {
	        int start_index = Integer.parseInt(arguments.get("start_index"));
	    	int end_index = Integer.parseInt(arguments.get("end_index"));
	    	boolean logging = Boolean.valueOf(arguments.get("logging"));
	    	boolean headless = Boolean.valueOf(arguments.get("headless"));
	    	System.setProperty("start_index", String.valueOf(start_index));
	    	System.setProperty("end_index", String.valueOf(end_index));
	    	org.apache.logging.log4j.core.config.Configurator.initialize(null, "log4j2.xml");
	    	DriverOpen ob = new DriverOpen(start_index,end_index,logging,headless);
	    	ob.start_finding();
        }
        else {
	        DriverOpen ob = new DriverOpen();
	        System.setProperty("start_index", String.valueOf("Default"));
	    	System.setProperty("end_index", String.valueOf("Default"));
	    	org.apache.logging.log4j.core.config.Configurator.initialize(null, "log4j2.xml");
	        ob.start_finding();
        }
	}
}
