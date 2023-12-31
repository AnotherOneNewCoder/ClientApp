# Pet project для портфолио "ClientApp"
![Icon](https://github.com/AnotherOneNewCoder/ClientApp/blob/doubleToLong/app/src/main/res/mipmap-xxxhdpi/ic_launcher_round.webp)

## Краткое описание приложения.
Приложение для эффективного управления расписанием и ведения клиентской базы. Запись клиентов в несколько нажатий. Вся история сохраняется в 
карточке клиента. Приложение автоматически считает общую стоимость, полученную от клиента и чаевые. Поддержка русского и английского языка.

### Инструменты.
 - Архитиктура MVVM 
 - Библиотеки:
    - Jetpack Compose 
    - Material Design
    - ROOM
    - Hilt
    - GSON
    - Navigation
    - LiveData, Flow
    - Coroutines

### Описание ключевых функций и возможностей.
 - **Страница календаря и отображения событий в выбранный день.** 

Здесь пользователь может выбрать день для создания записи и просмотреть записи на конкретный день. Пользователь сам задает какой день будет выходным, а какой рабочим.
По умолчанию цвет дня черный, выходной красный, а рабочий зеленый. Цвет события в выбранном дне зависит от гендерного признака клиента, женский - розовый, а мужской - синий.

[//]: # (   ![Image alt text]&#40;https://github.com/AnotherOneNewCoder/ClientApp/blob/doubleToLong/app/src/main/res/screenshots/Screenshot_calendar_screen.jpg&#41;)

   <img src ="https://github.com/AnotherOneNewCoder/ClientApp/blob/doubleToLong/app/src/main/res/screenshots/Screenshot_calendar_screen.jpg" width=35% height=35%>

 - **Создание записи клиента.**

Пользователь выбирает день в календаре и нажимает на кнопку "+". Происходит переход на окно создания события. Здесь необходимо пользователю выбрать "клиента" из базы данных, задать время начала и окончания. Описание - опционально и может быть пустым. Выбрать checkbox "Рабочий день".

Для отметки выходного дня достаточно просто выбрать checkbox "Выходной день", никакой дополнительной информации вводить не нужно.

<img src ="https://github.com/AnotherOneNewCoder/ClientApp/blob/doubleToLong/app/src/main/res/screenshots/Screenshot_create_event.jpg" width=30% height=30%> <img src ="https://github.com/AnotherOneNewCoder/ClientApp/blob/doubleToLong/app/src/main/res/screenshots/Screenshot_create_event_choose_client.jpg" width=30% height=30%> <img src ="https://github.com/AnotherOneNewCoder/ClientApp/blob/doubleToLong/app/src/main/res/screenshots/Screenshot_create_event_search_choose_client.jpg" width=30% height=30%>
<img src ="https://github.com/AnotherOneNewCoder/ClientApp/blob/doubleToLong/app/src/main/res/screenshots/Screenshot_create_event_choose_time.jpg" width=30% height=30%>
<img src ="https://github.com/AnotherOneNewCoder/ClientApp/blob/doubleToLong/app/src/main/res/screenshots/Screenshot_create_event_full_example.jpg" width=30% height=30%>

 - **Закрытие записи.**

Запись можно закрыть (в случае если она прошла успешно) или удалить за ненадобностью. 

   <img src ="https://github.com/AnotherOneNewCoder/ClientApp/blob/doubleToLong/app/src/main/res/screenshots/Screenshot_done_or_delete.jpg" width=35% height=35%>

Для закрытия записи достаточно ввести значение в поле "цена", остальные поля заполняются опционально. Цвет фона также зависит от гендерного признака клиента, женский - розовый, а мужской - синий. После закрытия записи цвет события изменится на серый.

   <img src ="https://github.com/AnotherOneNewCoder/ClientApp/blob/doubleToLong/app/src/main/res/screenshots/Screenshot_clicke_done_event.jpg" width=35% height=35%> <img src ="https://github.com/AnotherOneNewCoder/ClientApp/blob/doubleToLong/app/src/main/res/screenshots/Screenshot_done_event.jpg" width=35% height=35%>

 - **Создание, отображение, редактирование и удаление клиентской базы.**

При переходе на вкладку "Data base" отображается список "клиентов".

<img src ="https://github.com/AnotherOneNewCoder/ClientApp/blob/doubleToLong/app/src/main/res/screenshots/Client_screen.jpg" width=35% height=35%>

В этом разделе можно создать, отредактировать и удалить клиента. 

Для создания нового клиента пользователю нужно обязательно заполнить поле "имя" и поле "фамилия", остальные поля опциональны. 

По нажатию на поле "имя" клиента пользователь переходит на "карту клиента" со всей информацией.

<img src ="https://github.com/AnotherOneNewCoder/ClientApp/blob/doubleToLong/app/src/main/res/screenshots/Screenshot_new_client.jpg" width=30% height=30%> <img src ="https://github.com/AnotherOneNewCoder/ClientApp/blob/doubleToLong/app/src/main/res/screenshots/Screenshot_edit_client.jpg" width=30% height=30%> <img src ="https://github.com/AnotherOneNewCoder/ClientApp/blob/doubleToLong/app/src/main/res/screenshots/Screenshot_delete_client.jpg" width=30% height=30%>
<img src ="https://github.com/AnotherOneNewCoder/ClientApp/blob/doubleToLong/app/src/main/res/screenshots/Screenshot_client_card_1.jpg" width=30% height=30%>
<img src ="https://github.com/AnotherOneNewCoder/ClientApp/blob/doubleToLong/app/src/main/res/screenshots/Screenshot_client_card_2.jpg" width=30% height=30%>