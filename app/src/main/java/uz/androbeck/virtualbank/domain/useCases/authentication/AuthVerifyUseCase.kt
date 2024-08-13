package uz.androbeck.virtualbank.domain.useCases.authentication

import uz.androbeck.virtualbank.domain.ui_models.common.CodeVerifyReqUIModel
import uz.androbeck.virtualbank.ui.screens.Screen
import javax.inject.Inject

class AuthVerifyUseCase @Inject constructor(
    private val signInVerifyUseCase: SignInVerifyUseCase,
    private val signUpVerifyUseCase: SignUpVerifyUseCase
) {
    operator fun invoke(screen: Screen, codeVerifyReqUIModel: CodeVerifyReqUIModel) =
        when (screen) {
            Screen.LOGIN -> signInVerifyUseCase(codeVerifyReqUIModel)
            Screen.REGISTRATION -> signUpVerifyUseCase(codeVerifyReqUIModel)
        }
}