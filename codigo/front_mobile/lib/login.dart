import 'package:flutter/material.dart';
import 'package:front_mobile/button.dart';
import 'package:front_mobile/cores.dart';
import 'package:front_mobile/text.dart';

class login extends StatelessWidget {
  login({super.key});
  final TextEditingController emailController = TextEditingController();
  final TextEditingController passwordController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SingleChildScrollView(
        child: SafeArea(
          child: Container(
            width: double.infinity,
            padding: const EdgeInsets.all(15.0),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Container(
                  alignment: Alignment.center,
                  child: Text(
                    'Avaliaí',
                    style: TextStyle(
                      color: cores.mainColor,
                      fontSize: 35,
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                ),
                const SizedBox(height: 50),
                Text(
                  'Login to your account',
                  style: TextStyle(
                    color: cores.textColor,
                    fontSize: 16,
                    fontWeight: FontWeight.w500,
                  ),
                ),
                const SizedBox(
                  height: 15,
                ),
                text(
                  controller: emailController,
                  texto: 'Email',
                  obscure: false,
                  textInputType: TextInputType.emailAddress,
                ),
                const SizedBox(
                  height: 10,
                ),
                text(
                    controller: passwordController,
                    texto: 'Password',
                    textInputType: TextInputType.text,
                    obscure: true),
                const SizedBox(
                  height: 10,
                ),
                button(),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
