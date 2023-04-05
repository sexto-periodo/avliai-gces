import 'package:flutter/material.dart';

class login_page extends StatefulWidget {
  const login_page({super.key});

  @override
  State<login_page> createState() => _login_pageState();
}

class _login_pageState extends State<login_page> {
  @override
  Widget build(BuildContext context) {
    return Material(
      child: SizedBox(
        width: double.infinity,
        height: double.infinity,
      child: Padding(
        padding: const EdgeInsets.all(8.0),
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children:
         [TextField(
          keyboardType: TextInputType.emailAddress,
          decoration: InputDecoration(
            labelText: 'Email',
             border: OutlineInputBorder()
          ),
        ),
        SizedBox(height: 10,),
        TextField(
          obscureText: true,
          decoration: InputDecoration(
            labelText: 'Password',
             border: OutlineInputBorder()
          ),
        ),
         SizedBox(height: 15,),
        ElevatedButton(onPressed:() {} , child: const Text('entrar'))
        ],
      ),
    ),
    ),
    );
  }
}