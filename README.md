# nLean

This repo is a nLearn application that implements **MVVM architecture** using **Android Architectural Components from Google (JetPack)**,**AppCompat, CardView, RecyclerView an DesignLibrary**, **ViewModel**, **RxJava2**, **Dagger2**, **Retrofit2**, **Builder,View Holder & Repository Patterns** and **SOLID Principles**.

## The app has following packages:

* **datamanager**: It contains all the data accessing and manipulating components.
* **di**: Dependency providing classes using Dagger2.
* **view**: View classes along with their corresponding ViewModels, adapters, View Holders and etc...
* **utils**: Utility classes.
* **analytics** : contains analytics initialization part

## Programming Practices Followed
* **[Android Architectural Components from Google (JetPack)](https://developer.android.com/jetpack)**
* **[AppCompat, CardView, RecyclerView an DesignLibrary](http://developer.android.com/intl/es/tools/support-library/index.html)**
* **Dagger 2** : Dependency providing classes using Dagger2.
* **[RxJava2](https://github.com/ReactiveX/RxJava)**: a library for composing asynchronous and event-based programs by using observable sequences.
* **[Retrofit2 with Okhttp](http://square.github.io/retrofit/)** : For network calls
* **[Dynamic Data Binding](https://developer.android.com/topic/libraries/data-binding/)** : bind UI components in your layouts to data sources in your app using a declarative format rather than programmatically
* **MVVM, Builder, View Holder & Repository Patterns**
* **SOLID Principles**

Android Architectural Components from Google (JetPack):
* **[ViewModel ](https://developer.android.com/topic/libraries/architecture/viewmodel)** :  Handle data effectively around lifecycle changes of Android components
* **[Data Binding](https://developer.android.com/topic/libraries/data-binding/)** :  Cut down on findViewByIds and make data observing / reactive UI elements and bind UI components in your layouts to data sources in your app using a declarative format rather than programmatically
* **[Lifecycle ](https://developer.android.com/topic/libraries/architecture/lifecycle)** :  Create lifecycle aware components
* **[LiveData](https://developer.android.com/topic/libraries/architecture/livedata)** :   A observable stream of data your Activities & Fragments can react to
* **[Navigation](https://developer.android.com/topic/libraries/architecture/navigation/)** :  Define in-app navigation stacks in a single, concise testable file
* **[Room](https://developer.android.com/topic/libraries/architecture/room)** :  An SQLlite ORM to handle database management in your apps
* **[Worker Manager](https://developer.android.com/topic/libraries/architecture/workmanager)** :  Run parameterized background tasks efficiently


### License

   ```Copyright (C) 2017 NSPIRA CONSUMER BUSINESS DIVISION

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.```
