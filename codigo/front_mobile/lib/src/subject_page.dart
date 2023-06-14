import 'package:flutter/material.dart';
import 'package:front_mobile/src/_subject.dart';
import 'package:front_mobile/src/api/base_api.dart';
import 'package:front_mobile/src/components/review_card.dart';
import 'package:front_mobile/src/new_review_page.dart';
import 'package:http/http.dart';
import 'package:readmore/readmore.dart';

import 'components/color_palette.dart';

import 'package:flutter_secure_storage/flutter_secure_storage.dart';

const storage = FlutterSecureStorage();

class SubjectPage extends StatefulWidget {
  final VoidCallback onBackButtonPressed;
  final Subject subject;
  const SubjectPage({super.key, required this.subject, required this.onBackButtonPressed});

  @override
  State<SubjectPage> createState() => _SubjectPageState();
}

class _SubjectPageState extends State<SubjectPage> {
  late VoidCallback onBackButtonPressed;
  late Future<List<Review>> futureReviews;
  late String token;

  late Subject subject;

  bool showFab = false;

  @override
  void initState() {
    super.initState();

    subject = widget.subject;
    onBackButtonPressed = widget.onBackButtonPressed;
    getUserAuth();
    futureReviews = fetchReviews();
    checkReview();
  }

  Future<Subject> getSubject() async{
    return await UniversityApi().getSubjectById(token, subject.hashId);
  }

  Future<List<Review>> fetchReviews() async {
    String? authToken = await storage.read(key: 'userAuth');

    return await ReviewApi().getReviewsBySubject(authToken!, subject.hashId);
  }

  Future<void> checkReview() async {
    String? authToken = await storage.read(key: 'userAuth');
    final bool response =
        await ReviewApi().checkIfAlreadyReviwed(authToken!, subject.hashId);

    setState(() {
      showFab = !response;
    });
  }

  Future<void> getUserAuth() async {
    String? authToken = await storage.read(key: 'userAuth');

    setState(() {
      token = authToken! as String;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
          title: Text(subject.name), backgroundColor: ColorPalette.mainColor,
          leading: Builder(builder: (BuildContext context) {
      return IconButton(
        icon: const Icon(Icons.arrow_back),
        onPressed: () { 
          onBackButtonPressed();
          Navigator.pop(context); 
          },
      );
    },),
          ),
      body: Padding(
          padding: const EdgeInsets.only(top: 24.0),
          child: FutureBuilder(
              future: futureReviews,
              builder: (BuildContext context, snapshot) {
                if (snapshot.connectionState == ConnectionState.waiting) {
                  return const Center(
                    child: CircularProgressIndicator(),
                  );
                } else {
                  final List<Review>? reviews = snapshot.data;
                  return Column(
                    mainAxisSize: MainAxisSize.min,
                    children: <Widget>[
                      Expanded(
                        flex: 10,
                        child: ListView.builder(
                          itemCount: reviews!.length + 1,
                          itemBuilder: (context, index) {
                            if (index == 0) {
                              return subjectHeader(subject);
                            }
                            return ReviewCard(
                              review: reviews[index - 1],
                              hasVote: false,
                            );
                          },
                        ),
                      )
                    ],
                  );
                }
              })),
      floatingActionButton: showFab
          ? FloatingActionButton.large(
              onPressed: () async {
                final reLoadPage = await Navigator.push(
                    context,
                    MaterialPageRoute(builder: (context) => NewReviewPage(subject: subject, userAuth: token)));

                if (reLoadPage) {
                  Subject sbj = await getSubject();
                    setState(() {
                    checkReview();
                    futureReviews = fetchReviews();
                    subject = sbj;
                  });
                }
              },
              child: const Icon(Icons.rate_review),
              shape: RoundedRectangleBorder(
                  borderRadius: new BorderRadius.circular(28.0)),
            )
          : null,
    );
  }
}

Widget subjectHeader(Subject subject) {
  return Column(children: [
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
    Padding(
      padding: const EdgeInsets.all(12),
      child: Card(
        child: Padding(
          padding: EdgeInsets.all(10.0),
          child: ReadMoreText(
            subject.longDescription,
            textAlign: TextAlign.justify,
            style: TextStyle(color: Colors.black54),
            trimLines: 5,
            trimMode: TrimMode.Line,
            trimCollapsedText: 'Mostrar mais',
            trimExpandedText: 'Mostrar menos',
            moreStyle: TextStyle(
                fontSize: 14,
                fontWeight: FontWeight.bold,
                color: ColorPalette.mainColor),
            lessStyle: TextStyle(
                fontSize: 14,
                fontWeight: FontWeight.bold,
                color: ColorPalette.mainColor),
          ),
        ),
      ),
    )
  ]);
}
