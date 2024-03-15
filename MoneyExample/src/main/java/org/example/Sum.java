package org.example;

public class Sum implements Expression{
    public Expression augend;
    public Expression addend;

    public Sum(Expression augend, Expression addend) {
        this.augend = augend; // fiveBucks
        this.addend = addend; // tenFrancs
    }

    public Money reduce(Bank bank, String to) {
        int amount = augend.reduce(bank, to).amount + addend.reduce(bank, to).amount;
        return new Money(amount, to);
    }

    public Expression plus(Expression addend) { // fiveBucks
        return new Sum(this, addend); // Sum -> (fiveBucks, tenFrancs), fiveBucks
    }

    public Expression times(int multiplier) {
        return new Sum(augend.times(multiplier), addend.times(multiplier));
    }
}
