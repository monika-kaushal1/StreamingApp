package com.hellofresh.StreamingApplication.utils;

import com.hellofresh.StreamingApplication.domains.Event;
import com.hellofresh.StreamingApplication.domains.Stats;
import com.hellofresh.StreamingApplication.exceptions.ErrorCodes;
import com.hellofresh.StreamingApplication.exceptions.ValidationException;
import org.springframework.util.StringUtils;

public class MapperUtil {

    public static Event mapStringToEventObj(String eventStr) {
        if (!StringUtils.hasLength(eventStr)) {
            throw new ValidationException(ErrorCodes.EMPTY_EVENT);
        }
        String[] items = eventStr.split(",");
        if (items.length != 3) {
            throw new ValidationException(ErrorCodes.EVENT_FORMAT_NOT_PROPER);
        }

        if (!StringUtils.hasLength(items[0])) {
            throw new ValidationException(ErrorCodes.MISSING_PARAMETER_TIMESTAMP);
        }

        if (!StringUtils.hasLength(items[1])) {
            throw new ValidationException(ErrorCodes.MISSING_PARAMETER_X);
        }

        if (!StringUtils.hasLength(items[2])) {
            throw new ValidationException(ErrorCodes.MISSING_PARAMETER_Y);
        }

        return new Event(Long.valueOf(items[0]), Double.valueOf(items[1]),
                Long.valueOf(items[2]));
    }

    public static String statToResponseStrMapper(Stats stats) {
        String[] response = new String[5];

        response[0] = String.valueOf(stats.getTotal());
        response[1] = String.format("%.10f", stats.getSumX());
        response[2] = String.format("%.10f", stats.getAvgX());
        response[3] = String.valueOf(stats.getSumY());
        response[4] = String.valueOf(stats.getAvgY());
        return String.join(",", response);
    }
}
