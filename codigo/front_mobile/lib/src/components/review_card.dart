import 'package:flutter/material.dart';
import 'package:front_mobile/src/_subject.dart';
import 'package:front_mobile/src/api/base_api.dart';
import 'package:http/http.dart' as http;

import 'color_palette.dart';

class ReviewCard extends StatelessWidget {
  final Review review;
  final bool hasVote;

  ReviewCard({super.key, required this.review, required this.hasVote});

  @override
  Widget build(BuildContext context) {
    return Card(
      margin: EdgeInsets.all(14.0),
      child: Padding(
          padding: EdgeInsets.all(10.0),
          child: Column(
            mainAxisAlignment:MainAxisAlignment.start,
            children: [
              ListTile(
                leading: Icon(
                  Icons.account_circle,
                  size: 50,
                ),
                title: Text('${review.firstname} ${review.lastname}',
                    style: const TextStyle(fontSize: 16)),
                    trailing: hasVote ? voteButtons() : null,
              ),
               SizedBox(height: 20),
              Text(review.reviewText, textAlign: TextAlign.justify,)
            ],
          )),
    );
  }
}

Widget voteButtons() {
  return Container(
    width: 110.0,
    child: Row(
      mainAxisAlignment: MainAxisAlignment.center,
      children: [
        IconButton.filled(
            onPressed: () => {}, icon: const Icon(Icons.arrow_upward)),
              Spacer(),
        Text('0'),
         Spacer(),
        IconButton.filled(
            onPressed: () => {}, icon: const Icon(Icons.arrow_downward)),
      ],
    ),
  );
}
