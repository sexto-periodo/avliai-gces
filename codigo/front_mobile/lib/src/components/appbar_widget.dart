import 'package:flutter/material.dart';
import 'package:front_mobile/src/home_page.dart';
import 'package:front_mobile/src/info_page.dart';
import 'package:front_mobile/src/new_subj_page.dart';
import 'package:front_mobile/src/profile.dart';

class AppBarWidget extends StatefulWidget {
  const AppBarWidget({super.key});

  @override
  State<StatefulWidget> createState() {
    return AppBarWidgetState();
  }
}

class AppBarWidgetState extends State<AppBarWidget> {
  int _currentIndex = 0;
  static const List<Widget> _telas = [HomePage(), NewSubjPage(), InfoPage(), Profile()];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: _telas[_currentIndex],
      bottomNavigationBar: NavigationBar(
        destinations: const [
          NavigationDestination(icon: Icon(Icons.home), label: ''),
          NavigationDestination(icon: Icon(Icons.library_add), label: ''),
          NavigationDestination(icon: Icon(Icons.info), label: ''),
          NavigationDestination(icon: Icon(Icons.person),label: '')
        ],
        selectedIndex: _currentIndex,
        onDestinationSelected: onTabTapped,
      ),
    );
  }

  void onTabTapped(int index) {
    setState(() {
      _currentIndex = index;
    });
  }
}