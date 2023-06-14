import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:front_mobile/src/components/appbar_widget.dart';
import 'package:front_mobile/src/components/color_palette.dart';
import 'package:front_mobile/src/login_page.dart';
import 'package:get/get.dart';

import 'package:flutter_dotenv/flutter_dotenv.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:front_mobile/src/home_page.dart';



const storage = FlutterSecureStorage();

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await dotenv.load(fileName: '.env');
  SystemChrome.setSystemUIOverlayStyle(SystemUiOverlayStyle(
    systemNavigationBarColor: ColorPalette.mainColor, // navigation bar color
    statusBarColor: ColorPalette.mainColor, // status bar color
  ));
  runApp(const App());
}

class App extends StatelessWidget {
  const App({super.key});

   Future<String> get jwtOrEmpty async {
    var jwt = await storage.read(key: "jwt");
    if(jwt == null) return "";
    return jwt;
  }

  @override
  Widget build(BuildContext context) {
    return GetMaterialApp(
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        colorScheme: ColorScheme.fromSwatch().copyWith(
          primary: ColorPalette.mainColor,
          secondary: ColorPalette.mainColor
        ),
      ),
      home: FutureBuilder(
        future: jwtOrEmpty,
        builder: ((context, snapshot){
          if(!snapshot.hasData) return const CircularProgressIndicator();
           if(snapshot.data != "") {
            var str = snapshot.data;
            var jwt = str!.split(".");

            if(jwt.length !=3) {
              return const LoginPage();
            } else {
              var payload = json.decode(ascii.decode(base64.decode(base64.normalize(jwt[1]))));
              if(DateTime.fromMillisecondsSinceEpoch(payload["exp"]*1000).isAfter(DateTime.now())) {
                return AppBarWidget(str, payload);
              } else {
                return const LoginPage();
              }
            }
          } else {
            return const LoginPage();
          }
        })),
    );
  }
}


