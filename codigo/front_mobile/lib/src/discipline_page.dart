import 'package:flutter/material.dart';
import 'package:front_mobile/src/Discipline.dart';

class DisciplinePage extends StatelessWidget {
  const DisciplinePage({super.key, required this.discipline});

  final Discipline discipline;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(discipline.name),
      ),
      body: Container(
        padding: const EdgeInsets.all(12),
        child: Row(
          children: <Widget>[
            SizedBox(
              width: 200,
              child: Image.network(discipline.imgUrl),
            ),
            Padding(
              padding: const EdgeInsets.only(left: 50),
              child: Text(
                discipline.score.toString(),
                style: const TextStyle(fontSize: 48),
                textAlign: TextAlign.center,
              ),
            )
          ],
        ),
      ),
    );
  }
}
