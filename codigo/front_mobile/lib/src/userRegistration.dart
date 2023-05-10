import 'package:flutter/material.dart';
import 'package:flutter/src/widgets/framework.dart';
import 'package:flutter/src/widgets/placeholder.dart';
import 'package:front_mobile/src/home_page.dart';
import 'package:front_mobile/src/components/text.dart';
import 'package:front_mobile/src/login_page.dart';
import 'package:path/path.dart';
import 'package:sqflite/sqflite.dart';
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
        title: Text('Cadastro de usuário'),
        backgroundColor: ColorPalette.mainColor,
        leading: IconButton(
          icon: Icon(
            Icons.arrow_back,
            color: Colors.white,
          ),
          onPressed: () {
            Navigator.push(
              context,
              MaterialPageRoute(builder: (context) => LoginPage()),
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
              children: <Widget>[
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
                  controller: passwordController,
                  texto: 'Senha',
                  obscure: true,
                  textInputType: TextInputType.emailAddress,
                ),
                const SizedBox(
                  height: 10,
                ),
                /* text(
                    controller: passwordController,
                    texto: 'Repita a senha',
                    textInputType: TextInputType.text,
                    obscure: true),
                const SizedBox(
                  height: 10,
                  
                ),
                */
                ElevatedButton(
                    child: Container(
                      alignment: Alignment.center,
                      height: 70,
                      decoration: BoxDecoration(
                        color: Colors.blue,
                        borderRadius: BorderRadius.circular(6),
                        boxShadow: [
                          BoxShadow(
                              color: Colors.black.withOpacity(0.1),
                              blurRadius: 10)
                        ],
                      ),
                      child: const Text(
                        'Confirmar cadastro',
                        style: TextStyle(
                          color: Colors.white,
                          fontWeight: FontWeight.w600,
                        ),
                      ),
                    ),
                    onPressed: () {
                      _salvarDados(
                          emailController.text, passwordController.text);
                      Navigator.push(context,
                          MaterialPageRoute(builder: (context) => LoginPage()));
                    }),
              ],
            ),
          ),
        ),
      ),
    );
  }

  _recuperarBancoDados() async {
    final caminhoBancoDados = await getDatabasesPath();
    final localBancoDados = join(caminhoBancoDados, "banco3.bd");
    var bd = await openDatabase(localBancoDados, version: 1,
        onCreate: (db, dbVersaoRecente) {
      String sql =
          "CREATE TABLE usuarios (id INTEGER PRIMARY KEY AUTOINCREMENT, email VARCHAR, senha VARCHAR) ";
      db.execute(sql);
    });
    return bd;
    //print("aberto: " + bd.isOpen.toString() );
  }

  _salvarDados(String emailController, String passwordController) async {
    Database bd = await _recuperarBancoDados();
    Map<String, dynamic> dadosUsuario = {
      "email": emailController,
      "senha": passwordController
    };
    int id = await bd.insert("usuarios", dadosUsuario);
    print("Salvo: $id ");
  }

  _loginTeste(String email, String senha) async {
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

      if (usu["email"] == email && usu["senha"] == senha) {
        print("usuario encontrado");
      } else {
        print("usuario nao encontrado");
      }
    }
  }
}
