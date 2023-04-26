import 'package:flutter/material.dart';
import 'package:flutter/src/widgets/framework.dart';
import 'package:flutter/src/widgets/placeholder.dart';
import 'package:front_mobile/src/components/discpline_card.dart';
import 'package:material_floating_search_bar/material_floating_search_bar.dart';

import 'Discipline.dart';

class HomePage extends StatefulWidget {
  const HomePage({super.key});

  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  List<Discipline> disciplines = [
    Discipline('Testes de Software', "Engenharia de Software", 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam ut sagittis sapien. Praesent nibh ipsum, bibendum non tempus eget, consectetur id nunc.',
        'https://placehold.co/400.png', 3.5),
    Discipline(
        'Arquitetura de Software',
        "Engenharia de Software",
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam ut sagittis sapien. Praesent nibh ipsum, bibendum non tempus eget, consectetur id nunc.',
        'https://placehold.co/400.png', 4),
    Discipline('Trabalho Interdisciplinar', "Engenharia de Software", 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam ut sagittis sapien. Praesent nibh ipsum, bibendum non tempus eget, consectetur id nunc.',
        'https://placehold.co/400.png', 2.4),
    Discipline('Matéria Genérica de Placeholder', "Engenharia de Software", 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam ut sagittis sapien. Praesent nibh ipsum, bibendum non tempus eget, consectetur id nunc.',
        'https://placehold.co/400.png', 5),
  ];

  List<Discipline> filtered = [];

  TextEditingController searchController = TextEditingController();

   @override
  void initState() {
    super.initState();
    filtered = disciplines;
  }

  void search(String query) {
    setState(() {
      filtered = disciplines.where((object) {
        final nameLower = object.name.toLowerCase();
        final descriptionLower = object.course.toLowerCase();
        final searchLower = query.toLowerCase();

        return nameLower.contains(searchLower) ||
            descriptionLower.contains(searchLower);
      }).toList();
    });
  }
  
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        resizeToAvoidBottomInset: false,
        body: Padding(padding: const EdgeInsets.all(24.0),
        child: Column(
          mainAxisSize: MainAxisSize.min,
          children: <Widget>[
             Container(
              padding: EdgeInsets.only(top: 12,),
              child: TextField(
                controller: searchController,
                decoration: InputDecoration(
                  hintText: 'Search',
                  prefixIcon: Icon(Icons.search),
                    border: OutlineInputBorder(
                    borderRadius: BorderRadius.circular(30))
                ),
                onChanged: (query) {
                  search(query);
                },
              ),
            ),
            Expanded(
              flex: 10,
              child: ListView.builder(
              itemCount: filtered.length,
              itemBuilder: (context, index) {
                return DisciplineCard(
                  discipline: filtered[index],
                );
              },
            ),)
          ],
        ),
        ));
  }
}
