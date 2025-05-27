# Games Hub

This Flutter app features a simple game menu with two options: an **NFL Game** (that currently uses NFL team names as the answer set but mistakenly asks users to "Guess a Great Lake"), and a placeholder **MLB Game** screen.

---

##  Reflection

### How did learning and implementing the feature of the programming language you just learned go?

Learning Flutter and Dart was definitely a leap from what we were used to. I had to learn **state management**, **navigation between screens**, and how to implement a **countdown timer** using `Timer.periodic`. The syntax was unfamiliar at first, but Flutter’s widget system started to make sense after a few examples. The game logic itself (checking guesses, validating input, and managing time) was easier to implement once we understood how state changes work inside `StatefulWidget`.

The biggest challenge was keeping the UI responsive and resetting state properly when restarting the game — it helped me get more comfortable with `setState()` and Flutter’s widget lifecycle.

---

## Resources Used

- [Flutter Timer tutorial](https://www.youtube.com/watch?v=8HCFgMk-WP8)
- [Flutter Navigator.push Explained](https://docs.flutter.dev/cookbook/navigation/navigation-basics)
- [TextField controller & input handling](https://api.flutter.dev/flutter/widgets/TextEditingController-class.html)
- Flutter documentation: https://docs.flutter.dev
- StackOverflow threads about `StatefulWidget`, `Timer`, and best practices for game loops

---

## Important Classes and Functions Used

- `StatefulWidget` and `State` (for the game screen logic)
- `TextEditingController` (to handle user input)
- `Timer.periodic()` (to run the countdown)
- `Navigator.push()` (to navigate between screens)
- `setState()` (to trigger UI updates)

## Did We Fail?
- Yes!
- We were not able to create a centralized widget for out games that we could just put content into and it would generate a game