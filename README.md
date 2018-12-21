# MVVMDemo
MVVM Demo


## Programming Practices Followed
* Android Architectural Components 
* Dagger 2 for Dependency Injection 
* MVVM Pattern
* Retrofit2 with Okhttp
* Dynamic Data Binding
* Builder & View Holder Pattern
* SOLID Principles

## Features
* Cache clear option with complete and partially.
* Wide customization of ImageLoader's configuration.
* Multiple format support: Raw,Assert,Content Provider,Local File,WebImage etc.
* Automatic compress image.
* Automatic memory caching.
* Auto recycle memory.
* Multithread image loading (async).
* Builder Design Mode.

# How to Use?
### Load Image from URL:
```java
ImageLoader.createTask().web("your image link").into(imageView).start();
```
### Cache Control

```java
ImageLoader.clearMemCache(80);//trim to 80%
ImageLoader.clearMemCache();//clear all
ImageLoader.clearDiskCache(this);//clear all
ImageLoader.createTask().web("your image link").cleanCache(this);//clear custom memory and disk cache
ImageLoader.createTask().web("your image link").cleanDiskCache(this);//clear custom disk cache
ImageLoader.createTask().web("your image link").cleanMemCache();//clear custom memory cache
```

### Set Loading and Failed Images
```java
ImageLoader.createTask().web("your image link")
         .loadingRes(R.mipmap.ic_launcher)
         .failedRes(R.drawable.ic_launcher)
         .into(imageView).start();
```
### Callback
```java
ImageLoader.createTask().web("your image link")
         .callback(new ImageLoader.OnResultListener() {
             @Override
             public void onFailed() {
                 //Failed response
             }
             @Override
             public void onSuccess(Bitmap bitmap) {
                 //Success response
             }
         })
         .into(imageView).start();
```
