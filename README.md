<img src="https://user-images.githubusercontent.com/238738/173244201-e403272c-fa59-4122-91a2-eba4614b8081.svg" width="300px">

# Beyond Identity Android SDKs

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

### [Embedded](wiki/embedded/getting_started)

The Embedded SDK is a holistic SDK solution offering the entire experience embedded in your product. Users will not need
to download the Beyond Identity Authenticator.

### [Authenticator](wiki/authenticator/getting_started)

The Authenticator SDK is used in conjunction with the
existing [Beyond Identity Authenticator](https://app.byndid.com/downloads) where most of the heavy lifting is handled in
the Beyond Identity Authenticator and will need to be downloaded by your users.

## Installation

### Gradle

To enable the retrieval of Cloudsmith hosted packages via Gradle, we need to add the Cloudsmith repository to
the `root/build.gradle` file.

```groovy
repositories {
    maven {
        url "https://packages.beyondidentity.com/public/bi-sdk-android/maven/"
    }
}
```

After the repository is added, we can specify the Beyond Identity dependencies.

```groovy
dependencies {
    implementation 'com.beyondidentity.android.sdk:[embedded|authenticator]:[version]'
}
```

## Usage
Check out the [documentation](https://developer.beyondidentity.com) for more information.
