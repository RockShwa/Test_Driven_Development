# Test Driven Development

- The mantra of TDD:
1) Red: Write a test that dosen't work, and perhaps dosen't even compile at first
2) Green: Make the test work quickly, commiting whatever (coding) sins necessary in the process 
3) Refactor: Eliminate all of the duplication created in merely getting the test to work

## Running the Examples
- For the .java files: ./gradlew.bat build or ./gradlew.bat test in root project directory
- For the .py files: python ./<DesiredFile.py> IN the xUnitExample directory

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
// $5 + $5 = $10 -> DONE
// Return Money from $5 + $5
// Bank.reduce(Money) -> DONE
// Reduce Money with conversion -> DONE
// Reduce (Bank, String) -> DONE
// Sum.plus -> DONE
// Expression.times -> DONE
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

### Chapter 12 - Addition, Finally
- When the object we have does not behave in the way we want it to, we make another object with the same external protocol (an imposter) but a different implementation
- The Expressions seem to be the heart of the goal here (adding diff currencies). I try to keep objects at the heart as ignorant of the rest of the world as possible, so they stay flexible

## PART 2 - The xUnit Example
- This is about testing our testing framework :D

### Chapter 18 - First Steps to xUnit
- In this part, we're develping our own testing framework with xUnit principles
- To-Do List for a Testing Framework:
1) Invoke test method -> DONE
2) Invoke setUp first -> DONE
3) Invoke tearDown afterward -> DONE
4) Invoke tearDown even if the test method fails -> DONE
5) Run multiple tests -> DONE
6) Report collected results -> DONE
7) Log string in WasRun -> DONE
8) Report failed tests -> DONE
- General patterns of refactoring:
    - Seperate two parts so you can work on them seperately; if they go back together, fine. If not, you can leave them seperate
    - Take code that works in one instance and generalize it to work in many by replacing constants with variables

### Chapter 19 - Set the Table
- When you write tests, here is a common pattern:
1) Arrange - create some objects (often the same from test to test)
2) Act - simulate them (unique to a test)
3) Assert - check the results (unique to a test)
- How to create the objects to test? Two concerns:
1) Performance - we want our tests to run as quickly as possible; if we use similar objectss in several tests, we would like to create them once for all tests
2) Isolation - we want the success or failure of one test to be irrelevant to other tests; if tests share objects and one test changes the objects, following tests are likely to change their results
    - **DO NOT Couple Tests**

## Part 3 - Patterns for Test-Driven Development

### Chapter 25 - TDD Patterns
- Basic Strategic Questions:
1) What do we mean by testing?
2) When do we test?
3) How do we choose what logic to test?
4) How do we choose what data to test?
- **Test**
    - Verb: to evaluate; not the same as having tests
    - Noun: a procedure that runs automatically 
    - More stress means to running tests less, and the less testing you do, the more errors you make, which in turns raises your stress
    - With automated tests, when I feel stressed, I just run the tests; the more stress I feel, the more I run the tests, which reduces my errors and thus my stress
    - Go ahead and run the test after you write it, even if you're pretty sure it will fail (like it should)
- **Isolated Test**
    - The running of one test should not affect another; if one test fails, I should have one problem. If two fail, I should have two problems
    - Isolated tests also means that the tests are order independent
    - Encourages you to compose solutions out of many highly cohesive, loosely coupled objects
- **Test List**
    - Before you begin, write a list of all the tests you know you will have to write; what is it we intend to accomplish?
    - "Now" list (what I want done today) and a "Later" list (what I want done in the next few weeks/months)
    - Put on these lists every operations that you know you need to implement, null versions of operations that don't already exist, and list all the refactorings you think you will have to do to have clean code and the end of the session
- **Test First**
    - You should write your tests before you write the code that is to be tested
    - When we test first, we reduce stress, which makes us more likely to test
