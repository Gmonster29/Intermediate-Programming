import 'package:flutter/material.dart';
import 'dart:async';
void main() => runApp(const MyApp());

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) => MaterialApp(
        title: 'Games Hub',
        theme: ThemeData(
          colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
        ),
        home: const HomePage(),
      );
}

class GreatLakesGame extends StatefulWidget {
  const GreatLakesGame({super.key});

  @override
  State<GreatLakesGame> createState() => _GreatLakesGameState();
}

class HomePage extends StatelessWidget {
  const HomePage({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Game Menu')),
      body: Center(
        child: Column(
          mainAxisSize: MainAxisSize.min,
          children: [
            ElevatedButton(
              onPressed: () {
                Navigator.push(
                  context,
                  MaterialPageRoute(builder: (_) => const GreatLakesGame()),
                );
              },
              child: const Text('Play Great Lakes Game'),
            ),
            const SizedBox(height: 20),
            ElevatedButton(
              onPressed: () {
                Navigator.push(
                  context,
                  MaterialPageRoute(builder: (_) => const MLBGame()),
                );
              },
              child: const Text('MLB Game'),
            ),
          ],
        ),
      ),
    );
  }
}
// Placeholder for the MLB game
class MLBGame extends StatelessWidget {
  const MLBGame({super.key});
  
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('MLB Game')),
      body: Center(
        child: const Text('MLB Game'),
      ),
    );
  }
}

class _GreatLakesGameState extends State<GreatLakesGame> {
  final Set<String> _answers = {'superior', 'michigan', 'huron', 'erie', 'ontario'};
  final Set<String> _guessed = {};
  final TextEditingController _controller = TextEditingController();

  bool _started = false;
  bool _gameOver = false;
  String _feedback = '';
  int _timeRemaining = 60;
  Timer? _timer;

  void _startGame() {
    setState(() {
      _started = true;
      _gameOver = false;
      _guessed.clear();
      _feedback = '';
      _controller.clear();
      _timeRemaining = 60;
    });

    _timer?.cancel();
    _timer = Timer.periodic(const Duration(seconds: 1), (timer) {
      setState(() {
        if (_timeRemaining > 0) {
          _timeRemaining--;
        } else {
          _feedback = 'Time\'s up!';
          _gameOver = true;
          timer.cancel();
        }
      });
    });
  }

  void _checkGuess() {
    final guess = _controller.text.trim().toLowerCase();

    if (guess.isEmpty || _guessed.contains(guess) || _gameOver) return;

    setState(() {
      _guessed.add(guess);
      _feedback = _answers.contains(guess) ? 'Correct!' : 'Try again!';
      _controller.clear();
    });
  }

  @override
  void dispose() {
    _timer?.cancel();
    _controller.dispose();
    super.dispose();


  }

  @override
  Widget build(BuildContext context) {
    final correct = _guessed.where(_answers.contains).toSet();

    return Scaffold(
      appBar: AppBar(title: const Text('Great Lakes Game')),
      body: Center(
        child: _started
            ? Padding(
                padding: const EdgeInsets.all(16),
                child: Column(
                  mainAxisSize: MainAxisSize.min,
                  children: [
                    Text(
                      'Time remaining: $_timeRemaining seconds',
                      style: const TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
                    ),
                    const SizedBox(height: 10),
                    const Text('Guess a Great Lake:'),
                    TextField(
                      controller: _controller,
                      onSubmitted: (_) => _checkGuess(),
                      enabled: !_gameOver,
                      decoration: const InputDecoration(hintText: 'e.g. Ooga Booga'),
                    ),
                    const SizedBox(height: 10),
                    ElevatedButton(
                      onPressed: _gameOver ? null : _checkGuess,
                      child: const Text('Submit'),
                    ),
                    const SizedBox(height: 10),
                    Text(_feedback, style: const TextStyle(fontSize: 16)),
                    const SizedBox(height: 10),
                    Text('Correct: ${correct.join(', ')}'),
                    Text('Remaining: ${_answers.length - correct.length}'),
                  ],
                ),
              )
              
            : ElevatedButton(
                onPressed: _startGame,
                child: const Text('Start Game'),
              ),
      ),
    );
  }
}