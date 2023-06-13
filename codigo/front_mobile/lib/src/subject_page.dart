import 'package:flutter/material.dart';
import 'package:front_mobile/src/_subject.dart';

import 'components/color_palette.dart';

class SubjectPage extends StatelessWidget {
  const SubjectPage({super.key, required this.subject});

  final Subject subject;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(subject.name),
         backgroundColor: ColorPalette.mainColor
      ),
      body: Container(
        padding: const EdgeInsets.all(12),
        child: Row(
          children: <Widget>[
            // SizedBox(
            //   width: 200,
            //   child: Image.network(subject.picUrl),
            // ),
            Padding(
              padding: const EdgeInsets.only(left: 50),
              child: Text(
                subject.score.toString(),
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
