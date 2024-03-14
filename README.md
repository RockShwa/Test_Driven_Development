# Test Driven Development
- The mantra of TDD:
1) Red: Write a test that dosen't work, and perhaps dosen't even compile at first
2) Green: Make the test work quickly, commiting whatever (coding) sins necessary in the process 
3) Refactor: Eliminate all of the duplication created in merely getting the test to work

## PART 1 - The Money Example
- The Rhythm of Test Driven Development:
1) Quickly add a test
2) Run all tests and see the new one fail
3) Make a little change
4) Run all tests and see them all succeed
5) Refactor to remove duplication

### Chapter 1 - Multi-Currency Money
- Context of the Money Example:
    - WyCash, a bond portfolio management system; originally can only handle U.S. dollar denominated bonds -> customer wants multi-currency support
- Behavior needed to produce the revised report (what tests, when passed, will demonstrate the presence of code we are confident will compute report correctly):
    - We need to be able to add amounts in two different currencies and convert the result given a set of exchange rates
    - We need to be able to multiply an amount (price per share) by a number (number of shares) and recieve an amount
- When you write a test, you image the perfect interface for our operation. We tell ourselves a story about the operation will look on the outside
- Example of the multiplication test:
~~~ java
public void testMultiplication() {
    // This does not even compile, there is no Dollar class, no constructor, no method times(int), and no amount field
    Dollar five = new Dollar(5);
    five.times(2);
    assertEquals(10, five.amount);
}
// TO-DO List:
// $5 + 10 CHF = $10 of rate is 2:1
// $5 * 2 = $10 -> DONE
// Make amount private -> DONE
// Dollar side-effects? -> DONE
// Money rounding?
// equals() -> DONE
// hashCode()
// Equal null
// Equal object
// 5 CHF * 2 = 10 CHF -> DONE
// Dollar/Franc Duplication -> DONE
// Common equals -> DONE
// Common times -> DONE
// Compare Francs to Dollars -> DONE
// Currency? -> DONE
// Delete testFrancMultiplication? -> DONE
~~~
- The smallest change I can make to make this test pass, is to make amount = 10, so we start there
- However, our goal is to be able to write a test that makes sense to us, without having to change the code (otherwise, the test and code are dependent on each other)
- If dependency is the problem, duplication is the symptom; remove duplication and you remove dependency
- The point is to be able to take teeny steps, you can always make larger steps, but you need to be able to make small steps first

### Chapter 2 - Degenerate Objects
- The Three Strategies to get to Green quickly:
1) Fake it: Return a constant and gradually replace constants with variables until you have the real code
2) Use Obvious Implementation: Type in the real implementation
3) Triangulation, see Ch.3
- Often #2 is used, until the compiler is red, and then we go back to #1, and then back to #2 once we get back the confidence 

### Chapter 3 - Equality for All
- Value Objects: the values of the instance variables of the object never change once they have been set in the constructor; you never have to worry about aliasing problems
    - If I have one check and I set it to $5, and then I set another check's amount to the same $5, aliasing is when changing the first check's value inadvertently changed the second check's value
    - When you have Value Objects, I am garaunteed that the $5 will always be $5. If someone wants $7, they have to make an entire new object
    - One implication of Value Objects is that all operations must return a new object, and another is that Value Objects should implement equals(), because one $5 is pretty much good as another (also means you have to implment hashCode())
- Triangulation: When we triagulate, we only generalize code when we have two examples or more; we briefly ignore the duplication between test and model code. When the second example demands a general solution, then and only then do we generalize
    - Author uses it as a last resort, when he's totally unsure of what to refactor




