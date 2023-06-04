import 'package:flutter/material.dart';
import 'package:flutter/src/widgets/framework.dart';
import 'package:front_mobile/src/home_page.dart';
import 'package:front_mobile/src/userRegistration.dart';
import 'package:path/path.dart';
import 'package:sqflite/sqflite.dart';

import 'components/color_palette.dart';

class LoginPage extends StatefulWidget {
  const LoginPage({super.key});

  @override
  State<LoginPage> createState() => _LoginPageState();
}

class _LoginPageState extends State<LoginPage> {
  final TextEditingController emailController = TextEditingController();
  final TextEditingController passwordController = TextEditingController();

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
                  controller: emailController,
                  decoration: InputDecoration(
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
                  controller: passwordController,
                  obscureText: true,
                  decoration: InputDecoration(
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
                              builder: (context) => userRegistration()),
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
    await _submit(context);
  }

  Future<void> _submit(BuildContext context) async {
    Database bd = await _recuperarBancoDados();
    String sql = "SELECT * FROM usuarios";

    List usuarios =
        await bd.rawQuery(sql); //conseguimos escrever a query que quisermos
    for (var usu in usuarios) {
      print(" id: " +
          usu['id'].toString() +
          " email: " +
          usu['email'] +
          " senha: " +
          usu['senha'].toString());

      if (usu["email"] == emailController.text &&
          usu["senha"] == passwordController.text) {
        Navigator.push(
          context,
          MaterialPageRoute(builder: (context) => const HomePage()),
        );
        print("usuario encontrado");
      } else {
        print("usuario nao encontrado");
      }
    }
  }
}

Future<Database> _recuperarBancoDados() async {
  final caminhoBancoDados = await getDatabasesPath();
  final localBancoDados = join(caminhoBancoDados, "banco3.bd");
  var bd = await openDatabase(localBancoDados, version: 1,
      onCreate: (db, dbVersaoRecente) {
    String sql =
        "CREATE TABLE usuarios (id INTEGER PRIMARY KEY AUTOINCREMENT, email VARCHAR, senha VARCHAR) ";
    db.execute(sql);
  });
  return bd;
}
