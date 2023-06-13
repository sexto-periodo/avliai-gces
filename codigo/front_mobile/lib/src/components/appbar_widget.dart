import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:front_mobile/src/home_page.dart';
import 'package:front_mobile/src/info_page.dart';
import 'package:front_mobile/src/login_page.dart';
import 'package:front_mobile/src/new_subj_page.dart';
import 'package:front_mobile/src/profile.dart';

import 'color_palette.dart';

class AppBarWidget extends StatefulWidget {

  final String authToken;
  final Map<String, dynamic> payload;

  const AppBarWidget(this.authToken, this.payload, {super.key});

  @override
  State<StatefulWidget> createState() {
    return AppBarWidgetState();
  }

  factory AppBarWidget.fromBase64(String authToken) =>
    AppBarWidget(
      authToken,
      json.decode(
        ascii.decode(
          base64.decode(base64.normalize(authToken.split(".")[1]))
        )
      )
    );
}

class AppBarWidgetState extends State<AppBarWidget> {
  int _currentIndex = 0;
  
 late String token;

 @override
  void initState() {
    super.initState();
    token = widget.authToken;
  }


  final List<Widget> _telas = [HomePage(), NewSubjPage(), InfoPage(), Profile()];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: _telas[_currentIndex],
      bottomNavigationBar: NavigationBar(
        destinations: const [
          NavigationDestination(icon: Icon(Icons.home), label: 'Home'),
          NavigationDestination(icon: Icon(Icons.library_add), label: 'Requisitar'),
          NavigationDestination(icon: Icon(Icons.info), label: 'Info'),
          NavigationDestination(icon: Icon(Icons.person),label: 'Usuário')
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