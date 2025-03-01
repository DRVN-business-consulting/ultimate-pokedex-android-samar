//
//
//package dev.samar.ultimatepokedex.sqlite.dao;
//
//import android.content.ContentValues;
//import android.database.Cursor;
//import android.provider.BaseColumns;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//
//
//
//import net.zetetic.database.sqlcipher.SQLiteDatabase;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import dev.samar.ultimatepokedex.entity.dto.UserDto;
//import dev.samar.ultimatepokedex.sqlite.contract.UserContract;
//import dev.samar.ultimatepokedex.sqlite.helper.AppDbHelper;
//
//public class SecureUserDao {
//    private SecureUserDao() {}
//
//    @Nullable
//    public static UserDto getUser(int id) {
//        // Get user
//        SQLiteDatabase db = AppDbHelper.getInstance().getSecureDatabase();
//
//        String[] projection = {
//                BaseColumns._ID,
//                UserContract.UserEntry.COLUMN_FIRST_NAME,
//                UserContract.UserEntry.COLUMN_LAST_NAME,
//                UserContract.UserEntry.COLUMN_AGE,
//                UserContract.UserEntry.COLUMN_EMAIL,
//                UserContract.UserEntry.COLUMN_PASSWORD,
//                UserContract.UserEntry.COLUMN_CREATED_AT,
//                UserContract.UserEntry.COLUMN_UPDATED_AT
//        };
//
//        String selection = UserContract.UserEntry._ID + " = ?";
//        String[] selectionArgs = { String.valueOf(id) };
//
//        String sortingOrder = UserContract.UserEntry.COLUMN_FIRST_NAME + " DESC";
//
//        Cursor cursor = db.query(
//                UserContract.UserEntry.TABLE_NAME,
//                projection,
//                selection,
//                selectionArgs,
//                null,
//                null,
//                sortingOrder
//        );
//
//        UserDto userDTO = null;
//        while (cursor.moveToNext()) {
//            userDTO = UserDto.fromCursor(cursor);
//        }
//
//        cursor.close();
//
//        return userDTO;
//    }
//
//    @NonNull
//    public static List<UserDto> getUsers() {
//        // Get users
//        SQLiteDatabase db = AppDbHelper.getInstance().getSecureDatabase();
//
//        String[] projection = {
//                BaseColumns._ID,
//                UserContract.UserEntry.COLUMN_FIRST_NAME,
//                UserContract.UserEntry.COLUMN_LAST_NAME,
//                UserContract.UserEntry.COLUMN_AGE,
//                UserContract.UserEntry.COLUMN_EMAIL,
//                UserContract.UserEntry.COLUMN_PASSWORD,
//                UserContract.UserEntry.COLUMN_CREATED_AT,
//                UserContract.UserEntry.COLUMN_UPDATED_AT
//        };
//
//        String sortingOrder = UserContract.UserEntry.COLUMN_FIRST_NAME + " DESC";
//
//        Cursor cursor = db.query(
//                UserContract.UserEntry.TABLE_NAME,
//                projection,
//                null,
//                null,
//                null,
//                null,
//                sortingOrder
//        );
//
//        List<UserDto> userDTOs = new ArrayList<>();
//        while (cursor.moveToNext()) {
//            UserDto userDTO = UserDto.fromCursor(cursor);
//            userDTOs.add(userDTO);
//        }
//
//        cursor.close();
//
//        return userDTOs;
//    }
//
//    public static boolean insertUser(String firstName,
//                                     String lastName,
//                                     int age,
//                                     String email,
//                                     String password) {
//        // Insert user
//        SQLiteDatabase db = AppDbHelper.getInstance().getSecureDatabase();
//
//        long creationTime = System.currentTimeMillis();
//        ContentValues values = new ContentValues();
//        values.put(UserContract.UserEntry.COLUMN_FIRST_NAME, firstName);
//        values.put(UserContract.UserEntry.COLUMN_LAST_NAME, lastName);
//        values.put(UserContract.UserEntry.COLUMN_AGE, age);
//        values.put(UserContract.UserEntry.COLUMN_EMAIL, email);
//        values.put(UserContract.UserEntry.COLUMN_PASSWORD, password);
//        values.put(UserContract.UserEntry.COLUMN_CREATED_AT, creationTime);
//        values.put(UserContract.UserEntry.COLUMN_UPDATED_AT, creationTime);
//
//        long id = db.insert(UserContract.UserEntry.TABLE_NAME, null, values);
//
//        return id != -1;
//    }
//
//    public static boolean updateUser(int id,
//                                     String firstName,
//                                     String lastName,
//                                     int age,
//                                     String email,
//                                     String password) {
//        // Update user
//        SQLiteDatabase db = AppDbHelper.getInstance().getSecureDatabase();
//
//        long updateTime = System.currentTimeMillis();
//        ContentValues values = new ContentValues();
//        values.put(UserContract.UserEntry.COLUMN_FIRST_NAME, firstName);
//        values.put(UserContract.UserEntry.COLUMN_LAST_NAME, lastName);
//        values.put(UserContract.UserEntry.COLUMN_AGE, age);
//        values.put(UserContract.UserEntry.COLUMN_EMAIL, email);
//        values.put(UserContract.UserEntry.COLUMN_PASSWORD, password);
//        values.put(UserContract.UserEntry.COLUMN_UPDATED_AT, updateTime);
//
//        String selection = UserContract.UserEntry._ID + " = ?";
//        String[] selectionArgs = { String.valueOf(id) };
//
//        int count = db.update(
//                UserContract.UserEntry.TABLE_NAME,
//                values,
//                selection,
//                selectionArgs
//        );
//
//        return count > 0;
//    }
//
//    public static boolean deleteUser(int id) {
//        // Delete user
//        SQLiteDatabase db = AppDbHelper.getInstance().getSecureDatabase();
//
//        String selection = UserContract.UserEntry._ID + " = ?";
//        String[] selectionArgs = { String.valueOf(id) };
//
//        int count = db.delete(
//                UserContract.UserEntry.TABLE_NAME,
//                selection,
//                selectionArgs
//        );
//
//        return count > 0;
//    }
//
//    public static int getFirstUserId() {
//        // Get first user id
//        SQLiteDatabase db = AppDbHelper.getInstance().getSecureDatabase();
//
//        String[] projection = {
//                BaseColumns._ID
//        };
//
//        String sortingOrder = UserContract.UserEntry._ID + " ASC LIMIT 1";
//
//        Cursor cursor = db.query(
//                UserContract.UserEntry.TABLE_NAME,
//                projection,
//                null,
//                null,
//                null,
//                null,
//                sortingOrder
//        );
//
//        int id = -1;
//        if (cursor.moveToNext()) {
//            id = cursor.getInt(cursor.getColumnIndexOrThrow(UserContract.UserEntry._ID));
//        }
//
//        cursor.close();
//
//        return id;
//    }
//}
