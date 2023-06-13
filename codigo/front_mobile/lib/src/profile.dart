// ignore_for_file: dead_code

import 'package:flutter/material.dart';
import 'package:front_mobile/src/components/color_palette.dart';

import 'package:flutter_secure_storage/flutter_secure_storage.dart';

import 'api/base_api.dart';

const storage = FlutterSecureStorage();

class Profile extends StatefulWidget {
  const Profile({super.key});

  @override
  State<Profile> createState() => _ProfileState();
}

class _ProfileState extends State<Profile> {
  Future<User>? futureUser;

  @override
  void initState() {
    super.initState();
    futureUser = getUserInfo();
  }

  Future<User> getUserInfo() async {
    String? userString = await storage.read(key: 'loggedUser');

    return User.deserialize(userString!);
  }

  @override
  // ignore: dead_code, dead_code
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
          padding: const EdgeInsets.only(left: 15, top: 20, right: 15),
          child: GestureDetector(
            onTap: () {
              FocusScope.of(context).unfocus();
            },
            child: Center(
              child:  FutureBuilder<User>(
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
                                'https://images.unsplash.com/photo-1605559911160-a3d95d213904'),
                            radius: 80,
                          ),
                          SizedBox(height: 20),
                          Text(
                            '${loggedUser?.firstname} ${loggedUser?.lastname}',
                            style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold),
                          ),
                          SizedBox(height: 20),
                        ]
                      );
                    }
                  },
                )
            ),
          ),
    ));
  }
}
