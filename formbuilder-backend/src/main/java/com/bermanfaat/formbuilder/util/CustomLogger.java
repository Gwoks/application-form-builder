package com.bermanfaat.formbuilder.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CustomLogger {

	private static Logger logger = LogManager.getLogger(CustomLogger.class);

	public static void logSQL(String args) {
		logger.info("Generated SQL Statement : " + args);
	}

}
