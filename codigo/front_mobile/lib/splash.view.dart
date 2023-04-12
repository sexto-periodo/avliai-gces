import 'dart:async';

import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:front_mobile/cores.dart';
import 'package:front_mobile/login.dart';

class splashView extends StatelessWidget {
  const splashView({super.key});

  @override
  Widget build(BuildContext context) {
    Timer(const Duration(seconds: 4), () {
      Get.to(login());
    });
    return Scaffold(
        backgroundColor: cores.mainColor,
        body: const Center(
          child: Text(
            'Avaliaí',
            style: TextStyle(
                color: Colors.white, fontSize: 30, fontWeight: FontWeight.bold),
          ),
        ));
  }
}
