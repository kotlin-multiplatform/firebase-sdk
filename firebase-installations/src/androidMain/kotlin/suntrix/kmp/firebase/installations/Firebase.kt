package suntrix.kmp.firebase.installations

import suntrix.kmp.firebase.core.Firebase
import suntrix.kmp.firebase.core.FirebaseApp

/**
 * Created by Sebastian Owodzin on 28/09/2022
 */
actual val Firebase.installations
    get() = FirebaseInstallations(com.google.firebase.installations.FirebaseInstallations.getInstance())

actual fun Firebase.installations(app: FirebaseApp) =
    FirebaseInstallations(com.google.firebase.installations.FirebaseInstallations.getInstance(app.nativeSdk))