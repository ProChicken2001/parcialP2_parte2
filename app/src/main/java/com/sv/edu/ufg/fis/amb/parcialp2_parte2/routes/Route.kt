package com.sv.edu.ufg.fis.amb.parcialp2_parte2.routes

const val ROOT_MAIN_PAGE = "main_page"
const val ROOT_LOCATION_PAGE = "location_page"
const val ROOT_FILES_PAGE = "files_page"
const val ROOT_NOTIFY_PAGE = "notify_page"

sealed class Route(
    val route : String
) {
    object MainRoutePage : Route(route = ROOT_MAIN_PAGE)
    object LocationRoutePage : Route(route = ROOT_LOCATION_PAGE)
    object FilesRoutePage : Route(route = ROOT_FILES_PAGE)
    object NotifyRoutePage : Route(route = ROOT_NOTIFY_PAGE)
}