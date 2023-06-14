import 'package:flutter/material.dart';

class ColorPalette {
  static HexColor mainColor = HexColor('#6750A4');
  static HexColor textColor = HexColor('#4F4F4F');
}

class HexColor extends Color {
  static int _getColorFromHex(String hexColor) {
    hexColor = hexColor.toUpperCase().replaceAll("#", "");
    if (hexColor.length == 6) {
      hexColor = "FF$hexColor";
    }
    return int.parse(hexColor, radix: 16);
  }

  HexColor(final String hexColor) : super(_getColorFromHex(hexColor));
}
