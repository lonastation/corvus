import 'package:flutter/material.dart';
import 'bottom_navigation_box.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: const BottomNavigationBox(),
      theme: ThemeData(
        useMaterial3: true,
      ),
    );
  }
}
