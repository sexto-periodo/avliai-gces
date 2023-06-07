import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:front_mobile/src/components/subject_card.dart';

import '_subject.dart';

class HomePage extends StatefulWidget {
  
  final String authToken;
  final Map<String, dynamic> payload;
  

 @override
  State<StatefulWidget> createState() {
    return HomePageState();
  }

  const HomePage(this.authToken, this.payload, {super.key});

  factory HomePage.fromBase64(String authToken) =>
    HomePage(
      authToken,
      json.decode(
        ascii.decode(
          base64.decode(base64.normalize(authToken.split(".")[1]))
        )
      )
    );
}

class HomePageState extends State<HomePage> {


  @override
  Widget build(BuildContext context) {
    return const Placeholder();
  }}


// class NewPageScreen extends StatelessWidget {
//   final String texto;

//   NewPageScreen(this.texto);

//   @override
//   Widget build(BuildContext context) {
//     return Container(
//       child: Scaffold(
//         appBar: AppBar(
//           title: Text('Sobre'),
//           backgroundColor: ColorPalette.mainColor,
//           leading: IconButton(
//             icon: Icon(
//               Icons.arrow_back,
//               color: Colors.white,
//             ),
//             onPressed: () {
//               Navigator.push(
//                 context,
//                 MaterialPageRoute(builder: (context) => HomePage()),
//               );
//             },
//           ),
//         ),
//         body: Column(
//           mainAxisAlignment: MainAxisAlignment.start,
//           children: [
//             const SizedBox(
//               height: 70,
//             ),
//             Expanded(
//               child: Align(
//                 alignment: Alignment.topCenter,
//                 child: Text(
//                   'AvaliAí',
//                   style: TextStyle(fontSize: 50),
//                 ),
//               ),
//             ),
//             Align(
//               alignment: Alignment.bottomLeft,
//               child: Padding(
//                 padding: EdgeInsets.only(
//                     bottom: 0), // Ajuste o valor conforme necessário
//                 child: Column(
//                   children: [
//                     Text(
//                       '      O AvaliAí busca dar aos alunos, inicialmente, do curso de engenharia de software uma maneira de avaliar, discutir sobre e criticar matérias disponibilizadas pelo curso. Visando promover um maior entendimento sobre as disciplinas existentes, principalmente para optativas, das quais muitas não têm relação direta com nossa área. Além disso, os dados acumulados poderiam, posteriormente, serem disponibilizados para uso da instituição que poderia utilizar para quaisquer fins desejados.',
//                       style: TextStyle(fontSize: 20),
//                     ),
//                     Text(
//                       '      Com o desenvolvimento do AvaliAí alunos serão capazes de entender melhor o funcionamento das matérias do seu curso e, consequentemente, poderão se preparar melhor para as mesmas. Além disso, o aplicativo promoverá uma melhor decisão da escolha de matérias optativas a partir das análises de outros estudantes.',
//                       style: TextStyle(fontSize: 20),
//                     ),
//                   ],
//                 ),
//               ),
//             ),
//           ],
//         ),
//       ),
//     );
//   }
// }
