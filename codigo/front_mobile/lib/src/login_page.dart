import 'package:flutter/material.dart';
import 'package:front_mobile/src/components/appbar_widget.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:front_mobile/src/api/base_api.dart';
import 'package:front_mobile/src/home_page.dart';
import 'package:front_mobile/src/userRegistration.dart';

import 'components/color_palette.dart';

const storage = FlutterSecureStorage();

class LoginPage extends StatefulWidget {
  const LoginPage({super.key});

  @override
  State<LoginPage> createState() => _LoginPageState();
}

class _LoginPageState extends State<LoginPage> {
  final TextEditingController _emailController = TextEditingController();
  final TextEditingController _passwordController = TextEditingController();

  String _email = '';
  String _password = '';

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
                      color: ColorPalette.mainColor,
                      fontSize: 35,
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                ),
                const SizedBox(height: 50),
                Center(
                  child: Text(
                    'Insira suas informações para entrar no AvaliAí',
                    style: TextStyle(
                      color: ColorPalette.textColor,
                      fontSize: 16,
                      fontWeight: FontWeight.w500,
                    ),
                  ),
                ),
                const SizedBox(
                  height: 20,
                ),
                TextFormField(
                  controller: _emailController,
                  decoration: const InputDecoration(
                    labelText: 'Email Acadêmico',
                    border: OutlineInputBorder(),
                  ),
                  onChanged: (value) {
                    setState(() {
                      _email = value;
                    });
                  },
                ),
                const SizedBox(
                  height: 10,
                ),
                TextFormField(
                  controller: _passwordController,
                  obscureText: true,
                  decoration: const InputDecoration(
                    labelText: 'Senha',
                    border: OutlineInputBorder(),
                  ),
                  onChanged: (value) {
                    setState(() {
                      _password = value;
                    });
                  },
                ),
                const SizedBox(
                  height: 10,
                ),
                Container(
                    alignment: Alignment.center,
                    child: SizedBox(
                      width: double.infinity,
                      height: 50,
                      child: ElevatedButton(
                          onPressed: () => onLoginButtonPressed(context),
                          style: ElevatedButton.styleFrom(
                              backgroundColor: ColorPalette.mainColor,
                              shape: RoundedRectangleBorder(
                                borderRadius: BorderRadius.circular(100),
                              )),
                          child: const Text(
                            'Entrar',
                            style: TextStyle(
                              color: Colors.white,
                              fontWeight: FontWeight.w600,
                              fontSize: 24,
                            ),
                          )),
                    )),
                const SizedBox(
                  height: 5,
                ),
                Row(
                  mainAxisAlignment: MainAxisAlignment.end,
                  children: [
                    TextButton(
                      onPressed: () {
                        Navigator.push(
                          context,
                          MaterialPageRoute(
                              builder: (context) => const UserRegistration()),
                        );
                      },
                      child: Text("Criar nova conta",
                          style: TextStyle(color: ColorPalette.mainColor)),
                    )
                  ],
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }

  void onLoginButtonPressed(BuildContext context) async {
    UserAuth authTokens = await AuthApi().login(_email, _password);
    String userToken = authTokens.access_token.toString();
    if (authTokens != null) {
      storage.write(key: "userAuth", value: userToken);
      Navigator.push(
          context,
          MaterialPageRoute(
              builder: (context) => AppBarWidget.fromBase64(userToken)));
    } else {
      showDialog(
          context: context,
          builder: (context) => const AlertDialog(
              title: Text('Erro'),
              content: Text(
                  'Usuário não encontrado. Verique se o email e senha estão corretos.')));
    }
  }
}

