%Diablo

%ФАКТЫ
%Какие персонажи существуют в игре
person_in_game(barbarian).
person_in_game(necromancer).
person_in_game(enchantress).
person_in_game(amazon).
person_in_game(paladin).
person_in_game(assassin).
person_in_game(druid).

%Какое бывает оружие
weapon_in_game(scepter).
weapon_in_game(staff).
weapon_in_game(sword).
weapon_in_game(spear).
weapon_in_game(knife).
weapon_in_game(axe).
weapon_in_game(whip).

%Какая бывает броня
armor_in_game(helmet).
armor_in_game(armour).
armor_in_game(bone_armor).
armor_in_game(cape).
armor_in_game(amulet).
armor_in_game(chainmail).
armor_in_game(skin).

%ФАКТЫ

%Уровень персонажей
person_lvl(barbarian, 5).
person_lvl(necromancer, 34).
person_lvl(enchantress, 12).
person_lvl(amazon, 12).
person_lvl(paladin, 24).
person_lvl(assassin, 29).
person_lvl(druid, 22).

%Урон от оружия
weapon_damage(scepter, 3).
weapon_damage(staff, 5).
weapon_damage(sword, 3).
weapon_damage(spear, 4).
weapon_damage(knife, 5).
weapon_damage(axe, 8).
weapon_damage(whip, 5).

%Уровень защиты брони
armor_lvl(helmet, 2).
armor_lvl(armour, 10).
armor_lvl(bone_armor, 11).
armor_lvl(cape, 5).
armor_lvl(amulet, 3).
armor_lvl(chainmail, 7).
armor_lvl(skin, 2).

%ОТНОШЕНИЯ
%Принадлежность оружия персонажу
person_weapon(barbarian, axe).
person_weapon(necromancer, scepter).
person_weapon(enchantress, staff).
person_weapon(amazon, spear).
person_weapon(paladin, sword).
person_weapon(assassin, knife).
person_weapon(druid, whip).

%Принадлежность брони персонажу
person_armor(barbarian, helmet).
person_armor(necromancer, bone_armor).
person_armor(enchantress, amulet).
person_armor(amazon, chainmail).
person_armor(paladin, armour).
person_armor(assassin, cape).
person_armor(druid, skin).

%ПРАВИЛА

%Правило о наличии оружия
is_person_has_weapon(Person, Weapon):-
    person_in_game(Person),
    weapon_in_game(Weapon),
    person_weapon(Person, Weapon).

%Правило об использовании амулета
is_person_has_amulet(Person):-
    person_in_game(Person),
    person_armor(Person, amulet).


%Правило об использовании амулета
is_person_has_amulet(Person):-
    person_in_game(Person),
    person_armor(Person, amulet).

%Правило получения урона персонажа
person_damage(Person, Damage):-
    person_in_game(Person),
    person_weapon(Person, Weapon),
    weapon_damage(Weapon, Damage).

%Правило подсчёта поинтов персонажа
person_points(Person, Point):-
    person_in_game(Person),
    person_damage(Person, Damage),
    person_armor(Person, Armor),
    armor_lvl(Armor, Shield),
    person_lvl(Person, Lvl),
    (Damage + Shield + Lvl = Point -> true; false).

%Правило о победе первого игрока над вторым
is_first_win(Person1, Person2):-
    person_points(Person1, X),
    person_points(Person2, Y),
    (X > Y -> true; false).

