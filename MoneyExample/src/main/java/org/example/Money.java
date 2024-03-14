package org.example;

public class Money {
    protected int amount;
    protected String currency;

    public Money(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public boolean equals(Object obj) {
        Money otherMoney = (Money) obj;
        // If amounts and currency type is the same
        return (amount == otherMoney.amount) && (currency().equals(otherMoney.currency()));
    }

    // This factory method means that no client code knows that a Dollar subclass exists, which
    // further decouples the tests from the existence of the subclasses (can change inheritance 
    // without affecting test code)

    static Money dollar(int amount) {
        return new Money(amount, "USD");
    }

    static Money franc(int amount) {
        return new Money(amount, "CHF");
    }

    public String currency() {
        return currency;
    }

    public String toString() {
        return amount + " " + currency;
    }

    public Money times(int multiplier) {
        return new Money(amount * multiplier, currency);
    }
}
