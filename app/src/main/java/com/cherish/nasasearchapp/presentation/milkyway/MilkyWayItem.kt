package com.cherish.nasasearchapp.presentation.milkyway

import android.os.Parcelable
import com.cherish.nasasearchapp.common.BaseViewState
import kotlinx.parcelize.Parcelize


@Parcelize
data class MilkyWayItem(
  val id : String?,
  val title: String?,
  var center: String?,
  val date : String?,
  val image : String?,
  val description: String?): BaseViewState(),Parcelable



//Parcelable {
//  fun id() = response.collection?.items?.map{ it.data?.map { it.nasaId }}
//  fun title() = response.collection?.items?.map{ it.data?.map { it.title }}
//  fun center() = response.collection?.items?.map{ it.data?.map { it.center }}
//  fun date() = response.collection?.items?.map{ it.data?.map { it.dateCreated }}
//  fun image() = response.collection?.items?.map{ it.links?.map { it.href }}
//  fun description() = response.collection?.items?.map{ it.data?.map { it.description }}