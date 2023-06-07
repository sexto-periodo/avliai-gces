import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

import 'api/base_api.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'API Example',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: MyHomePage(),
    );
  }
}

class MyHomePage extends StatefulWidget {
  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  List<dynamic> _posts = [];
  String _connectionStatus = '';

  Future<void> fetchData() async {
    final url = Uri.parse(BaseApi.baseURL + 'university');
    final response = await http.get(url);

    if (response.statusCode == 200) {
      final data = json.decode(response.body);

      setState(() {
        _posts = data;
        _connectionStatus = 'API connected';
      });
    } else {
      setState(() {
        _posts = [];
        _connectionStatus = 'API connection failed';
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('API'),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            ElevatedButton(
              child: Text('Fetch Data'),
              onPressed: fetchData,
            ),
            SizedBox(height: 20),
            Text(_connectionStatus),
            SizedBox(height: 20),
            if (_posts.isNotEmpty)
              Column(
              children: _posts.map((post) => Text(post['title'] ?? 'Title not available')).toList(),
              )
            else
              Text('No data available'),
          ],
        ),
      ),
    );
  }
}