/*
 * This is the entry point for the Application
 */

package apc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main
{
    final static Logger logger = LogManager.getLogger(Main.class);
    public static void main(String[] args)
    {
        logger.trace("trace");
        logger.debug("debug");
        logger.info("info");
        logger.error("error");
        logger.fatal("fatal");
    }

}
