package studio.crunchyiee.degreeprogrammemapper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    //Database stuff don't worry
    private static final int DATABASE_VERSION = 9;
    private static final String DATABASE_NAME = "dpm_info.db";

    //Variables for database tables and columns
    public static final String TABLE_SOFTTWARE = "software";
    public static final String TABLE_DATABSE = "databases";
    public static final String TABLE_NETWORKING = "networking";
    public static final String TABLE_MEDIA = "media";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "_name";
    public static final String COLUMN_LEVEL = "_level";
    public static final String COLUMN_CREDITS = "_credits";
    public static final String COLUMN_PREREQ = "_prereq";
    public static final String COLUMN_DESC = "_description";
    public static final String COLUMN_SEMESTER = "_semester";

    public static final String TABLE_STUDENT = "student";
    public static final String COLUMN_DEGREE = "_degree";
    public static final String COLUMN_PATHWAY = "_pathway";

    //Creating Tables
    //Start
    public static final String COURSE_SOFTWARE = "CREATE TABLE " + TABLE_SOFTTWARE + "(" +
            COLUMN_ID + " TEXT PRIMARY KEY, " +
            COLUMN_NAME + " TEXT, " +
            COLUMN_LEVEL + " INTEGER, " +
            COLUMN_CREDITS + " INTEGER DEFAULT 15, " +
            COLUMN_PREREQ + " TEXT, " +
            COLUMN_DESC + " TEXT, " +
            COLUMN_SEMESTER + " INTEGER " +
            ");";

    public static final String COURSE_DATABASE = "CREATE TABLE " + TABLE_DATABSE + "(" +
            COLUMN_ID + " TEXT PRIMARY KEY, " +
            COLUMN_NAME + " TEXT, " +
            COLUMN_LEVEL + " INTEGER, " +
            COLUMN_CREDITS + " INTEGER DEFAULT 15, " +
            COLUMN_PREREQ + " TEXT, " +
            COLUMN_DESC + " TEXT, " +
            COLUMN_SEMESTER + " INTEGER " +
            ");";

    public static final String COURSE_NETWORKING = "CREATE TABLE " + TABLE_NETWORKING + "(" +
            COLUMN_ID + " TEXT PRIMARY KEY, " +
            COLUMN_NAME + " TEXT, " +
            COLUMN_LEVEL + " INTEGER, " +
            COLUMN_CREDITS + " INTEGER DEFAULT 15, " +
            COLUMN_PREREQ + " TEXT, " +
            COLUMN_DESC + " TEXT, " +
            COLUMN_SEMESTER + " INTEGER " +
            ");";

    public static final String COURSE_MEDIA = "CREATE TABLE " + TABLE_MEDIA + "(" +
            COLUMN_ID + " TEXT PRIMARY KEY, " +
            COLUMN_NAME + " TEXT, " +
            COLUMN_LEVEL + " INTEGER, " +
            COLUMN_CREDITS + " INTEGER DEFAULT 15, " +
            COLUMN_PREREQ + " TEXT, " +
            COLUMN_DESC + " TEXT, " +
            COLUMN_SEMESTER + " INTEGER " +
            ");";

    public static final String STUDENT_TABLE = "CREATE TABLE " + TABLE_STUDENT + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY, " +
            COLUMN_DEGREE + " TEXT, " +
            COLUMN_PATHWAY + " TEXT " +
            ");";
    //End

    //DBHandler for referencing the database
    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    //Creating the database tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        createTables(db);
    }

    public void createTables(SQLiteDatabase db) {
        db.execSQL(COURSE_SOFTWARE);
        db.execSQL(COURSE_DATABASE);
        db.execSQL(COURSE_NETWORKING);
        db.execSQL(COURSE_MEDIA);
        db.execSQL(STUDENT_TABLE);
    }

    //Used for resetting database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SOFTTWARE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DATABSE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NETWORKING);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEDIA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
        onCreate(db);
    }

    //Method for inserting a module
    public void insertModule(Course module) {
        //Set content data to store values for inserting
        ContentValues module_data = new ContentValues();
        //Put data into module_data
        module_data.put(COLUMN_ID, module.get_id());
        module_data.put(COLUMN_NAME, module.get_name());
        module_data.put(COLUMN_LEVEL, module.get_level());
        module_data.put(COLUMN_CREDITS, module.get_credits());
        module_data.put(COLUMN_PREREQ, module.get_prereq());
        module_data.put(COLUMN_DESC, module.get_desc());
        module_data.put(COLUMN_SEMESTER, module.get_semester());
        //Reference to database
        SQLiteDatabase db = getWritableDatabase();
        //Try updating the row first, otherwise insert the row
        try {
            db.update(module.get_id(), module_data, "_id=" + COLUMN_ID, null);
        } catch (Exception e) {
            db.insert(module.get_pathway(), null, module_data);
        }
        db.close();
    }

    //For Manager section, ignore for now
    public void removeModule(String pathway, Course module){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + pathway + " WHERE " + COLUMN_ID + " =\"" + module + "\";");
    }

    //Get Module info for dynamic cards and their pop up menus
    public String[] getModuleInfo(String pathway, String module) {
        //Individual variables to store each data
        String courseCode = "";
        String courseName = "";
        int courseLevel = 0;
        int courseCredits = 0;
        String coursePrereq = "";
        String courseDesc = "";
        int courseSemester = 0;

        //Reference to database
        SQLiteDatabase db = getWritableDatabase();

        //Create query for getting module info
        String query = "SELECT * FROM " + pathway + " WHERE 1";

        //Cursor - for getting a pointer to each value in the table
        Cursor c = db.rawQuery(query, null);
        //Move to first value
        c.moveToFirst();

        //Loop through query to get each value
        while (!c.isAfterLast()) {
            //If the column matches the Module specified, append each value
            if (c.getString(c.getColumnIndex("_id")).equals(module)) {
                courseCode = c.getString(c.getColumnIndex("_id"));
                courseName = c.getString(c.getColumnIndex("_name"));
                courseLevel = c.getInt(c.getColumnIndex("_level"));
                courseCredits = c.getInt(c.getColumnIndex("_credits"));
                coursePrereq = c.getString(c.getColumnIndex("_prereq"));
                courseDesc = c.getString(c.getColumnIndex("_description"));
                courseSemester = c.getInt(c.getColumnIndex("_semester"));
            }
            //Go to next value
            c.moveToNext();
        }
        c.close();
        db.close();

        //Create array of the results (this is for the Module card)
        String[] results = new String[7];
        results[0] = courseCode;
        results[1] = courseName;
        results[2] = String.valueOf(courseLevel);
        results[3] = String.valueOf(courseCredits);
        results[4] = coursePrereq;
        results[5] = courseDesc;
        results[6] = String.valueOf(courseSemester);

        //Return the array results
        return results;
    }

    //Get modules by semester, this is for section the Module page by semester
    //Takes Pathway and Semester as parameters for sectioning
    public String[] getModuleSemester(String pathway, int semester) {
        //Create code variable
        String courseCode = "";

        //Reference to database
        SQLiteDatabase db = getWritableDatabase();

        //Create query for getting each module in that semester
        String query = "SELECT * FROM " + pathway + " WHERE 1";

        //Cursor - for getting a pointer to each value in the table
        Cursor c = db.rawQuery(query, null);
        //Move to first value
        c.moveToFirst();

        //For creating a pointer in the array later
        int count = 0;
        while (!c.isAfterLast()) {
            //If Module is equal to that semester, increase the number of modules found
            if (c.getInt(c.getColumnIndex("_semester")) == semester) {
                count++;
            }
            c.moveToNext();
        }
        c.close();

        //Reset cursor
        c = db.rawQuery(query, null);
        c.moveToFirst();

        //Create array for storing the results, based on the number of results (count)
        String[] results = new String[count];

        //Reset count value to store Module info respectively
        count = 0;
        while (!c.isAfterLast()) {
            //If the Module matches the semester, add to the result respectively
            if (c.getInt(c.getColumnIndex("_semester")) == semester) {
                courseCode = c.getString(c.getColumnIndex("_id"));
                results[count] = courseCode;
                count++;
            }
            c.moveToNext();
        }
        c.close();
        db.close();

        //Return the array results
        return results;
    }
}