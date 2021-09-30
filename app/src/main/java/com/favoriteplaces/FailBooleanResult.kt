package com.favoriteplaces

class FailBooleanResult {
    suspend operator fun invoke(isSomeValueValid: Boolean = true): Result<Boolean> {
        return Result.success(true)
    }
}