package com.example.todo

//data class for storing IntentContent
data class IntentVariables(
    val INTENT_REQUEST_CODE: Int = 1,
    val INTENT_REQUEST_CODE2: Int = 2,
    val INTENT_MESSAGE_DATA: String = "message_date",
    val INTENT_ITEM_POSITION: String = "item_position",
    val INTENT_MESSAGE_FIELD: String = "message_filed",
    val INTENT_RESULT_CODE: Int = 1,
    val INTENT_RESULT_CODE2: Int = 2,
    val INTENT_CHANGED_MESSAGE: String = "changed_message"
)