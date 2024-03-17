from TestResult import TestResult
class TestSuite:
    def __init__(self):
        self.tests = []
    def add(self, test):
        self.tests.append(test)
    # We want a single TestResult to be used by all of the tests that run
    def run(self, result):
        for test in self.tests:
            test.run(result)