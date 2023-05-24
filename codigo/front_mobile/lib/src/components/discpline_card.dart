
import 'package:flutter/material.dart';
import 'package:front_mobile/src/Discipline.dart';
import 'package:front_mobile/src/discipline_page.dart';

class DisciplineCard extends StatelessWidget {
  const DisciplineCard({super.key, required this.discipline});

  final Discipline discipline;

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: () {
        Navigator.push(
          context, MaterialPageRoute(builder: (context) => DisciplinePage(discipline: discipline))
        );
      },

      child: Padding(
      padding: const EdgeInsets.symmetric(vertical: 5.0),
      child: Row(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          Expanded(
            flex: 2,
            child: Image.network(discipline.imgUrl),
          ),
          Expanded(
            flex: 3,
            child: ListTile(
              title: Text(discipline.name, style: const TextStyle(fontSize: 18)),
              subtitle: Text(discipline.course),
            ),
          ),
          Text(discipline.score.toString(), style: const TextStyle(fontSize: 36)),
        ],
      ),
    ),
    );
  }
}