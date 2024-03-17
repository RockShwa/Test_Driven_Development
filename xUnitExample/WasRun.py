# keyword pass is used when there is no implementation of a class or method
# wasRun attribute is a flag, was the test run or not?
# names of classes and methods can be treated as methods; when we get an attribute corresponding 
# to the name of the test case, we are returned an object, which, when invoked as a function, 
# invokes the method
from TestCase import TestCase

class WasRun(TestCase):
    # Keeping track of whether a method was invoked or not
    def __init__(self, name):
        TestCase.__init__(self, name)

    def testMethod(self):
        self.log = self.log + "testMethod "

    def setUp(self):
        self.log = "setUp "

    def tearDown(self):
        self.log = self.log + "tearDown "

    def testBrokenMethod(self):
        raise Exception
    
    def testSetUpFailure(self):
        raise Exception
    
    