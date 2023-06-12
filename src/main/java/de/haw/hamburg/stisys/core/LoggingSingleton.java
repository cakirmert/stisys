package de.haw.hamburg.stisys.core;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * The LoggingSingleton class represents a singleton logger instance.
 * It implements the Singleton design pattern.
 */
public class LoggingSingleton {
    private static LoggingSingleton instance;
    private Logger logger;

    private LoggingSingleton() {
        // Initialize the logger
        logger = Logger.getLogger(LoggingSingleton.class.getName());
        configureLogger();
    }

    /**
     * Returns the singleton instance of LoggingSingleton.
     * @return The LoggingSingleton instance.
     */
    public static LoggingSingleton getInstance() {
        if (instance == null) {
            synchronized (LoggingSingleton.class) {
                if (instance == null) {
                    instance = new LoggingSingleton();
                }
            }
        }
        return instance;
    }

    /**
     * Logs an information message.
     * @param message The log message.
     */
    public void logInfo(String message) {
        logger.info(message);
    }

    /**
     * Logs a warning message.
     * @param message The log message.
     */
    public void logWarning(String message) {
        logger.warning(message);
    }

    /**
     * Configures the logger.
     */
    private void configureLogger() {
        try {
            // Create a FileHandler to log messages into a file
            FileHandler fileHandler = new FileHandler("access.log", true);
            fileHandler.setFormatter(new SimpleFormatter());

            // Set the logging level and add the FileHandler to the logger
            logger.setLevel(Level.INFO);
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
