package com.gen.souhaikr.daltons.utils;

/**
 * Created by SouhaiKr on 25/02/2019.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.gen.souhaikr.daltons.models.Question;

import java.util.ArrayList;
import java.util.List;

import static com.gen.souhaikr.daltons.utils.QuizContract.QEntry.KEY_ANSWER;
import static com.gen.souhaikr.daltons.utils.QuizContract.QEntry.KEY_DEUT;
import static com.gen.souhaikr.daltons.utils.QuizContract.QEntry.KEY_ID;
import static com.gen.souhaikr.daltons.utils.QuizContract.QEntry.KEY_OPTA;
import static com.gen.souhaikr.daltons.utils.QuizContract.QEntry.KEY_OPTB;
import static com.gen.souhaikr.daltons.utils.QuizContract.QEntry.KEY_OPTC;
import static com.gen.souhaikr.daltons.utils.QuizContract.QEntry.KEY_OPTD;

import static com.gen.souhaikr.daltons.utils.QuizContract.QEntry.KEY_PROT;
import static com.gen.souhaikr.daltons.utils.QuizContract.QEntry.TABLE_QUEST;


public class dbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "triviaQuiz";
    // tasks table name

    private SQLiteDatabase dbase;
    public dbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        dbase=db;
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUEST + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_ANSWER+ " TEXT, "+ KEY_PROT+ " TEXT, "+ KEY_DEUT+ " TEXT, "+KEY_OPTA +" TEXT, "
                +KEY_OPTB +" TEXT, "+KEY_OPTC+" TEXT, "+KEY_OPTD+" TEXT)";
        db.execSQL(sql);
        addQuestions();
        //db.close();
    }
    private void addQuestions()
    {
//        Question q1=new Question("12", "2 ", "11", "14","12");
//        this.addQuestion(q1);
//
//        Question q2=new Question("5", "8", "0", "3","8");
//        this.addQuestion(q2);
//
//        Question q3=new Question("6", "2 ", "0", "5","6");
//        this.addQuestion(q3);
//
//        Question q4=new Question("20", "29 ", "19", "70","29");
//        this.addQuestion(q4);
//
//        Question q5=new Question("7", "50", "35", "57","57");
//        this.addQuestion(q5);

        Question q6=new Question("9", "2", "3","5","5","2","");
        this.addQuestion(q6);

        Question q7=new Question("0", "5", "3", "8","3","5","");
        this.addQuestion(q7);

        Question q8=new Question("5", "17", "15", "9","15","17","");
        this.addQuestion(q8);

        Question q9=new Question("2", "21", "74", "4","74","21","");
        this.addQuestion(q9);

//        Question q10=new Question("20", "29 ", "19", "39","2");
//        this.addQuestion(q10);
//
//        Question q11=new Question("6", "50", "37", "5","6");
//        this.addQuestion(q11);
//
//        Question q12=new Question("9", "97", "3", "57","97");
//        this.addQuestion(q12);
//
//        Question q13=new Question("40", "2 ", "3", "45","45");
//        this.addQuestion(q13);
//
//        Question q14=new Question("5", "29 ", "19", "4","5");
//        this.addQuestion(q14);
//
//        Question q15=new Question("11", "50", "7", "57","7");
//        this.addQuestion(q15);
//
//        Question q16=new Question("9", "6 ", "3", "16","16");
//        this.addQuestion(q16);
//
//        Question q17=new Question("79", "73", "3", "8","73");
//        this.addQuestion(q17);
//
//        Question q18=new Question("5", "8", "0", "2","");
//        this.addQuestion(q18);
//
//        Question q19=new Question("6", "2", "45", "33","");
//        this.addQuestion(q19);

//        Question q20=new Question("45", "73", "19", "39","");
//        this.addQuestion(q20);
//
//        Question q21=new Question("7", "50", "73", "57","");
//        this.addQuestion(q21);

        Question q22=new Question("9", "2", "6", "26","26","6","2");
        this.addQuestion(q22);

        Question q23=new Question("9", "2", "4", "42","42","2","4");
        this.addQuestion(q23);

        Question q24=new Question("20", "3", "5", "35","35","5","3");
        this.addQuestion(q24);

        Question q25=new Question("96", "50", "6", "9","96","6","9");
        this.addQuestion(q25);

        Question q26=new Question("tracés pourpre et rouge", "tracé pourpe", "tracé rouge", "tracé gris","tracés pourpre et rouge","tracé pourpe", "tracé rouge");
        this.addQuestion(q26);

        Question q27=new Question("tracés pourpre et rouge", "tracé pourpe", "tracé rouge", "tracé gris","tracés pourpre et rouge","tracé pourpe", "tracé rouge");
        this.addQuestion(q27);

//        Question q28=new Question("circle", "line", "two lines", "triangle","");
//        this.addQuestion(q28);
//
//        Question q29=new Question("6", "2 ", "0", "4","6");
//        this.addQuestion(q29);
//
//        Question q30=new Question("20", "29 ", "19", "39","29");
//        this.addQuestion(q30);
//
//        Question q31=new Question("7", "50", "37", "57","57");
//        this.addQuestion(q31);
//
//        Question q32=new Question("9", "2 ", "3", "5","5");
//        this.addQuestion(q32);
//
//        Question q33=new Question("9", "2 ", "3", "5","5");
//        this.addQuestion(q33);
//
//        Question q34=new Question("20", "29 ", "19", "39","29");
//        this.addQuestion(q34);
//
//        Question q35=new Question("7", "50", "37", "57","57");
//        this.addQuestion(q35);
//
//        Question q36=new Question("9", "2 ", "3", "5","5");
//        this.addQuestion(q36);
//
//        Question q37=new Question("0", "9", "3", "8","3");
//        this.addQuestion(q37);

//        Question q38=new Question("line", "8", "0", "9","8");
//        this.addQuestion(q38);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST);
        // Create tables again
        onCreate(db);
    }
    public void addQuestion(Question quest) {
        //SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ANSWER, quest.getANSWER());
        values.put(KEY_PROT, quest.getPr());
        values.put(KEY_DEUT, quest.getDe());
        values.put(KEY_OPTA, quest.getOPTA());
        values.put(KEY_OPTB, quest.getOPTB());
        values.put(KEY_OPTC, quest.getOPTC());
        values.put(KEY_OPTD, quest.getOPTD());



        // Inserting Row
        dbase.insert(TABLE_QUEST, null, values);
    }
    public List<Question> getAllQuestions() {
        List<Question> quesList = new ArrayList<Question>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
        dbase=this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Question quest = new Question();
                quest.setID(cursor.getInt(0));
                quest.setANSWER(cursor.getString(1));
                quest.setPr(cursor.getString(2));
                quest.setDe(cursor.getString(3));
                quest.setOPTA(cursor.getString(4));
                quest.setOPTB(cursor.getString(5));
                quest.setOPTC(cursor.getString(6));
                quest.setOPTD(cursor.getString(7));

                quesList.add(quest);
            } while (cursor.moveToNext());
        }
        // return quest list
        return quesList;
    }
    public int rowcount()
    {
        int row=0;
        String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        row=cursor.getCount();
        return row;
    }
}
