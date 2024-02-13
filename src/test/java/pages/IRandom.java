package pages;

import java.util.List;
import java.util.Random;

public interface IRandom {

    default <T> T getRandomValue(List<T> listValues) {

        Random random = new Random();

        return listValues.get(random.nextInt(listValues.size()));
    }
}
