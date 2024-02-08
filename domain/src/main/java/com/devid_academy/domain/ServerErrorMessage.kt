package com.devid_academy.domain

enum class ServerErrorMessage(val messageResId: Int) {
    SERVER_ANSWER_EMPTY(R.string.user_message_server_answer_empty),
    NO_SERVER_ANSWER(R.string.user_message_no_server_answer),

    AD_NOT_UPDATED(R.string.ad_could_not_be_updated),
    AD_NOT_CREATED(R.string.ad_could_not_be_created),
    AD_NOT_DELETED(R.string.ad_could_not_be_deleted),
    FAV_STATUS_NOT_MODIFIED(R.string.fav_status_not_modified)
}