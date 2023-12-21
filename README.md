# Pet project для портфолио "ClientApp"
![Icon](https://github.com/AnotherOneNewCoder/ClientApp/blob/doubleToLong/app/src/main/res/mipmap-xxxhdpi/ic_launcher_round.webp)

## Краткое описание приложения:
Приложение для эффективного управления расписанием и введением клиентской базы. Записывать клиентов в несколько нажатий. Вся история сохраняется в 
карточке клиента. Приложение автомотически считает общую стоимость, полученную от клиента и чаевые.

### Инструменты:
 - Архитиктура MVVM (для возможности будущего взаимодействия с серверной частью)
 - Библиотеки:
    - Jetpack Compose 
    - Material Design
    - ROOM
    - Hilt
    - GSON
    - Navigation
    - LiveData, Flow
    - Coroutines

### Описание ключевых функций и возможностей:
 - **Страница календаря и отображения событий в выбранный день** 
Здесь пользователь может выбрать день для создания записи и просмотреть записи на конкретный день. Пользователь сам задает какой день будет выхоным, а какой рабочим.
По умолчанию цвет дня черный, выходной красный, а рабочий зеленый. Цвет события, в выбранном дне, зависит от половой принадлежности клиента, женский - розовый, а мужской - синий.

[//]: # (   ![Image alt text]&#40;https://github.com/AnotherOneNewCoder/ClientApp/blob/doubleToLong/app/src/main/res/screenshots/Screenshot_calendar_screen.jpg&#41;)

   <img src ="https://github.com/AnotherOneNewCoder/ClientApp/blob/doubleToLong/app/src/main/res/screenshots/Screenshot_calendar_screen.jpg" width=35% height=35%>