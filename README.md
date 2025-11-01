<a name="readme-top"></a>
<!-- Template Credit: Othneil Drew (https://github.com/othneildrew),
                      https://github.com/othneildrew/Best-README-Template/tree/master -->
<!-- PROJECT SHIELDS -->
<div align="center">

[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![LinkedIn][linkedin-shield]][linkedin-url]

</div>

<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://www.themoviedb.org/">
    <img src="https://www.themoviedb.org/assets/2/v4/logos/v2/blue_square_2-d537fb228cf3ded904ef09b136fe3fec72548ebc1fea3fbbd1ad9e36364db38b.svg" alt="Logo" width="360" height="260">
  </a>
  <br/>
  <a href="https://www.themoviedb.org/">themoviedb.org</a>
  <h3 align="center">tmdb-java-client</h3>

  <p align="center">
    A Java client to access The Movie Database
    <br />
    <a href="https://www.amilesend.com/tmdb-java-client"><strong>Maven Project Info</strong></a>
    -
    <a href="https://www.amilesend.com/tmdb-java-client/apidocs/index.html"><strong>Javadoc</strong></a>
    <br />
    <a href="https://github.com/andy-miles/tmdb-java-client/issues">Report Bug</a>
    -
    <a href="https://github.com/andy-miles/tmdb-java-client/issues">Request Feature</a>
  </p>
</div>


<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#unsupported-features">Current Unsupported Features</a></li>
      </ul>
    </li>
    <li>
      <a href="#usage">Usage</a>
      <ul>
        <li><a href="#getting-started">Getting Started</a></li>
        <li><a href="#recipes">Recipes</a></li>
      </ul>
    </li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>

<!-- ABOUT THE PROJECT -->
# About The Project

A client for Java programmatic access to [The Movie Database v3 API](https://developer.themoviedb.org/docs/getting-started).

<a name="unsupported-features"></a>
## Current Unsupported Features
1. This client does not support [v4 API](https://developer.themoviedb.org/v4/docs/getting-started) for Account, Auth, and Lists APIs. This
client only supports v3 at this time. Please file a <a href="https://github.com/andy-miles/tmdb-java-client/issues">feature request</a> if your use-case
requires these APIs.

<div align="right">(<a href="#readme-top">back to top</a>)</div>

<a name="usage"></a>
# Usage
<a name="getting-started"></a>
## Getting Started

1. Per the [API documentation](https://developer.themoviedb.org/docs/getting-started), you will need to obtain an API read access token in order to make requests to the service.
2. Include this package as a dependency in your project. Note: This package is published to both
   [GitHub](https://github.com/andy-miles/tmdb-java-client/packages/2277927) and Maven Central repositories.

   ```xml
   <dependency>
       <groupId>com.amilesend</groupId>
       <artifactId>tmdb-java-client</artifactId>
       <version>3.3.5</version>
   </dependency>
   ```
3. Instantiate the client with the read access token:

   ```java
   Tmdb tmdb = new Tmdb("MyReadAccessToken", "MyUserAgent/1.0");
   // Access APIs (e.g., MoviesApi)
   MoviesApi moviesApi = tmdb.getMoviesApi();
   ```

<div align="right">(<a href="#readme-top">back to top</a>)</div>

<a name="recipes"></a>
## Recipes
### Searching for content
```java
SearchApi searchApi = tmdb.getSearchApi();
SearchMoviesResponse response = searchApi.searchMovies(
        SearchMoviesRequest.builder()
                .query("Harry Potter")
                .build());

// Alternatively, one can search for more than just movies
// where the response results can return Person, TV, and Movie
// based results.
SearchMultiResponse multiResponse = searchApi.searchMulti(
        SearchMultiRequest.builder()
                .query("Firefly")
                .build());
```

### Discovering content with filters

Discovering content can optionally include filters. Some request filters define use of a delimited list of attributes.
This can be defined through use of a [FilterQueryBuilder](https://www.amilesend.com/tmdb-java-client/apidocs/com/amilesend/tmdb/client/model/discover/filter/FilterQueryBuilder.html)

```java
DiscoverMoviesRequest discoverMoviesRequest = DiscoverMoviesRequest.builder()
        .withReleaseType(String.valueOf(MovieReleaseType.DIGITAL.getType()))
        .watchRegionFilter(WatchRegionFilter.builder()
                .watchRegion(Locale.US.getCountry())
                .withWatchProviders(
                        // Include only Netflix (8) and Hulu (15)
                        // Watch Provider IDs can be obtained via the WatchProvidersApi
                        // This creates the String value of "8|15"
                        new FilterQueryBuilder<>(8, FilterQueryBuilder.Type.OR)
                                .append(15)
                                .build())
                .build())
        .build();
DiscoverMoviesResponse movieResponse = discoverApi.discoverMovies(movieRequest);
```

### Creating a session to manage a user's account

```java
AuthenticationApi authApi = tmdb.getAuthenticationApi();
AccountApi accountApi = tmdb.getAccountApi();

// Opens a browser to prompt the user to approve the new session.
SessionManager sessionManager = SessionManager.builder()
        .authApi(authApi)
        .config(RequestTokenGrantReceiver.Config.builder().build())
        .build();
// The sessionId can be persisted to prevent having to prompt the user
// every time a sessionId is required between application instances.
String sessionId = sessionManager.registerNewSession();

// Get the account identifier for the user
GetAccountDetailsResponse accountDetails = accountApi.getAccountDetailsForSession(
        GetAccountDetailsForSessionRequest.builder()
                .sessionId(sessionId)
                .build());
Integer accountId = accountDetails.getId();
  
// Manage the user's account and lists via AccountApi

// Either securely persist the user session ID for future use, 
// or you can delete the session.
sessionManager.deleteSession();
```

### Customizing the HTTP client configuration

<details>
<summary>OkHttpClientBuilder example</summary>

If your use-case requires configuring the underlying <code>OkHttpClient</code> instance (e.g., configuring your own
SSL cert verification, proxy, and/or connection timeouts), you can configure the client with the provided
[OkHttpClientBuilder](https://github.com/andy-miles/tmdb-java-client/blob/main/src/main/java/com/amilesend/tmdb/client/connection/http/OkHttpClientBuilder.java),
or alternatively with [OkHttp's builder](https://square.github.io/okhttp/4.x/okhttp/okhttp3/-ok-http-client/).

```java
OkHttpClient httpClient = OkHttpClientBuilder.builder()
        .trustManager(myX509TrustManager) // Custom trust manager for self/internally signed SSL/TLS certs
        .hostnameVerifier(myHostnameVerifier) // Custom hostname verification for SSL/TLS endpoints
        .addInterceptor(myInterceptor) // Custom okhttp interceptor (e.g., logging)
        .proxy(myProxy, myProxyUsername, myProxyPassword) // Proxy config
        .connectTimeout(8000L) // connection timeout in milliseconds
        .readTimeout(5000L) // read timeout in milliseconds
        .writeTimeout(5000L) // write timeout in milliseconds
        .build();
Connection connection = Connection.builder()
        .httpClient(httpClient)
        .gsonFactory(GsonFactory.getInstance())
        .readAccessToken("MyReadAccessToken")
        .build();
Tmdb tmdb = new Tmdb(connection);
```

</details>


<div align="right">(<a href="#readme-top">back to top</a>)</div>

<!-- CONTRIBUTING -->
## Contributing

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks again!

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/MyFeature`)
3. Commit your Changes (`git commit -m 'Add my feature'`)
4. Push to the Branch (`git push origin feature/MyFeature`)
5. Open a Pull Request

<div align="right">(<a href="#readme-top">back to top</a>)</div>

<!-- LICENSE -->
## License

Distributed under the GPLv3 license. See [LICENSE](https://github.com/andy-miles/tmdb-java-client/blob/main/LICENSE) for more information.

<div align="right">(<a href="#readme-top">back to top</a>)</div>


<!-- CONTACT -->
## Contact

Andy Miles - andy.miles (at) amilesend.com

Project Link: [https://github.com/andy-miles/tmdb-java-client](https://github.com/andy-miles/tmdb-java-client)

<div align="right">(<a href="#readme-top">back to top</a>)</div>


<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/andy-miles/tmdb-java-client.svg?style=for-the-badge
[contributors-url]: https://github.com/andy-miles/tmdb-java-client/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/andy-miles/tmdb-java-client.svg?style=for-the-badge
[forks-url]: https://github.com/andy-miles/tmdb-java-client/network/members
[stars-shield]: https://img.shields.io/github/stars/andy-miles/tmdb-java-client.svg?style=for-the-badge
[stars-url]: https://github.com/andy-miles/tmdb-java-client/stargazers
[issues-shield]: https://img.shields.io/github/issues/andy-miles/tmdb-java-client.svg?style=for-the-badge
[issues-url]: https://github.com/andy-miles/tmdb-java-client/issues
[license-shield]: https://img.shields.io/github/license/andy-miles/tmdb-java-client.svg?style=for-the-badge
[license-url]: https://github.com/andy-miles/tmdb-java-client/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://www.linkedin.com/in/andy-miles
[product-screenshot]: images/screenshot.png
[Next.js]: https://img.shields.io/badge/next.js-000000?style=for-the-badge&logo=nextdotjs&logoColor=white
[Next-url]: https://nextjs.org/
[React.js]: https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB
[React-url]: https://reactjs.org/
[Vue.js]: https://img.shields.io/badge/Vue.js-35495E?style=for-the-badge&logo=vuedotjs&logoColor=4FC08D
[Vue-url]: https://vuejs.org/
[Angular.io]: https://img.shields.io/badge/Angular-DD0031?style=for-the-badge&logo=angular&logoColor=white
[Angular-url]: https://angular.io/
[Svelte.dev]: https://img.shields.io/badge/Svelte-4A4A55?style=for-the-badge&logo=svelte&logoColor=FF3E00
[Svelte-url]: https://svelte.dev/
[Laravel.com]: https://img.shields.io/badge/Laravel-FF2D20?style=for-the-badge&logo=laravel&logoColor=white
[Laravel-url]: https://laravel.com
[Bootstrap.com]: https://img.shields.io/badge/Bootstrap-563D7C?style=for-the-badge&logo=bootstrap&logoColor=white
[Bootstrap-url]: https://getbootstrap.com
[JQuery.com]: https://img.shields.io/badge/jQuery-0769AD?style=for-the-badge&logo=jquery&logoColor=white
[JQuery-url]: https://jquery.com 
