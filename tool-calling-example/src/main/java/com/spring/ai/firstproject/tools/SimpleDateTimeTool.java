package com.spring.ai.firstproject.tools;

import org.slf4j.Logger;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.context.i18n.LocaleContextHolder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SimpleDateTimeTool {

    private Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());


    //Information Tool
    @Tool(description = "Get the current date and time in users zone.")
    public String getCurrentDateTime() {
        this.logger.info("Tool calling");
        return LocalDateTime.now()
                .atZone(LocaleContextHolder.getTimeZone().toZoneId())
                .toString();
    }

    //Action tool: that I can take action
    @Tool(description = "Set the alarm for given time.")
    void setAlarm(@ToolParam(description = "Time in ISO-8601 format") String time) {
        var dateTime = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME);
        this.logger.info("Alarm set for {}", dateTime);
    }
}
