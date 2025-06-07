# Simple MVI Architecture Example in Kotlin Multiplatform (Compose)

This project is a very basic example demonstrating the Model-View-Intent (MVI) architectural pattern using Kotlin Multiplatform with Jetpack Compose for the UI. It's designed to be a clear and understandable illustration of MVI principles, suitable for developers looking to grasp the core concepts.

## Purpose

The main goal of this project is to showcase:
1.  A unidirectional data flow.
2.  Clear separation of concerns between UI, state management, business logic, and side effects.
3.  How different MVI components (State, ViewAction, Intent, Effect, Store, Executor, Reducer) work together.

This example is intentionally kept simple to focus on the architecture itself, rather than complex features.

## MVI Architecture Overview

The MVI pattern structures the application into distinct, predictable components:

*   **Model (State):** Represents the current state of the UI. It's an immutable data structure.
*   **View (Composable UI):** Observes the State and renders the UI. It also captures user interactions and translates them into ViewActions.
*   **Intent (ViewAction leading to ScreenIntent):** Represents the user's intention to change the state or perform an action. In this project:
  *   `ViewAction`: Originates from the UI.
  *   `ScreenIntent`: Represents a specific desire to change the state, often produced by the Executor.

The flow is typically:
**View -> ViewAction -> Store -> Executor -> ExecutionResult (containing ScreenIntent/ScreenEffect) -> Store -> [Reducer -> New State -> View] / [Effect -> View]**
