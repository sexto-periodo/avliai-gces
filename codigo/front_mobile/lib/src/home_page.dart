import 'package:flutter/material.dart';
import 'package:flutter/src/widgets/framework.dart';
import 'package:flutter/src/widgets/placeholder.dart';
import 'package:material_floating_search_bar/material_floating_search_bar.dart';

class HomePage extends StatefulWidget {
  const HomePage({super.key});

  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      resizeToAvoidBottomInset: false,
    body: Stack(
      fit: StackFit.expand,
      children: [searchBar()],

      )
    );
  }
}

Widget searchBar() {
  
  return FloatingSearchBar(
      hint: 'Pesquisar Disciplinas...',
      openAxisAlignment: 0.0,
      borderRadius: BorderRadius.circular(100),
      width: 350,
      axisAlignment:0.0,
      scrollPadding: EdgeInsets.only(top: 16,bottom: 20),
      elevation: 4.0,
      physics: BouncingScrollPhysics(),
      onQueryChanged: (query){
        //Your methods will be here
      },
      transitionCurve: Curves.easeInOut,
      transitionDuration: Duration(milliseconds: 200),
      transition: CircularFloatingSearchBarTransition(),
      debounceDelay: Duration(milliseconds: 200),
      actions: [
        FloatingSearchBarAction(
          showIfOpened: false,
          child: CircularButton(icon: Icon(Icons.search),
              onPressed: (){
            print('Places Pressed');
              },),
        ),
        FloatingSearchBarAction.searchToClear(
          showIfClosed: false,
        ),
      ],
      builder: (context, transition){
        return ClipRRect(
          borderRadius: BorderRadius.circular(8.0),
          child: Material(
            color: Colors.white,
          )
        );
      },

    );
} 