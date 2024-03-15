package org.example;

public interface Expression {
    // Interface is lighter weight than a class
    Money reduce(Bank bank, String to);

    Expression plus(Expression addend);

    Expression times (int multipler);
}
