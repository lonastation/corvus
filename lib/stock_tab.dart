import 'dart:async';

import 'package:flutter/widgets.dart';
import 'package:path/path.dart';
import 'package:sqflite/sqflite.dart';



class Thing {
  final int id;
  final int categoryId;
  final int thingStatus;
  final String code;
  final String name;
  final String location;
  final int quantity;
  final double rateOfLoss;
  final String remark;
  final double price;
  final DateTime purchaseDate;
  final String purchaseChannel;
  final String deleteReason;
  final DateTime deleteTime;
  final DateTime createTime;
  final DateTime lastUpdateTime;

  const Thing({
    required this.id,
    required this.categoryId,
    required this.thingStatus,
    required this.code,
    required this.name,
    required this.location,
    required this.quantity,
    required this.rateOfLoss,
    required this.remark,
    required this.price,
    required this.purchaseDate,
    required this.purchaseChannel,
    required this.deleteReason,
    required this.deleteTime,
    required this.createTime,
    required this.lastUpdateTime,
  });
}
