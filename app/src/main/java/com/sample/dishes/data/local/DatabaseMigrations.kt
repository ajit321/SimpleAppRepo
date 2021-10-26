
package com.sample.dishes.data.local

import androidx.room.migration.Migration


object DatabaseMigrations {
    const val DB_VERSION = 1

    val MIGRATIONS: Array<Migration>
        get() = arrayOf<Migration>(
//            migration12()
        )
//    // TODO Uncomment when needed for migration from v1 to v2
//    private fun migration12(): Migration = object : Migration(1, 2) {
//        override fun migrate(database: SupportSQLiteDatabase) {
//
//        }
//    }
}
