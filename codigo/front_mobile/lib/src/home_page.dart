import 'package:flutter/material.dart';
import 'package:front_mobile/src/components/subject_card.dart';

import '_subject.dart';

class HomePage extends StatefulWidget {
  const HomePage({super.key});

  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  List<Subject> subjects = [
   
  ];

  List<Subject> filtered = [];

  TextEditingController searchController = TextEditingController();

   @override
  void initState() {
    super.initState();
    filtered = subjects;
  }

  void search(String query) {
    setState(() {
      filtered = subjects.where((object) {
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
              padding: const EdgeInsets.only(top: 12,),
              child: TextField(
                controller: searchController,
                decoration: InputDecoration(
                  hintText: 'Search',
                  prefixIcon: const Icon(Icons.search),
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
                return SubjectCard(
                  subject: filtered[index],
                );
              },
            ),)
          ],
        ),
        ));
  }
}