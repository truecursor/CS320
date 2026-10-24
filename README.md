# CS 320 Software Testing, Automation, and Quality Assurance
## REFLECT
### How can I ensure that my code, program, or software is functional and secure?

I can make sure my code works correctly by testing it against the requirements and checking how it handles both valid and invalid inputs. In Project One, I used JUnit tests to check things like field length limits, null values, duplicate IDs, and updating or deleting contacts. I also used code coverage results to see which parts of my program were being tested. I learned that having high code coverage is helpful, but it is also important to make sure the tests actually check the correct behavior. Input validation can also help prevent errors and reduce security risks.

### How do I interpret user needs and incorporate them into a program?

I start by looking at the requirements and breaking them down into smaller parts that I can implement and test. For example, the contact service had specific rules for contact IDs, names, phone numbers, and addresses. I used those rules to decide what the program should accept or reject, then created tests to make sure everything worked as expected. This helped me understand why following requirements matters instead of just assuming that a program works because it runs without errors.

### How do I approach designing software?

When designing software, I try to keep the code organized, simple, and easy to understand. For Project One, I separated the contact object from the contact service so each class had its own responsibility. I also created unit tests to check different features and catch problems without having to test the entire program manually. Throughout this course, I learned that testing should be part of the development process instead of something done only at the end. I plan to continue using this approach in future projects to make my code more reliable and easier to maintain.
