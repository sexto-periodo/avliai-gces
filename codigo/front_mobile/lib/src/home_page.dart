import 'package:flutter/material.dart';
import 'package:front_mobile/src/profile.dart';
import 'package:path/path.dart';
import 'package:sqflite/sqflite.dart';

class HomePage extends StatefulWidget {
  const HomePage({super.key});

  @override
  State<StatefulWidget> createState() {
    return HomePageState();
  }
}

class HomePageState extends State<HomePage> {
  int _currentIndex = 0;
  static const List<Widget> _telas = [NewWidget(), Profile()];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: _telas[_currentIndex],
      bottomNavigationBar: NavigationBar(
        destinations: const [
          NavigationDestination(icon: Icon(Icons.home), label: 'Home'),
          NavigationDestination(
            icon: Icon(Icons.person),
            label: 'Perfil',
          )
        ],
        selectedIndex: _currentIndex,
        onDestinationSelected: onTabTapped,
      ),
    );
    throw UnimplementedError();
  }

  void onTabTapped(int index) {
    setState(() {
      _currentIndex = index;
    });
  }
}

class NewWidget extends StatelessWidget {
  const NewWidget({
    super.key,
  });

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text(
              'AvaliAí',
              textDirection: TextDirection.ltr,
              style: TextStyle(fontSize: 50),
            ),
            ElevatedButton(
              child: Text("Listar todos os cadastro"),
              onPressed: () {
                _loginTeste();
              },
            ),
          ],
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

  _loginTeste() async {
    Database bd = await _recuperarBancoDados();
    String sql = "SELECT * FROM usuarios";
    //String sql = "SELECT * FROM usuarios WHERE idade=58";
    //String sql = "SELECT * FROM usuarios WHERE idade >=30 AND idade <=58";
    //String sql = "SELECT * FROM usuarios WHERE idade BETWEEN 18 AND 58";
    //String sql = "SELECT * FROM usuarios WHERE nome='Maria Silva'";
    List usuarios =
        await bd.rawQuery(sql); //conseguimos escrever a query que quisermos
    for (var usu in usuarios) {
      print(" id: " +
          usu['id'].toString() +
          " email: " +
          usu['email'] +
          " senha: " +
          usu['senha'].toString());
    }
  }
}

class NewPageScreen extends StatelessWidget {
  final String texto;

  NewPageScreen(this.texto);

  @override
  Widget build(BuildContext context) {
    return Container(
      child: Center(
        child: Text(texto),
      ),
    );
  }
}
