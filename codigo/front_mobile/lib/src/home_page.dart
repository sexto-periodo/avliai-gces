import 'package:flutter/material.dart';
import 'package:front_mobile/src/profile.dart';

import 'components/color_palette.dart';

class HomePage extends StatefulWidget {
  const HomePage({super.key});

  @override
  State<StatefulWidget> createState() {
    return HomePageState();
  }
}

class HomePageState extends State<HomePage> {
  int _currentIndex = 0;
  static List<Widget> get _telas =>
      [NewWidget(), Profile(), NewPageScreen('Sobre')];

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
          ),
          NavigationDestination(icon: Icon(Icons.info), label: 'Sobre')
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
    return const Center(
        child: Text(
      'AvaliAí',
      textDirection: TextDirection.ltr,
      style: TextStyle(fontSize: 50),
    ));
  }
}

class NewPageScreen extends StatelessWidget {
  final String texto;

  NewPageScreen(this.texto);

  @override
  Widget build(BuildContext context) {
    return Container(
      child: Scaffold(
        appBar: AppBar(
          title: Text('Sobre'),
          backgroundColor: ColorPalette.mainColor,
          leading: IconButton(
            icon: Icon(
              Icons.arrow_back,
              color: Colors.white,
            ),
            onPressed: () {
              Navigator.push(
                context,
                MaterialPageRoute(builder: (context) => HomePage()),
              );
            },
          ),
        ),
        body: Column(
          mainAxisAlignment: MainAxisAlignment.start,
          children: [
            const SizedBox(
              height: 70,
            ),
            Expanded(
              child: Align(
                alignment: Alignment.topCenter,
                child: Text(
                  'AvaliAí',
                  style: TextStyle(fontSize: 50),
                ),
              ),
            ),
            Align(
              alignment: Alignment.bottomLeft,
              child: Padding(
                padding: EdgeInsets.only(
                    bottom: 0), // Ajuste o valor conforme necessário
                child: Column(
                  children: [
                    Text(
                      '      O AvaliAí busca dar aos alunos, inicialmente, do curso de engenharia de software uma maneira de avaliar, discutir sobre e criticar matérias disponibilizadas pelo curso. Visando promover um maior entendimento sobre as disciplinas existentes, principalmente para optativas, das quais muitas não têm relação direta com nossa área. Além disso, os dados acumulados poderiam, posteriormente, serem disponibilizados para uso da instituição que poderia utilizar para quaisquer fins desejados.',
                      style: TextStyle(fontSize: 20),
                    ),
                    Text(
                      '      Com o desenvolvimento do AvaliAí alunos serão capazes de entender melhor o funcionamento das matérias do seu curso e, consequentemente, poderão se preparar melhor para as mesmas. Além disso, o aplicativo promoverá uma melhor decisão da escolha de matérias optativas a partir das análises de outros estudantes.',
                      style: TextStyle(fontSize: 20),
                    ),
                  ],
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
