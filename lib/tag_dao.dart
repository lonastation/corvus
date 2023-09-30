import 'dart:async';

import 'package:flutter/services.dart';
import 'package:flutter/widgets.dart';
import 'package:path/path.dart';
import 'package:sqflite/sqflite.dart';

class Tag {
  int id;
  String name;
  int typeId;

  Tag({
    required this.id,
    required this.name,
    required this.typeId,
  });

  Map<String, dynamic> toMap() {
    return {
      'id': id,
      'name': name,
      'type_Id': typeId,
    };
  }

  // Implement toString to make it easier to see information about
  // each dog when using the print statement.
  @override
  String toString() {
    return 'Tag{id: $id, name: $name, typeId: $typeId}';
  }
}

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  String databasePath = await getDatabasesPath();
  String path = join(databasePath, 'corvus_nest.db');
  final database = openDatabase(path, onCreate: (db, version) {
    return db.execute(
      'create table tag (id integer PRIMARY KEY AUTOINCREMENT NOT NULL,name TEXT not null,type_id integer not null);',
    );
  }, version: 1);

  Future<void> insertTag(Tag tag) async {
    final db = await database;
    await db.insert(
      'tag',
      {
        'name': tag.name,
        'type_Id': tag.typeId,
      },
      conflictAlgorithm: ConflictAlgorithm.replace,
    );
  }

  Future<List<Tag>> findAll() async {
    final db = await database;
    final List<Map<String, dynamic>> maps = await db.query('tag');
    return List.generate(maps.length, (i) {
      return Tag(
        id: maps[i]['id'],
        name: maps[i]['name'],
        typeId: maps[i]['type_id'],
      );
    });
  }

  Future<void> updateTag(Tag tag) async {
    final db = await database;
    await db.update(
      'tag',
      tag.toMap(),
      where: 'id = ?',
      whereArgs: [tag.id],
    );
  }

  Future<void> deleteTag(int id) async {
    final db = await database;
    await db.delete(
      'tag',
      where: 'id = ?',
      whereArgs: [id],
    );
  }

  var fido = Tag(
    id: 0,
    name: 'Fido',
    typeId: 1,
  );

  await insertTag(fido);
  List<Tag> all = await findAll();
  print(all); // Prints a list that include Fido.

  fido = all.first;
  fido.name = 'cookie';

  await updateTag(fido);

  // print(await findAll()); // Prints Fido with age 42.

  await deleteTag(fido.id);

  print(await findAll());
}
