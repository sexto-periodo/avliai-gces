import 'package:flutter/material.dart';
import 'package:front_mobile/profile.dart';

class HomePage extends StatefulWidget {
  const HomePage({super.key});

  @override
  State<StatefulWidget> createState() {
    return HomePageState();
  }
}

class HomePageState extends State<HomePage> {
  int _currentIndex = 0;
  static const List<Widget> _telas = [
    NewWidget(),
    Profile()];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: _telas[_currentIndex],
      bottomNavigationBar: NavigationBar(
        destinations: const [
          NavigationDestination(icon: Icon(Icons.home), label: 'Home'),
          NavigationDestination(
            icon: Icon(Icons.person),
            label: 'Profile',
          )
        ], selectedIndex: _currentIndex,
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
      child: Center(
        child: Text(texto),
      ),
    );
  }
}