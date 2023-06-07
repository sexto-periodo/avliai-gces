import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:front_mobile/src/components/subject_card.dart';

import '_subject.dart';

class HomePage extends StatefulWidget {
  
  // final String authToken;
  // final Map<String, dynamic> payload;
  

 @override
  State<StatefulWidget> createState() {
    return HomePageState();
  }

  // const HomePage(this.authToken, this.payload, {super.key});
  const HomePage( {super.key});

  // factory HomePage.fromBase64(String authToken) =>
  //   HomePage(
  //     authToken,
  //     json.decode(
  //       ascii.decode(
  //         base64.decode(base64.normalize(authToken.split(".")[1]))
  //       )
  //     )
  //   );
}

class HomePageState extends State<HomePage> {
List<Subject> subjects = [
   Subject('840f122c4f99f0eac12c6a5d564d24ce', 'Cálculo II', 'https://static.wikia.nocookie.net/demi-lovato/images/4/43/Fall_out_boy.jpg/revision/latest?cb=20151016175854', 'Praça da Liberdade', '1f061de68a7a0da8378fd30974dd1a98', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent pretium lorem vitae libero vulputate, sed gravida arcu eleifend. Phasellus euismod tristique malesuada. Vestibulum quis lacus vitae ligula consectetur fringilla', ' Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent pretium lorem vitae libero vulputate, sed gravida arcu eleifend. Phasellus euismod tristique malesuada. Vestibulum quis lacus vitae ligula consectetur fringilla. Nullam suscipit elit at enim tempus, sit amet volutpat tortor maximus. Mauris non lacinia nulla. Donec consequat ligula eu aliquet vestibulum. Fusce suscipit, mi id blandit consectetur, erat velit bibendum ante, vitae interdum quam neque vel nunc. Quisque ac nunc vitae nisi pellentesque pretium. Nulla facilisi. In ut metus vel lectus tristique feugiat at eu lectus. Ut consequat condimentum justo, a consectetur nunc. In bibendum consectetur enim at egestas. Sed tristique sagittis mauris, a rhoncus ex lacinia vel. Nulla facilisi. Sed facilisis iaculis ante sed ultrices. Integer ut justo ut elit vestibulum cursus. Fusce sed eros ac risus fermentum vulputate. Vivamus mollis massa in fermentum lobortis. Cras aliquam felis a magna pulvinar, nec aliquet arcu iaculis. Morbi cursus est in orci fermentum, sit amet pulvinar diam interdum.', 0),
   Subject('ae51f98c55f6f9f00670e71b14ff51b5', 'Modelagem de Processos de Negócios', 'https://static.wikia.nocookie.net/demi-lovato/images/4/43/Fall_out_boy.jpg/revision/latest?cb=20151016175854', 'Praça da Liberdade', '1f061de68a7a0da8378fd30974dd1a98', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent pretium lorem vitae libero vulputate, sed gravida arcu eleifend. Phasellus euismod tristique malesuada. Vestibulum quis lacus vitae ligula consectetur fringilla', ' Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent pretium lorem vitae libero vulputate, sed gravida arcu eleifend. Phasellus euismod tristique malesuada. Vestibulum quis lacus vitae ligula consectetur fringilla. Nullam suscipit elit at enim tempus, sit amet volutpat tortor maximus. Mauris non lacinia nulla. Donec consequat ligula eu aliquet vestibulum. Fusce suscipit, mi id blandit consectetur, erat velit bibendum ante, vitae interdum quam neque vel nunc. Quisque ac nunc vitae nisi pellentesque pretium. Nulla facilisi. In ut metus vel lectus tristique feugiat at eu lectus. Ut consequat condimentum justo, a consectetur nunc. In bibendum consectetur enim at egestas. Sed tristique sagittis mauris, a rhoncus ex lacinia vel. Nulla facilisi. Sed facilisis iaculis ante sed ultrices. Integer ut justo ut elit vestibulum cursus. Fusce sed eros ac risus fermentum vulputate. Vivamus mollis massa in fermentum lobortis. Cras aliquam felis a magna pulvinar, nec aliquet arcu iaculis. Morbi cursus est in orci fermentum, sit amet pulvinar diam interdum.', 0)
  ];

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
        body: Padding(padding: const EdgeInsets.all(24.0),
        child: Column(
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
              itemCount: subjects.length,
              itemBuilder: (context, index) {
                return SubjectCard(
                  subject: subjects[index],
                );
              },
            ),)
          ],
        ),
        ));
  }
  
  
  }