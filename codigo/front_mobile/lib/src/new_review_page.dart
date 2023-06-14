import 'package:flutter/material.dart';
import 'package:flutter_rating_bar/flutter_rating_bar.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:front_mobile/src/_subject.dart';
import 'package:front_mobile/src/components/color_palette.dart';
import 'package:front_mobile/src/subject_page.dart';
import 'package:http/http.dart' as http;

import 'api/base_api.dart';



// ignore: constant_identifier_names
enum Score { ONE, TWO, THREE, FOUR, FIVE }
const storage = FlutterSecureStorage();

class NewReviewPage extends StatefulWidget {
  final String userAuth;
  final Subject subject;
  const NewReviewPage({super.key, required this.subject, required this.userAuth});

  @override
  State<NewReviewPage> createState() => _NewReviewPageState();
}

class _NewReviewPageState extends State<NewReviewPage> {
  late User user;
  late Subject subject;
  late String userAuth;
  Score selectedScore = Score.ONE;
  TextEditingController reviewTextController = TextEditingController();

  @override
  void initState() {
    super.initState();
    subject = widget.subject;
    userAuth = widget.userAuth;
    getUserInfo();
  }

  Future<void> getUserInfo() async {
    String? userString = await storage.read(key: 'loggedUser');

    setState(() {
      user = User.deserialize(userString!);
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text('Avaliar ${subject.name}'),
          leading: Builder(
    builder: (BuildContext context) {
      return IconButton(
        icon: const Icon(Icons.arrow_back),
        onPressed: () { Navigator.pop(context, true); },
      );
    },
    )),
        body: Padding(
        padding: EdgeInsets.all(16.0),
        child: Column(
          children: [
             SizedBox(
                    width: 300,
                    child: Image.network('https://placehold.co/600x400.png'),
                  ),
                  SizedBox(height: 20.0),
            RatingBar.builder(
              initialRating: selectedScore.index.toDouble() + 1,
              minRating: 1,
              direction: Axis.horizontal,
              allowHalfRating: false,
              itemCount: 5,
              itemSize: 50.0,
              itemBuilder: (context, _) => Icon(
                Icons.star,
                color: ColorPalette.mainColor,
              ),
              onRatingUpdate: (rating) {
                setState(() {
                  selectedScore = Score.values[rating.toInt() - 1];
                });
              },
            ),
            SizedBox(height: 16.0),
            TextField(
              keyboardType: TextInputType.multiline,
              maxLines: 4,
              controller: reviewTextController,
              decoration: InputDecoration(
                labelText: 'Comentários',
                focusedBorder: OutlineInputBorder(
                            borderSide: BorderSide(width: 1)
                         )
              ),
            ),
            SizedBox(height: 16.0),
            ElevatedButton(
              onPressed: () {
                  ReviewForm reviewForm = ReviewForm(reviewText: reviewTextController.text, score: selectedScore.toString().split('.').last.toUpperCase(), userHashId: user.hashId, subjectHashId: subject.hashId, universityHashId: subject.universityHashId, courseHashId: subject.courseHashId);

                handleSumbmit(reviewForm);
              },
              child: Text('Enviar'),
            ),
          ],
        ),
      ),
    );
    
  }


  void handleSumbmit(ReviewForm form) async{
    final http.Response response = await ReviewApi().addReview(form, userAuth);

    if (response.statusCode == 200) {
      // Review submitted successfully
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(content: Text('Avaliação feita com sucesso')),
      );

      Navigator.pop(context, true);
    } else {
      // Error occurred while submitting review
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(content: Text('Erro ao enviar avaliação'),),
      );
  }
}}
