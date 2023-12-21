# Pet project для портфолио "ClientApp"
![Icon](https://github.com/AnotherOneNewCoder/ClientApp/blob/doubleToLong/app/src/main/res/mipmap-xxxhdpi/ic_launcher_round.webp)

## Краткое описание приложения:
Приложение для эффективного управления расписанием и ведения клиентской базы. Запись клиентов в несколько нажатий. Вся история сохраняется в 
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
 - **Страница календаря и отображения событий в выбранный день.** 

Здесь пользователь может выбрать день для создания записи и просмотреть записи на конкретный день. Пользователь сам задает какой день будет выхоным, а какой рабочим.
По умолчанию цвет дня черный, выходной красный, а рабочий зеленый. Цвет события, в выбранном дне, зависит от половой принадлежности клиента, женский - розовый, а мужской - синий.

[//]: # (   ![Image alt text]&#40;https://github.com/AnotherOneNewCoder/ClientApp/blob/doubleToLong/app/src/main/res/screenshots/Screenshot_calendar_screen.jpg&#41;)

   <img src ="https://github.com/AnotherOneNewCoder/ClientApp/blob/doubleToLong/app/src/main/res/screenshots/Screenshot_calendar_screen.jpg" width=35% height=35%>

 - **Создание записи происходит следущим образом:**

Пользователь выбирает день в календаре и нажимает на кнопку "+". Перходит на окно создания события. Здесь необходимо выбрать "клиента" из базы данных, задать время начала и оканчания, описание опционально, оно может быть пустым. Выбрать рабочий день.
Для отметки выходного дня достаточно просто выбрать чек бокс "Выходной день", никакой дополнительной информации вводить не нужно.

<img src ="https://github.com/AnotherOneNewCoder/ClientApp/blob/doubleToLong/app/src/main/res/screenshots/Screenshot_create_event.jpg" width=30% height=30%><img src ="https://github.com/AnotherOneNewCoder/ClientApp/blob/doubleToLong/app/src/main/res/screenshots/Screenshot_create_event_choose_client.jpg" width=30% height=30%><img src ="https://github.com/AnotherOneNewCoder/ClientApp/blob/doubleToLong/app/src/main/res/screenshots/Screenshot_create_event_search_choose_client.jpg" width=30% height=30%>
<img src ="https://github.com/AnotherOneNewCoder/ClientApp/blob/doubleToLong/app/src/main/res/screenshots/Screenshot_create_event_choose_time.jpg" width=30% height=30%>
<img src ="https://github.com/AnotherOneNewCoder/ClientApp/blob/doubleToLong/app/src/main/res/screenshots/Screenshot_create_event_full_example.jpg" width=30% height=30%>

 - **Закрытие записи:**
Запись можно закрыть (в случае если она прошла успешно) или удалить, например, если "клиент" не пришел. 

   <img src ="https://github.com/AnotherOneNewCoder/ClientApp/blob/doubleToLong/app/src/main/res/screenshots/Screenshot_done_or_delete.jpg" width=35% height=35%>

Для закрытия записи достаточно ввести значение в поле "цена", остальные поля заполняются опционально. Цвет фона также зависит от половой принадлежности клиента, женский - розовый, а мужской - синий. После закрытия цвет события изменится на серый.

   <img src ="https://github.com/AnotherOneNewCoder/ClientApp/blob/doubleToLong/app/src/main/res/screenshots/Screenshot_clicke_done_event.jpg" width=35% height=35%> <img src ="https://github.com/AnotherOneNewCoder/ClientApp/blob/doubleToLong/app/src/main/res/screenshots/Screenshot_done_event.jpg" width=35% height=35%>

 - **Создание, отображени, редактировние и удаление клиентской базы:**
При переходе на вкладку "Data base" отображается список "клиентов".

<img src ="https://github.com/AnotherOneNewCoder/ClientApp/blob/doubleToLong/app/src/main/res/screenshots/Client_screen.jpg" width=35% height=35%>

