# Restaurants
A simple restaurants list with sorting and filtering support  
Source code can be found [here](https://github.com/ponsuyambu/Restaurants)  
Download the apk [here](docs/Restaurants.apk?raw=true "Restaurants APK")

## Use cases
- When launching the app, the restaurants list will be fetched from the server and shown to the user
- User can filter the restaurants by name
- Use can also sort the list with the provided sorting options

### Error handling
Exceptions are properly caught and shown to the user gracefully. The mapping of error messages has to be handled based on business requirements.

## Technical Details
- 100% Kotlin used. For build scripts also Kotlin DSL is used.

### Architecture
- Each feature is grouped under a separate folder, this folder can be moved to a separate repo for the independent modularized development in future.
- Each layer of the feature is structured using the CLEAN architecture below 

![clean_arch_layers](docs/clean-architecture-layers.png?raw=true "clean-architecture-layers")

- The interaction between the layers are happening as below  

![clean-architecture-interaction](docs/clean-architecture-interaction.png?raw=true "clean-architecture-interaction"). 

- MVVM pattern along with android arch components is used to handle the android lifecycle smoothly.  

### Libraries Used
- Arch components(LiveData, ViewModel) for MVVM
- Hilt for Dependency Injection
- Mockk for unit testing
- Espresso and Barista for UI/Instrumentation tests
- Material Design Library for responsive layouts
- ktlint for code formatting
- SavedStateHandle for handling the process death smartly within ViewModel

### CI/CD Integration
- Github Actions are used for CI/CD. On each PR, Build check, Lint check(`./gradlew lint`), Unit tests are configured to make the master branch bug-free and more stable.
- You can see the PR checkers in the pull requests [page](https://github.com/ponsuyambu/Restaurants/pulls?q=is%3Apr+is%3Aclosed).
- You can find the workflow scripts [here](.github/workflows)

### Build

Use the latest android studio version to build the code(v4.2.1+). 

- To generate the APK from the command line, run `./gradlew assembleDebug`.
- To run all tests, run `./gradlew test`
- To run all instrumentation tests, run `./gradlew connectedAndroidTest`
- To run android lint run `./gradlew lint`
- To run ktlint, run the command after downloading the [ktlint](https://github.com/pinterest/ktlint#installation) in the root folder `./ktlint -F`

### Tests

- Each module consists of the tests only related to them. Even UI tests are segregated in their corresponding presentation module with the help of `common-instrumentation-test-utils`. Common test utils/mocks are shared via the common test modules `common-test-utils', `common-instrumentation-test-utils`. 
- To run all the tests from the command line, run `./gradlew test`

### Instrumentation tests

- To run the tests on a real device, make sure the battery optimization is disabled for the app
- You might also need to turn off the animation settings via developer options
- To run all the instrumentation tests from the command line, run `./gradlew connectedAndroidTest`
- It is recommended to run the tests on the device because if the system's memory is low, the emulator will be laggy which leads to flaky test results
Note: Not all the cases are covered for instrumentation tests

### Code Formatting & Lint

- ktlint, Android lint are used to enforce the code formatting and quality.
