import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extensions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ObjectStreamField;
import java.lang.annotation.*;
import java.util.ArrayList;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class HippodromeTest {

    @Mock
    Hippodrome hippodrome ;

    @Mock
    Horse horse;


    @Test
    void construtor_null_test(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()-> {
            new Hippodrome(null);
        },"test fail");
        assertEquals("Horses cannot be null.", exception.getMessage());

    }

    @Test
    void contructor_blank_test(){

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,()->{
            new Hippodrome(new ArrayList<>());
        });

        assertEquals("Horses cannot be empty.",e.getMessage());

    }
    @Test
    void getHorsesTest() {
        ArrayList<Horse> horses = new ArrayList<>();
        for (int i=0;i<30;i++){
           // horses.add(new Horse(String.format("horse%s",i),i+1));
            horses.add(horse);
        }
        hippodrome = new Hippodrome(horses);
        assertEquals(horses,hippodrome.getHorses());

    }


    @Test
    void move_allHorses_Test() {
        ArrayList<Horse> horses = new ArrayList<>();
        for (int i=0;i<50;i++){
            horses.add(horse);
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        Mockito.verify(horse,times(10)).move();

    }

    @Test
    void getWinner() {
        ArrayList<Horse> horses = new ArrayList<>();
        for (int i=0;i<30;i++){
            horses.add(new Horse(String.format("horse%s",i),i+1,i+2));

        }
        Hippodrome hippodrome1 = new Hippodrome(horses);
        assertEquals(hippodrome1.getWinner(),horses.stream().max(Comparator.comparing(Horse::getDistance)).get());


    }


}