# Registration Application - Interview Example
## Table of content
1. [Setup](#setup)
1. [Functionality](#functionality)
1. [Architecture](#architecture)
1. [Dependencies](#dependencies)
1. [Testing](#testing)
1. [Cookbook](#cookbook)

## Setup
### Clone Repository
```
git clone git@github.com:Vancore/Registration_Example.git
```

## Functionality
Provides 3 input fields:
- Name
- Email
- Date

You can jump from textfield to textfield by just using the keyboard. All fields are validated when pressing the registration button. When the validation fails, an error text is shown below the respective field. When the validation fails, the user is redirected to the success page, showing all the data from the 3 input fields.

You can navigate back and register anew.

## Architecture
This project follows the MVVM pattern. I was experimenting with Jetpack Compose as I did not yet have used it in a professional environment. I also tried to apply [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html) as far as possible. For asynchronous tasks this project uses coroutines in conjunction with Flow.

### ViewModel
* Handling business logic
* Transforms views for the Screens
* Entry point to the Domain Layer
* Can be injected with UseCases to have more functionality

### Repository
* Is not being used currently, could be used as top layer for the local (and remote) datastore

### Usecases
* Processing business logic
* Are reusable, can be injected to provide business logic processing in ViewModels

### Entities
* Currently all Entities are also used to display in the UI, this can be separated

## Dependencies
* [Compose / Navigation Compose](https://developer.android.com/jetpack/compose/documentation)
* [Jetpack DataStore Preferences](https://developer.android.com/topic/libraries/architecture/datastore?gclid=CjwKCAiA9tyQBhAIEiwA6tdCrNXCs46cLLqvJViHPqk2s4y2QtIgVcTm_3vkazxJ2p6oX5_9cQa6mhoC1OwQAvD_BwE&gclsrc=aw.ds)
* [Dagger Hilt](https://github.com/google/dagger/tree/master/java/dagger/hilt)
* [Accompanist](https://github.com/google/accompanist)
* [Android Kotlin Coroutines](https://github.com/Kotlin/kotlinx.coroutines)

## Testing
- follows - 

## Cookbook
The current architecture in a nutshell:

1) **Data**: Contains all models and the database
2) **DI**: Handling of the dependency injection with Hilt
3) **UI/Screens**: UI for the different composable screens including their responsive viewModels
4) **Usecases**: Reusable and providable/injectable instances for business logic
5) **Utils**: Util classes, e.g. date converter etc., can also contain extensions
