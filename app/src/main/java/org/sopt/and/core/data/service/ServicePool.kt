package org.sopt.and.core.data.service

import org.sopt.and.network.RetrofitInstance

object ServicePool {
    val signInService: SignInService = RetrofitInstance.create<SignInService>()
    val signUpService: SignUpService = RetrofitInstance.create<SignUpService>()
    val hobbyService: HobbyService = RetrofitInstance.create<HobbyService>()
}