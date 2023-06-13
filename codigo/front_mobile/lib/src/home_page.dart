import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:front_mobile/src/api/base_api.dart';
import 'package:front_mobile/src/components/subject_card.dart';
import 'package:front_mobile/src/login_page.dart';

import '_subject.dart';

const storage = FlutterSecureStorage();

class HomePage extends StatefulWidget {
  // final String authToken;
  // final Map<String, dynamic> payload;

  @override
  State<StatefulWidget> createState() {
    return HomePageState();
  }

  const HomePage({super.key});
}

class HomePageState extends State<HomePage> {
  late Future<List<Subject>> futureFubjects;

  @override
  void initState() {
    super.initState();
    futureFubjects = fetchSubjects();
  }

  Future<List<Subject>>fetchSubjects() async {
    String? authToken = await storage.read(key: 'userAuth');

    return await UniversityApi().getSubjects(authToken!);
  }

  // List<Subject> filtered = [];

  // TextEditingController searchController = TextEditingController();

  //  @override
  // void initState() {
  //   super.initState();
  //   filtered = subjects;
  // }

  // void search(String query) {
  //   setState(() {
  //     filtered = subjects.where((object) {
  //       final nameLower = object.name.toLowerCase();
  //       final descriptionLower = object.course.toLowerCase();
  //       final searchLower = query.toLowerCase();

  //       return nameLower.contains(searchLower) ||
  //           descriptionLower.contains(searchLower);
  //     }).toList();
  //   });
  // }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        resizeToAvoidBottomInset: false,
        body: Padding(
            padding: const EdgeInsets.all(24.0),
            child: FutureBuilder(
                future: futureFubjects,
                builder: (BuildContext context, snapshot) {
                  if (snapshot.connectionState == ConnectionState.waiting) {
                    return const Center(
                      child: CircularProgressIndicator(),
                    );
                  } else {
                    final List<Subject>? subjects = snapshot.data;
                    return Column(
                      mainAxisSize: MainAxisSize.min,
                      children: <Widget>[
                        //  Container(
                        //   padding: const EdgeInsets.only(top: 12,),
                        //   child: TextField(
                        //     controller: searchController,
                        //     decoration: InputDecoration(
                        //       hintText: 'Search',
                        //       prefixIcon: const Icon(Icons.search),
                        //         border: OutlineInputBorder(
                        //         borderRadius: BorderRadius.circular(30))
                        //     ),
                        //     onChanged: (query) {
                        //       search(query);
                        //     },
                        //   ),
                        // ),
                        Expanded(
                          flex: 10,
                          child: ListView.builder(
                            itemCount: subjects?.length,
                            itemBuilder: (context, index) {
                              return SubjectCard(
                                subject: subjects![index],
                              );
                            },
                          ),
                        )
                      ],
                    );
                  }
                })));
  }
}
