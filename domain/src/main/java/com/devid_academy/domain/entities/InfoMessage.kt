package com.devid_academy.domain.entities

import com.devid_academy.domain.R

enum class InfoMessage(val messageResId: Int) {
    AD_CREATED(R.string.ad_was_created),
    AD_DELETED(R.string.ad_was_deleted),
    AD_UPDATED(R.string.ad_was_updated),
    FAV_STATUS_MODIFIED(R.string.fav_status_modified),

    EMAIL_OR_PASSWORD_INCORRECT(R.string.user_message_email_or_password_not_found),
    ACCOUNT_ALREADY_EXISTS(R.string.user_message_account_already_exists),
    ACCOUNT_NOT_CREATED(R.string.user_message_account_not_created),

    PASSWORDS_DONT_MATCH(R.string.user_message_passwords_dont_match),
    PASSWORD_SPECS_NOT_RESPECTED(R.string.user_message_password_specs_not_respected),

    FILL_OUT_ALL_FIELDS(R.string.user_message_please_fill_out_all_fields)

}