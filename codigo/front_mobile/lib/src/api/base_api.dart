import 'dart:convert';
import 'dart:io';

import 'package:flutter/foundation.dart';
import 'package:front_mobile/src/shared/config.dart';
import 'package:http/http.dart' as http;

class BaseApi {
  static var baseURL = Config.apiURL;

  var universityRoute = '${baseURL}university';
  var subjectRoute = '${baseURL}subject';
  var reviewRoute = '${baseURL}subject-review';
  var courseRoute = '${baseURL}course';
  var userRoute = '${baseURL}user';
  var authRoute = '${baseURL}auth';
}

class AuthApi extends BaseApi {
  Future<http.Response> register(SignUpForm formData) async {
    try {
      final http.Response response = await http.post(Uri.parse('${super.authRoute}/register'),
        headers: <String, String>{
          'Accept': 'application/json',
          'Content-Type': 'application/json',
          
        },
        body: jsonEncode(<String, String>{
          'firstname': formData.firstname,
          'lastname': formData.lastname,
          'email': formData.email,
          'password': formData.password,
          'academicRegister': formData.academicRegister,
          'universityHashId': formData.universityHashId,
          'courseHashId': formData.courseHashId,
          'role': formData.role
        }));
       switch (response.statusCode) {
      case 200:
        return response;
      default:
        throw Exception(response.reasonPhrase);
    }
    } on Exception catch(_) {
      rethrow;
    }
  }

  Future<http.Response> login(String email, String password) async {
    try {
      final http.Response response = await http.post(Uri.parse('${super.authRoute}/authenticate'),
      headers: <String, String>{
          'Accept': 'application/json',
          'Content-Type': 'application/json',
          
        },
      body: jsonEncode(
        <String, String>{
          'email': email,
          'password': password
        }
      )
      );

      switch (response.statusCode) {
        case 200:
          return response;
        default:
          throw Exception(response.reasonPhrase);
      }
    } on Exception catch(_) {
      rethrow;
    }
  }
}

class SignUpForm {
  final String firstname;
  final String lastname;
  final String email;
  final String password;
  final String academicRegister;
  final String universityHashId;
  final String courseHashId;
  final String role;

  const SignUpForm(
      {required this.firstname,
      required this.lastname,
      required this.email,
      required this.password,
      required this.academicRegister,
      required this.universityHashId,
      required this.courseHashId,
      required this.role});
}


class UserAuth {
  final String access_token;
  final String refresh_token;

  const UserAuth({required this.access_token, required this.refresh_token});
  

  factory UserAuth.fromJson(Map<String, dynamic> json) {
    return UserAuth(access_token: json['access_token'], refresh_token: json['refresh_token']);
  }
}