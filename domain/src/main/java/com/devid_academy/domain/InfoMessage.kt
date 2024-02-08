package com.devid_academy.domain

enum class InfoMessage(val messageResId: Int) {
    AD_CREATED(R.string.ad_was_created),
    AD_DELETED(R.string.ad_was_deleted),
    AD_UPDATED(R.string.ad_was_updated),
    FAV_STATUS_MODIFIED(R.string.fav_status_modified),

    EMAIL_OR_PASSWORD_INCORRECT(R.string.user_message_account_already_exists),
    ACCOUNT_ALREADY_EXISTS(R.string.user_message_account_already_exists),
    ACCOUNT_NOT_CREATED(R.string.user_message_account_not_created)

}