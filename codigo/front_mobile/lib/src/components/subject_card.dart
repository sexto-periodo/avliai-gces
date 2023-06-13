
import 'package:flutter/material.dart';
import 'package:front_mobile/src/_subject.dart';
import 'package:front_mobile/src/subject_page.dart';

class SubjectCard extends StatelessWidget {
  const SubjectCard({super.key, required this.subject});

  final Subject subject;

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: () {
        Navigator.push(
          context, MaterialPageRoute(builder: (context) => SubjectPage(subject: subject))
        );
      },

      child: Card(
        child: Padding(padding: EdgeInsets.all(12.0), child: Row(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          // Expanded(
          //   flex: 2,
          //   child: Image.network(subject.picUrl),
          // ),
          Expanded(
            flex: 3,
            child: ListTile(
              title: Text(subject.name, style: const TextStyle(fontSize: 18)),
              subtitle: Text(subject.campus),
            ),
          ),
          Text(subject.score.toString(), style: const TextStyle(fontSize: 36)),
        ],
      ),)
      )
    );
  }
}