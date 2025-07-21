package com.uilover.project2322.Domain

import java.io.Serializable

data class Profile(
    var profileName: String = "",
    var profileImage: String = "",
    var totalbalance: String = "",
    var income: String = "",
    var outcome: String = "",
    var banner: String = "",
    var friend: ArrayList<Friend> = ArrayList(),
    var transaction: ArrayList<Transction> = ArrayList()
):Serializable

data class Friend(
    var imageUrl: String = "",
    var name: String = ""
):Serializable

data class Transction(
    var imageUrl: String = "",
    var name: String = "",
    var date: String = "",
    var amount: String = ""
):Serializable
