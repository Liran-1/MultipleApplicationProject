# TimeManager Android Application ReadMe

## Overview
This project comprises two Android applications that utilize a shared module featuring a `TimeManager` class. The `TimeManager` class enables users to manage a countdown timer, allowing them to set, pause, resume, reset, and adjust countdown times ranging from 1 minute to 1 hour.

## Features
Both applications include the following features:
- **Countdown Timer**: Users can set a timer from 1 minute up to 1 hour.
- **Pause and Resume**: Ability to pause and resume the timer.
- **Reset**: Option to reset the timer to its initial state.
- **Session-Specific**: Each app is specialized for:
  - **Study Session Timer**
  - **Workout Session Timer**

## Module: TimeManager Class
The `TimeManager` class provides core functionality for managing the countdown timer:
- **startTimer(int timerMinutesFromInput)**: Initiates the countdown timer with the specified duration in minutes provided as `timerMinutesFromInput`.
- **getTimePassed()**: Returns the amount of time that has passed in milliseconds since the timer started.
- **setTimePassed(long timePassed)**: Sets the time that has passed in milliseconds, useful for resuming a timer from a paused state.
- **stopTimer()**: Stops the countdown timer and resets it to its initial state.
- **pauseTimer()**: Pauses the countdown timer at its current remaining time.
- **startCountDownTimer()**: Starts or resumes the countdown timer.

## TimerCallback
The `TimeManager` class utilizes `TimerCallback` for UI updates:
- **Time Left**: Provides the remaining time formatted as "%02d:%02d:%02d".
- **Percentage Completion**: Indicates the percentage of time elapsed from start to current time.


## Application Structure
Each application includes:
- **Panel_Activity**: Class controlling the UI and inheriting from `MainActivity` in the shared module.
- **CountDownTimer Usage**: The `TimeManager` class utilizes Android's `CountDownTimer` to control the timer functionality.

## Development Environment
- **Language**: Java
- **IDE**: Android Studio
- **Compatibility**: Minimum SDK version 26

## How to Use
1. **Clone Repository**: Clone the repository containing both applications and the shared module.
2. **Open in Android Studio**: Import the project into Android Studio.
3. **Build and Run**: Build and run either application on an Android device or emulator.
4. **Set Timer**: Enter desired countdown time (1 minute to 1 hour) and start.
5. **Control Timer**: Pause, resume, or reset the timer as needed.

## Dependencies
- No additional dependencies beyond standard Android libraries are required.

## Contributing
- Contributions are welcome via pull requests.
- For major changes, please open an issue first to discuss.

## License
- This project is licensed under the [MIT License](https://www.mit.edu/~amini/LICENSE.md).

## Authors
- [Liran](Github.com/Liran-1)
