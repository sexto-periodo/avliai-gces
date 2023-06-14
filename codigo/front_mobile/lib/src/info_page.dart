import 'package:flutter/material.dart';
import 'package:flutter/src/widgets/framework.dart';
import 'package:flutter/src/widgets/placeholder.dart';

class InfoPage extends StatefulWidget {
  const InfoPage({super.key});

  @override
  State<InfoPage> createState() => _InfoPageState();
}

class _InfoPageState extends State<InfoPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Sobre'), automaticallyImplyLeading: false),
      body: Padding(
          padding: const EdgeInsets.only(left: 20, top: 24, right: 20),
          child: Column(children: [
            SizedBox(
                    width: 600,
                    child: Image.network('https://placehold.co/600x400.png'),
                  ),
                  SizedBox(height: 20),
            Center(child: Text('AvaliAí', style: TextStyle(fontSize: 48))),
            SizedBox(height: 30),
            Text(
                'AvaliAí é uma plataforma de avaliação de disciplinas universitárias. Com AvaliAí, os alunos podem avaliar suas disciplinas no decorrer do semestre, compartilhando sua experiência e fornecendo feedbacks detalhados para as universidades.',
                textAlign: TextAlign.justify),
                SizedBox(height: 10),
                Text('Nosso serviço ainda preza pelo anonimato do usuário, de forma a garantir que o mesmo consiga enviar seu feedback às universidades de forma anônima para demais usuários.', textAlign: TextAlign.justify),
                SizedBox(height: 40),
                Text('Criada por: Gabriel Victor Couto Martins de Paula, João Victor Guerra Prata Lima, Luís Antônio de Souza e Sousa, Luiz Gustavo Santos Soares, Pedro Henrique Fernandes Machado', textAlign: TextAlign.justify, style: TextStyle(fontSize: 11),)
          ])),
    );
  }
}
