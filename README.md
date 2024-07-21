# TaskManager
Design and develop a personal task manager app that allows users to add, update, and delete tasks with deadlines. The app includes local storage for task persistence but api data fetching is developing :(. Additionally, app implements a reminder system to notify users of upcoming tasks.

## Built With

* [Room] - Local Database Library
* [Compose] - UI Library
* [AlarmManager] - Library for time-base operations
* [retrofit2] - fetch data from api Library (developing)

## Add task page
![alt text](https://github.com/farshadHP/TaskManager/blob/master/Screenshot_20240722-002538_TaskManagaer%20MVVM.jpg)
## Task list page
![alt text](https://github.com/farshadHP/TaskManager/blob/master/Screenshot_20240722-002627_TaskManagaer%20MVVM.jpg)
## Notifican
![alt text](https://github.com/farshadHP/TaskManager/blob/master/Screenshot_20240722-003018_Telegram.jpg)



In MVVM architecture, the main components are as follows:

* Model:
Responsible for data, business logic, and data source access.
Holds data models and notifies the ViewModel of changes.
* View:
Responsible for displaying data and interacting with the user.
Receives data from the ViewModel and sends changes back to it.
* ViewModel:
Acts as an intermediary between the Model and the View.
Key responsibilities include transforming data for display, managing states, and observing changes.
The ViewModel receives data from the Model and communicates it to the View.


### I apologize for the sudden design change, which caused me to fall behind in implementing the feature of fetching data from an API. However, the general approach is as follows:

* Dual Data Sources:
You need two data sources: one connected to a local database and the other to a server (API).
The local database can store data offline, while the server provides real-time or remote data.
* Repository Pattern:
Create a repository that acts as an intermediary between the ViewModel and the data sources.
The repository manages data synchronization between the local and API sources.
The ViewModel interacts with the repository without being aware of the specific data origin.
In summary, the key is to seamlessly synchronize local and API data through a well-designed repository. 😊 
