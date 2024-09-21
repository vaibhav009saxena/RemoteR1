# Indigo skyplus automation

[Cucumber JVM](https://cucumber.io/docs/reference/jvm) Integration with BrowserStack.

## Using Maven

### Run sample build

- Clone the repository
- Replace YOUR_USERNAME and YOUR_ACCESS_KEY with your BrowserStack access credentials in browserstack.yml.
- Install dependencies `mvn compile`
- To run the test suite having cross-platgit form with parallelization, run `mvn test -P sample-test`
- To run local tests, run `mvn test -P cloud-test`
- To run local tests, run `mvn test -P browserstack-local-test`
- To run local tests, run `mvn test -P local-test`

## Notes

* You can view your test results on the [BrowserStack Automate dashboard](https://www.browserstack.com/automate)



## MVN COMMANDS
-  `mvn clean compile test`
