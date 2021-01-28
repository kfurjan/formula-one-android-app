# formula-one-android-app

Formula One android application using data from [Formula One API](https://documenter.getpostman.com/view/11586746/SztEa7bL#intro) hosted at Ergast API. Goal of this application is to
view and search data about Formula 1, drivers, constructors, seasons, circuits and circuit locations with integrated Google Maps.

## Architecture

Application with Retrofit library fetches data from [Formula One API](https://documenter.getpostman.com/view/11586746/SztEa7bL#intro) REST API and saves that data to local SQLite database using Room Persistence library.
That data is then shown on the UI by following [Guide to app architecture](https://developer.android.com/jetpack/guide) which really focuses on principles of _separation of concerns_ and _driving UI from a model_ by
separating Activities/Fragments from models with ViewModels and ViewModels from database with Repository layer. To further enhance application architecture, Android Hilt dependency injection library was used which
is built on top of Dagger2.
Using LiveData, observable Lifecycle-Aware data holder created, enables Observer Pattern when displaying data on UI, especially helpful since data on the UI can be filtered using Search Bar.

## Application features

As mentioned before, goal of this application is to view and search data about Formula 1, drivers, constructors, circuits and seasons. Each of the entities are shown using RecyclerView, and since
every entity has URL to the Wikipedia page, every item in RecyclerView can be clicked to open WebView but also items in RecyclerView can be searched and filtered by using Search Bar.
Circuit entity has also latitude and longitude information which is the reason why Google Maps fragment was implemented to show circuit locations. Application also fully supports light and dark theme.
