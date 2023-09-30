import 'package:corvus/expire_tab.dart';
import 'package:corvus/setting_tab.dart';
import 'package:corvus/stock_tab.dart';
import 'package:corvus/tag_tab.dart';
import 'package:flutter/material.dart';

class BottomNavigationBox extends StatefulWidget {
  const BottomNavigationBox({super.key});

  @override
  State<StatefulWidget> createState() {
    return _BottomNavigationBarBoxState();
  }
}

class _BottomNavigationBarBoxState extends State<BottomNavigationBox> {
  int _selectedIndex = 0;
  final _widgetOptions = <Widget> [
    const StockTab(),
    const ExpireTab(),
    const TagTab(),
    const SettingTab(),
  ];

  void _onItemTapped(int index) {
    setState(() {
      _selectedIndex = index;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: _widgetOptions[_selectedIndex],
      bottomNavigationBar: BottomNavigationBar(
        items: const <BottomNavigationBarItem>[
          BottomNavigationBarItem(
            icon: Icon(
              Icons.whatshot_outlined,
            ),
            activeIcon: Icon(
              Icons.whatshot,
            ),
            label: '库存',
            // backgroundColor: Colors.blue,
          ),
          BottomNavigationBarItem(
            icon: Icon(
              Icons.catching_pokemon_outlined,
            ),
            activeIcon: Icon(
              Icons.catching_pokemon,
            ),
            label: '上货',
            // backgroundColor: Colors.green,
          ),
          BottomNavigationBarItem(
            icon: Icon(
              Icons.loyalty_outlined,
            ),
            activeIcon: Icon(
              Icons.loyalty,
            ),
            label: '标签',
            // backgroundColor: Colors.pinkAccent,
          ),
          BottomNavigationBarItem(
            icon: Icon(
              Icons.eco_outlined,
            ),
            activeIcon: Icon(Icons.eco),
            label: '设置',
            // backgroundColor: Colors.teal,
          ),
        ],
        currentIndex: _selectedIndex,
        selectedItemColor: Colors.blue,
        unselectedItemColor: Colors.grey,
        onTap: _onItemTapped,
        selectedLabelStyle: const TextStyle(fontSize: 14),
        showSelectedLabels: true,
        showUnselectedLabels: false,
        type: BottomNavigationBarType.fixed,
      ),
    );
  }
}
