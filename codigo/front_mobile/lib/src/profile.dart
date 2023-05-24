import 'package:flutter/material.dart';
import 'package:front_mobile/src/components/color_palette.dart';
import 'package:front_mobile/src/home_page.dart';

class Profile extends StatefulWidget {
  const Profile({super.key});

  @override
  State<Profile> createState() => _ProfileState();
}

class _ProfileState extends State<Profile> {
  @override
  /*Widget build(BuildContext context) {
    return const Scaffold(
      child: Column(mainAxisAlignment: MainAxisAlignment.center,
      children: [
        Center(
          child: CircleAvatar(radius: 80),
        )
      ]),
    );
  }
}
*/
  bool isObscurePassword = true;
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Perfil'),
        backgroundColor: ColorPalette.mainColor,
        leading: IconButton(
          icon: const Icon(
            Icons.arrow_back,
            color: Colors.white,
          ),
          onPressed: () {
            Navigator.push(
              context,
              MaterialPageRoute(builder: (context) => const HomePage()),
            );
          },
        ),
      ),
      body: Container(
        padding: const EdgeInsets.only(left: 15, top: 20, right: 15),
        child: GestureDetector(
          onTap: () {
            FocusScope.of(context).unfocus();
          },
          child: ListView(
            children: [
              Center(
                child: Stack(
                  children: [
                    Container(
                      width: 130,
                      height: 130,
                      decoration: BoxDecoration(
                          border: Border.all(width: 4, color: Colors.white),
                          boxShadow: [
                            BoxShadow(
                                spreadRadius: 2,
                                blurRadius: 10,
                                color: Colors.black.withOpacity(0.1)),
                          ],
                          shape: BoxShape.circle,
                          image: const DecorationImage(
                              fit: BoxFit.cover,
                              image: NetworkImage(
                                  'https://i1.rgstatic.net/ii/profile.image/272734387175449-1442036353375_Q512/Zenilton-Patrocinio-Jr.jpg'))),
                    ),
                    const Positioned(
                      bottom: 0,
                      right: 0,
                      child: SizedBox(
                        height: 40,
                        width: 40,
                      ),
                    )
                  ],
                ),
              ),
              const SizedBox(
                height: 30,
              ),
              buildTextField("Nome", "Zenilton", false),
              buildTextField("Email", "@pucminas.com.br", false),
              buildTextField(
                  "Ultimas avaliações ", "Usuário sem avaliações", false),
            ],
          ),
        ),
      ),
    );
  }

  Widget buildTextField(
      String labelText, String placeHolder, bool ispassworld) {
    return Padding(
        padding: const EdgeInsets.only(bottom: 30),
        child: TextField(
          obscureText: ispassworld ? isObscurePassword : false,
          decoration: InputDecoration(
              suffixIcon: ispassworld
                  ? IconButton(
                      onPressed: () {},
                      icon: const Icon(
                        Icons.remove_red_eye,
                        color: Colors.grey,
                      ))
                  : null,
              enabled: false,
              contentPadding: const EdgeInsets.only(bottom: 5),
              labelText: labelText,
              floatingLabelBehavior: FloatingLabelBehavior.always,
              hintText: placeHolder,
              hintStyle: const TextStyle(
                  fontSize: 16,
                  fontWeight: FontWeight.bold,
                  color: Colors.grey)),
        ));
  }
}
