package ru.netology;

import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;


import java.sql.DriverManager;
public class Sql {

    @BeforeEach
    @SneakyThrows
    void setUp() {
        var faker = new Faker();
        var dataSQL = "INSERT INTO users(login, password) VALUES (?, ?);";

        try (
                var conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                );
                var dataStmt = conn.prepareStatement(dataSQL);
        ) {
            dataStmt.setString(1, faker.name().username());
            dataStmt.setString(2, "password");
            dataStmt.executeUpdate();
            dataStmt.setString(1, faker.name().username());
            dataStmt.setString(2, "password");
            dataStmt.executeUpdate();
        }
    }}

