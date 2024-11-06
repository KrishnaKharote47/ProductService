package com.example.ecommerce.exceptionHandler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.example.ecommerce.models.CommonConstants;

public class ExceptionLogger {

    private static final Logger LOG = LogManager.getFormatterLogger();

    public static void printException(Exception e , String lodID){
        LOG.error(lodID + CommonConstants.EXCEPTION_STRING_START);
        LOG.error(lodID + CommonConstants.EXCEPTION_DETAILS);
        LOG.error(lodID + CommonConstants.CLASSNAME + e.getStackTrace()[0].getClassName());
        LOG.error(lodID + CommonConstants.METHODNAME + e.getStackTrace()[0].getMethodName() + CommonConstants.BLANK_SPACE
                + CommonConstants.LINENUMBER + e.getStackTrace()[0].getLineNumber());
        LOG.error(lodID + CommonConstants.CAUSE + e.getCause());

        e.printStackTrace();

    }

}