- **Assert First**
    - You should write the asserts first -> What is the right answer? How am I going to check?
    - Examples:
    ~~~ java
    // say we want to communicate with another systme over a socket; when we're done, the socket should be closed and we should have the string abc
    testCompleteTransaction() {
        Server writer = Server(defaultPort(), "abc");
        Socket reader = Socket("localhost", defaultPort());
        Buffer reply = reader.contents();
        assertTrue(reader.isClosed()); // Work backward from these last 2 lines
        assertEquals("abc", reply.contents());
    }
    ~~~
- **Test Data**
    - Use data that makes the tests easy to read and follow; you are reading tests to an audience
        - Only scatter data around if there is a good reason to; if you can use 1 or 2, use 1
    - Never use the same constant to mean more than one thing
        - If I'm testing a plus() method, 2 + 2 is tempting to use, but what if the arguments were reversed? Or what if there is a negative?
    - Realistic Data (data from the real world) is useful when you are testing real-time systems; parallel testing; or when refactoring a simulation and expect precisely the same answers when you are finished
- **Evident Data**
    - To represent the intent of the data, include expected and actual results in the test itself, to make their relationship apparent, you're writing tests for the reader and the computer
    - This can make your code much easier to read and easier to implement your tests (within the method scope, it's not good to just have magic numbers everywhere)
    - Example:
    ~~~ java
    // Non-Evident Way:
    Bank bank = new Bank();
    bank.addRate("USD", "GBP", STANDARD_RATE);
    bank.comission(STANDARD_COMMISSION);
    Money result = bank.convert(new Note(100, "USD"), "GBP");
    assertEquals(new Note(49.25, "GBP"), result);
    // Evident Way (relationship more clear):
    // Assuming that the have 1.5 percent commision and exchange rate (USD to GBP) is 2:1
    Bank bank = new Bank();
    bank.addRate("USD", "GBP", 2);
    bank.comission(0.015);
    Money result = bank.convert(new Note(100, "USD"), "GBP");
    assertEquals(new Note(100 / 2 * (1 - 0.015), "GBP"), result);
    ~~~

### Chapter 26 - Red Bar Patterns
- These patterns are about when you write tests, where you write tests, and when you stop writing tests
- **One Step Test**
    - The test you should pick next from the list is the test that will teach you something AND that you are confident you can implement (not always the obvious ones)
    - This is a known-to-unknown way of writing software where the program affects the environmwnt and the environment affects the program
- **Starter Test**
    - You should start by testing a variant of an operation that doesn't do anything
    - The first questions you should ask are: "Where does it belong?" & "What are the correct inputs?" & "What is the correct output given those inputs?"
    - Your starter test should be quick and something you can learn from
    - Often the starter test is more high level, and then you get to the nitty-gritty later
- **Explanation Test**
    - To spread the use of automated testing, ask for an give explanations in terms of tests (it helps get other people on board so you're not lonely)
- **Learning Test**
    - You write tests for externally produced software before the first time you are going to use a new facility in the package
    - If we want to develpo on top of the Mobile Information Device Profile library for Java; we want to store some data in the RecordStore and retrieve it
    - First, we would write a little test that verifies the API works as expected:
    ~~~ java
    public void setUp() {
        store = RecordStore.openRecordStore("testing", true);
    }
    public void tearDown() {
        RecordStore.deleteRecordStore("testing");
    }
    public void testStore() {
        int id = store.addRecord(new byte[] {5, 6}, 0, 2);
        assertEquals(2, store.getRecordSize(id));
        byte[] buffer = new byte[2];
        assertEquals(2, store.getRecord(id, buffer, 0));
        assertEquals(5, buffer[0]);
        assertEquals(6, buffer[1]);
    }
    ~~~
    - This maintains confidence in the API and it helps you understand how to use the API
- **Regression Test**
    - The first thing you do when a defect is reported is write the smallest possible test that fails and that, once run, will be repaired
    - Regression tests are tests that, will perfect foreknowledge, you would have written when coding originally (think about how you could have known to write that test in the first place)
    - Regression tests at the application scale give users a chance to speak concretely about what they expect and what is wrong; at the smaller scale, they give you a way to improve your testing

### Chapter 27 - Testing Patterns
- **Child Test**
    - If you have a test case running that's too big, write a smaller test case that represents the broken part of a bigger test case
    - The red/green/refactor process is so important, that if you are risking losing it, its worth more effort to maintain the process
- **Mock Object**
    - To test an object that relies on an expensive or complicated resource, create a fake version of the resource that answers constrants
    - Classic example is a database; they take a long time to start, they are difficult to keep clean, and if they are located on a remote server, they tie your tests to a physical location on a network
    ~~~ java
    public void testOrderLookup() {
        // If the Mockdatabase does not get the value it expects, it throws an exception
        Database db = new MockDatabase();
        db.expectQuery("select order_no from Order where cust_no is 123");
        db.returnResult(new String[] {"Order 2", "Order 3"});
    }
    ~~~
    - However, if you use Mock objects, you can't easily store expensive resources in global variables
    - Mock objects encourage you down the path of carefully considering the visibility of every object, reducing the coupling of your designs
- **Self Shunt**
    - To test that one object communicates correctly with another, have the object under test communicate with the test case instead of with the object it expects
    - If we wanted to dynamically update the green bar on the testing user interface; if we connect an object to the TestResult, then it coudl be notified when the test ran, when it failed, when the whole suite started and finished, etc. When we were notified that a test ran, we would update the interface
    ~~~ python
    # ResultListenerTest
    def testNotification(self):
        self.count = 0
        result = TestResult()
        result.addListener(self)
        WasRun("testMethod").run(result)
        assert(1 == self.count)
    def startTest(self):
        self.count = self.count + 1
    # The test case can itself becomes a kind of Mock Object
    ~~~
    - These kinds of tests are often easier to read; might have to use Extract Interface to get an interface to implement
    - Any interfaces you implement need to be small and lightweight, so you don't have to implement so many functions
- **Log String**
    - To test that the sequence in which messsages are called is correct, keep a log in a string, and append to the string when a message is called
    - Very useful when implementing Observer and you expect notifications to come in a certain order
    - Works well with self shunt, the test case implements the methods in the shunted interface by adding to the log and then returning reasonable values
- **Crash Test Dummy**
    - To test error code that is unlikely to be invoked, invoke it anyway with a special object that throws an exception instead of doing real work
    ~~~ java
    private class FullFile extends File {
        public FullFile(String path) {
            super(path);
        }
        public boolean createNewFile() throws IOException {
            throw new IOException();
        }
    }

    public void testFileSystemError() {
        File f = new FullFule("foo");
        try {
            saveAs(f);
            fail();
        } catch (IOException e) {}
    }
    // Turns into this:
    public void testFileSystemError() {
        File f = new File("foo");
        public boolean createNewFile() throws IOException {
            throw new IOException();
        }
    };
    try {
        saveAs(f);
        fail();
    } catch (IOException) {}
    ~~~
    - A Crash Test Dummy works like a Mock Object, except you don't need to mock up the whole object
    - Java's inner classes work well for sabotaging just the right method to simulate the error we wamt to exercise; you can override just the one method you want, right in your test case, making the test easier to read
- **Broken Test**
    - Finish a solo programming session by writing a test case and running it to be sure it dosen't pass; when you come back to the code, you then have an obvious place to start where you can pick up your previous line of thought

### Chapter 28 - Green Bar Patterns
- Once you have a broken test, you need to fix it. If you treat a red bar as a condition to be fixed as quickly as possible, then you will discover that you can get to green quickly. Use these patterns to make the code pass
- **Fake It ('Til You Make It)**
    - Your first implementation once you have a broken test is to return a constant. Once you have the test running, gradually transform the constant into an expression using variables
- **Triangulate**
    - To most conservatively drive abstraction with tests, abstract only when you have two or more examples
    - Creates a sort of infinite loop, good for when you REALLY don't know what to do
- **Obvious Implementation**
    - To implement simple operations, just implement them
    - Be careful of using this method too much though, it can be discouraging and sometimes taking big steps is NOT the best way to go
- **One to Many**
    - To implement an operation that woeks with collections of objects, implement it without the collections first, then make it work with collections
    ~~~ java
    public void testSum() {
        // started with an int[] of just one element
        assertEquals(12, sum(5, new int[] {5, 7}));
    }
    private int sum(int[] values) {
        int sum = 0;
        for (int i = 0; i < values.length; i++) {
            sum += values[i];
        }
        return sum;
    }
    ~~~

### Chapter 29 - xUnit Patterns
- These are patterns for using one of the xUnit familt of testing frameworks
- **Assertion**
    - To check that the tests worked correctly, write boolean exprsesions that automate your judgement about whether the code worked (remove human judgement: evaluated by computer)
    - Assertions should be specific; expected usually goes first, then actual
    - Anytime I want to use a variable as a way of checking to see whether code ran correctly or not, there is an opportunity for design improvement
    - You can a message in the beginning or end of an assertion to give more explanation of the expected should an assertion fail
- **Fixture**
    - To create common objects needed by several tests, convert the local variables in the tests into instance variables, override setUp() and initialize those variables
    - The duplication in tests that is bad is the kind that is for setting up objects for several tests (takes a while to write)
    - However, duplication can make your tests more readable from top to bottom
    ~~~ java
    public void testEmpty() {
        Rectangle empty = new Rectangle(0,0,0,0);
        assertTrue(empty.isEmpty());
    }
    public void testWidth() {
        Rectangle empty = new Rectangle(0,0,0,0);
        assertEquals(0.0, empty.getWidth(), 0.0)
    }
    // Rid duplication:
    private Rectangle empty;
    public void setUp() {
        empty = new Rectangle(0,0,0,0);
    }
    public void testEmpty() {
        assertTrue(empty.isEmpty());
    }
    public void testWidth() {
        assertEquals(0.0, empty.getWidth(), 0.0)
    }
    ~~~
    - Each new kind of fixture should be a new subclass of TestCase; each new fixture is created in an instance of that subclass, used once, and then discarded. If I find myself wanting a slightly different fixture, then I start a new subclass of TestCase
- **External Fixture**
    - To release external resources in the fixture, override tearDown() and release the resources. The goal of each test is to leave the world exactly the same as before it ran
    - tearDown() will be called regardless of whether the test passes or not (if setUp() fails, then tearDown() won't be called)
- **Test Method**
    - To represent a single test case, create a method whose name begins with the word "test"
    - OO Languages have 3 levels of hierarchy for organizations (Module/Package, Class, Method)
    - If we are using classes to represent fitures, then the natural home for tests is as methods. All of the tests that share a single fixture will be methods in the same class
    - Test methods should have expressive names and be short and easy to read
- **Exception Test**
    - To test for expected exceptions, catch expected exceptions and ignore them, failing only if the exception is not thrown
    ~~~ java
    // If a value is not found, then we want to throw an exception
    public void testRate() {
        exchange.addRate("USD", "GBP", 2);
        int rate = exchange.findRate("USD", "GBP");
        assertEquals(2, rate);
    }
    public void testMissingRate() {
        try {
            exchange.fingRate("USD", "GBP");
            fail();
        } catch (IllegalArgumentException expected) {}
    }
    // If findRate does not throw an exception, ww will call fail(), an xUnit method that reports that a test failed (only catch exception we expect)
    ~~~
- **All Tests**
    - To run all tests together, make a suite of all the suites - one for each package, and one aggregating the package tests for the whole application
    - Suppose you add the TestCase subclass to a package and you add a test method to that class. The next time all of the tests run, that test method should run too
    - Each package should declare a class AllTests that implements a static method suite() that returns a TestSuite()
    - AllTests for money example:
    ~~~ java
    public class AllTests {
        public static void main(String[] args) {
            // Can also give AllTests a main() method so that the class can be run directly from
            // IDE or command line
            junit.swingui.TestRunner.run(AllTests.class);
        }
        public static Test suite() {
            TestSuite result = new TestSuite("TFD tests");
            result.addTestSuite(MoneyTest.class);
            result.addTestSuite(ExchangeTest.class);
            result.addTestSuite(IdentityRateTest.class);
            return result;
        }
    }
    ~~~