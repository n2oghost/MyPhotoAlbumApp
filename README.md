# MyPhotoAlbumApp
My Photo Album App

## Modularisation
I went for a feature based modular setup as this is scalable, increases build times and separates concerns very well.

## Architecture
No brainer here: I chose the MVVM app architecture because of it great decoupling and testability. It’s also what Google advises. ¯\_(ツ)_/¯

## Navigation
Single Activity using Android Navigation with included graphs and centralised deeplinks. This leverages the power of Android Navigation while still being compliant with the modular setup.

## Networking
I went with good ol’ Retrofit because no third party stuff was allowed but there are some interesting alternatives that would probably work better with such a Kotlin app; Fuel and Ktor for example.

## Serialisation
I went with Moshi because Gson is ancient and Moshi provides a way faster and lighter Kotlin based alternative. KotlinX serialisation seems even better but it requires a third party lib in order to be used with Retrofit.

## Dependency Injection
Hilt! Koin would be great as well though.

## Some things I would have done but didn’t do due to time constraints
Use Jetpack Compose (still plan on implementing that though)
Add Unit tests (would have used MockK, still going to implement)
Add a database for storing data for off-line use (would’ve picked Room)
Add favourites (would have used a bottom nav bar for navigation and Room for persistence)
