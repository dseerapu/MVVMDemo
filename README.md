# nLean

This repo is a nLearn application that implements **MVVM architecture** using **ViewModel**, **RxJava2**, **Dagger2**, **Retrofit2**, **Room**, **LiveData** and **Data Binding**.

This repo uses Retrofit and Room library for network calls and database operations respectively

Base Architecture contains BaseFragment, BaseActivity, BaseAdapter & EndlessBaseAdapter


## Programming Practices Followed
* **[Android Architectural Components from Google (JetPack)](https://developer.android.com/jetpack)**
* **[AppCompat, CardView, RecyclerView an DesignLibrary](http://developer.android.com/intl/es/tools/support-library/index.html)**
* **Dagger 2 for Dependency Injection** : Dependency providing classes using Dagger2.
* **[RxJava2](https://github.com/ReactiveX/RxJava)**: a library for composing asynchronous and event-based programs by using observable sequences.
* **[Retrofit2 with Okhttp](http://square.github.io/retrofit/)** : For network calls
* **[Dynamic Data Binding](https://developer.android.com/topic/libraries/data-binding/)** : bind UI components in your layouts to data sources in your app using a declarative format rather than programmatically
* **MVVM Pattern**
* **Builder, View Holder & Repository Patterns**
* **SOLID Principles**

Architecture I have followed (introduced by Google):
* **[Data Binding](https://developer.android.com/topic/libraries/data-binding/)** :  Cut down on findViewByIds and make data observing / reactive UI elements and bind UI components in your layouts to data sources in your app using a declarative format rather than programmatically
* **[Lifecycle ](https://developer.android.com/topic/libraries/architecture/lifecycle)** :  Create lifecycle aware components
* **[LiveData](https://developer.android.com/topic/libraries/architecture/livedata)** :   A observable stream of data your Activities & Fragments can react to
* **[Navigation](https://developer.android.com/topic/libraries/architecture/navigation/)** :  Define in-app navigation stacks in a single, concise testable file
* **[Paging](https://developer.android.com/topic/libraries/architecture/paging/)** :  Paginate loading data from data sources
* **[Room](https://developer.android.com/topic/libraries/architecture/room)** :  An SQLlite ORM to handle database management in your apps
* **[ViewModel ](https://developer.android.com/topic/libraries/architecture/viewmodel)** :  Handle data effectively around lifecycle changes of Android components
* **[Worker Manager](https://developer.android.com/topic/libraries/architecture/paging/)** :  Run parameterized background tasks efficiently
s
