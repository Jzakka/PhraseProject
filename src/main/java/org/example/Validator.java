package org.example;

import org.example.driver.JsonFileDriver;
import org.example.driver.TextFileDriver;
import org.example.driver.ValidatableDriver;
import java.util.NoSuchElementException;

public class Validator {
    ValidatableDriver driver = new JsonFileDriver();

    public void validateId(long id) throws RuntimeException {
        if (!driver.exists(id)) {
            throw new NoSuchElementException(String.format("%d번 명언은 존재하지 않습니다.", id));
        }
    }
}
