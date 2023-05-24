import 'package:flutter/material.dart';
import 'package:front_mobile/src/login_page.dart';
import 'package:get/get.dart';


class App extends StatelessWidget {
  const App({super.key});

  @override
  Widget build(BuildContext context) {
    return const GetMaterialApp(
      debugShowCheckedModeBanner: false,
      home: LoginPage(),
    );
  }
}

void main() {
  runApp(const App());
}
