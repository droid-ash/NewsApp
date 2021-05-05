<h1 align="center">NewsApp</h1>

<p align="center">
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
  <a href="https://android-arsenal.com/api?level=23"><img alt="API" src="https://img.shields.io/badge/API-23%2B-brightgreen.svg?style=flat"/></a>
  <a href="https://ashish1.medium.com/"><img alt="Medium" src="https://skydoves.github.io/badges/Story-Medium.svg"/></a>
</p>

<p align="center">  
NewsApp is a an android project based on modern Android application tech-stacks and MVVM architecture.<br>This project is for focusing especially on the new jetpack libraries.<br>
Also fetching data from the network and integrating persisted data in the database via repository pattern.
</p>
</br>

<p align="center">
  <img alt="home" src="https://github.com/ashish410/NewsApp/blob/master/screenshots/home.png" width=200/></a>
  <img alt="home" src="https://github.com/ashish410/NewsApp/blob/master/screenshots/article_and_save.png" width=200/></a>
  <img alt="home" src="https://github.com/ashish410/NewsApp/blob/master/screenshots/search.png" width=200/></a>
  <img alt="home" src="https://github.com/ashish410/NewsApp/blob/master/screenshots/save_and_delete.png" width=200/></a>
  
</p> <br>

<h2>MAD Scorecard</h2>
<p align="center">
  <img alt="home" src="https://github.com/ashish410/NewsApp/blob/master/screenshots/mad_scorecard.png" /></a>
  
</p> <br>

<h2>Preview</h2>
<img src="https://github.com/ashish410/NewsApp/blob/master/previews/app_preview.gif" align="center" width="32%"/> <br>

## Libraries and tech used
- Minimum SDK level 23
- [Kotlin](https://kotlinlang.org/) based, [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) for asynchronous handling.
- Hilt (stable) for dependency injection.
- JetPack
  - LiveData - notify domain layer data to views.
  - Lifecycle - observing data when lifecycle state changes.
  - ViewModel - lifecycle aware UI related data holder.
  - Room Persistence - construct a database to store news article.
- Architecture
  - MVVM Architecture (View - DataBinding - ViewModel - Model)
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - for REST APIs and network data.
- [Gson](https://github.com/google/gson/) - A JSON library for parsing network response.
- [Glide](https://github.com/bumptech/glide) - loading images.
- [Material-Components](https://github.com/material-components/material-components-android) - Material design components for CardView, ShapeableImageView.
