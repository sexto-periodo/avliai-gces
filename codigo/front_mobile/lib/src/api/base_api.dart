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
  var reviewRoute = '${baseURL}review';
  var courseRoute = '${baseURL}course';
  var userRoute = '${baseURL}user';
  var authRoute = '${baseURL}auth';
  var voteRoute = '${baseURL}vote';

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

// ===============================================
class UserApi extends BaseApi {
  Future<User> getUser(String authToken) async {
    try {
      final http.Response response = await http.get(Uri.parse(super.userRoute),
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
    User user = await this.getUser(authToken);
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

  const User(
      {required this.id,
      required this.firstname,
      required this.lastname,
      required this.email,
      required this.hashId,
      required this.universityHashId,
      required this.courseHashId,
      required this.role});

  factory User.fromJson(Map<String, dynamic> json) {
    return User(
        id: json['id'],
        firstname: json['firstname'],
        lastname: json['lastname'],
        email: json['email'],
        hashId: json['hashId'],
        universityHashId: json['universityHashId'],
        courseHashId: json['courseHashId'],
        role: json['role']);
  }

  static Map<String, dynamic> toMap(User user) => <String, dynamic>{
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

// ===============================================

class UniversityApi extends BaseApi {
  Future<List<University>> getUniversities() async {
    try {
      final http.Response response =
          await http.get(Uri.parse(super.universityRoute));
      switch (response.statusCode) {
        case 200:
          Iterable list = jsonDecode(utf8.decode(response.bodyBytes));
          List<University> universities =
              List<University>.from(list.map((e) => University.fromJson(e)));
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
      final http.Response response = await http.get(
        Uri.parse('${super.courseRoute}/university/$hashId'),
      );
      switch (response.statusCode) {
        case 200:
          Iterable list = jsonDecode(utf8.decode(response.bodyBytes));
          List<Course> courses =
              List<Course>.from(list.map((e) => Course.fromJson(e)));
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
      final http.Response response = await http.get(
          Uri.parse(super.subjectRoute),
          headers: super.authHeaders(authToken));
      switch (response.statusCode) {
        case 200:
          Iterable list = jsonDecode(utf8.decode(response.bodyBytes));
          List<Subject> subjects =
              List<Subject>.from(list.map((e) => Subject.fromJson(e)));
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

  const Course(
      {required this.id,
      required this.hashId,
      required this.name,
      required this.overtime,
      required this.statusCurriculum});

  factory Course.fromJson(Map<String, dynamic> json) {
    return Course(
        id: json['id'],
        hashId: json['hashId'],
        name: json['name'],
        overtime: json['overtime'],
        statusCurriculum: json['statusCurriculum']);
  }
}

class University {
  final int id;
  final String hashId;
  final String name;
  final String cnpj;

  const University(
      {required this.id,
      required this.hashId,
      required this.name,
      required this.cnpj});

  factory University.fromJson(Map<String, dynamic> json) {
    return University(
        id: json['id'],
        hashId: json['hashId'],
        name: json['name'],
        cnpj: json['cnpj']);
  }
}

// ===============================================

class ReviewApi extends BaseApi {
  Future<http.Response> addReview(ReviewForm review, String authToken) async {
    try {
      final http.Response response =
          await http.post(Uri.parse('${super.reviewRoute}'),
              headers: super.authHeaders(authToken),
              body: jsonEncode(<String, String>{
                "reviewText": review.reviewText,
                "score": review.score,
                "userHashId": review.userHashId,
                "subjectHashId": review.subjectHashId,
                "universityHashId": review.universityHashId,
                "courseHashId": review.courseHashId
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

  Future<List<Review>> getReviewsByUser(String authToken) async {
    try {
      final http.Response response = await http.get(
        Uri.parse('${super.reviewRoute}/user'),
        headers: super.authHeaders(authToken),
      );

      switch (response.statusCode) {
        case 200:
          Iterable list = jsonDecode(utf8.decode(response.bodyBytes));
          List<Review> reviews =
              List<Review>.from(list.map((e) => Subject.fromJson(e)));
          return reviews;
        default:
          throw Exception(response.reasonPhrase);
      }
    } on Exception catch (_) {
      rethrow;
    }
  }

  Future<List<Review>> getReviewsBySubject(
      String authToken, String subjectHashId) async {
    try {
      final http.Response response = await http.get(
        Uri.parse('${super.reviewRoute}/subject/$subjectHashId'),
        headers: super.authHeaders(authToken),
      );

      switch (response.statusCode) {
        case 200:
          Iterable list = jsonDecode(utf8.decode(response.bodyBytes));
          List<Review> reviews =
              List<Review>.from(list.map((e) => Subject.fromJson(e)));
          return reviews;
        default:
          throw Exception(response.reasonPhrase);
      }
    } on Exception catch (_) {
      rethrow;
    }
  }

  Future<bool> checkIfAlreadyReviwed(
      String authToken, String subjectHashId) async {
    try {
      final http.Response response = await http.get(
        Uri.parse(
            '${super.reviewRoute}/subject/already-reviewed-by-user/$subjectHashId'),
        headers: super.authHeaders(authToken),
      );

      switch (response.statusCode) {
        case 200:
          return response.body as bool;
        default:
          throw Exception(response.reasonPhrase);
      }
    } on Exception catch (_) {
      rethrow;
    }
  }

  Future<http.Response> vote(String authToken, VoteForm vote) async {
    try {
      final http.Response response =
          await http.post(Uri.parse('${super.voteRoute}'),
              headers: super.authHeaders(authToken),
              body: jsonEncode(<String, String>{
                "isVoted": vote.isVoted.toString(),
                "voteUpDown": vote.voteUpDown.toString(),
                "reviewHashId": vote.reviewHashId
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
}

class Review {
  final int id;
  final String hashId;
  final String reviewText;
  final Vote vote;

  const Review(
      {required this.id,
      required this.hashId,
      required this.reviewText,
      required this.vote});

  factory Review.fromJson(Map<String, dynamic> json) {
    return Review(
        id: json['id'],
        hashId: json['hashId'],
        reviewText: json['reviewText'],
        vote: json['vote']);
  }
}

class ReviewByUser extends Review {
  final String firstname;
  final String lastname;

  const ReviewByUser(
      {required int id,
      required String hashId,
      required String reviewText,
      required Vote vote,
      required this.firstname,
      required this.lastname})
      : super(id: id, hashId: hashId, reviewText: reviewText, vote: vote);

  factory ReviewByUser.fromJson(Map<String, dynamic> json) {
    return ReviewByUser(
        id: json['id'],
        hashId: json['hashId'],
        reviewText: json['reviewText'],
        vote: json['vote'],
        firstname: json['firstname'],
        lastname: json['lastname']);
  }
}

class Vote {
  final bool isVoted;
  final bool voteUpDown;
  final String subjectReviewHashId;
  final int voteCount;

  const Vote(
      {required this.isVoted,
      required this.voteUpDown,
      required this.subjectReviewHashId,
      required this.voteCount});
}

class VoteForm {
  final bool isVoted;
  final bool voteUpDown;
  final String reviewHashId;

  const VoteForm({required this.isVoted, required this.voteUpDown, required this.reviewHashId});
}

class ReviewForm {
  final String reviewText;
  final String score;
  final String userHashId;
  final String subjectHashId;
  final String universityHashId;
  final String courseHashId;

  const ReviewForm(
      {required this.reviewText,
      required this.score,
      required this.userHashId,
      required this.subjectHashId,
      required this.universityHashId,
      required this.courseHashId});

  factory ReviewForm.fromJson(Map<String, dynamic> json) {
    return ReviewForm(
        reviewText: json['reviewText'],
        score: json['score'],
        userHashId: json['userHashId'],
        subjectHashId: json['subjectHashId'],
        universityHashId: json['universityHashId'],
        courseHashId: json['courseHashId']);
  }
}
