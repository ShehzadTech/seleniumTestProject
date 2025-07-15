package utils;

import com.github.javafaker.Faker;

public class TestDataReader {
    private static final Faker faker = new Faker();

    public static String getFakeUsername() {
        return faker.name().username();
    }

    public static String getFakePassword() {
        return faker.internet().password();
    }
}
