import 'package:flutter/material.dart';
import 'package:get/get.dart';

import 'src/splash_view.dart';

class App extends StatelessWidget {
  const App({super.key});

  @override
  Widget build(BuildContext context) {
    return const GetMaterialApp(
      debugShowCheckedModeBanner: false,
      home: splashView(),
    );
  }
}

void main() {
  runApp(const App());
}
