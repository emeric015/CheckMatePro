package com.checkmatepro.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogUtils
{
    private static final Logger LOGGER = LogManager.getLogger(LogUtils.class);

    public static Logger getLogger()
    {
        return LOGGER;
    }
}
