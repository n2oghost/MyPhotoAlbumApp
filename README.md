# MyPhotoAlbumApp
My Photo Album App

## Modularisation
I went for a feature based modular setup as this is scalable, increases build times and separates concerns very well.

## Architecture
No-brainer here: I chose the official recommended App Architecture (data, domain and ui layers + MVVM pattern) because of it great decoupling and testability.

## Navigation
Single Activity using Android Navigation with included graphs and centralised deeplinks. This leverages the power of Android Navigation while still being compliant with the modular setup.

## Networking
I went with good ol’ Retrofit because no third party stuff was allowed but there are some interesting alternatives that would probably work better with such a Kotlin app; Fuel and Ktor for example.

## Serialisation
I went with Moshi because Gson is ancient and Moshi provides a way faster and lighter Kotlin based alternative. KotlinX serialisation seems even better but it requires a third party lib in order to be used with Retrofit.

## Dependency Injection
Hilt! Koin would be great as well though.

## UI
I initially started this project with fragments and xml navigation + ui (see v1.0.0) because of time constraints but since I had some time left after finishing v1.0.0 I decided to spend it by implementing Jetpack Compose as it offers better testability, possible speed improvements and overall just more simplicity/readability. It's also nice to just have all UI/Navigation on the same level as the rest of the code.

## Some things I would have done but didn’t do due to time constraints
Add Unit tests (would have used MockK, still going to implement)
Add a database for storing data for off-line use (would’ve picked Room)
Add favourites (would have used a bottom nav bar for navigation and Room for persistence)
