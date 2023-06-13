import 'package:flutter/material.dart';
import 'package:front_mobile/src/_subject.dart';
import 'package:front_mobile/src/subject_page.dart';

import 'color_palette.dart';

class SubjectCard extends StatelessWidget {
  const SubjectCard({super.key, required this.subject});

  final Subject subject;

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
        onTap: () {
          Navigator.push(
              context,
              MaterialPageRoute(
                  builder: (context) => SubjectPage(subject: subject)));
        },
        child: Card(
            color: ColorPalette.mainColor,
            child: Padding(
              padding: EdgeInsets.all(10.0),
              child: Row(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: <Widget>[
                  Expanded(
                    flex: 4,
                    child: ListTile(
                      leading: Icon(
                        Icons.subject,
                        size: 40,
                      ),
                      title: Text(subject.name,
                          style: const TextStyle(
                              fontSize: 16, color: Colors.white)),
                      subtitle: Text(subject.campus, style: const TextStyle(color: Colors.white70),),
                    ),
                  ),
                  Text(subject.score.toString(),
                      style: const TextStyle(fontSize: 36, color: Colors.white)),
                ],
              ),
            )));
  }
}
