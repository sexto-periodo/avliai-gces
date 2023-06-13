import 'dart:convert';
import 'package:front_mobile/src/shared/config.dart';
import 'package:http/http.dart' as http;
import '../_subject.dart';

import 'package:flutter_secure_storage/flutter_secure_storage.dart';
const storage = FlutterSecureStorage();





class BaseApi {
  static var baseURL = Config.apiURL;

  var universityRoute = '${baseURL}university';
  var subjectRoute = '${baseURL}subject';
  var reviewRoute = '${baseURL}subject-review';
  var courseRoute = '${baseURL}course';
  var userRoute = '${baseURL}user';
  var authRoute = '${baseURL}auth';

  Map<String, String> authHeaders(String authToken) {
    return <String, String>{
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': 'Bearer $authToken'
          };
  }
}

// ===============================================

class AuthApi extends BaseApi {
  Future<http.Response> register(SignUpForm formData) async {
    try {
      final http.Response response =
          await http.post(Uri.parse('${super.authRoute}/register'),
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
    } on Exception catch (_) {
      rethrow;
    }
  }

  Future<UserAuth> login(String email, String password) async {
    try {
      final http.Response response = await http.post(
          Uri.parse('${super.authRoute}/authenticate'),
          headers: <String, String>{
            'Accept': 'application/json',
            'Content-Type': 'application/json',
          },
          body: jsonEncode(
              <String, String>{'email': email, 'password': password}));

      switch (response.statusCode) {
        case 200:
          return UserAuth.fromJson(jsonDecode(response.body));
        default:
          throw Exception(response.reasonPhrase);
      }
    } on Exception catch (_) {
      rethrow;
    }
  }
}

class UserAuth {
  final String access_token;
  final String refresh_token;

  const UserAuth({required this.access_token, required this.refresh_token});

  factory UserAuth.fromJson(Map<String, dynamic> json) {
    return UserAuth(
        access_token: json['access_token'],
        refresh_token: json['refresh_token']);
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

// ====================================
class UserApi extends BaseApi {
  Future<User> getUser(String authToken) async {
    try {
      final http.Response response =
          await http.get(Uri.parse(super.userRoute),
          headers: super.authHeaders(authToken));
          switch (response.statusCode) {
            case 200:
              return User.fromJson(jsonDecode(utf8.decode(response.bodyBytes)));
            default:
              throw Exception(response.reasonPhrase);
          }
    } on Exception catch (_) {
      rethrow;
    }
  }

  void SaveLoggedUser(String authToken) async {
    User user =  await this.getUser(authToken);
    storage.write(key: 'loggedUser', value: User.serialize(user));
  }
}

class User {
  final int id;
  final String firstname;
  final String lastname;
  final String email;
  final String hashId;
  final String universityHashId;
  final String courseHashId;
  final String role;

  const User({required this.id, required this.firstname, required this.lastname, required this.email, required this.hashId, required this.universityHashId, required this.courseHashId, required this.role});

  factory User.fromJson(Map<String, dynamic> json) {
    return User(id: json['id'], firstname: json['firstname'], lastname: json['lastname'], email: json['email'], hashId: json['hashId'], universityHashId: json['universityHashId'], courseHashId: json['courseHashId'], role: json['role']);
  }

  static Map<String, dynamic> toMap(User user) => <String, dynamic> {
    'id': user.id,
    'firstname': user.firstname,
    'lastname': user.lastname,
    'email': user.email,
    'hashId': user.hashId,
    'universityHashId': user.universityHashId,
    'courseHashId': user.courseHashId,
    'role': user.role
  };

  static String serialize(User user) => jsonEncode(User.toMap(user));
  static User deserialize(String json) => User.fromJson(jsonDecode(json));
}

// ====================================

class UniversityApi extends BaseApi{
  Future<List<University>> getUniversities() async {
    try {
      final http.Response response =
          await http.get(Uri.parse(super.universityRoute));
          switch (response.statusCode) {
            case 200:
              Iterable list = jsonDecode(utf8.decode(response.bodyBytes));
              List<University> universities = List<University>.from(list.map((e) =>  University.fromJson(e)));
              return universities;
            default:
              throw Exception(response.reasonPhrase);
          }
    } on Exception catch (_) {
      rethrow;
    }
  }

  Future<List<Course>> getCoursesByUniversityHashId(String hashId) async {
    try {
      final http.Response response =
          await http.get(Uri.parse('${super.courseRoute}/university/$hashId'),
          );
          switch (response.statusCode) {
            case 200:
              Iterable list = jsonDecode(utf8.decode(response.bodyBytes));
              List<Course> courses = List<Course>.from(list.map((e) =>  Course.fromJson(e)));
              return courses;
            default:
              throw Exception(response.reasonPhrase);
          }
    } on Exception catch (_) {
      rethrow;
    }
  }


  Future<List<Subject>> getSubjects(String authToken) async {
    try {
      final http.Response response =
          await http.get(Uri.parse(super.subjectRoute),
          headers: super.authHeaders(authToken));
          switch (response.statusCode) {
            case 200:
              Iterable list = jsonDecode(utf8.decode(response.bodyBytes));
              List<Subject> subjects = List<Subject>.from(list.map((e) =>  Subject.fromJson(e)));
              return subjects;
            default:
              throw Exception(response.reasonPhrase);
          }
    } on Exception catch (_) {
      rethrow;
    }
  }
}

class Course {
  final int id;
  final String hashId;
  final String name;
  final int overtime;
  final bool statusCurriculum;

  const Course({required this.id, required this.hashId, required this.name, required this.overtime, required this.statusCurriculum});

  factory Course.fromJson(Map<String, dynamic> json) {
    return Course(id: json['id'], hashId: json['hashId'], name: json['name'], overtime: json['overtime'], statusCurriculum: json['statusCurriculum']);
  }
}

class University {
  final int id;
  final String hashId;
  final String name;
  final String cnpj;

  const University({required this.id, required this.hashId, required this.name, required this.cnpj});

  factory University.fromJson(Map<String, dynamic> json) {
    return University(id: json['id'], hashId: json['hashId'], name: json['name'], cnpj: json['cnpj']);
  }
}



