package me.GGGEDR.GreenLandFall.Logger;

import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

public class LogFormater extends SimpleFormatter {

    @Override

    public synchronized String format(LogRecord record){

        record.setSourceClassName(LogFormater.class .getName());
        return String.format(
                "| %2$s\n",
                record.getLevel().getName(), formatMessage(record));

    }
}
