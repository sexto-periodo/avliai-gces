import 'package:flutter/material.dart';
import 'package:front_mobile/src/components/text.dart';
import 'package:front_mobile/src/login_page.dart';

import 'components/button.dart';
import 'components/color_palette.dart';

class userRegistration extends StatelessWidget {
  const userRegistration({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: Registration(),
    );
  }
}

class Registration extends StatelessWidget {
  Registration({super.key});
  final TextEditingController emailController = TextEditingController();
  final TextEditingController passwordController = TextEditingController();
  final TextEditingController RepeatpasswordController =
      TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Cadastro de usuário'),
        backgroundColor: ColorPalette.mainColor,
        leading: IconButton(
          icon: const Icon(
            Icons.arrow_back,
            color: Colors.white,
          ),
          onPressed: () {
            Navigator.push(
              context,
              MaterialPageRoute(builder: (context) => const LoginPage()),
            );
          },
        ),
      ),
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
                      color: ColorPalette.mainColor,
                      fontSize: 35,
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                ),
                const SizedBox(height: 50),
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
                  controller: RepeatpasswordController,
                  texto: 'Senha',
                  obscure: false,
                  textInputType: TextInputType.emailAddress,
                ),
                const SizedBox(
                  height: 10,
                ),
                text(
                    controller: passwordController,
                    texto: 'Repita a senha',
                    textInputType: TextInputType.text,
                    obscure: true),
                const SizedBox(
                  height: 10,
                ),
                const buttonRegistration(),
                const SizedBox(
                  height: 5,
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
