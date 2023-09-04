package com.hellofresh.StreamingApplication.utils;

import com.hellofresh.StreamingApplication.domains.Event;
import com.hellofresh.StreamingApplication.domains.Stats;
import com.hellofresh.StreamingApplication.exceptions.ValidationException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapperUtilTest {

    @Test
    public void mapStringToEventObjTest() {
        Event event = MapperUtil.mapStringToEventObj("1607341341814,0.0442672968,1282509067");
        Assertions.assertThat(event.getX()).isEqualTo(0.0442672968);
        Assertions.assertThat(event.getY()).isEqualTo(1282509067);
    }

    @Test
    public void mapStringToEventObjTestException() {
        Exception exception = assertThrows(ValidationException.class, () -> {
            MapperUtil.mapStringToEventObj("1607341341814,,1282509067");
        });

        String expectedMessage = "Parameter missing - x";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void statToResponseStrMapperTest() {
        Stats stats = new Stats(10, 0.1100000, 0.100000, 10, 1282509067,
                10.0, 10);
        String result = MapperUtil.statToResponseStrMapper(stats);
        Assertions.assertThat(result).isEqualTo("10,0.1100000000,0.1000000000,1282509067,10.0");
    }
}
