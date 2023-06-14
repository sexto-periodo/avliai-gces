class Subject {
  final int id;
  final String hashId;
  final String name;
  final String campus;
  final String course;
  final String university;
  final String courseHashId;
  final String universityHashId;
  final String shortDescription;
  final String longDescription;
  final double score;

  const Subject({required this.id, required this.hashId, required this.name, required this.campus, required this.course, required this.university, required this.courseHashId, required this.universityHashId, required this.shortDescription, required this.longDescription, required this.score});

  factory Subject.fromJson(Map<String, dynamic> json) {
    return Subject(id: json['id'], hashId: json['hashId'], name: json['name'], campus: json['campus'],course: json['course'], university: json['university'], courseHashId: json['courseHashId'],universityHashId: json['universityHashId'], shortDescription: json['shortDescription'], longDescription: json['longDescription'], score: json['score']);
  }
}