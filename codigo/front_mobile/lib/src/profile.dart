// ignore_for_file: dead_code

import 'package:flutter/material.dart';
import 'package:front_mobile/src/components/color_palette.dart';
import 'api/base_api.dart';

import 'package:flutter_secure_storage/flutter_secure_storage.dart';

import 'components/review_card.dart';
const storage = FlutterSecureStorage();

class Profile extends StatefulWidget {
  const Profile({super.key});

  @override
  State<Profile> createState() => _ProfileState();
}

class _ProfileState extends State<Profile> {
  Future<User>? futureUser;
  late Future<List<Review>> futureReviews;

  @override
  void initState() {
    super.initState();
    futureUser = getUserInfo();
    futureReviews = fetchReviews();
  }

  Future<User> getUserInfo() async {
    String? userString = await storage.read(key: 'loggedUser');

    return User.deserialize(userString!);
  }

  Future<List<Review>> fetchReviews() async{
    String? authToken = await storage.read(key: 'userAuth');

    return await ReviewApi().getReviewsByUser(authToken!);

  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Perfil'), automaticallyImplyLeading: false),
      body: Container(
          padding: const EdgeInsets.only(left: 15, top: 20, right: 15),
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
                                return profileHeader(futureUser!);
                              }
                              return ReviewCard(review: reviews[index - 1], hasVote: false,);
                            },
                          ),
                        )
                      ],  
                    );
                  }
                })
          ),
    );
  }
}

Widget profileHeader(Future<User> futureUser) {
  return Container(
    child: FutureBuilder<User>(
                  future: futureUser,
                  builder: (BuildContext context, snapshot) {

                    if (snapshot.connectionState == ConnectionState.waiting) {
                      return const CircularProgressIndicator();
                    } else {
                      final loggedUser = snapshot.data;
                     return Column(
                        children:  [
                          SizedBox(height: 50),
                          CircleAvatar(
                            backgroundImage: NetworkImage(
                                'https://placehold.co/400x400.png'),
                            radius: 80,
                            backgroundColor: ColorPalette.mainColor,
                          ),
                          SizedBox(height: 20),
                          Text(
                            '${loggedUser?.firstname} ${loggedUser?.lastname}',
                            style: TextStyle(fontSize: 28, fontWeight: FontWeight.bold),
                          ),
                          SizedBox(height: 12),
                          Text(
                            '${loggedUser?.email}',
                            style: TextStyle(fontSize: 18),
                          ),
                        ]
                      );
                    }
                  },
                ),
  );
}
