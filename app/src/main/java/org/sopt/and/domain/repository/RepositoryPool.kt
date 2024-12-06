package org.sopt.and.domain.repository

import org.sopt.and.core.data.datasourceimpl.HobbyDataSourceImpl
import org.sopt.and.core.data.datasourceimpl.SignInDataSourceImpl
import org.sopt.and.core.data.datasourceimpl.SignUpDataSourceImpl
import org.sopt.and.core.data.repositoryimpl.HobbyRepositoryImpl
import org.sopt.and.core.data.repositoryimpl.SignInRepositoryImpl
import org.sopt.and.core.data.repositoryimpl.SignUpRepositoryImpl
import org.sopt.and.core.data.service.ServicePool

object RepositoryPool {
    val hobbyRepository: HobbyRepository by lazy {
        HobbyRepositoryImpl(HobbyDataSourceImpl(ServicePool.hobbyService))
    }

    val signInRepository: SignInRepository by lazy {
        SignInRepositoryImpl(SignInDataSourceImpl(ServicePool.signInService))
    }

    val signUpRepository: SignUpRepository by lazy {
        SignUpRepositoryImpl(SignUpDataSourceImpl(ServicePool.signUpService))
    }
}
