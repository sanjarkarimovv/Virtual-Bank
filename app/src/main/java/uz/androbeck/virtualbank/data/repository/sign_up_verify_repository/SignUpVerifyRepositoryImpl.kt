package uz.androbeck.virtualbank.data.repository.sign_up_verify_repository

import uz.androbeck.virtualbank.data.dto.request.SignUpVerifyReqDto
import uz.androbeck.virtualbank.data.source.remote.sign_up_verify_remote.SignUpVerifyRemoteDataSource
import javax.inject.Inject

class SignUpVerifyRepositoryImpl @Inject constructor(
    private val remoteDataSource: SignUpVerifyRemoteDataSource,
) : SignUpVerifyRepository {
    override fun signUpVerify(request: SignUpVerifyReqDto) =
        remoteDataSource.signUpVerify(request)
}