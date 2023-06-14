import 'package:flutter/material.dart';
import 'package:front_mobile/src/_subject.dart';
import 'package:readmore/readmore.dart';

import 'components/color_palette.dart';

class SubjectPage extends StatelessWidget {
  const SubjectPage({super.key, required this.subject});

  final Subject subject;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
            title: Text(subject.name), backgroundColor: ColorPalette.mainColor),
        body: Column(
          children: [
            Container(
              padding: const EdgeInsets.all(12),
              child: Row(
                children: <Widget>[
                  SizedBox(
                    width: 200,
                    child: Image.network('https://placehold.co/600x400.png'),
                  ),
                  Padding(
                    padding: const EdgeInsets.only(left: 50),
                    child: Text(
                      subject.score.toString(),
                      style: const TextStyle(fontSize: 48),
                      textAlign: TextAlign.center,
                    ),
                  ),
                ],
              ),
            ),
            SizedBox(height: 5),
            Padding(padding: const EdgeInsets.all(12),
            child: Card(
              child: Padding(padding: EdgeInsets.all(10.0), child: 
              ReadMoreText(
                subject.longDescription,
              textAlign: TextAlign.justify,
              style: TextStyle(color: Colors.black54),
              trimLines: 5,
              trimMode: TrimMode.Line,
              trimCollapsedText: 'Mostrar mais',
              trimExpandedText: 'Mostrar menos',
              moreStyle: TextStyle(fontSize: 14, fontWeight: FontWeight.bold, color: ColorPalette.mainColor),
              lessStyle: TextStyle(fontSize: 14, fontWeight: FontWeight.bold, color: ColorPalette.mainColor),
            ),
            ),
            )
        )],
        ),
        floatingActionButton: FloatingActionButton.large(
        onPressed: () {
          // Add your onPressed code here!
        },
        child: const Icon(Icons.rate_review),
        shape: RoundedRectangleBorder(
          borderRadius: new BorderRadius.circular(28.0)
        ),
      ),
        
        );
  }
}
