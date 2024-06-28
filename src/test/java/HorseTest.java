import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.junit.jupiter.MockitoExtension;


import java.lang.invoke.TypeDescriptor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HorseTest {
    @Mock
    Horse horseMOCK;


    @BeforeEach
    void write() {
        System.out.println("Test start");
    }

    @AfterEach
    void after_each() {
        System.out.println("Test finished");
    }

    @Test
    void should_getName_null_exception() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse(null, 1, 1);
        }, "test fail");
        assertEquals("Name cannot be null.", exception.getMessage());

    }

    @ParameterizedTest
    @ValueSource(strings = {"   ", " ", "   ", ""})
    void should_getName_not_blanc(String strings) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse(strings, 1, 1);
        }, "shit,test fail");
        assertEquals("Name cannot be blank.", exception.getMessage());

    }

    @ParameterizedTest
    @ValueSource(doubles = {-1, -11, -10, -20})
    void should_Speed_cannot_be_negative(double doubles) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse("name", doubles, 10);
        }, "test_fail");
        assertEquals("Speed cannot be negative.", exception.getMessage());


    }

    @ParameterizedTest
    @ValueSource(doubles = {-1, -10, -100})
    void should_Distance_cannot_not_negative(double doubles) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse("name", 10, doubles);
        }, "test_fail");
        assertEquals("Distance cannot be negative.", exception.getMessage());

    }


    @ParameterizedTest
    @ValueSource(strings = {"lucky", "snow", "eagle"})
    void should_get_name(String strings) {
        Mockito.doReturn(strings).when(horseMOCK).getName();
        assertEquals(strings, horseMOCK.getName());

    }

    @ParameterizedTest
    @ValueSource(doubles = {1, 2, 3})
    void should_get_Speed(double doubles) {
        //assertEquals(new Horse("horse",doubles,2).getSpeed(),doubles);
        Mockito.when(horseMOCK.getSpeed()).thenReturn(doubles);
        assertEquals(doubles, horseMOCK.getSpeed());

    }

    @ParameterizedTest
    @ValueSource(doubles = {1.0, 5, 7.0, 8.0})
    void should_get_Distance(double doubles) {
        //assertEquals(new Horse("horse",1,doubles).getDistance(),doubles);
        Mockito.doReturn(doubles).when(horseMOCK).getDistance();
        assertEquals(doubles, horseMOCK.getDistance());

    }

    @Test
    @EmptySource
    void should_get_Distance_empty() {
        assertTrue(new Horse("horse", 1).getDistance() == 0);

    }

    @Test
    @MethodSource()
    void move_test() {
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            Horse.getRandomDouble(0.2, 0.9);
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9), times(1));

        }
    }


    @ParameterizedTest
    @ValueSource(doubles = {1, 3, 5, 6})
    void move_formula_test(double doub) {
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(doub);
            Horse horse = new Horse("hors", 10, 1);
            Double result = horse.getDistance() + horse.getSpeed() * doub;
            horse.move();
            assertEquals(horse.getDistance(), result);

        }

    }


}