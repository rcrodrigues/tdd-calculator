This is a simple project executed as part of my TDD studies.

Requirements (v1.)
1. Create a simple String calculator with a method int Add(string numbers)
2. The method can take 0, 1 or 2 numbers, and will return their sum (for an empty string it will return 0) for example “” or “1” or “1,2”
3. Allow the Add method to handle an unknown amount of numbers
4. Calling Add with a negative number will throw an exception “negatives not allowed” – and the negative that was passed. If there are multiple negatives, show all of them in the exception message stop here if you are a beginner.
5. Numbers bigger than 1000 should be ignored, so adding 2 + 1001 = 2

Requirements (v2.)
1. Calculator should also support the remaning operations (subtract, multiply and divide).
2. For all operations, numbers should ONLY be positive. Otherwise, an error should be thrown (like req. v1. #4)
3. For divide, there can be no denominator equal to zero. Othwerise, an exception should be thrown (division by zero)
4. For multiply and divide, the result value should be a real number rounded to at most one decimal digit (i.e. "10.2").
5. The input values cannot be negative, but the result may be negative or zero.