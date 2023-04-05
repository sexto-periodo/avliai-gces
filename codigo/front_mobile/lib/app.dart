import 'package:flutter/material.dart';
import 'package:front_mobile/home_page.dart';
import 'package:front_mobile/login_page.dart';

class App extends StatelessWidget {
  const App({super.key});

  @override
  Widget build(BuildContext context) {
    return const MaterialApp(
      home: LoginPage(),
    );
  }
}