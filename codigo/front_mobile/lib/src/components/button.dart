import 'package:flutter/material.dart';
import 'package:front_mobile/src/components/color_palette.dart';
import 'package:front_mobile/src/login_page.dart';

class button extends StatelessWidget {
  const button({super.key});

  @override
  Widget build(BuildContext context) {
    return InkWell(
      onTap: () {
        print('Login');
      },
      child: Container(
        alignment: Alignment.center,
        height: 55,
        decoration: BoxDecoration(
          color: ColorPalette.mainColor,
          borderRadius: BorderRadius.circular(100),
          boxShadow: [
            BoxShadow(color: Colors.black.withOpacity(0.1), blurRadius: 10)
          ],
        ),
        child: const Text(
          'Sign in',
          style: TextStyle(
            color: Colors.white,
            fontWeight: FontWeight.w600,
          ),
        ),
      ),
    );
  }
}

class buttonRegistration extends StatelessWidget {
  const buttonRegistration({super.key});

  @override
  Widget build(BuildContext context) {
    return InkWell(
      onTap: () {
        print('Login');
        Navigator.push(
          context,
          MaterialPageRoute(builder: (context) => const LoginPage()),
        );
      },
      child: Container(
        alignment: Alignment.center,
        height: 55,
        decoration: BoxDecoration(
          color: ColorPalette.mainColor,
          borderRadius: BorderRadius.circular(6),
          boxShadow: [
            BoxShadow(color: Colors.black.withOpacity(0.1), blurRadius: 10)
          ],
        ),
        child: const Text(
          'Confirmar cadastro',
          style: TextStyle(
            color: Colors.white,
            fontWeight: FontWeight.w600,
          ),
        ),
      ),
    );
  }
}
