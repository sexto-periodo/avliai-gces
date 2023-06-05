import 'package:flutter/material.dart';
import 'package:front_mobile/src/components/color_palette.dart';
import 'package:front_mobile/src/login_page.dart';
import 'package:get/get.dart';


class App extends StatelessWidget {
  const App({super.key});

  @override
  Widget build(BuildContext context) {
    return GetMaterialApp(
      debugShowCheckedModeBanner: false,
      home: LoginPage(),
      theme: ThemeData(
        colorScheme: ColorScheme.fromSwatch().copyWith(
          primary: ColorPalette.mainColor,
          secondary: ColorPalette.mainColor
        ),
      ),
    );
  }
}

void main() {
  runApp(const App());
}
