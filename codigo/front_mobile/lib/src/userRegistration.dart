import 'package:flutter/material.dart';
import 'package:front_mobile/src/api/base_api.dart';
import 'package:front_mobile/src/components/text.dart';
import 'package:front_mobile/src/login_page.dart';

import 'components/color_palette.dart';
import 'package:http/http.dart' as http;

class UserRegistration extends StatefulWidget {
  const UserRegistration({super.key});

  @override
  _UserRegistrationState createState() => _UserRegistrationState();
}

class _UserRegistrationState extends State<UserRegistration> {
  final TextEditingController _emailController = TextEditingController();
  final TextEditingController _passwordController = TextEditingController();
  late TextEditingController _universityController = TextEditingController();
  late TextEditingController _courseController = TextEditingController();
  final TextEditingController _academicRegisterController = TextEditingController();
  final TextEditingController _firstnameController = TextEditingController();
  final TextEditingController _lastnameController = TextEditingController();

  University? _selectedUniversity;
  Course? _selectedCourse;


  late List<University> universities = [];
  late List<Course> courses = [];

  @override
  void initState() {
    super.initState();
    _universityController = TextEditingController();
    _courseController = TextEditingController();
    fetchUniversities();
  }

  @override
  void dispose() {
    _universityController.dispose();
    _courseController.dispose();
    super.dispose();
  }

  void _selectUniversity(University university) {
    setState(() {
      _selectedUniversity = university;
      _universityController.text = university.name;
    });

    fetchCourses(_selectedUniversity!.hashId);
  }

  void _selectCourse(Course course) {
    setState(() {
      _selectedCourse = course;
      _courseController.text = course.name;
    });
  }

  fetchUniversities() async {
    List<University> fetchedUniversities =
        await UniversityApi().getUniversities();
    setState(() {
      universities = fetchedUniversities;
    });
  }

  Future<void> fetchCourses(String hashId) {
    return Future.delayed(Duration(seconds: 2), () async {
      // Replace this with your actual asynchronous method to get courses
      List<Course> fetchedCourses =
          await UniversityApi().getCoursesByUniversityHashId(hashId);

      setState(() {
        courses = fetchedCourses;
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: const Text('Cadastro de usuário'),
          backgroundColor: ColorPalette.mainColor,
          leading: IconButton(
            icon: const Icon(
              Icons.arrow_back,
              color: Colors.white,
            ),
            onPressed: () {
              Navigator.push(
                context,
                MaterialPageRoute(builder: (context) => const LoginPage()),
              );
            },
          ),
        ),
        body: SingleChildScrollView(
          child: SafeArea(
            child: Container(
              width: double.infinity,
              padding: const EdgeInsets.all(15.0),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Container(
                    alignment: Alignment.center,
                    child: Text(
                      'Avaliaí',
                      style: TextStyle(
                        color: ColorPalette.mainColor,
                        fontSize: 35,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                  ),
                  const SizedBox(height: 50),
                  const SizedBox(
                    height: 15,
                  ),
                  const SizedBox(
                    height: 15,
                  ),
                  Row(
                    children: [
                      Expanded(
                        child: text(
                          controller: _firstnameController,
                          texto: 'Nome',
                          obscure: false,
                          textInputType: TextInputType.text,
                        ),
                      ),
                      const SizedBox(
                          width: 10), // Espaço entre os campos de texto
                      Expanded(
                        child: text(
                          controller: _lastnameController,
                          texto: 'Sobrenome',
                          obscure: false,
                          textInputType: TextInputType.text,
                        ),
                      ),
                    ],
                  ),
                  const SizedBox(
                    height: 15,
                  ),
                  DropdownButtonFormField<University>(
                    value: _selectedUniversity,
                    items: universities.map((University university) {
                      return DropdownMenuItem<University>(
                        value: university,
                        child: Text(university.name),
                      );
                    }).toList(),
                    onChanged: (University? university) {
                      if (university != null) {
                        _selectUniversity(university);
                      }
                    },
                    decoration: InputDecoration(
                      labelText: 'Selecione uma Universidade',
                    ),
                  ),
                  const SizedBox(
                    height: 15,
                  ),
                  DropdownButtonFormField<Course>(
                    value: _selectedCourse,
                    items: courses.map((Course course) {
                      return DropdownMenuItem<Course>(
                        value: course,
                        child: Text(course.name),
                      );
                    }).toList(),
                    onChanged: (Course? course) {
                      if (course != null) {
                        setState(() {
                          _selectedCourse = course;
                        });
                      }
                    },
                    decoration: InputDecoration(
                      labelText: 'Selecione um Curso',
                    ),
                  ),
                  const SizedBox(
                    height: 15,
                  ),
                  Row(children: [
                    Expanded(
                      child: text(
                        controller: _academicRegisterController,
                        texto: 'Registro acadêmico',
                        obscure: false,
                        textInputType: TextInputType.text,
                      ),
                    ),
                    const SizedBox(width: 10),
                  ]),
                  const SizedBox(
                    height: 15,
                  ),
                  text(
                    controller: _emailController,
                    texto: 'Email',
                    obscure: false,
                    textInputType: TextInputType.emailAddress,
                  ),
                  const SizedBox(
                    height: 10,
                  ),
                  text(
                    controller: _passwordController,
                    texto: 'Senha',
                    obscure: false,
                    textInputType: TextInputType.emailAddress,
                  ),
                  const SizedBox(
                    height: 10,
                  ),
                  /*
                text(
                    controller: passwordController,
                    texto: 'Repita a senha',
                    textInputType: TextInputType.text,
                    obscure: true),
                const SizedBox(
                  height: 10,
                ),
                const buttonRegistration(),
                const SizedBox(
                  height: 5,
                ),
                */
                
                   Container(
                    alignment: Alignment.center,
                    child: SizedBox(
                      width: double.infinity,
                      height: 50,
                      child: ElevatedButton(
                          onPressed: () => {handleSubmit()},
                          style: ElevatedButton.styleFrom(
                              backgroundColor: ColorPalette.mainColor,
                              shape: RoundedRectangleBorder(
                                borderRadius: BorderRadius.circular(100),
                              )),
                          child: const Text(
                            'Cadastrar',
                            style: TextStyle(
                              color: Colors.white,
                              fontWeight: FontWeight.w600,
                              fontSize: 24,
                            ),
                          )),
                    )),
                ],
              ),
            ),
          ),
        ));
  }

  handleSubmit() async {
    String uniHash = _selectedUniversity!.hashId.toString();
    String cHash = _selectedCourse!.hashId.toString();

    SignUpForm newUser = SignUpForm(
        firstname: _firstnameController.text,
        lastname: _lastnameController.text,
        email: _emailController.text,
        password: _passwordController.text,
        academicRegister: _academicRegisterController.text,
        universityHashId: uniHash,
        courseHashId: cHash,
        role: 'USER');
    http.Response response = await AuthApi().register(newUser);

    if (response.statusCode == 200) {
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(content: Text('Cadastro realizado com sucesso')),
      );

       Navigator.pop(context);
    } else {
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(content: Text('Falha ao cadastrar')),
      );

    }
  }
}
