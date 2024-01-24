package uz.schoolapp.constants

import jakarta.xml.bind.annotation.XmlType.DEFAULT

class AppConstants {
    companion object {
        const val DEFAULT_PAGE_NUMBER = "0"
        const val DEFAULT_PAGE_SIZE = "10"
        const val DEFAULT_SORT_BY = "id"
        const val DEFAULT_SORT_DIRECTION  = "asc"
    }

}